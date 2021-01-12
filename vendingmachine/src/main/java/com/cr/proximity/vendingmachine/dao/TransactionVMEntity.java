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
	
	
	

}
