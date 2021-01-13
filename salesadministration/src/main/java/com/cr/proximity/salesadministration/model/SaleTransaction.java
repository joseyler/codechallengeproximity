package com.cr.proximity.salesadministration.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SaleTransaction {
	
	private Long id;
	private Item item;
	private PaymentMethod paymentMethod;
	private double amount;
	private String paymentExternalReference;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date transactionDate;
	private VendingMachine vendingMachine;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the paymentExternalReference
	 */
	public String getPaymentExternalReference() {
		return paymentExternalReference;
	}
	/**
	 * @param paymentExternalReference the paymentExternalReference to set
	 */
	public void setPaymentExternalReference(String paymentExternalReference) {
		this.paymentExternalReference = paymentExternalReference;
	}
	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}
	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	/**
	 * @return the vendingMachine
	 */
	public VendingMachine getVendingMachine() {
		return vendingMachine;
	}
	/**
	 * @param vendingMachine the vendingMachine to set
	 */
	public void setVendingMachine(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}
	
	

}
