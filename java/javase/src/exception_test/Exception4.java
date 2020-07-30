package exception_test;

public class Exception4 {
	
public static void main(String[] args) {
	Test t= new Test();
	int y=t.fun();
	System.out.println(y);

}
}
class Test{
	public  int fun(){//打印的结果2
		int x=1;				
			try{
				return ++x;
			}catch(Exception e){			
			}finally{
				 x=x+10;		
			}	
			return x;	
	  }
}