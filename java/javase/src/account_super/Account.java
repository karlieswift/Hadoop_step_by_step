package account_super;
/*
 * 写一个名为Account的类模拟账户。该类的属性和方法如下图所示。
 * 该类包括的属性：账号id，余额balance，年利率annualInterestRate；
 * 包含的方法：访问器方法（getter和setter方法），
 * 返回月利率的方法getMonthlyInterest()，取款方法withdraw()，存款方法deposit()。
 */
public class Account {

	
	private int id;//账号
	private double balance=100;//余额
	private double annualInterestRate;//利率
	
	public Account(int id, double balance, double annualInterestRate) {
		super();
		this.id = id;
		this.balance = balance;
		this.annualInterestRate = annualInterestRate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	
	public double getMonthlyInterest() {
		return getAnnualInterestRate();
	}
	public void withdraw (double amount) {  //取钱
		if(amount>balance) {
			System.out.println("余额不足");
		}
		else {
			setBalance(getBalance()-amount);
		}
	}
	public void deposit (double amount) {
		setBalance(getBalance()+amount);
	}


}
