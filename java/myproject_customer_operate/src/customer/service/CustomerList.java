package customer.service;

import customer.bean.Customer;
/**
 * 
 * CustomerListΪCustomer����Ĺ���ģ�飬�ڲ����������һ��Customer����

 * @Description: <������ϸ����>
 * @author  karlieswift
 * @date 2020��3��24��
 */
/**
 * 
 * @Description: <������ϸ����>
 * @author  karlieswift
 * @date 2020��3��24��
 *@version "13.0.1"
 */
/**
 * 
 * @Description: <������ϸ����>
 * @author  karlieswift
 * @date 2020��3��24��
 * @version "13.0.1"
 */
public class CustomerList {
	Customer[] customers;//��������ͻ����������
	int realCount= 0;//����¼�ѱ���ͻ����������

	public CustomerList(int totalCount) {
		customers=new Customer[10];
	}
	public boolean addCustomer(Customer customer) {
		if(customers.length<realCount) {
			return false;
		}
		else {
			customers[realCount]=customer;
			realCount++;
			return true;
		}
		
	}
	public boolean isdeleteCustomer(int index) {
		if(index<=0 && index>realCount) {
			return false;
		}else {
			Customer cust=customers[index-1];
			for(int i=index-1;i<realCount-1;i++) {
				customers[i]=customers[i+1];
			}
			customers[realCount]=null;
			realCount--;
			return true;
		}
		
	}
	public Customer[] getAllCustomers() {
		 Customer[] cu=new Customer[realCount];
		 for(int i=0;i<realCount;i++) {
			 cu[i]=customers[i];
		 }
		 return cu;
		
	}
	public Customer getCustomer(int index) 
	{
		if(index<0 && index>realCount) {
			return null;
		}else {
			return customers[index-1];
		}
	}
}
