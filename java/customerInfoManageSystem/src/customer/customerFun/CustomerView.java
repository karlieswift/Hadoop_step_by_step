package customer.customerFun;

import java.util.Scanner;

/**
 * 
 * @Description: <Function> 进行对用户的信息进行添加,删除，修改，展示
 * @author  karlieswift
 * @date 2020年3月24日
 * @version "13.0.1"
 */
public class CustomerView {
	CustomerList c=new CustomerList(3);
	
	Scanner scan=new Scanner(System.in);
	/**
	 * 菜单输入
	 * @Function
	 */
	public void enterMainMenu() {
		System.out.println("-----------------客户信息管理软件-----------------");
		System.out.println("                1 添 加 客 户                     ");
		System.out.println("                2 修 改 客 户                     ");
		System.out.println("                3 删 除 客 户                     ");
		System.out.println("                4 客 户 列 表                     ");
		System.out.println("                5 退       出                 	 ");
		System.out.println("                请选择(1-5) : ");
		System.out.println("-----------------客户信息管理软件-----------------");
		
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
				System.out.println("程序退出");
				break;
			}
			default:
				System.out.println("无效的数字 !!   请再次输入！！");
			}
			
		}
		
	}
	/**
	 * 添加用户
	 * @Function
	 */
	private void addNewCustomer() {
		System.out.println("添加功能:");
		System.out.print("请输入你的名字:");
		String name =scan.next();
		System.out.print("请输入你的性别:");
		char gender =scan.next().charAt(0);
		System.out.print("请输入你的年龄:");
		int age=scan.nextInt();
		System.out.print("请输入你的电话:");
		String phone =scan.next();
		System.out.print("请输入你的邮箱:");
		String email =scan.next();
		Customer cust=new Customer(name, gender, age, phone, email);
		boolean issu=c.isaddCustomer(cust);
		if(issu) {
			System.out.println("添加成功");
		}
		else {
			System.out.println("添加失败，数据库已经达到最大范围！");
		}
		
	}
	/**
	 * 修改用户信息
	 * @Function
	 */
	private void modifyCustomer() {
		System.out.println("修改功能:");
		System.out.print("输入要修改的用户ID:");
		int index=scan.nextInt();
		if(index<1 || index>c.getRealTotal()) {
			System.out.println("无法找到ID:"+index);
			return ;
		}
		Customer cu=c.getCustomer(index);
		
		System.out.println("你当前的信息:"+index+" "+"姓名:"+cu.getName()+" 性别:"+cu.getGender()+" 年龄:"
				+cu.getAge()+" 电话:"+cu.getPhone()+" 邮箱:"+cu.getEmail());
	
		
		System.out.print("请输入你的名字:");
		String name =scan.next();
		cu.setName(name);
		
		System.out.print("请输入你的性别:");
		char gender =scan.next().charAt(0);
		cu.setGender(gender);
		
		System.out.print("请输入你的年龄:");
		int age=scan.nextInt();
		cu.setAge(age);
		
		System.out.print("请输入你的电话:");
		String phone =scan.next();
		cu.setPhone(phone);
		
		System.out.print("请输入你的邮箱:");
		String email =scan.next();
		cu.setEmail(email);
		System.out.println("修改成功");
		System.out.println("你当前的信息:"+index+" "+"姓名:"+cu.getName()+" 性别:"+cu.getGender()+" 年龄:"
				+cu.getAge()+" 电话:"+cu.getPhone()+" 邮箱:"+cu.getEmail());
	}
	/**
	 * 删除用户信息
	 * @Function
	 */
	private void deleteCustomer() {
		System.out.println("删除功能:");
		System.out.print("请输入你的ID:");
		int index=scan.nextInt();
		
		boolean issu=c.isdeleteCustomer(index);
		if(issu) {
			System.out.println("删除成功");
		}
		else {
			System.out.println("删除失败，无法找到ID:"+index);
		}
	}
	/**
	 * 遍历所有用户
	 * @Function
	 */
	private void listAllCustomers() {
		Customer[] cus=c.getAllCustomers();
		for(int i=0;i<cus.length;i++) {
			System.out.println("ID:"+(i+1)+" "+"姓名:"+cus[i].getName()+" 性别:"+cus[i].getGender()+" 年龄:"
					+cus[i].getAge()+" 电话:"+cus[i].getPhone()+" 邮箱:"+cus[i].getEmail());
		}
	}

}
