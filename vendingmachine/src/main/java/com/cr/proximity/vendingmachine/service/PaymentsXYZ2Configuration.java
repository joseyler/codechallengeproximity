package com.cr.proximity.vendingmachine.service;

import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;

public class PaymentsXYZ2Configuration extends PaymentsXYZ1Configuration {
	
	public PaymentsXYZ2Configuration() {
		super();
	}


	@Override
	protected void configurePayments() {
		super.configurePayments();
		getPaymentsAvailables().put(PaymentMethod.CREDIT_CARD.getCode(), Boolean.TRUE);
	}

	

}
