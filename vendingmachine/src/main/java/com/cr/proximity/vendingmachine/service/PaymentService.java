package com.cr.proximity.vendingmachine.service;

import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;

public interface PaymentService {
	
	/**
	 * 
	 * @param paymentMethod
	 * @param amount
	 * @throws VendingMachineException
	 */
	public void performPayment(PaymentMethod paymentMethod,double amount)  throws VendingMachineException;

}
