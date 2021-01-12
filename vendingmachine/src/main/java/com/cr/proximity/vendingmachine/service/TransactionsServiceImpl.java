package com.cr.proximity.vendingmachine.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cr.proximity.vendingmachine.dao.TransactionVMRepository;
import com.cr.proximity.vendingmachine.dao.entities.TransactionVMEntity;
import com.cr.proximity.vendingmachine.exceptions.BadRequestException;
import com.cr.proximity.vendingmachine.exceptions.InvalidStateVMException;
import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.machine.VendingMachineInterface;
import com.cr.proximity.vendingmachine.model.Item;
import com.cr.proximity.vendingmachine.model.ItemStock;
import com.cr.proximity.vendingmachine.model.ItemTransaction;
import com.cr.proximity.vendingmachine.model.transaction.Payment;
import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;
import com.cr.proximity.vendingmachine.state.MachineState;

@Service("TransactionsService")
public class TransactionsServiceImpl implements TransactionsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionsServiceImpl.class);

	private Environment environment;

	private PaymentsXYZ1Configuration serviceImpl;

	private PaymentServiceStrategy paymentServiceStrategy;

	private MachineState machineState;

	TransactionVMRepository transactionVMRepository;
	
	VendingMachineInterface vendingMachineInterface;

	public TransactionsServiceImpl(Environment environment, PaymentServiceStrategy paymentServiceStrategy,
			MachineState machineState, TransactionVMRepository transactionVMRepository,VendingMachineInterface vendingMachineInterface) {
		super();
		this.environment = environment;
		this.paymentServiceStrategy = paymentServiceStrategy;
		this.machineState = machineState;
		this.transactionVMRepository = transactionVMRepository;
		this.vendingMachineInterface = vendingMachineInterface;
		configure();
	}

	private void configure() {
		String model = environment.getProperty("vending.machine.model");
		LOGGER.info("Starting configuration");
		LOGGER.info("Vending Machine Model: " + model);

		switch (model) {
		case "XYZ1":
			this.serviceImpl = new PaymentsXYZ1Configuration();
			break;
		case "XYZ2":
			this.serviceImpl = new PaymentsXYZ2Configuration();
			break;
		default:
			throw new RuntimeException("Configuration error, model not supported");
		}
	}

	@Override
	public void addCash(Payment payment) throws VendingMachineException {
		if (isValidPayment(payment.getCode())) {
			PaymentMethod paymentMethod = PaymentMethod.getPaymentMethod(payment.getCode());
			PaymentService paymentService = paymentServiceStrategy.getPaymentService(paymentMethod);
			paymentService.performPayment(paymentMethod);
		} else {
			throw new BadRequestException("Payment code is invalid");
		}
	}
	
	private boolean isValidPayment(Integer code) {
		return this.serviceImpl.getPaymentsAvailables().get(code) !=null;
	}

	@Override
	public void addItem(Item item) throws VendingMachineException {
		ItemTransaction currentTransaccion = machineState.getCurrentTransaccion();
		if (currentTransaccion==null) {
			//select item start a new transaction
			currentTransaccion = new ItemTransaction();
			machineState.setCurrentTransaccion(currentTransaccion);
		}
		ItemStock itStk = machineState.getItemStock(item.getCode());
		if (itStk==null || itStk.getQuantity() < countItemsTrx(item.getCode()) + 1) {
			throw new InvalidStateVMException("Stock not available for the item selected");
		}
		currentTransaccion.getItems().add(itStk.getItem());	
		currentTransaccion.setTransactionAmount(currentTransaccion.getTransactionAmount() + itStk.getItem().getUnitPrice());
	}
	
	private int countItemsTrx(Integer code) {
		int quantity = 0;
		for (Item item : machineState.getCurrentTransaccion().getItems()) {
			if (item.getCode().equals(code)) {
				quantity++;
			}
		}
		return quantity;
	}

	@Override
	public ItemTransaction endTransaction() throws VendingMachineException {
		ItemTransaction currentTransaccion = machineState.getCurrentTransaccion();
		validateTransaction(currentTransaccion);
		paymentServiceStrategy.getPaymentService(currentTransaccion.getPaymentMethod()).cashout(currentTransaccion);
		for (Item item : currentTransaccion.getItems()) {
			vendingMachineInterface.expend(item);
			machineState.removeStock(item);
		}

		for (Item item : currentTransaccion.getItems()) {
			TransactionVMEntity trxEntity = new TransactionVMEntity();
			trxEntity.setAmount(currentTransaccion.getTransactionAmount());
			trxEntity.setIdItem(item.getCode());
			trxEntity.setPaymentMethodId(currentTransaccion.getPaymentMethod().getCode());
			trxEntity.setPaymentMethodReference(currentTransaccion.getExternalReference());
			trxEntity.setQuantity(1);
			trxEntity.setTransactionDate(new Date());
			transactionVMRepository.save(trxEntity);
		}
		return currentTransaccion;
	}
	
	
	/**
	 * 
	 * @param currentTransaccion
	 * @throws InvalidStateVMException
	 */
	private void validateTransaction(ItemTransaction currentTransaccion) throws InvalidStateVMException {
		if (currentTransaccion==null) {
			throw new InvalidStateVMException("No transaction present");
		}
		if (currentTransaccion.getItems().isEmpty()) {
			throw new InvalidStateVMException("Transaction has no items selected");
		}
		if (currentTransaccion.getPaymentMethod()==null) {
			throw new InvalidStateVMException("Transaction has no payment method selected");
		}
	}

	@Override
	public void processTransactions() throws VendingMachineException {
		List<TransactionVMEntity> trxs = transactionVMRepository.findByRegistered(false);
		for (TransactionVMEntity trx : trxs) {
			//trx.se
		}
		
	}

	@Override
	public void initializeMachine() throws VendingMachineException {
		this.machineState.initializeMachine();
	}

	@Override
	public void printCurrentState() throws VendingMachineException {
		this.machineState.printMachineState();
	}

}
