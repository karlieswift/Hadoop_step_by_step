package customer.customerFun;
/**
 * 
 * @Description: <Function> 
 * CustomerList ����һ��Customer��������б������û�
 * 
 * @author  karlieswift
 * @date 2020��3��24��
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
	 * ����totalCount����������Ĵ�С
	 */
	public CustomerList(int totalCount) {
		customers=new Customer[totalCount];
	}
	
	/**
	 * �ж��Ƿ���ӳɹ�
	 * �ɹ�����true,���򷵻�false
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
	 * �ж��Ƿ�ɾ���ɹ�
	 * �ɹ�����true,���򷵻�false
	 */
	public boolean isdeleteCustomer(int index) {
		if(index<1 || index>realTotal) {
			return false;
		}
		for(int i=index-1;i<realTotal-1;i++) {  //ǰһ������һ������
			customers[i]=customers[i+1];
		}
		customers[realTotal-1]=null; 
		realTotal--;
		return true;
	}
	public Customer[] getAllCustomers() {
		//��ʵ�����Ͽ���ֱ�ӷ���customers[i],�����ǵ�customers��realTotal��length��ͬ
		//�������ڴ���һ��customers����,realTotal��length��ͬ����Ϊtotalreal��˽�е��ⲿ�಻�ܷ���
		//�����getrealtoatalҲ����
		//����ֱ��ͨ��length�ĳ���ֱ��forѭ�������е�customer����Ϣ
		Customer []cu=new Customer[realTotal];
		for(int i=0;i<realTotal;i++) {
			cu[i]=customers[i];
		}
		return cu;
	}
	

	/**
	 * ��ȡ���� idΪindex���û�
	 * index��1��ʼ
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
