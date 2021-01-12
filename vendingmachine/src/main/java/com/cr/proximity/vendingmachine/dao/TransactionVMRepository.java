package com.cr.proximity.vendingmachine.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionVMRepository extends CrudRepository<TransactionVMEntity, Long>{

}
