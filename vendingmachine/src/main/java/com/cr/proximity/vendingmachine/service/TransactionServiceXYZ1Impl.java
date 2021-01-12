package com.cr.proximity.vendingmachine.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cr.proximity.vendingmachine.exceptions.BadRequestException;
import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.model.Item;
import com.cr.proximity.vendingmachine.model.transaction.Payment;
import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;

public class TransactionServiceXYZ1Impl implements TransactionsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceXYZ1Impl.class);

	protected Map<Integer,Boolean> paymentsAvailables;
	
	protected PaymentServiceStrategy paymentServiceStrategy;

	public TransactionServiceXYZ1Impl(PaymentServiceStrategy paymentServiceStrategy) {
		super();
		paymentsAvailables = new HashMap<Integer,Boolean>();
		this.paymentServiceStrategy = paymentServiceStrategy;
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
		
		
	}


	@Override
	public void endTransaction() throws VendingMachineException {
		// TODO Auto-generated method stub
		
	}

}
