package com.java.domian;

public class Programmer extends Employee {

	/**
	 * memberid������¼��Ա���뿪���ŶӺ����Ŷ��е�ID
	 */
	private int memberid;

	/**
	 * status����Ŀ�Զ����ö�����ͣ���ʾ��Ա��״̬�� 
	 * FREE-���� BUSY-�Ѽ��뿪���Ŷ� VOCATION-�����ݼ�
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
	 * equipment ��ʾ�ó�Ա���õ��豸
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
		return getDetails()+ "\t����Ա\t" + status + "\t\t\t" + equipment.getDescription();
		
	}
	
	public String getDatailsTeam() {
		return memberid+"/"+getId() +"\t" + getName() +"\t"+getAge()+ "\t" +getSalary()+"\t����Ա\t";
	}
	
}








