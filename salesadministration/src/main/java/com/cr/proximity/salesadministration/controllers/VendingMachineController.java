package com.cr.proximity.salesadministration.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cr.proximity.salesadministration.exceptions.SalesAdministrarionException;
import com.cr.proximity.salesadministration.model.VendingMachine;
import com.cr.proximity.salesadministration.service.VendingMachineService;

@RestController
@RequestMapping("/v1/vendingmachines")
public class VendingMachineController  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VendingMachineController.class);
	
	private VendingMachineService vendingMachineService;
	
	public VendingMachineController(VendingMachineService vendingMachineService) {
		super();
		this.vendingMachineService = vendingMachineService;
	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> addTransaction(@RequestBody VendingMachine vendingMachine) {
        try {
        	VendingMachine savedVendingMachine = vendingMachineService.saveVendingMachine(vendingMachine);
            return new ResponseEntity<Object>(savedVendingMachine, HttpStatus.CREATED);
        } catch (SalesAdministrarionException sae) {
        	LOGGER.error(sae.getMessage(),sae);
            return new ResponseEntity<Object>(sae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
