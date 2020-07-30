package customer.customerFun;

/**
 * 
 * @Description: <Function> 功能描述： 定义 CustomerView为主模块， 负责菜单的显示和处理用户操作
 *               CustomerList为Customer对象的管理模块， 内部用数组管理一组Customer对象，
 *               并提供相应的添加、删除和获取方法，供CustomerView调用 Customer为实体对象，用来封装客户信息
 *               CustomerMain为主类,包含项目入口方法
 *               Customer类主要封装用户的一些个人信息,包括name,gender,age,phone,email;定义为私有权限，通过get,set进行调用
 * @author karlieswift
 * @date 2020年3月24日
 * @version "13.0.1"
 */
public class Customer {
	/**
	 * @Description 属性定义
	 * @param name   客户姓名
	 * @param gender 客户性别
	 * @param age    年龄
	 * @param phone  电话号码
	 * @param email  邮件
	 */
	public String name;// 客户姓名
	private char gender;// 性别
	private int age;// 年龄
	private String phone;// 电话号码
	private String email;// 电子邮箱

	/**
	 * 带参数的构造函数
	 * Customer(name,gebder,age,phone,email)
	 */
	public Customer(String name, char gender, int age, String phone, String email) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * 无参数的构造函数
	 * Customer()
	 */
	public Customer() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
