package polymorphism;

/*
 * 多态练习Polymorphism
 * 若子类重写了父类方法，就意味着子类里定义的方法彻底覆盖了父共里的同名方法，
 * 系统将不可能把父类里的方法转移到子类中，编译看左边，运行看右边
 * 对于实侧交量则不存在这样的现象，即使子类里定义了与父类完全相同的实例交量，
 * 这个实钢交量依然不可能覆盖父类中定义的实例交量:编译运行都看左边
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