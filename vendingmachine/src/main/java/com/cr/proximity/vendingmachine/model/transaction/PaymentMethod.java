package com.cr.proximity.vendingmachine.model.transaction;

public enum PaymentMethod {
	CENTS_5(1001,0.05),CENTS_10(1002,0.1),CENTS_25(1003,0.25),CENTS_50(1004,0.5),DOLLAR_1(1005,1),DOLLAR_2(1006,1), CREDIT_CARD(2055,0,false);

	private int code;
	private double amount;
	private boolean cash;
	
	PaymentMethod(int code,double amount) {
		this.code = code;
		this.amount = amount;
		this.cash = true;
	}
	
	PaymentMethod(int code,double amount,boolean cash) {
		this.code = code;
		this.amount = amount;
		this.cash = cash;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the cash
	 */
	public boolean isCash() {
		return cash;
	}

	/**
	 * 
	 * @param codeQuery
	 * @return
	 */
	public static PaymentMethod getPaymentMethod(int codeQuery) {
		for (PaymentMethod payMethod: PaymentMethod.values()) {
			if (payMethod.getCode() == codeQuery) {
				return payMethod;
			}
		}
		return null;
	}
	
	
	
	
}
