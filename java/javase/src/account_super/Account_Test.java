package account_super;

public class Account_Test {
public static void main(String[] args) {
	CheckAccount ca=new CheckAccount(1, 1000, 1, 6000);
	ca.withdraw(800);   
	System.out.println("��"+ca.getBalance()+"  ��͸֧��"+ca.getOverdraft());
	ca.deposit(100);
	System.out.println("��"+ca.getBalance()+"  ��͸֧��"+ca.getOverdraft());
	ca.withdraw(300);
	System.out.println("��"+ca.getBalance()+"  ��͸֧��"+ca.getOverdraft());
	ca.withdraw(5000);
	System.out.println("��"+ca.getBalance()+"  ��͸֧��"+ca.getOverdraft());
	ca.withdraw(1000);
	System.out.println("��"+ca.getBalance()+"  ��͸֧��"+ca.getOverdraft());
	ca.withdraw(1000);
	System.out.println("��"+ca.getBalance()+"  ��͸֧��"+ca.getOverdraft());
}
}
