package com.cr.proximity.salesadmin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cr.proximity.salesadministration.SalesAdministrationApplication;
import com.cr.proximity.salesadministration.model.Item;
import com.cr.proximity.salesadministration.model.PaymentMethod;
import com.cr.proximity.salesadministration.model.SaleTransaction;
import com.cr.proximity.salesadministration.model.VendingMachine;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(classes = SalesAdministrationApplication.class)
@WebAppConfiguration
public class TransactionsTest {

    private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;

	
	@Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setUpMock() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        Item it = new Item();
        it.setCode(4558);
        it.setName("Coke");
        this.mockMvc.perform(post("/v1/items").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(objectMapper.writeValueAsString(it)))
        		.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        
        it = new Item();
        it.setCode(4332);
        it.setName("Diet-Coke");
        this.mockMvc.perform(post("/v1/items").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(objectMapper.writeValueAsString(it)))
        		.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        
        it = new Item();
        it.setCode(7555);
        it.setName("Sprite");
        this.mockMvc.perform(post("/v1/items").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(objectMapper.writeValueAsString(it)))
        		.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(1);
        paymentMethod.setName("Cash");
        this.mockMvc.perform(post("/v1/paymentsmethod").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(objectMapper.writeValueAsString(paymentMethod)))
		.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        
        paymentMethod = new PaymentMethod();
        paymentMethod.setId(2);
        paymentMethod.setName("Credit Card");
        this.mockMvc.perform(post("/v1/paymentsmethod").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(objectMapper.writeValueAsString(paymentMethod)))
		.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        
        
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setId(458);
        vendingMachine.setModel("XYZ1");
        vendingMachine.setLocation("Downtown Office");
        this.mockMvc.perform(post("/v1/vendingmachines").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(objectMapper.writeValueAsString(vendingMachine)))
		.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        
        vendingMachine = new VendingMachine();
        vendingMachine.setId(15223);
        vendingMachine.setModel("XYZ2");
        vendingMachine.setLocation("Downtown Office");
        this.mockMvc.perform(post("/v1/vendingmachines").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(objectMapper.writeValueAsString(vendingMachine)))
		.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

	
	@Test
    void registerTransactions() throws Exception {
		
		SaleTransaction st = new SaleTransaction();
		st.setAmount(1.2);
		st.setItem(new Item());
		st.getItem().setId(1); //for coke
		st.setPaymentMethod(new PaymentMethod());
		st.getPaymentMethod().setId(1);
		st.setTransactionDate(new Date());
		st.setVendingMachine(new VendingMachine());
		st.getVendingMachine().setId(458);
		
		this.mockMvc.perform(post("/v1/sales/transaction").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(objectMapper.writeValueAsString(st)))
        	.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
		

    }
	
	

}
