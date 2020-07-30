package com.java.service;

import com.java.domian.Architect;
import com.java.domian.Designer;

/**
 * 
 * @Description: CompanyService和TeamService类分别用各自的数组来管理公司员工和开发团队成员对象
 * @author  karlieswift
 * @date 2020年3月29日
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
	 * employees用来保存所有公司员工对象
	 * 
	 */
	Employee[] employees;

	/**
	 * NameListService()构造器： 根据项目提供的Data类构建相应大小的employees数组
	 * 再根据Data类中的数据构建不同的对象,包括Employee、Programmer、Designer和Architect对象,以及相关联的Eqipment子类的对象
	 * 将对象存于数组中
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
			// 获取员工信息
			int type = Integer.parseInt(Data.EMPLOYEES[i][0]);
			// 获取员工的4个基本类型
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
 * 获取所有员工
 * @Function @return
 */
	public Employee[] getAllEmployees() {
		return employees;
	}
	/**
	 * 获取指定的员工
	 * @throws TeamException 
	 * @Function @return
	 */
	public Employee getEmployee(int index) throws TeamException {
		for(int i=0;i<employees.length;i++) {
			if(employees[i].getId()==index) {
				return employees[i];
			}
		}
		throw new TeamException("找不到指定员工");
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










