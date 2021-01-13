package com.cr.proximity.salesadministration.dao.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_sale")
public class TransactionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="id_item")
	private int itemId;
	
	@Column(name="id_payment_menthod")
	private int  paymentMethodId;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="payment_ext_reference")
	private String paymentExternalReference;
	
	@Column(name="transaction_date")
	private Date transactionDate;
	
	@Column(name="id_vending_machine")
	private int vendingMachineId;

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
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the paymentMethodId
	 */
	public int getPaymentMethodId() {
		return paymentMethodId;
	}

	/**
	 * @param paymentMethodId the paymentMethodId to set
	 */
	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
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
	 * @return the vendingMachineId
	 */
	public int getVendingMachineId() {
		return vendingMachineId;
	}

	/**
	 * @param vendingMachineId the vendingMachineId to set
	 */
	public void setVendingMachineId(int vendingMachineId) {
		this.vendingMachineId = vendingMachineId;
	}

	

}
