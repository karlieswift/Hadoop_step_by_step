package polymorphism;

/*
 * ��̬��ϰPolymorphism
 * ��������д�˸��෽��������ζ�������ﶨ��ķ������׸����˸������ͬ��������
 * ϵͳ�������ܰѸ�����ķ���ת�Ƶ������У����뿴��ߣ����п��ұ�
 * ����ʵ�ཻ���򲻴������������󣬼�ʹ�����ﶨ�����븸����ȫ��ͬ��ʵ��������
 * ���ʵ�ֽ�����Ȼ�����ܸ��Ǹ����ж����ʵ������:�������ж������
 * 
 */
public class Ploymorphism {
	public static void main(String[] args) {
		Sub s=new Sub();
		System.out.println(s.count);//20
		s.show();//20
		Base b=s;
		System.out.println(b==s);//true
		System.out.println(b.count);//10
		b.show();//20
	}
}
class Base {
	int count = 10;
	public void show() {
		System.out.println(this.count);
	}
}
class Sub extends Base {
	int count = 20;
	public void show() {
		System.out.println(this.count);
	}
}