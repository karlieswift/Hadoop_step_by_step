package customer.customerFun;
/**
 * 
 * @Description: <Function> 
 * CustomerList 创建一个Customer的数组进行保存多个用户
 * 
 * @author  karlieswift
 * @date 2020年3月24日
 * @version "13.0.1"
 */
public class CustomerList {
	
	private Customer []customers;
	private int realTotal=0;
	
	public int getRealTotal() {
		return realTotal;
	}
	/**
	 * 
	 * @param totalCount
	 * 传入totalCount来创建数组的大小
	 */
	public CustomerList(int totalCount) {
		customers=new Customer[totalCount];
	}
	
	/**
	 * 判断是否添加成功
	 * 成功返回true,否则返回false
	 */
	public boolean isaddCustomer(Customer customer) {
		if(customers.length<realTotal+1) {
			return false;
		}
		customers[realTotal]=customer;
		realTotal++;
		return true;
	}
	
	/**
	 * 判断是否删除成功
	 * 成功返回true,否则返回false
	 */
	public boolean isdeleteCustomer(int index) {
		if(index<1 || index>realTotal) {
			return false;
		}
		for(int i=index-1;i<realTotal-1;i++) {  //前一个被后一个覆盖
			customers[i]=customers[i+1];
		}
		customers[realTotal-1]=null; 
		realTotal--;
		return true;
	}
	public Customer[] getAllCustomers() {
		//其实理论上可以直接返回customers[i],但考虑到customers的realTotal与length不同
		//，所以在创建一个customers数组,realTotal与length相同，因为totalreal是私有的外部类不能访问
		//亦可以getrealtoatal也可以
		//后面直接通过length的长度直接for循环出所有的customer的信息
		Customer []cu=new Customer[realTotal];
		for(int i=0;i<realTotal;i++) {
			cu[i]=customers[i];
		}
		return cu;
	}
	

	/**
	 * 获取索引 id为index的用户
	 * index从1开始
	 * @Function @param index
	 * @Function @return
	 */
	public Customer getCustomer(int index) {
		if(index<1 || index>realTotal) {
			return null;
		}
		else {
			return customers[index-1];
		}
	}

}
