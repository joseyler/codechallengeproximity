package com.cr.proximity.vendingmachine.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;

public class AbstractVMController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractVMController.class);
	
	/**
	 * 
	 * @param vme
	 * @return
	 */
	protected ResponseEntity<Object> processVMException(VendingMachineException vme) {
		LOGGER.error(vme.getMessage(), vme);
		return null;
		
	}

}
