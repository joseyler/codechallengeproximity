package com.cr.proximity.vendingmachine.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class TransactionVMEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="id_item")
	private Integer idItem;
	
	@Column(name="transaction_date")
	private Date transactionDate;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="payment_method_id")
	private int paymentMethodId;
	
	@Column(name="payment_method_reference",length = 200)
	private String paymentMethodReference;

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
	 * @return the idItem
	 */
	public Integer getIdItem() {
		return idItem;
	}

	/**
	 * @param idItem the idItem to set
	 */
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
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
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	 * @return the paymentMethodReference
	 */
	public String getPaymentMethodReference() {
		return paymentMethodReference;
	}

	/**
	 * @param paymentMethodReference the paymentMethodReference to set
	 */
	public void setPaymentMethodReference(String paymentMethodReference) {
		this.paymentMethodReference = paymentMethodReference;
	}
	
	
	

}
