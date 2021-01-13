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
import com.cr.proximity.salesadministration.model.Item;
import com.cr.proximity.salesadministration.service.ItemsService;

@RestController
@RequestMapping("/v1/items")
public class ItemsController  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemsController.class);
	
	private ItemsService itemsService;
	
	public ItemsController(ItemsService itemsService) {
		super();
		this.itemsService = itemsService;
	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> addTransaction(@RequestBody Item item) {
        try {
        	Item savedItem = itemsService.saveItem(item);
            return new ResponseEntity<Object>(savedItem, HttpStatus.CREATED);
        } catch (SalesAdministrarionException sae) {
        	LOGGER.error(sae.getMessage(),sae);
            return new ResponseEntity<Object>(sae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
