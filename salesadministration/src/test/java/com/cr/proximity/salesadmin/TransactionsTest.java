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
