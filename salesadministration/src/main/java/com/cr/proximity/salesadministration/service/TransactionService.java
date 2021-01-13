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

	public TransactionService(TransactionsDao transactionsDao) {
		this.transactionsDao = transactionsDao;
	}

	public SaleTransaction registerTransaction(SaleTransaction saleTransaction) throws SalesAdministrarionException {
		try {
			SaleTransaction saved = transactionsDao.saveTransaction(saleTransaction);
			return transactionsDao.getTransactionById(saved.getId());
		} catch (SalesAdministrarionException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new SalesAdministrarionException("Error saving transaction: " + e.getMessage());
		}

	}

}
