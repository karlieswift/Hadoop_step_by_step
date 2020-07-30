package customer.customerFun;

/**
 * 
 * @Description: <Function> ���������� ���� CustomerViewΪ��ģ�飬 ����˵�����ʾ�ʹ����û�����
 *               CustomerListΪCustomer����Ĺ���ģ�飬 �ڲ����������һ��Customer����
 *               ���ṩ��Ӧ����ӡ�ɾ���ͻ�ȡ��������CustomerView���� CustomerΪʵ�����������װ�ͻ���Ϣ
 *               CustomerMainΪ����,������Ŀ��ڷ���
 *               Customer����Ҫ��װ�û���һЩ������Ϣ,����name,gender,age,phone,email;����Ϊ˽��Ȩ�ޣ�ͨ��get,set���е���
 * @author karlieswift
 * @date 2020��3��24��
 * @version "13.0.1"
 */
public class Customer {
	/**
	 * @Description ���Զ���
	 * @param name   �ͻ�����
	 * @param gender �ͻ��Ա�
	 * @param age    ����
	 * @param phone  �绰����
	 * @param email  �ʼ�
	 */
	public String name;// �ͻ�����
	private char gender;// �Ա�
	private int age;// ����
	private String phone;// �绰����
	private String email;// ��������

	/**
	 * �������Ĺ��캯��
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
	 * �޲����Ĺ��캯��
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
