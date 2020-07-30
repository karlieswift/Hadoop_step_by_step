package account;

import java.util.Scanner;

public class UserTest {
	public static void main(String[] args) {
		// 创建customer
		Scanner scan = new Scanner(System.in);
		Customer c1 = new Customer("taylor", "swift");
		Account account = new Account(1001, 1000, 1.2);

		c1.setAccount(account);
		c1.showInfo();
		System.out.print("输入取款金额：");
		account.withdraw(scan.nextDouble());// 取100
		c1.showInfo();
		System.out.print("输入存款金额：");
		account.deposit(scan.nextDouble());// 存100
		c1.showInfo();

	}

}
