package com.cr.proximity.salesadministration.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cr.proximity.salesadministration.dao.ItemRepository;
import com.cr.proximity.salesadministration.dao.entities.ItemEntity;
import com.cr.proximity.salesadministration.dao.entities.PaymentMethodEntity;
import com.cr.proximity.salesadministration.dao.entities.mappers.ItemMapper;
import com.cr.proximity.salesadministration.dao.entities.mappers.PaymentMethodMapper;
import com.cr.proximity.salesadministration.exceptions.SalesAdministrarionException;
import com.cr.proximity.salesadministration.model.Item;
import com.cr.proximity.salesadministration.model.PaymentMethod;

@Service
public class ItemsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemsService.class);

	private ItemRepository itemRepository;

	public ItemsService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public Item saveItem(Item item) throws SalesAdministrarionException {
		try {
			ItemEntity saved = itemRepository.save(ItemMapper.mapEntity(item));
			item.setId(saved.getId());
			return item;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new SalesAdministrarionException("Error saving Item: " + e.getMessage());
		}
	}
	
	@Cacheable("itemid")
	public Item getItem(Integer id)  {
		Optional<ItemEntity> itemOp = itemRepository.findById(id);
		if (itemOp.isPresent()) {
			return ItemMapper.mapModel(itemOp.get());
		}
		return null;
	}

}
