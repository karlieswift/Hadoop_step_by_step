package account;

import java.util.Scanner;

public class UserTest {
	public static void main(String[] args) {
		// ����customer
		Scanner scan = new Scanner(System.in);
		Customer c1 = new Customer("taylor", "swift");
		Account account = new Account(1001, 1000, 1.2);

		c1.setAccount(account);
		c1.showInfo();
		System.out.print("����ȡ���");
		account.withdraw(scan.nextDouble());// ȡ100
		c1.showInfo();
		System.out.print("�������");
		account.deposit(scan.nextDouble());// ��100
		c1.showInfo();

	}

}
