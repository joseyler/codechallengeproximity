package com.cr.proximity.salesadministration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.cr.proximity.salesadministration.exceptions.SalesAdministrarionException;
import com.cr.proximity.salesadministration.model.Item;
import com.cr.proximity.salesadministration.model.PaymentMethod;
import com.cr.proximity.salesadministration.model.VendingMachine;
import com.cr.proximity.salesadministration.service.ItemsService;
import com.cr.proximity.salesadministration.service.PaymentsService;
import com.cr.proximity.salesadministration.service.VendingMachineService;

@Configuration
public class InitialDataForTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InitialDataForTest.class);
	
	@Autowired
	Environment environment;
	
	@Autowired
	private ItemsService itemsService;
	
	@Autowired
	private PaymentsService paymentsService;
	
	@Autowired
	private VendingMachineService vendingMachineService;
	
	@PostConstruct
    public void postConstruct() throws SalesAdministrarionException {
		
		String isTest = environment.getProperty("app.sales.test");
		if (isTest!=null && Boolean.parseBoolean(isTest)) {
			LOGGER.info("Application running in test mode. Loading Initial Data");
			
			
			Item it = new Item();
	        it.setCode(4558);
	        it.setName("Coke");
	        itemsService.saveItem(it);
	        
	        it = new Item();
	        it.setCode(4332);
	        it.setName("Diet-Coke");
	        itemsService.saveItem(it);
	        
	        it = new Item();
	        it.setCode(7555);
	        it.setName("Sprite");
	        itemsService.saveItem(it);
	        
	        PaymentMethod paymentMethod = new PaymentMethod();
	        paymentMethod.setId(1);
	        paymentMethod.setName("Cash");
	        paymentsService.savePaymentMethod(paymentMethod);
	        
	        paymentMethod = new PaymentMethod();
	        paymentMethod.setId(2);
	        paymentMethod.setName("Credit Card");
	        paymentsService.savePaymentMethod(paymentMethod);
	        
	        
	        VendingMachine vendingMachine = new VendingMachine();
	        vendingMachine.setId(458);
	        vendingMachine.setModel("XYZ1");
	        vendingMachine.setLocation("Downtown Office");
	        vendingMachineService.saveVendingMachine(vendingMachine);
	        
	        vendingMachine = new VendingMachine();
	        vendingMachine.setId(15223);
	        vendingMachine.setModel("XYZ2");
	        vendingMachine.setLocation("Downtown Office");
	        vendingMachineService.saveVendingMachine(vendingMachine);
		}
        
    }

}
