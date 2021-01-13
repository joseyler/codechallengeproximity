package com.cr.proximity.vendingmachine.model.transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;

public enum PaymentMethod {
	
	
	
	CENTS_5(1001,0.05),CENTS_10(1002,0.1),CENTS_25(1003,0.25),CENTS_50(1004,0.5),DOLLAR_1(1005,1),DOLLAR_2(1006,2), CREDIT_CARD(2055,0,false);

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentMethod.class);
	
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

	public static List<PaymentMethod> getCoins() throws VendingMachineException {
		try {
			List<PaymentMethod> coins = new ArrayList<PaymentMethod>(Arrays.asList(PaymentMethod.values()));
			coins.removeIf(c -> (c.getAmount() >= 1 || !c.cash));
			coins.sort(new PaymentMethodComparator());
			return coins;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new VendingMachineException("Unable to recover coins");
		}
	}
	
	
	
	
}
