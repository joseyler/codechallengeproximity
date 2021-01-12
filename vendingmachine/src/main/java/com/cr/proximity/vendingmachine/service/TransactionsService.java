package com.cr.proximity.vendingmachine.service;

import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.model.Item;
import com.cr.proximity.vendingmachine.model.ItemTransaction;
import com.cr.proximity.vendingmachine.model.transaction.Payment;

public interface TransactionsService {
	
	/**
	 * 
	 * @param payment
	 * @throws VendingMachineException
	 */
	public void addCash(Payment payment) throws VendingMachineException;

	/**
	 * 
	 * @param item
	 * @throws VendingMachineException
	 */
	public void addItem(Item item)  throws VendingMachineException;

	/**
	 * 
	 * @return
	 * @throws VendingMachineException
	 */
	public ItemTransaction endTransaction()  throws VendingMachineException;

}
