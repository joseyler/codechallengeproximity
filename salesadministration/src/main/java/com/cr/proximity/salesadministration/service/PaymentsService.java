package com.cr.proximity.salesadministration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cr.proximity.salesadministration.dao.PaymentMethodRepository;
import com.cr.proximity.salesadministration.dao.entities.PaymentMethodEntity;
import com.cr.proximity.salesadministration.dao.entities.mappers.PaymentMethodMapper;
import com.cr.proximity.salesadministration.exceptions.SalesAdministrarionException;
import com.cr.proximity.salesadministration.model.PaymentMethod;

@Service
public class PaymentsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentsService.class);

	private PaymentMethodRepository paymentMethodRepository;

	public PaymentsService(PaymentMethodRepository paymentMethodRepository) {
		this.paymentMethodRepository = paymentMethodRepository;
	}

	
	public PaymentMethod savePaymentMethod(PaymentMethod paymentMethod) throws SalesAdministrarionException {
		try {
			PaymentMethodEntity paymentMethodEntity =PaymentMethodMapper.mapEntity(paymentMethod);
			paymentMethodRepository.save(paymentMethodEntity);
			return paymentMethod;
		}  catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new SalesAdministrarionException("Error saving PaymentMethod: " + e.getMessage());
		}

	}

}
