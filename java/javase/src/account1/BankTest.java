package account1;

import java.util.Scanner;

public class BankTest {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Bank bank = new Bank(10);//创建大小为10的银行
		bank.addCustomerInfo("taylor", "swift");// 先声明一个用户，在创建用户的信息
		
		bank.getCustomer(0).setAccount(new Account(1001, 10000, 1.2));
		bank.addCustomerInfo("karlie", "kloss");// 声明第二个用户，在创建用户的信息
		bank.getCustomer(1).setAccount(new Account(1002, 66660, 1.2));
		
		System.out.println("当前银行共有"+bank.getCount_customer()+"个用户:");
		for(int i=0;i<bank.getCount_customer();i++) {
			bank.getCustomer(i).showInfo();
		}
		System.out.println("-----------------------------------");
		Customer cust=bank.getCustomer(0);
		String name=cust.getFirstName()+" "+cust.getLastName();
		System.out.print(name+":输入取款金额：");
		bank.getCustomer(0).getAccount().withdraw(scan.nextDouble());// 取100
		bank.getCustomer(0).showInfo();
		System.out.print(name+":输入存款金额：");
		bank.getCustomer(0).getAccount().deposit(scan.nextDouble());// 存100
		bank.getCustomer(0).showInfo();

	}
}
