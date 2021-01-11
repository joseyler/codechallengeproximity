package com.cr.proximity.vendingmachine.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.model.transaction.Payment;

@Service("TransactionsService")
public class TransactionServiceFacade implements TransactionsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceFacade.class);

	private Environment environment;
	
	private TransactionsService serviceImpl;

	public TransactionServiceFacade(Environment environment) {
		super();
		this.environment = environment;
		configure();
	}

	private void configure() {
		String model = environment.getProperty("vending.machine.model");
		LOGGER.info("Starting configuration");
		LOGGER.info("Vending Machine Model: " + model);
		
		switch (model) {
		case "XYZ1":
			this.serviceImpl = new TransactionServiceXYZ1Impl();
			break;
		case "XYZ2":
			this.serviceImpl = new TransactionServiceXYZ2Impl();
			break;
		default:
			throw new RuntimeException("Configuration error, model not supported");
		}
	}

	@Override
	public void addCash(Payment payment) throws VendingMachineException {
		this.serviceImpl.addCash(payment);
	}

}
