package com.cr.proximity.vendingmachine.model;

import java.util.ArrayList;
import java.util.List;

import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;

public class ItemTransaction {
	
	private List<Item> items;
	private double transactionAmount;
	private double transactionCash;
	private PaymentMethod paymentMethod;
	private String externalReference;
	
	public ItemTransaction() {
		super();
		this.items = new ArrayList<Item>();
	}
	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
	/**
	 * @return the transactionAmount
	 */
	public double getTransactionAmount() {
		return transactionAmount;
	}
	/**
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	/**
	 * @return the transactionCash
	 */
	public double getTransactionCash() {
		return transactionCash;
	}
	/**
	 * @param transactionCash the transactionCash to set
	 */
	public void setTransactionCash(double transactionCash) {
		this.transactionCash = transactionCash;
	}
	/**
	 * @return the paymentMethod
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	/**
	 * @return the externalReference
	 */
	public String getExternalReference() {
		return externalReference;
	}
	/**
	 * @param externalReference the externalReference to set
	 */
	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
	}
	
	

}
