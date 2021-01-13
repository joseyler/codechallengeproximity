package com.cr.proximity.salesadministration.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cr.proximity.salesadministration.dao.VendingMachineRepository;
import com.cr.proximity.salesadministration.dao.entities.VendingMachineEntity;
import com.cr.proximity.salesadministration.dao.entities.mappers.VendingMachineMapper;
import com.cr.proximity.salesadministration.exceptions.SalesAdministrarionException;
import com.cr.proximity.salesadministration.model.VendingMachine;

@Service
public class VendingMachineService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VendingMachineService.class);

	private VendingMachineRepository vendingMachineRepository;

	public VendingMachineService(VendingMachineRepository vendingMachineRepository) {
		this.vendingMachineRepository = vendingMachineRepository;
	}

	public VendingMachine saveVendingMachine(VendingMachine vendingMachine) throws SalesAdministrarionException {
		try {
			VendingMachineEntity vendingMachineEntity =VendingMachineMapper.mapEntity(vendingMachine);
			vendingMachineRepository.save(vendingMachineEntity);
			return vendingMachine;
		}  catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new SalesAdministrarionException("Error saving vendingMachine: " + e.getMessage());
		}
	}
	
	@Cacheable("vendingmachineid")
	public VendingMachine getVendingMachine(Integer id) throws SalesAdministrarionException {
		Optional<VendingMachineEntity> vendingMachineOp = vendingMachineRepository.findById(id);
		if (vendingMachineOp.isPresent()) {
			return VendingMachineMapper.mapModel(vendingMachineOp.get());
		}
		return null;
	}
	
	public void sendAlert(Integer machineId) throws SalesAdministrarionException{
		try {
			VendingMachine vmachine = getVendingMachine(machineId);
			LOGGER.info("ALERT. Need to collect cash on machine " + vmachine.getId() + " located at " + vmachine.getLocation());
		}  catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new SalesAdministrarionException("Error sending alert: " + e.getMessage());
		}
		
		
	}

}
