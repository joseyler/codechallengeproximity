package com.cr.proximity.vendingmachine.machine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cr.proximity.vendingmachine.model.Item;

/**
 * Simulate interactions whith hardware in the machine
 * @author josey
 *
 */
@Component
public class VendingMachineInterface {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VendingMachineInterface.class);

	/**
	 * Simulate return change
	 * @param code
	 * @param value
	 */
	public void returnCash(int paymentMethodCode, Integer quantity) {
		LOGGER.info("Returns " + quantity + " times cash the payment method code " + paymentMethodCode);
	}

	public void expend(Item item) {
		LOGGER.info("Expends one  " + item.getName());
	}

}
