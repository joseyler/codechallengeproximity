package com.cr.proximity.salesadministration.dao.entities.mappers;

import com.cr.proximity.salesadministration.dao.entities.ItemEntity;
import com.cr.proximity.salesadministration.model.Item;

public class ItemMapper {
	
	private ItemMapper() {
		super();
	}

	public static Item mapModel(ItemEntity itemEntity) {
		Item itemModel = new Item();
		itemModel.setCode(itemEntity.getCode());
		itemModel.setId(itemEntity.getId());
		itemModel.setName(itemEntity.getName());
		return itemModel;
	}

	public static ItemEntity mapEntity(Item itemModel) {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setCode(itemModel.getCode());
		itemEntity.setId(itemModel.getId());
		itemEntity.setName(itemModel.getName());
		return itemEntity;
	}
	
	

}
