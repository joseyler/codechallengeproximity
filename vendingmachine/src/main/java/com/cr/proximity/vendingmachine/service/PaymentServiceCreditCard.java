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
import com.cr.proximity.vendingmachine.model.ItemTransaction;
import com.cr.proximity.vendingmachine.model.external.ExternalPaymentModel;
import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;
import com.cr.proximity.vendingmachine.state.MachineState;

@Service
public class PaymentServiceCreditCard implements PaymentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceCreditCard.class);
	
	private MachineState machineState;
	

	public PaymentServiceCreditCard(MachineState machineState) {
		super();
		this.machineState = machineState;
	}


	@Override
	public void performPayment(PaymentMethod payment) throws VendingMachineException {
		
		ItemTransaction currentTransaccion = machineState.getCurrentTransaccion();
		if (currentTransaccion==null || currentTransaccion.getTransactionAmount() == 0) {
			throw new PaymentException("No payment required or transaction is not present");
		}
		
		ExternalPaymentModel modelPost = new ExternalPaymentModel(machineState.getCreditCardInfo(),currentTransaccion.getTransactionAmount());
		try {
            String url = "https://urlexternalpaymentSite.com";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "JWTAUTH");
            HttpEntity<ExternalPaymentModel> request = new HttpEntity<ExternalPaymentModel>(modelPost,headers);
            RestTemplate restTemplate = new RestTemplate();
            //commented for test success payment
            //restTemplate.exchange(url, HttpMethod.POST, request, Void.class);
            //success payment
            currentTransaccion.setTransactionCash(currentTransaccion.getTransactionAmount());
        }  catch (Exception e) {
            LOGGER.error("Error calling payment site", e);
            throw new PaymentException("Error calling payment site: " + e.getMessage());
        }
	}





}
