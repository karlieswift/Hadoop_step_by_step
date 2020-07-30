package annotation;

public class AnnotationCalculator {

	
	@check
	public void fun1() {
		System.out.println(1);
	}
	@check
	public void fun2() {
		int a=1/0;
		System.out.println(2);
	}
	@check
	public void fun3() {
		System.out.println(3);
	}
	@check
	public void fun4() {
		System.out.println(4);
	}
	@check
	public void fun5() {
		int a=1/0;
		System.out.println(5);
	}
	@check
	public void fun6() {
		System.out.println(6);
	}
}
