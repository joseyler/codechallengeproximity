package com.cr.proximity.vendingmachine.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cr.proximity.vendingmachine.exceptions.VendingMachineException;
import com.cr.proximity.vendingmachine.service.TransactionsService;

public class TransactionsProcessorTask extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionsProcessorTask.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    	TransactionsService transactionsService = (TransactionsService) context.getJobDetail().getJobDataMap().get("TransactionsService");
        try {
            LOGGER.info("##############  Processing transactions  ##############");
            transactionsService.processTransactions();
            transactionsService.evaluateAlerts();
        } catch (VendingMachineException e) {
            LOGGER.trace(e.getMessage(), e);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

}
