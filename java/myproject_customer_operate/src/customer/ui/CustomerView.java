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

			System.out.println("-----------------�ͻ���Ϣ�������-----------------");
			System.out.println("                                                  ");
			System.out.println("                1 �� �� �� ��                     ");
			System.out.println("                2 �� �� �� ��                     ");
			System.out.println("                3 ɾ �� �� ��                     ");
			System.out.println("                4 �� �� �� ��                     ");
			System.out.println("                5 ��       ��                 	  ");
			System.out.println("                                                  ");
			System.out.print("                 ��ѡ��(1-5) : ");
			System.out.println("-----------------�ͻ���Ϣ�������-----------------");
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
		 * private String name;// �ͻ����� private char gender;// �Ա� private int age;// ����
		 * private String phone;// �绰���� private String email;// ��������
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
			System.out.println("��ӳɹ�");
		} else {
			System.out.println("���ʧ��");
		}

	}

	private void modifyCustomer() {
		System.out.println("����Ҫ�޸ĵ��û���index");
		Scanner scan = new Scanner(System.in);
		int index = scan.nextInt();
		Customer cust = custList.getCustomer(index);
		System.out.print("����Ҫ�޸ĵ��û�������");
		String name = scan.next();
		cust.setName(name);
		System.out.print("����Ҫ�޸ĵ��û����Ա�");
		char gender = scan.next().charAt(0);
		cust.setGender(gender);
		System.out.print("����Ҫ�޸ĵ��û�������");
		int age = scan.nextInt();
		cust.setAge(age);
		System.out.print("����Ҫ�޸ĵ��û��ĵ绰");
		String phone = scan.next();
		cust.setPhone(phone);
		System.out.print("����Ҫ�޸ĵ��û����ʼ�");
		String email = scan.next();
		cust.setEmail(email);

	}

	private void deleteCustomer() {
		System.out.println("����Ҫɾ�����û���index");
		Scanner scan = new Scanner(System.in);
		int index = scan.nextInt();
		//Customer cust = custList.getCustomer(index);
		boolean issu=custList.isdeleteCustomer(index);
		
		if (issu) {
			System.out.println("ɾ���ɹ�");
		} else {
			System.out.println("ɾ��ʧ��");
		}
	}

	private void listAllCustomers() {
		Customer[]cu=custList.getAllCustomers();
		for(int i=0;i<cu.length;i++) {
			System.out.println("index:"+i+1+" "+" ����:"+cu[i].getName()+"  �Ա�:"+cu[i].getGender()+" ����:"+cu[i].getAge()
					+" ����:"+cu[i].getPhone()+" �ʼ�:"+cu[i].getEmail());
		}
		
	}

}
