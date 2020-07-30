package equalsTest;


public class EqualsTest {

	public static void main(String[] args) {
		Customer cust_1=new Customer(12, "Tom");
		Customer cust_2=new Customer(12, "Tom");
		System.out.println(cust_1.toString());
		System.out.println(cust_1.equals(cust_2));
	}
}
class Customer{
	int age;
	String name;
	public Customer(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;//���õ�ַ��ͬ
		else if(obj instanceof Customer) {
			Customer cu=(Customer)obj;//���ﲻǿ��ת������ø�������Ժ�����ķ���
			return cu.age==this.age && cu.name.equals(this.name);
		}
		else return false;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[name:"+name+" age:"+age+"]";
	}
}