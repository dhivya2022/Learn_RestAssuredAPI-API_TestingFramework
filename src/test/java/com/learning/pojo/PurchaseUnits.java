package com.learning.pojo;

public class PurchaseUnits {
	
	
	private Amount amount;
	
	
	public PurchaseUnits(String currency_code,String value) {
		
		this.amount = new Amount(currency_code,value);
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}
	
	
	

}
