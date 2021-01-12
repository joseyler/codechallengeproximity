package com.cr.proximity.vendingmachine.service;

import java.util.HashMap;
import java.util.Map;

import com.cr.proximity.vendingmachine.machine.VendingMachineInterface;
import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;

public class PaymentsXYZ1Configuration  {

	private Map<Integer,Boolean> paymentsAvailables;
	
	protected PaymentServiceStrategy paymentServiceStrategy;

	
	VendingMachineInterface vendingMachineInterface;
	

	public PaymentsXYZ1Configuration() {
		paymentsAvailables = new HashMap<Integer,Boolean>();
		configurePayments();
	}

	/**
	 * 
	 */
	protected void configurePayments() {
		paymentsAvailables.put(PaymentMethod.CENTS_5.getCode(), Boolean.TRUE);
		paymentsAvailables.put(PaymentMethod.CENTS_25.getCode(), Boolean.TRUE);
		paymentsAvailables.put(PaymentMethod.CENTS_50.getCode(), Boolean.TRUE);
		paymentsAvailables.put(PaymentMethod.CENTS_10.getCode(), Boolean.TRUE);
		paymentsAvailables.put(PaymentMethod.DOLLAR_1.getCode(), Boolean.TRUE);
		paymentsAvailables.put(PaymentMethod.DOLLAR_2.getCode(), Boolean.TRUE);
	}

	/**
	 * @return the paymentsAvailables
	 */
	public Map<Integer, Boolean> getPaymentsAvailables() {
		return paymentsAvailables;
	}


	

}
