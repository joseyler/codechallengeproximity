package com.cr.proximity.salesadministration.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cr.proximity.salesadministration.dao.entities.TransactionEntity;
import com.cr.proximity.salesadministration.dao.entities.mappers.SaleTransactionMapper;
import com.cr.proximity.salesadministration.exceptions.SalesAdministrarionException;
import com.cr.proximity.salesadministration.model.SaleTransaction;

@Repository
public class TransactionsDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionsDao.class);
	
	@PersistenceContext
    private EntityManager entityManager;

	/**
	 * 
	 * @param saleTransaction
	 * @throws SalesAdministrarionException
	 */
	@Transactional
	public SaleTransaction saveTransaction(SaleTransaction saleTransaction) throws SalesAdministrarionException {
		try {
			TransactionEntity trxEntity = SaleTransactionMapper.mapEntity(saleTransaction);
			entityManager.persist(trxEntity);
			saleTransaction.setId(trxEntity.getId());
			return saleTransaction;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new SalesAdministrarionException("Error saving transaction: " +  e.getMessage());
		}
		
	}
	
	
	public SaleTransaction getTransactionById(Long saleTransactionId) throws SalesAdministrarionException {
		try {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select trx from TransactionEntity trx ");
			queryStr.append(" where  trx.id = :id ");
			TypedQuery<TransactionEntity> query = entityManager.createQuery(queryStr.toString(), TransactionEntity.class);
			query.setParameter("id", saleTransactionId);
			TransactionEntity trxEntity = query.getSingleResult();
			return SaleTransactionMapper.mapModelComplete(trxEntity);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new SalesAdministrarionException("Error saving transaction: " +  e.getMessage());
		}
	}
	

}
