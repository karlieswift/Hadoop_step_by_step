package customer.customerFun;

import java.util.Scanner;

/**
 * 
 * @Description: <Function> ���ж��û�����Ϣ�������,ɾ�����޸ģ�չʾ
 * @author  karlieswift
 * @date 2020��3��24��
 * @version "13.0.1"
 */
public class CustomerView {
	CustomerList c=new CustomerList(3);
	
	Scanner scan=new Scanner(System.in);
	/**
	 * �˵�����
	 * @Function
	 */
	public void enterMainMenu() {
		System.out.println("-----------------�ͻ���Ϣ�������-----------------");
		System.out.println("                1 �� �� �� ��                     ");
		System.out.println("                2 �� �� �� ��                     ");
		System.out.println("                3 ɾ �� �� ��                     ");
		System.out.println("                4 �� �� �� ��                     ");
		System.out.println("                5 ��       ��                 	 ");
		System.out.println("                ��ѡ��(1-5) : ");
		System.out.println("-----------------�ͻ���Ϣ�������-----------------");
		
		boolean flag=true;
		
		while(flag) {
			
			
			int key=scan.nextInt();
			switch (key) {
			case 1: {
				addNewCustomer();
				break;
			}
			case 2: {
				modifyCustomer();
				break;
			}
			case 3: {
				deleteCustomer();
				break;
			}
			case 4: {
				listAllCustomers();
				break;
			}
			case 5: {
				flag=false;
				System.out.println("�����˳�");
				break;
			}
			default:
				System.out.println("��Ч������ !!   ���ٴ����룡��");
			}
			
		}
		
	}
	/**
	 * ����û�
	 * @Function
	 */
	private void addNewCustomer() {
		System.out.println("��ӹ���:");
		System.out.print("�������������:");
		String name =scan.next();
		System.out.print("����������Ա�:");
		char gender =scan.next().charAt(0);
		System.out.print("�������������:");
		int age=scan.nextInt();
		System.out.print("��������ĵ绰:");
		String phone =scan.next();
		System.out.print("�������������:");
		String email =scan.next();
		Customer cust=new Customer(name, gender, age, phone, email);
		boolean issu=c.isaddCustomer(cust);
		if(issu) {
			System.out.println("��ӳɹ�");
		}
		else {
			System.out.println("���ʧ�ܣ����ݿ��Ѿ��ﵽ���Χ��");
		}
		
	}
	/**
	 * �޸��û���Ϣ
	 * @Function
	 */
	private void modifyCustomer() {
		System.out.println("�޸Ĺ���:");
		System.out.print("����Ҫ�޸ĵ��û�ID:");
		int index=scan.nextInt();
		if(index<1 || index>c.getRealTotal()) {
			System.out.println("�޷��ҵ�ID:"+index);
			return ;
		}
		Customer cu=c.getCustomer(index);
		
		System.out.println("�㵱ǰ����Ϣ:"+index+" "+"����:"+cu.getName()+" �Ա�:"+cu.getGender()+" ����:"
				+cu.getAge()+" �绰:"+cu.getPhone()+" ����:"+cu.getEmail());
	
		
		System.out.print("�������������:");
		String name =scan.next();
		cu.setName(name);
		
		System.out.print("����������Ա�:");
		char gender =scan.next().charAt(0);
		cu.setGender(gender);
		
		System.out.print("�������������:");
		int age=scan.nextInt();
		cu.setAge(age);
		
		System.out.print("��������ĵ绰:");
		String phone =scan.next();
		cu.setPhone(phone);
		
		System.out.print("�������������:");
		String email =scan.next();
		cu.setEmail(email);
		System.out.println("�޸ĳɹ�");
		System.out.println("�㵱ǰ����Ϣ:"+index+" "+"����:"+cu.getName()+" �Ա�:"+cu.getGender()+" ����:"
				+cu.getAge()+" �绰:"+cu.getPhone()+" ����:"+cu.getEmail());
	}
	/**
	 * ɾ���û���Ϣ
	 * @Function
	 */
	private void deleteCustomer() {
		System.out.println("ɾ������:");
		System.out.print("���������ID:");
		int index=scan.nextInt();
		
		boolean issu=c.isdeleteCustomer(index);
		if(issu) {
			System.out.println("ɾ���ɹ�");
		}
		else {
			System.out.println("ɾ��ʧ�ܣ��޷��ҵ�ID:"+index);
		}
	}
	/**
	 * ���������û�
	 * @Function
	 */
	private void listAllCustomers() {
		Customer[] cus=c.getAllCustomers();
		for(int i=0;i<cus.length;i++) {
			System.out.println("ID:"+(i+1)+" "+"����:"+cus[i].getName()+" �Ա�:"+cus[i].getGender()+" ����:"
					+cus[i].getAge()+" �绰:"+cus[i].getPhone()+" ����:"+cus[i].getEmail());
		}
	}

}
