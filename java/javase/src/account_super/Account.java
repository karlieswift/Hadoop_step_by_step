package account_super;
/*
 * дһ����ΪAccount����ģ���˻�����������Ժͷ�������ͼ��ʾ��
 * ������������ԣ��˺�id�����balance��������annualInterestRate��
 * �����ķ�����������������getter��setter��������
 * ���������ʵķ���getMonthlyInterest()��ȡ���withdraw()������deposit()��
 */
public class Account {

	
	private int id;//�˺�
	private double balance=100;//���
	private double annualInterestRate;//����
	
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
	public void withdraw (double amount) {  //ȡǮ
		if(amount>balance) {
			System.out.println("����");
		}
		else {
			setBalance(getBalance()-amount);
		}
	}
	public void deposit (double amount) {
		setBalance(getBalance()+amount);
	}


}
