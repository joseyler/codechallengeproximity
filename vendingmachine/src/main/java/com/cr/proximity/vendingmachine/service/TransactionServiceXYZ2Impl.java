package com.cr.proximity.vendingmachine.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;
import com.cr.proximity.vendingmachine.state.MachineState;

public class TransactionServiceXYZ2Impl extends TransactionServiceXYZ1Impl {
	
	public TransactionServiceXYZ2Impl(PaymentServiceStrategy paymentServiceStrategy,MachineState machineState) {
		super(paymentServiceStrategy,machineState);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceXYZ2Impl.class);

	@Override
	protected void initializeTrxService() {
		super.initializeTrxService();
		paymentsAvailables.put(PaymentMethod.CREDIT_CARD.getCode(), Boolean.TRUE);
	}

	

}
