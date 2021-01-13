package com.cr.proximity.vendingmachine.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cr.proximity.vendingmachine.dao.entities.TransactionVMEntity;

@Repository
public interface TransactionVMRepository extends CrudRepository<TransactionVMEntity, Long>{

	public List<TransactionVMEntity> findByRegistered(boolean b);
	
	
	
}
