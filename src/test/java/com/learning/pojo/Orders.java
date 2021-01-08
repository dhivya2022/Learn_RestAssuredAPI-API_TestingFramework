package com.learning.pojo;

import java.util.ArrayList;

public class Orders {
	
	
	private String intent;
	private ArrayList<PurchaseUnits> purchase_units;
	
	public Orders(String intent,ArrayList<PurchaseUnits> purchase_units) {
		
		
		this.intent = intent;
		this.purchase_units = purchase_units;
	}
	
	
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}


	public ArrayList<PurchaseUnits> getPurchase_units() {
		return purchase_units;
	}


	public void setPurchase_units(ArrayList<PurchaseUnits> purchase_units) {
		this.purchase_units = purchase_units;
	}
	
}
