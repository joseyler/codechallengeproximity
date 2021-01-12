package com.cr.proximity.salesadmin.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/vmachine")
public class AdministrationController extends AbstractVMController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdministrationController.class);
	
	
	
	public AdministrationController() {
		super();
		
	}

//	@PostMapping(value = "/cash", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<Object> addCash() {
//        try {
//        	
//            return new ResponseEntity<Object>( HttpStatus.CREATED);
//        } catch (SalesAdministrarionException vme) {
//            return processVMException(vme);
//        }
//    }
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
