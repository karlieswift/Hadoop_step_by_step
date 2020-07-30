package overwrite;

public class OverWrite {

	public static void main(String[] args) {
		
		
		Base b=new Sub();
		System.out.println(b.count);//1
		b.show(1,2,3);//22
		b.show(1,2,3,4,5,6);//22
		Sub s=(Sub)b;
		s.show(1,2,3);//33
		Sub d=new Sub();
	//	d.show(1,2,3,4);//报错
	}
}
class Base{
	int count=1;
	public void show(int a,int ...arr) {System.out.println(11);}
}
class Sub extends Base{
	int count=2;
	public void show(int a,int []arr) {System.out.println(22);}  //对Base的show()重写
	public void show(int a,int b,int c) {System.out.println(33);}
}