package com.cr.proximity.vendingmachine.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.model.Item;
import com.cr.proximity.vendingmachine.model.transaction.Payment;
import com.cr.proximity.vendingmachine.service.TransactionsService;

@RestController
@RequestMapping("/v1/vmachine")
public class VendingMachineController extends AbstractVMController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VendingMachineController.class);
	
	TransactionsService transactionSevice;
	
	public VendingMachineController(@Qualifier("TransactionsService") TransactionsService transactionSevice) {
		super();
		this.transactionSevice = transactionSevice;
	}
	
	
	@GetMapping(value = "/state", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getCurrentState() {
        try {
        	transactionSevice.printCurrentState();
            return new ResponseEntity<Object>( HttpStatus.OK);
        } catch (VendingMachineException vme) {
            return processVMException(vme);
        }
    }
	
	@PutMapping(value = "/initialize", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> initializeMachine() {
        try {
        	transactionSevice.initializeMachine();
            return new ResponseEntity<Object>( HttpStatus.OK);
        } catch (VendingMachineException vme) {
            return processVMException(vme);
        }
    }

	@PostMapping(value = "/cash", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> addCash(@RequestBody Payment payment) {
        try {
        	transactionSevice.addCash(payment);
            return new ResponseEntity<Object>( HttpStatus.CREATED);
        } catch (VendingMachineException vme) {
            return processVMException(vme);
        }
    }

	@PostMapping(value = "/item", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> addItem(@RequestBody Item item) {
        try {
        	transactionSevice.addItem(item);
            return new ResponseEntity<Object>( HttpStatus.CREATED);
        } catch (VendingMachineException vme) {
            return processVMException(vme);
        }
    }
	
	@PostMapping(value = "/finalize", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> endTransaction() {
        try {
        	transactionSevice.endTransaction();
            return new ResponseEntity<Object>( HttpStatus.CREATED);
        } catch (VendingMachineException vme) {
            return processVMException(vme);
        }
    }

}
