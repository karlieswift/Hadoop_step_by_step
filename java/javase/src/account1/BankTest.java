package account1;

import java.util.Scanner;

public class BankTest {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Bank bank = new Bank(10);//������СΪ10������
		bank.addCustomerInfo("taylor", "swift");// ������һ���û����ڴ����û�����Ϣ
		
		bank.getCustomer(0).setAccount(new Account(1001, 10000, 1.2));
		bank.addCustomerInfo("karlie", "kloss");// �����ڶ����û����ڴ����û�����Ϣ
		bank.getCustomer(1).setAccount(new Account(1002, 66660, 1.2));
		
		System.out.println("��ǰ���й���"+bank.getCount_customer()+"���û�:");
		for(int i=0;i<bank.getCount_customer();i++) {
			bank.getCustomer(i).showInfo();
		}
		System.out.println("-----------------------------------");
		Customer cust=bank.getCustomer(0);
		String name=cust.getFirstName()+" "+cust.getLastName();
		System.out.print(name+":����ȡ���");
		bank.getCustomer(0).getAccount().withdraw(scan.nextDouble());// ȡ100
		bank.getCustomer(0).showInfo();
		System.out.print(name+":�������");
		bank.getCustomer(0).getAccount().deposit(scan.nextDouble());// ��100
		bank.getCustomer(0).showInfo();

	}
}
