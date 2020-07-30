package com.java.domian;
/**
 * 
 * @Description: <Function>
 * @author  karlieswift
 * @date 2020��3��29��
 * @version "13.0.1"
 */
public class PC implements Equipment {

	private String model;//model��ʾ�������ͺ�

	private String display;// display��ʾ��ʾ������
	
	
	public PC(String model, String display) {
		super();
		this.model = model;
		this.display = display;
	}
	@Override
	public String getDescription() {
		return "("+getModel()+" "+getDisplay()+")";
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}

	
	
}
