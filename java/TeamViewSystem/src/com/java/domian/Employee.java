package com.java.domian;

/**
 * 
 * @Description: service模块为实体对象 （Employee及其子类如程序员等）的管理模块，
 *               CompanyService和TeamService类分别用各自的数组来管理公司员工和开发团队成员对象
 * @author karlieswift
 * @date 2020年3月29日
 * @version "13.0.1"
 */
public class Employee {
	private int id;
	private String name;
	private int age;
	private double salary;

	public Employee() {

	}

	public Employee(int id, String name, int age, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDetails() {
		return id + "\t" + name + "\t" + age + "\t" + salary;
		
	}
	@Override
	public String toString() {
		return getDetails();
		}
	
}
