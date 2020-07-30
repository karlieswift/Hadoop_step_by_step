package account1;

public class Customer {
	private String firstName;
	private String lastName;
	private Account account;
	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void showInfo() {
		System.out.println(this.getFirstName()+" "+this.getLastName()+"的账号:"
	    +this.account.getId()+" 余额为:"+this.account.getBalance()+" 当前利率为:"
        +this.account.getAnnualInterestRate());
	}
}
