package com.cr.proximity.vendingmachine.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cr.proximity.vendingmachine.exceptions.PaymentException;
import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.model.ItemTransaction;
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
	public void performPayment(PaymentMethod paymentMethod) throws VendingMachineException {
		try {
			Integer quantity = this.machineState.getCashStock().get(paymentMethod);
			if (quantity != null) {
				this.machineState.getCashStock().put(paymentMethod, quantity++);
			} else {
				this.machineState.getCashStock().put(paymentMethod, 1);
			}
			this.machineState.setCurrentCash(this.machineState.getCurrentCash() + paymentMethod.getAmount());
			
			ItemTransaction currentTransaccion = machineState.getCurrentTransaccion();
			if (currentTransaccion==null) {
				//insert money start a new transaction
				currentTransaccion = new ItemTransaction();
				machineState.setCurrentTransaccion(currentTransaccion);
			}
			currentTransaccion.setTransactionCash(currentTransaccion.getTransactionCash() + paymentMethod.getAmount());
			
		} catch (Exception e) {
			LOGGER.error("Error performing cash payment", e);
            throw new PaymentException("Error performing cash payment: " + e.getMessage());
		}
	}

	 
	
}
