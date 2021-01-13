package com.cr.proximity.salesadministration.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cr.proximity.salesadministration.dao.entities.VendingMachineEntity;

@Repository
public interface VendingMachineRepository extends CrudRepository<VendingMachineEntity, Integer>{

}
