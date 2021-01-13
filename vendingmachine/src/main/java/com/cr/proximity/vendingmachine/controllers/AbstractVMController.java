package com.cr.proximity.vendingmachine.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cr.proximity.vendingmachine.exceptions.BadRequestException;
import com.cr.proximity.vendingmachine.exceptions.InvalidStateVMException;
import com.cr.proximity.vendingmachine.exceptions.PaymentException;
import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;

public class AbstractVMController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractVMController.class);

	/**
	 * 
	 * @param vme
	 * @return
	 */
	protected ResponseEntity<Object> processVMException(VendingMachineException vme) {
		  if (vme instanceof BadRequestException) {
	            LOGGER.warn(vme.getMessage());
	            return new ResponseEntity<Object>(vme.getMessage(),HttpStatus.BAD_REQUEST);
	        }
	        if (vme instanceof PaymentException) {
	            LOGGER.warn(vme.getMessage());
	            return new ResponseEntity<Object>(vme.getMessage(),HttpStatus.PAYMENT_REQUIRED);
	        }
	        if (vme instanceof InvalidStateVMException) {
	            LOGGER.info(vme.getMessage());
	            return new ResponseEntity<Object>(vme.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	        }
	        LOGGER.error(vme.getMessage(),vme);
	        return new ResponseEntity<Object>(vme.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
