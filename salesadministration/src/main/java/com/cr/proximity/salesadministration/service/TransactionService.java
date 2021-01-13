package com.cr.proximity.salesadministration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cr.proximity.salesadministration.dao.TransactionsDao;
import com.cr.proximity.salesadministration.exceptions.SalesAdministrarionException;
import com.cr.proximity.salesadministration.model.SaleTransaction;

@Service
public class TransactionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

	private TransactionsDao transactionsDao;
	private VendingMachineService vendingMachineService;
	private PaymentsService paymentsService;
	private ItemsService itemsService;

	public TransactionService(TransactionsDao transactionsDao,PaymentsService paymentsService,ItemsService itemsService,
				VendingMachineService vendingMachineService) {
		this.transactionsDao = transactionsDao;
		this.itemsService = itemsService;
		this.paymentsService = paymentsService;
		this.vendingMachineService = vendingMachineService;
	}

	public SaleTransaction registerTransaction(SaleTransaction saleTransaction) throws SalesAdministrarionException {
		try {
			SaleTransaction saved = transactionsDao.saveTransaction(saleTransaction);
			SaleTransaction transactionById = transactionsDao.getTransactionById(saved.getId());
			transactionById.setItem(itemsService.getItem(transactionById.getItem().getId()));
			transactionById.setPaymentMethod(paymentsService.getPaymentMethod(transactionById.getPaymentMethod().getId()));
			transactionById.setVendingMachine(vendingMachineService.getVendingMachine(transactionById.getVendingMachine().getId()));
			return transactionById;
		} catch (SalesAdministrarionException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new SalesAdministrarionException("Error saving transaction: " + e.getMessage());
		}

	}

}
