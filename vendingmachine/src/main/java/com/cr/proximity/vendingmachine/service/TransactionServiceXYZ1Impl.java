package com.cr.proximity.vendingmachine.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cr.proximity.vendingmachine.exceptions.BadRequestException;
import com.cr.proximity.vendingmachine.exceptions.InvalidaStateVMException;
import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.model.Item;
import com.cr.proximity.vendingmachine.model.ItemTransaction;
import com.cr.proximity.vendingmachine.model.transaction.Payment;
import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;
import com.cr.proximity.vendingmachine.state.MachineState;

public class TransactionServiceXYZ1Impl implements TransactionsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceXYZ1Impl.class);

	protected Map<Integer,Boolean> paymentsAvailables;
	
	protected PaymentServiceStrategy paymentServiceStrategy;

	private MachineState machineState;

	public TransactionServiceXYZ1Impl(PaymentServiceStrategy paymentServiceStrategy,MachineState machineState) {
		super();
		paymentsAvailables = new HashMap<Integer,Boolean>();
		this.paymentServiceStrategy = paymentServiceStrategy;
		this.machineState = machineState;
		initialize();
	}


	protected void initialize() {
		paymentsAvailables.put(PaymentMethod.CENTS_5.getCode(), Boolean.TRUE);
		paymentsAvailables.put(PaymentMethod.CENTS_25.getCode(), Boolean.TRUE);
		paymentsAvailables.put(PaymentMethod.CENTS_50.getCode(), Boolean.TRUE);
		paymentsAvailables.put(PaymentMethod.CENTS_10.getCode(), Boolean.TRUE);
		paymentsAvailables.put(PaymentMethod.DOLLAR_1.getCode(), Boolean.TRUE);
		paymentsAvailables.put(PaymentMethod.DOLLAR_2.getCode(), Boolean.TRUE);
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
		return this.paymentsAvailables.get(code) !=null;
	}


	@Override
	public void addItem(Item item) throws VendingMachineException {
		ItemTransaction currentTransaccion = machineState.getCurrentTransaccion();
		if (currentTransaccion==null) {
			//select item start a new transaction
			currentTransaccion = new ItemTransaction();
			machineState.setCurrentTransaccion(currentTransaccion);
		}
		currentTransaccion.getItems().add(item);
	}


	@Override
	public ItemTransaction endTransaction() throws VendingMachineException {
		ItemTransaction currentTransaccion = machineState.getCurrentTransaccion();
		validateTransaction(currentTransaccion);
		paymentServiceStrategy.getPaymentService(currentTransaccion.getPaymentMethod()).cashout(currentTransaccion);
		
		return currentTransaccion;
	}

	/**
	 * 
	 * @param currentTransaccion
	 * @throws InvalidaStateVMException
	 */
	private void validateTransaction(ItemTransaction currentTransaccion) throws InvalidaStateVMException {
		if (currentTransaccion==null) {
			throw new InvalidaStateVMException("No transaction present");
		}
		if (currentTransaccion.getItems().isEmpty()) {
			throw new InvalidaStateVMException("Transaction has no items selected");
		}
		if (currentTransaccion.getPaymentMethod()==null) {
			throw new InvalidaStateVMException("Transaction has no payment method selected");
		}
	}

}
