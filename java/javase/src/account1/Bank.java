package account1;

public class Bank {

	private Customer[] customers;// 银行存取多个用户
	private int count_customer;// 记录用户的个数

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

	// 添加用户信息
	public void addCustomerInfo(String firstname, String lastname) {
		Customer cust = new Customer(firstname, lastname);
		customers[count_customer] = cust;
		count_customer++;
	}

	public Customer getCustomer(int index) { // 获取指定用户
		if (index >=0 && index < count_customer) {
			return customers[index];
		}
		return null;
	}
}
