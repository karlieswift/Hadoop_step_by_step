package equalsTest;

public class �����д {

	public static void main(String[] args) {
		Object o1=true ? new Integer(1) : new Double(2.0);
		System.out.println(o1); //1.0
		Integer in1=new Integer(11);
		Integer in2=new Integer(11);
		System.out.println(in1==in2);//false
		//Integer�ڲ�������IntegerCache�ṹ��IntegerCache�ж����� Integer[],
		//�����˴�-128~127��Χ���������������ʹ���Զ�װ��ķ�ʽ����Integer��ֵ�ķ�Χ��
		//-128~127��Χ��ʱ������ֱ��ʹ�������е�Ԫ�أ�������ȥnew�ˡ�
		Integer in3=11;
		Integer in4=11;
		System.out.println(in3==in4);//true
		Integer in5=131;
		Integer in6=131;
		System.out.println(in5==in6);//false
	}
	
	
}
