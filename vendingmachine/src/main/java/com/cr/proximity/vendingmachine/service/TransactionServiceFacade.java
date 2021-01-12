package com.cr.proximity.vendingmachine.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cr.proximity.vendingmachine.dao.TransactionVMRepository;
import com.cr.proximity.vendingmachine.dao.entities.TransactionVMEntity;
import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.model.Item;
import com.cr.proximity.vendingmachine.model.ItemTransaction;
import com.cr.proximity.vendingmachine.model.transaction.Payment;
import com.cr.proximity.vendingmachine.state.MachineState;

@Service("TransactionsService")
public class TransactionServiceFacade implements TransactionsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceFacade.class);

	private Environment environment;

	private TransactionsService serviceImpl;

	private PaymentServiceStrategy paymentServiceStrategy;

	private MachineState machineState;

	TransactionVMRepository transactionVMRepository;

	public TransactionServiceFacade(Environment environment, PaymentServiceStrategy paymentServiceStrategy,
			MachineState machineState, TransactionVMRepository transactionVMRepository) {
		super();
		this.environment = environment;
		this.paymentServiceStrategy = paymentServiceStrategy;
		this.machineState = machineState;
		this.transactionVMRepository = transactionVMRepository;
		configure();
	}

	private void configure() {
		String model = environment.getProperty("vending.machine.model");
		LOGGER.info("Starting configuration");
		LOGGER.info("Vending Machine Model: " + model);

		switch (model) {
		case "XYZ1":
			this.serviceImpl = new TransactionServiceXYZ1Impl(paymentServiceStrategy, machineState);
			break;
		case "XYZ2":
			this.serviceImpl = new TransactionServiceXYZ2Impl(paymentServiceStrategy, machineState);
			break;
		default:
			throw new RuntimeException("Configuration error, model not supported");
		}
	}

	@Override
	public void addCash(Payment payment) throws VendingMachineException {
		this.serviceImpl.addCash(payment);
	}

	@Override
	public void addItem(Item item) throws VendingMachineException {
		this.serviceImpl.addItem(item);
	}

	@Override
	public ItemTransaction endTransaction() throws VendingMachineException {
		ItemTransaction itemTrx = this.serviceImpl.endTransaction();
		for (Item item : itemTrx.getItems()) {
			TransactionVMEntity trxEntity = new TransactionVMEntity();
			trxEntity.setAmount(itemTrx.getTransactionAmount());
			trxEntity.setIdItem(item.getCode());
			trxEntity.setPaymentMethodId(itemTrx.getPaymentMethod().getCode());
			trxEntity.setPaymentMethodReference(itemTrx.getExternalReference());
			trxEntity.setQuantity(1);
			trxEntity.setTransactionDate(new Date());
			transactionVMRepository.save(trxEntity);
		}
		return itemTrx;
	}

	@Override
	public void processTransactions() throws VendingMachineException {
		transactionVMRepository.findByRegistered(false);
		
	}

}
