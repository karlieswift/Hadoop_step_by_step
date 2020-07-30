package account1;

public class Bank {

	private Customer[] customers;// ���д�ȡ����û�
	private int count_customer;// ��¼�û��ĸ���

	public Customer[] getCustomers() {
		return customers;
	}

	public Bank(int len) {
		customers=new Customer[len];
	}
	public int getCount_customer() {
		return count_customer;
	}

	public void setCount_customer(int count_customer) {
		this.count_customer = count_customer;
	}

	// ����û���Ϣ
	public void addCustomerInfo(String firstname, String lastname) {
		Customer cust = new Customer(firstname, lastname);
		customers[count_customer] = cust;
		count_customer++;
	}

	public Customer getCustomer(int index) { // ��ȡָ���û�
		if (index >=0 && index < count_customer) {
			return customers[index];
		}
		return null;
	}
}
