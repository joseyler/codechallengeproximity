package com.cr.proximity.vendingmachine;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cr.proximity.vendingmachine.model.transaction.Payment;
import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(classes = VendingmachineApplication.class)
@WebAppConfiguration
public class AlertCashTest {

    private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;

	
	@Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setUpMock() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

	
	@Test
    void TryAlert() throws Exception {
		this.mockMvc.perform(put("/v1/vmachine/initialize").contentType(MediaType.APPLICATION_JSON))
        	.andExpect(status().isOk());
		
		//add 120 dollars
		for (int i=0; i < 60; i++) {
			Payment payment = new Payment();
			payment.setCode(PaymentMethod.DOLLAR_2.getCode());
	        this.mockMvc.perform(post("/v1/vmachine/cash").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(payment)))
	                .andExpect(status().isCreated());
		}
		

        this.mockMvc.perform(post("/v1/vmachine/evaluatealerts").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotAcceptable());
    }
	
}
