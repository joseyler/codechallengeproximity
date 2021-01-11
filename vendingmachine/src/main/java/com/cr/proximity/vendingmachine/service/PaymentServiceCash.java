package com.cr.proximity.vendingmachine.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cr.proximity.vendingmachine.exceptions.PaymentException;
import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;
import com.cr.proximity.vendingmachine.state.MachineState;

@Service
public class PaymentServiceCash implements PaymentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceCash.class);
	
	private MachineState machineState;

	public PaymentServiceCash(MachineState machineState) {
		super();
		this.machineState = machineState;
	}

	@Override
	public void performPayment(PaymentMethod paymentMethod,double amount) throws VendingMachineException {
		try {
			Integer quantity = this.machineState.getCashStock().get(paymentMethod);
			if (quantity != null) {
				this.machineState.getCashStock().put(paymentMethod, quantity++);
			} else {
				this.machineState.getCashStock().put(paymentMethod, 1);
			}
			this.machineState.setCurrentCash(this.machineState.getCurrentCash() + amount);
		} catch (Exception e) {
			LOGGER.error("Error performing cash payment", e);
            throw new PaymentException("Error performing cash payment: " + e.getMessage());
		}
	}

	 
	
}
