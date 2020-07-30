package com.java.domian;

public class Notebook implements Equipment{

	
	private String model;
	private Double price;
	
	public Notebook(String model, Double price) {
		super();
		this.model = model;
		this.price = price;
	}

	@Override
	public String getDescription() {
		return "("+getModel()+" "+getPrice()+")";
		}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
