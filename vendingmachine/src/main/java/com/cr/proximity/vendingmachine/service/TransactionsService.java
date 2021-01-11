package com.cr.proximity.vendingmachine.service;

import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.model.transaction.Payment;

public interface TransactionsService {
	
	/**
	 * 
	 * @param payment
	 * @throws VendingMachineException
	 */
	public void addCash(Payment payment) throws VendingMachineException;

}
