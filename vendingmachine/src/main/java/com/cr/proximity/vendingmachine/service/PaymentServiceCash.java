package com.cr.proximity.vendingmachine.service;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cr.proximity.vendingmachine.exceptions.InvalidaStateVMException;
import com.cr.proximity.vendingmachine.exceptions.PaymentException;
import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.machine.VendingMachineInterface;
import com.cr.proximity.vendingmachine.model.ItemTransaction;
import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;
import com.cr.proximity.vendingmachine.state.MachineState;

@Service
public class PaymentServiceCash implements PaymentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceCash.class);
	
	private MachineState machineState;
	
	VendingMachineInterface vendingMachineInterface;

	public PaymentServiceCash(MachineState machineState,VendingMachineInterface vendingMachineInterface) {
		super();
		this.machineState = machineState;
		this.vendingMachineInterface = vendingMachineInterface;
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
				currentTransaccion.setPaymentMethod(paymentMethod);
				machineState.setCurrentTransaccion(currentTransaccion);
			}
			currentTransaccion.setTransactionCash(currentTransaccion.getTransactionCash() + paymentMethod.getAmount());
			
		} catch (Exception e) {
			LOGGER.error("Error performing cash payment", e);
            throw new PaymentException("Error performing cash payment: " + e.getMessage());
		}
	}

	@Override
	public void cashout(ItemTransaction currentTransaccion) throws VendingMachineException {
		if (currentTransaccion.getTransactionCash() <= currentTransaccion.getTransactionAmount()) {
			throw new InvalidaStateVMException("No enough money for the transaction");
		}
		double changeAmount = currentTransaccion.getTransactionCash() - currentTransaccion.getTransactionAmount();
		Map<PaymentMethod,Integer> change = calculateChange(changeAmount);
		for (Entry<PaymentMethod, Integer> changeItem : change.entrySet()) {
			//return x quantity of a payment method
			vendingMachineInterface.returnCash(changeItem.getKey().getCode(),changeItem.getValue());
		}
		
	}

	private Map<PaymentMethod, Integer> calculateChange(double changeAmount) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	
}
