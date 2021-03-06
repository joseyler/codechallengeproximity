package com.cr.proximity.vendingmachine.state;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cr.proximity.vendingmachine.model.CreditCardInfo;
import com.cr.proximity.vendingmachine.model.Item;
import com.cr.proximity.vendingmachine.model.ItemStock;
import com.cr.proximity.vendingmachine.model.ItemTransaction;
import com.cr.proximity.vendingmachine.model.State;
import com.cr.proximity.vendingmachine.model.transaction.PaymentMethod;

@Component
public class MachineState {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MachineState.class);
	
	private List<ItemStock> itemsStock;
	private State currentState;
	private Double currentCash;
	private Map<PaymentMethod,Integer> cashStock;
	private ItemTransaction currentTransaccion;
	private CreditCardInfo creditCardInfo;

	private Double initialCash;
	
	
	
	
	public MachineState() {
		super();
		this.itemsStock = new ArrayList<ItemStock>();
		this.currentCash = 0D;
		this.currentState = State.READY;
		this.cashStock = new EnumMap<PaymentMethod, Integer>(PaymentMethod.class);
	}
	/**
	 * @return the itemsStock
	 */
	public List<ItemStock> getItemsStock() {
		return itemsStock;
	}
	/**
	 * @param itemsStock the itemsStock to set
	 */
	public void setItemsStock(List<ItemStock> itemsStock) {
		this.itemsStock = itemsStock;
	}
	/**
	 * @return the currentState
	 */
	public State getCurrentState() {
		return currentState;
	}
	/**
	 * @param currentState the currentState to set
	 */
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	/**
	 * @return the currentCash
	 */
	public Double getCurrentCash() {
		return currentCash;
	}
	/**
	 * @param currentCash the currentCash to set
	 */
	public void setCurrentCash(Double currentCash) {
		this.currentCash = currentCash;
	}
	/**
	 * @return the cashStock
	 */
	public Map<PaymentMethod, Integer> getCashStock() {
		return cashStock;
	}
	/**
	 * @param cashStock the cashStock to set
	 */
	public void setCashStock(Map<PaymentMethod, Integer> cashStock) {
		this.cashStock = cashStock;
	}
	
	/**
	 * Check for all cashStock in order to count money, and updates currentCash attr
	 * @return
	 */
	public Double countCurrentCash() {
		double totalAmount = 0;
		for (Entry<PaymentMethod, Integer> entryCash : this.cashStock.entrySet()) {
			totalAmount += entryCash.getKey().getAmount()* entryCash.getValue();                              
		}
		this.currentCash = totalAmount;
		return totalAmount;
	}
	
	public void printMachineState() {
		LOGGER.info("Current Machine State:");
		LOGGER.info("STOCK");
		for (ItemStock itStk:this.itemsStock) {
			LOGGER.info(itStk.getQuantity().toString() + " units of " + itStk.getItem().getName());
		}
		LOGGER.info("");
		LOGGER.info("TOTAL CASH: " + this.countCurrentCash());
	}
	/**
	 * @return the currentTransaccion
	 */
	public ItemTransaction getCurrentTransaccion() {
		return currentTransaccion;
	}
	/**
	 * @param currentTransaccion the currentTransaccion to set
	 */
	public void setCurrentTransaccion(ItemTransaction currentTransaccion) {
		this.currentTransaccion = currentTransaccion;
	}
	
	/**
	 * 
	 * @return
	 */
	public CreditCardInfo getCreditCardInfo() {
		return this.creditCardInfo;
	}
	/**
	 * @param creditCardInfo the creditCardInfo to set
	 */
	public void setCreditCardInfo(CreditCardInfo creditCardInfo) {
		this.creditCardInfo = creditCardInfo;
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public ItemStock getItemStock(Integer code) {
		for (ItemStock itemStock : itemsStock) {
			if (itemStock.getItem().getCode().equals(code)) {
				return itemStock;
			}
		}
		return null;
	}
	
	
	public void initializeMachine() {
		
		this.itemsStock = new ArrayList<ItemStock>();
		this.currentCash = 0D;
		this.currentState = State.READY;
		this.cashStock = new EnumMap<PaymentMethod, Integer>(PaymentMethod.class);
		
		
		this.getCashStock().put(PaymentMethod.CENTS_5,50);
		this.getCashStock().put(PaymentMethod.CENTS_10,50);
		this.getCashStock().put(PaymentMethod.CENTS_25,50);
		this.getCashStock().put(PaymentMethod.CENTS_50,50);
		this.getCashStock().put(PaymentMethod.DOLLAR_1,50);
		this.getCashStock().put(PaymentMethod.DOLLAR_2,50);
		
		this.setCurrentCash(this.countCurrentCash());
		
		Item it = new Item();
		it.setCode(4558);
		it.setName("Coke");
		it.setUnitPrice(1.2);
		it.setId(1);
		ItemStock itStk = new ItemStock();
		itStk.setItem(it);
		itStk.setQuantity(20);
		this.getItemsStock().add(itStk);
		
		it = new Item();
		it.setCode(4332);
		it.setName("Diet-Coke");
		it.setUnitPrice(1.0);
		it.setId(2);
		itStk = new ItemStock();
		itStk.setItem(it);
		itStk.setQuantity(20);
		this.getItemsStock().add(itStk);
		
		it = new Item();
		it.setCode(7555);
		it.setName("Sprite");
		it.setUnitPrice(0.8);
		it.setId(3);
		itStk = new ItemStock();
		itStk.setItem(it);
		itStk.setQuantity(20);
		this.getItemsStock().add(itStk);
		
		this.initialCash = this.countCurrentCash();
		
		this.printMachineState();
	}
	
	/**
	 * 
	 * @param item
	 */
	public void removeStock(Item item) {
		ItemStock itemStock = getItemStock(item.getCode());
		itemStock.setQuantity(itemStock.getQuantity()-1);
	}
	/**
	 * @return the initialCash
	 */
	public Double getInitialCash() {
		return initialCash;
	}
	/**
	 * @param initialCash the initialCash to set
	 */
	public void setInitialCash(Double initialCash) {
		this.initialCash = initialCash;
	}
	
}
