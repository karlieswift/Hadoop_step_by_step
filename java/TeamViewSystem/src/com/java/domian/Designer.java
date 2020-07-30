package com.java.domian;

public class Designer extends Programmer {
	private double bonus;

	public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
		super(id, name, age, salary, equipment);
		this.bonus = bonus;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return getDetails() + "\t���ʦ\t" + status + "\t" + bonus + "\t\t" + getEquipment().getDescription();
	}

	public String getDatailsTeam() {
		return getMemberid() + "/" + getId() + "\t" + getName() + "\t" + getAge() + "\t" + getSalary() + "\t���ʦ\t"
				+ getBonus();
	}
}
