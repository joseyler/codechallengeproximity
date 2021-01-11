package com.cr.proximity.vendingmachine;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.cr.proximity.vendingmachine.controllers.VendingMachineController;
import com.cr.proximity.vendingmachine.model.Item;
import com.cr.proximity.vendingmachine.model.ItemStock;
import com.cr.proximity.vendingmachine.model.transaction.Payment;
import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;
import com.cr.proximity.vendingmachine.service.TransactionsService;
import com.cr.proximity.vendingmachine.state.MachineState;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = VendingMachineController.class)
@ActiveProfiles("test")
public class ItemSellTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@MockBean
	MachineState machineState;
	
	@MockBean
	@Qualifier("TransactionsService")
	private TransactionsService transactionsService;
	
	@BeforeEach
    void setUp() {
		machineState.getCashStock().put(PaymentMethod.CENTS_5,50);
		machineState.getCashStock().put(PaymentMethod.CENTS_10,50);
		machineState.getCashStock().put(PaymentMethod.CENTS_25,50);
		machineState.getCashStock().put(PaymentMethod.CENTS_50,50);
		machineState.getCashStock().put(PaymentMethod.DOLLAR_1,50);
		machineState.getCashStock().put(PaymentMethod.DOLLAR_2,50);
		
		machineState.setCurrentCash(machineState.countCurrentCash());
		
		Item it = new Item();
		it.setCode(4558);
		it.setName("Coke");
		it.setUnitPrice(1.2);
		it.setId(1);
		ItemStock itStk = new ItemStock();
		itStk.setItem(it);
		itStk.setQuantity(20);
		machineState.getItemsStock().add(itStk);
		
		it = new Item();
		it.setCode(4332);
		it.setName("Diet-Coke");
		it.setUnitPrice(1.0);
		it.setId(2);
		itStk = new ItemStock();
		itStk.setItem(it);
		itStk.setQuantity(20);
		machineState.getItemsStock().add(itStk);
		
		it = new Item();
		it.setCode(4332);
		it.setName("Sprite");
		it.setUnitPrice(0.8);
		it.setId(3);
		itStk = new ItemStock();
		itStk.setItem(it);
		itStk.setQuantity(20);
		machineState.getItemsStock().add(itStk);
		
		machineState.printMachineState();
		
    }
	
	@Test
    void buyCokeTest() throws Exception {
		  
//        given(userService.findAllUsers()).willReturn(userList);
//
		Payment payment = new Payment();
		payment.setCode(PaymentMethod.CENTS_25.getCode());
        this.mockMvc.perform(post("/v1/vmachine/cash").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(payment)))
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.size()", is(userList.size())));
        
        
    }

}
