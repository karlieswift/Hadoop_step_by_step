package exception_test;



public class Exception1{ 
	public static String output=""; 
	public static void foo(int i){ 
		try{ 
			if(i==1)
				throw new Exception(); 	
			output+="1"; 
		} 
		catch(Exception e){ 
			output+="2"; 
			return; //�������return��ԭ����134234����>13423,finallyһ��ִ��
		} 
		finally{ 
			output+="3"; 
		} 
		output+="4"; 
	}
	public static void main(String args[]){ 
		foo(0);
		System.out.println(output);//134
		foo(1); 
		System.out.println(output);// 134234
	}
}
