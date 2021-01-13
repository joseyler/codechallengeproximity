package com.cr.proximity.vendingmachine.model;

public enum State {

	READY(1), DISPENSE_CHANGE(2), DISPENSE_ITEM(3), TRANSACTION_CANCELLED(4);

	private int state;

	private State(int state) {
		this.state = state;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}
	
	

}
