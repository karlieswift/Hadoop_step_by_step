package account_super;

public class CheckAccount extends Account{

	
	private double overdraft=2000;
	
	public double getOverdraft() {
		return overdraft;
	}

	public CheckAccount(int id, double balance, double annualInterestRate,double overdraft) {
		super(id, balance, annualInterestRate);
		this.overdraft=overdraft;

	}

	public void withdraw (double amount) {  //ȡǮ
		if(amount<getBalance()) {
			 super.withdraw(amount);
		}
		else if(overdraft>=(amount-getBalance())){
			overdraft=overdraft-(amount-getBalance());
			setBalance(0);
		}
		else {
			System.out.println("����͸֧");
		}
	}
	public void deposit (double amount) {
		super.deposit(amount);
	}

	
	
}
