package com.cr.proximity.salesadministration.dao.entities.mappers;

import com.cr.proximity.salesadministration.dao.entities.VendingMachineEntity;
import com.cr.proximity.salesadministration.model.VendingMachine;

public class VendingMachineMapper {

	private VendingMachineMapper() {
		super();
	}
	
	public static VendingMachine mapModel(VendingMachineEntity vendingMachineEntity) {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.setId(vendingMachineEntity.getId());
		vendingMachine.setLocation(vendingMachineEntity.getLocation());
		vendingMachine.setModel(vendingMachineEntity.getModel());
		return vendingMachine;
	}

	public static VendingMachineEntity mapEntity(VendingMachine vendingMachine) {
		VendingMachineEntity vendingMachineEnt = new VendingMachineEntity();
		vendingMachineEnt.setId(vendingMachine.getId());
		vendingMachineEnt.setLocation(vendingMachine.getLocation());
		vendingMachineEnt.setModel(vendingMachine.getModel());
		return vendingMachineEnt;
	}

}
