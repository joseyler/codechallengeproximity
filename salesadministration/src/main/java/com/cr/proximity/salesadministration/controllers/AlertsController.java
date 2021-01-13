package com.cr.proximity.salesadministration.controllers;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cr.proximity.salesadministration.exceptions.SalesAdministrarionException;
import com.cr.proximity.salesadministration.model.SaleTransaction;
import com.cr.proximity.salesadministration.service.PaymentsService;
import com.cr.proximity.salesadministration.service.TransactionService;
import com.cr.proximity.salesadministration.service.VendingMachineService;

@RestController
@RequestMapping("/v1/alerts")
public class AlertsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlertsController.class);

	private VendingMachineService vendingMachineService;

	public AlertsController(VendingMachineService vendingMachineService) {
		super();
		this.vendingMachineService = vendingMachineService;
	}


	
	
	@PostMapping(value = "/{machineId}/cashcollect", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> addAlert(@PathVariable("machineId") Integer machineId) {
		try {
			vendingMachineService.sendAlert(machineId);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (SalesAdministrarionException sae) {
			LOGGER.error(sae.getMessage(), sae);
			return new ResponseEntity<Object>(sae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
