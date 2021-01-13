package com.cr.proximity.vendingmachine.model.transaction;

import java.util.Comparator;

public class PaymentMethodComparator implements Comparator<PaymentMethod> {

	@Override
	public int compare(PaymentMethod o1, PaymentMethod o2) {
		int returnValue = 0;
		if (o1.getAmount() < o2.getAmount()) {
			returnValue = 1;
		} else if (o1.getAmount() > o2.getAmount()) {
			returnValue = -1;
		}
		return returnValue;
	}

}
