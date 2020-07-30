package com.java.domian;

public class Architect extends Designer {
	private int stock;

	public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
		super(id, name, age, salary, equipment, bonus);
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return getDetails() + "\t�ܹ�ʦ\t" + status + "\t" + getBonus() + "\t" + getStock() + "\t"
				+ getEquipment().getDescription();
	}

	public String getDatailsTeam() {
		return getMemberid() + "/" + getId() + "\t" + getName() + "\t" + getAge() + "\t" + getSalary() + "\t�ܹ�ʦ\t"
				+ getBonus() + "\t" + getStock();
	}
}
