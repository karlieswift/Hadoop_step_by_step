package customer.ui;

import java.util.Scanner;


import customer.bean.Customer;
import customer.service.CustomerList;
import customer.uitl.CMUtility;

public class CustomerView {
	CustomerList custList = new CustomerList(10);

	public void enterMainMenu() {
		boolean flag = true;
		Scanner scan = new Scanner(System.in);
		while (flag) {

			System.out.println("-----------------客户信息管理软件-----------------");
			System.out.println("                                                  ");
			System.out.println("                1 添 加 客 户                     ");
			System.out.println("                2 修 改 客 户                     ");
			System.out.println("                3 删 除 客 户                     ");
			System.out.println("                4 客 户 列 表                     ");
			System.out.println("                5 退       出                 	  ");
			System.out.println("                                                  ");
			System.out.print("                 请选择(1-5) : ");
			System.out.println("-----------------客户信息管理软件-----------------");
			char c = CMUtility.readMenuSelection();
			switch (c) {
			case '1': {

				addNewCustomer();
				break;
			}
			case '2': {

				modifyCustomer();
				break;
			}

			case '3': {

				deleteCustomer();
				break;
			}
			case '4': {

				listAllCustomers();
				break;
			}
			case '5': {
				flag = false;
				break;
			}
			}
		}
	}

	private void addNewCustomer() {
		/**
		 * private String name;// 客户姓名 private char gender;// 性别 private int age;// 年龄
		 * private String phone;// 电话号码 private String email;// 电子邮箱
		 */
		Scanner scan = new Scanner(System.in);
		String name = scan.next();
		char gender = scan.next().charAt(0);
		int age = scan.nextInt();
		String phone = scan.next();
		String email = scan.next();

		Customer cust = new Customer(name, gender, age, phone, email);
		boolean issu = custList.addCustomer(cust);
		if (issu) {
			System.out.println("添加成功");
		} else {
			System.out.println("添加失败");
		}

	}

	private void modifyCustomer() {
		System.out.println("输入要修改的用户的index");
		Scanner scan = new Scanner(System.in);
		int index = scan.nextInt();
		Customer cust = custList.getCustomer(index);
		System.out.print("输入要修改的用户的姓名");
		String name = scan.next();
		cust.setName(name);
		System.out.print("输入要修改的用户的性别");
		char gender = scan.next().charAt(0);
		cust.setGender(gender);
		System.out.print("输入要修改的用户的年龄");
		int age = scan.nextInt();
		cust.setAge(age);
		System.out.print("输入要修改的用户的电话");
		String phone = scan.next();
		cust.setPhone(phone);
		System.out.print("输入要修改的用户的邮件");
		String email = scan.next();
		cust.setEmail(email);

	}

	private void deleteCustomer() {
		System.out.println("输入要删除的用户的index");
		Scanner scan = new Scanner(System.in);
		int index = scan.nextInt();
		//Customer cust = custList.getCustomer(index);
		boolean issu=custList.isdeleteCustomer(index);
		
		if (issu) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
	}

	private void listAllCustomers() {
		Customer[]cu=custList.getAllCustomers();
		for(int i=0;i<cu.length;i++) {
			System.out.println("index:"+i+1+" "+" 名字:"+cu[i].getName()+"  性别:"+cu[i].getGender()+" 年龄:"+cu[i].getAge()
					+" 号码:"+cu[i].getPhone()+" 邮件:"+cu[i].getEmail());
		}
		
	}

}
