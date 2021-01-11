package com.cr.proximity.vendingmachine.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cr.proximity.vendingmachine.exceptions.PaymentException;
import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.model.external.ExternalPaymentModel;
import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;
import com.cr.proximity.vendingmachine.state.MachineState;

@Service
public class PaymentServiceCreditCard implements PaymentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceCreditCard.class);
	
	private MachineState machineState;
	
	private RestTemplate restTemplate;
	
	

	public PaymentServiceCreditCard(MachineState machineState,RestTemplate restTemplate) {
		super();
		this.machineState = machineState;
		this.restTemplate = restTemplate;
	}



	@Override
	public void performPayment(PaymentMethod payment,double amount) throws VendingMachineException {
		ExternalPaymentModel modelPost = new ExternalPaymentModel(machineState.getCreditCardInfo(),amount);
		try {
            String url = "https://urlexternalpaymentSite.com";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "JWTAUTH");
            HttpEntity<ExternalPaymentModel> request = new HttpEntity<ExternalPaymentModel>(modelPost,headers);
            restTemplate.exchange(url, HttpMethod.POST, request, Void.class);
        }  catch (Exception e) {
            LOGGER.error("Error calling payment site", e);
            throw new PaymentException("Error calling payment site: " + e.getMessage());
        }
	}





}
