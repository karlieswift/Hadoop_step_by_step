package customer.service;

import customer.bean.Customer;
/**
 * 
 * CustomerList为Customer对象的管理模块，内部用数组管理一组Customer对象

 * @Description: <功能详细描述>
 * @author  karlieswift
 * @date 2020年3月24日
 */
/**
 * 
 * @Description: <功能详细描述>
 * @author  karlieswift
 * @date 2020年3月24日
 *@version "13.0.1"
 */
/**
 * 
 * @Description: <功能详细描述>
 * @author  karlieswift
 * @date 2020年3月24日
 * @version "13.0.1"
 */
public class CustomerList {
	Customer[] customers;//用来保存客户对象的数组
	int realCount= 0;//：记录已保存客户对象的数量

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
