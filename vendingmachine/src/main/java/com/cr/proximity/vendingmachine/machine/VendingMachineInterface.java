package com.cr.proximity.vendingmachine.machine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

}
