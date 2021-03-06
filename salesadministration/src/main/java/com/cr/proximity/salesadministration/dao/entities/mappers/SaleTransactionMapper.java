package com.cr.proximity.salesadministration.dao.entities.mappers;

import com.cr.proximity.salesadministration.dao.entities.TransactionEntity;
import com.cr.proximity.salesadministration.model.Item;
import com.cr.proximity.salesadministration.model.PaymentMethod;
import com.cr.proximity.salesadministration.model.SaleTransaction;
import com.cr.proximity.salesadministration.model.VendingMachine;

public class SaleTransactionMapper {
	
	private SaleTransactionMapper() {
		super();
	}

	/**
	 * 
	 * @param saleTrx
	 * @return
	 */
	public static TransactionEntity mapEntity(SaleTransaction saleTrx) {
		TransactionEntity trxEntity = new TransactionEntity();
		trxEntity.setAmount(saleTrx.getAmount());
		trxEntity.setId(saleTrx.getId());
		trxEntity.setItemId(saleTrx.getItem().getId());
		trxEntity.setPaymentExternalReference(saleTrx.getPaymentExternalReference());
		trxEntity.setPaymentMethodId(saleTrx.getPaymentMethod().getId());
		trxEntity.setTransactionDate(saleTrx.getTransactionDate());
		trxEntity.setVendingMachineId(saleTrx.getVendingMachine().getId());
		return trxEntity;
		
	}

	public static SaleTransaction mapModelComplete(TransactionEntity trxEntity) {
		SaleTransaction saleTransaction = new SaleTransaction();
		saleTransaction.setAmount(trxEntity.getAmount());
		saleTransaction.setId(trxEntity.getId());
		saleTransaction.setItem(new Item());
		saleTransaction.getItem().setId(trxEntity.getItemId());
		saleTransaction.setPaymentExternalReference(saleTransaction.getPaymentExternalReference());
		saleTransaction.setPaymentMethod(new PaymentMethod());
		saleTransaction.getPaymentMethod().setId(trxEntity.getPaymentMethodId());
		saleTransaction.setTransactionDate(trxEntity.getTransactionDate());
		saleTransaction.setVendingMachine(new VendingMachine());
		saleTransaction.getVendingMachine().setId(trxEntity.getVendingMachineId());
		return saleTransaction;
	}

}
