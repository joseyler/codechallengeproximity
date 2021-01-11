package com.cr.proximity.vendingmachine.service;

import org.springframework.stereotype.Service;

import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;

@Service
public class PaymentServiceStrategy {
	
	private PaymentServiceCash paymentServiceCash;
	private PaymentServiceCreditCard paymentServiceCreditCard;
	
	
	
	public PaymentServiceStrategy(PaymentServiceCash paymentServiceCash,
			PaymentServiceCreditCard paymentServiceCreditCard) {
		super();
		this.paymentServiceCash = paymentServiceCash;
		this.paymentServiceCreditCard = paymentServiceCreditCard;
	}



	public PaymentService getPaymentService(PaymentMethod method) {
		if (method.isCash()) {
			return paymentServiceCash;
		} else {
			return paymentServiceCreditCard;
		}
	}
}
