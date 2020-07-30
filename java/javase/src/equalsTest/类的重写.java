package equalsTest;

public class 类的重写 {

	public static void main(String[] args) {
		Object o1=true ? new Integer(1) : new Double(2.0);
		System.out.println(o1); //1.0
		Integer in1=new Integer(11);
		Integer in2=new Integer(11);
		System.out.println(in1==in2);//false
		//Integer内部定义了IntegerCache结构，IntegerCache中定义了 Integer[],
		//保存了从-128~127范围的整数。如果我们使用自动装箱的方式，给Integer赋值的范围在
		//-128~127范围内时，可以直接使用数组中的元素，不用再去new了。
		Integer in3=11;
		Integer in4=11;
		System.out.println(in3==in4);//true
		Integer in5=131;
		Integer in6=131;
		System.out.println(in5==in6);//false
	}
	
	
}
