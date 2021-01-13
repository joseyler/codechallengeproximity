package com.cr.proximity.salesadministration.dao.entities.mappers;

import com.cr.proximity.salesadministration.dao.entities.PaymentMethodEntity;
import com.cr.proximity.salesadministration.model.PaymentMethod;

public class PaymentMethodMapper {
	
	private PaymentMethodMapper() {
		super();
	}

	public static PaymentMethod mapModel(PaymentMethodEntity paymentMethod) {
		PaymentMethod pm = new PaymentMethod();
		pm.setId(paymentMethod.getId());
		pm.setName(paymentMethod.getName());
		return pm;
	}

	public static PaymentMethodEntity mapEntity(PaymentMethod paymentMethod) {
		PaymentMethodEntity pm = new PaymentMethodEntity();
		pm.setId(paymentMethod.getId());
		pm.setName(paymentMethod.getName());
		return pm;
	}

}
