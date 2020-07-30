package com.java.service;

import com.java.domian.Architect;
import com.java.domian.Designer;

/**
 * 
 * @Description: CompanyService��TeamService��ֱ��ø��Ե�����������˾Ա���Ϳ����Ŷӳ�Ա����
 * @author  karlieswift
 * @date 2020��3��29��
 * @version "13.0.1"
 */

import com.java.domian.Employee;
import com.java.domian.Equipment;
import com.java.domian.Notebook;
import com.java.domian.PC;
import com.java.domian.Printer;
import com.java.domian.Programmer;

public class NameListService {
	/**
	 * employees�����������й�˾Ա������
	 * 
	 */
	Employee[] employees;

	/**
	 * NameListService()�������� ������Ŀ�ṩ��Data�๹����Ӧ��С��employees����
	 * �ٸ���Data���е����ݹ�����ͬ�Ķ���,����Employee��Programmer��Designer��Architect����,�Լ��������Eqipment����Ķ���
	 * ���������������
	 * 
	 * @param employees
	 */
	public NameListService() {
		employees = new Employee[Data.EMPLOYEES.length];
		//Employee  :  10, id, name, age, salary
	    //Programmer:  11, id, name, age, salary
	    //Designer  :  12, id, name, age, salary, bonus
	    //Architect :  13, id, name, age, salary, bonus, stock
		for (int i = 0; i < employees.length; i++) {
			// ��ȡԱ����Ϣ
			int type = Integer.parseInt(Data.EMPLOYEES[i][0]);
			// ��ȡԱ����4����������
			int id = Integer.parseInt(Data.EMPLOYEES[i][1]);
			String name = Data.EMPLOYEES[i][2];
			int age = Integer.parseInt(Data.EMPLOYEES[i][3]);
			double salary = Double.parseDouble(Data.EMPLOYEES[i][4]);
			double bonus;
			int stock;
			switch (type) {
			case Data.EMPLOYEE: {
				employees[i] = new Employee(id, name, age, salary);
				break;
			}
			case Data.PROGRAMMER: {
				Equipment equipment = creatEquipment(i);
				employees[i] = new Programmer(id, name, age, salary, equipment);
				break;
			}
			case Data.DESIGNER: {
				Equipment equipment = creatEquipment(i);
				bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
				employees[i] = new Designer(id, name, age, salary, equipment, bonus);
				break;
			}
			case Data.ARCHITECT: {
				Equipment equipment = creatEquipment(i);
				bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
				stock = Integer.parseInt(Data.EMPLOYEES[i][6]);
				employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
				break;
			}
			}
		}
	}
/**
 * ��ȡ����Ա��
 * @Function @return
 */
	public Employee[] getAllEmployees() {
		return employees;
	}
	/**
	 * ��ȡָ����Ա��
	 * @throws TeamException 
	 * @Function @return
	 */
	public Employee getEmployee(int index) throws TeamException {
		for(int i=0;i<employees.length;i++) {
			if(employees[i].getId()==index) {
				return employees[i];
			}
		}
		throw new TeamException("�Ҳ���ָ��Ա��");
	}

	private Equipment creatEquipment(int index) {
		String model=Data.EQUIPMENTS[index][1];
		int key = Integer.parseInt(Data.EQUIPMENTS[index][0]);
		switch (key) {
		case Data.PC: {
			return new PC(model, Data.EQUIPMENTS[index][2]);
		}
		case Data.NOTEBOOK: {
			double price = Integer.parseInt(Data.EQUIPMENTS[index][2]);
			return new Notebook(model, price);
		}
		case Data.PRINTER: {
			String type = Data.EQUIPMENTS[index][2];
			return new Printer(model, type);
		}
		}
		return null;
	}
}










