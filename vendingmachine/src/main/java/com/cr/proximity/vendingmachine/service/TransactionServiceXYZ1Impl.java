package com.cr.proximity.vendingmachine.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cr.proximity.vendingmachine.exceptions.BadRequestException;
import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.model.transaction.Payment;
import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;

public class TransactionServiceXYZ1Impl implements TransactionsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceXYZ1Impl.class);

	protected Map<Integer,Boolean> paymentsAvailables;

	public TransactionServiceXYZ1Impl() {
		super();
		paymentsAvailables = new HashMap<Integer,Boolean>();
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
			
		} else {
			throw new BadRequestException("Payment code is invalid");
		}
	}


	private boolean isValidPayment(Integer code) {
		return this.paymentsAvailables.get(code) !=null;
	}

}
