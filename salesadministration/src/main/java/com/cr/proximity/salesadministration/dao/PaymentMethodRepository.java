package com.cr.proximity.salesadministration.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cr.proximity.salesadministration.dao.entities.PaymentMethodEntity;

@Repository
public interface PaymentMethodRepository extends CrudRepository<PaymentMethodEntity, Integer>{

}
