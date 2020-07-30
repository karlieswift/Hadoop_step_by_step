package com.java.domian;

public class Programmer extends Employee {

	/**
	 * memberid用来记录成员加入开发团队后在团队中的ID
	 */
	private int memberid;

	/**
	 * status是项目自定义的枚举类型，表示成员的状态： 
	 * FREE-空闲 BUSY-已加入开发团队 VOCATION-正在休假
	 * 
	 */
	Status status=Status.FREE;
	
	
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * equipment 表示该成员领用的设备
	 */
	private Equipment equipment;
	
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	public Programmer(){
		
	}
	public Programmer(int id, String name, int age, double salary,Equipment equipment) {
		super(id,name, age, salary);
		this.equipment = equipment;

	}

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	@Override
	public String toString() {
		return getDetails()+ "\t程序员\t" + status + "\t\t\t" + equipment.getDescription();
		
	}
	
	public String getDatailsTeam() {
		return memberid+"/"+getId() +"\t" + getName() +"\t"+getAge()+ "\t" +getSalary()+"\t程序员\t";
	}
	
}








