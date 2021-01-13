package com.cr.proximity.salesadministration.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cr.proximity.salesadministration.exceptions.SalesAdministrarionException;
import com.cr.proximity.salesadministration.model.PaymentMethod;
import com.cr.proximity.salesadministration.service.PaymentsService;

@RestController
@RequestMapping("/v1/paymentsmethod")
public class PaymentMethodController  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentMethodController.class);
	
	private PaymentsService paymentsService;
	
	public PaymentMethodController(PaymentsService paymentsService) {
		super();
		this.paymentsService = paymentsService;
	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> addTransaction(@RequestBody PaymentMethod paymentMethod) {
        try {
        	PaymentMethod savedPaymentMethod = paymentsService.savePaymentMethod(paymentMethod);
            return new ResponseEntity<Object>(savedPaymentMethod, HttpStatus.CREATED);
        } catch (SalesAdministrarionException sae) {
        	LOGGER.error(sae.getMessage(),sae);
            return new ResponseEntity<Object>(sae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
