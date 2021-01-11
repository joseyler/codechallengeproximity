package com.cr.proximity.vendingmachine.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;

public class TransactionServiceXYZ2Impl extends TransactionServiceXYZ1Impl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceXYZ2Impl.class);

	@Override
	protected void initialize() {
		super.initialize();
		paymentsAvailables.put(PaymentMethod.CREDIT_CARD.getCode(), Boolean.TRUE);
	}

	

}
