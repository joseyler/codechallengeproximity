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
import com.cr.proximity.salesadministration.model.SaleTransaction;
import com.cr.proximity.salesadministration.service.TransactionService;

@RestController
@RequestMapping("/v1/sales")
public class AdministrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdministrationController.class);

	private TransactionService transactionService;

	public AdministrationController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}

	@PostMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> addTransaction(@RequestBody SaleTransaction saleTransaction) {
		try {
			SaleTransaction registerTransaction = transactionService.registerTransaction(saleTransaction);
			return new ResponseEntity<Object>(registerTransaction, HttpStatus.CREATED);
		} catch (SalesAdministrarionException sae) {
			LOGGER.error(sae.getMessage(), sae);
			return new ResponseEntity<Object>(sae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//
//	@PostMapping(value = "/item", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<Object> addItem() {
//        try {
//        	
//            return new ResponseEntity<Object>( HttpStatus.CREATED);
//        } catch (SalesAdministrarionException vme) {
//            return processVMException(vme);
//        }
//    }
//	
//	@PostMapping(value = "/finalize", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<Object> endTransaction() {
//        try {
//        	
//            return new ResponseEntity<Object>( HttpStatus.CREATED);
//        } catch (SalesAdministrarionException vme) {
//            return processVMException(vme);
//        }
//    }

}
