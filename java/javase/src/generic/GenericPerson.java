package generic;

public class GenericPerson {
	public static void main(String args[]) {
		Person3<Contact> per = null; // ����Person3����
		Contact con = new Contact("beijing", "01088888888", "102206");
		per = new Person3<Contact>(con);
		System.out.println(per);

		Person3<Introduction> per2 = null; // ����Person3����
		per2 = new Person3<Introduction>(new Introduction("taylor", "��", 24));
		System.out.println(per2);
	}
}

interface Info { // ֻ�д˽ӿڵ�������Ǳ�ʾ�˵���Ϣ
}

class Contact implements Info { // ��ʾ��ϵ��ʽ
	private String address; // ��ϵ��ַ
	private String telephone; // ��ϵ��ʽ
	private String zipcode; // ��������

	public Contact(String address, String telephone, String zipcode) {
		this.address = address;
		this.telephone = telephone;
		this.zipcode = zipcode;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return this.address;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	@Override
	public String toString() {
		return "Contact [address=" + address + ", telephone=" + telephone + ", zipcode=" + zipcode + "]";
	}
}

class Introduction implements Info {
	private String name; // ����
	private String sex; // �Ա�
	private int age; // ����

	public Introduction(String name, String sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public String getSex() {
		return this.sex;
	}

	public int getAge() {
		return this.age;
	}

	@Override
	public String toString() {
		return "Introduction [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
}

class Person3<T> {
	private Object info;

	public Person3(Object info) { // ͨ�����췽��������Ϣ��������
		this.info = info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public Object getInfo() {
		return info;
	}

	@Override
	public String toString() {
		return "Person3 [info=" + info + "]";
	}

//	public String toString(){	// ��дObject���е�toString()����
//		return this.info.toString() ;
//	}
}