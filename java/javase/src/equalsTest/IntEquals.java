package equalsTest;

public class IntEquals {
	public static void main(String[] args) {

		int a=2;
		char c=2;
		double d=2.0;
		System.out.println(a==c);//true
		System.out.println(a==d);//true
		System.out.println(d==c);//true
		boolean b1=true;
		boolean b2=false;
		System.out.println(b1==b2);//false
	 // System.out.println(b1==a);//±¨´í
		char c1='A';
		char c2=65;
		System.out.println(c1); //A
		System.out.println(c2); //A
		System.out.println(c1==c2); //true
	}
}
