package exception_test;
class Person extends Exception{}
class Student extends Person{}
//父类的catch放在子类的下面
public class Exception2{
	public static void main(String[] args){
		try{
			throw new Student();
		}		
		catch(Student e){
			System.out.println("student");
		}
		catch(Person e){
			System.out.println("person");
		}
		catch(Exception e){
			System.out.println("Exception");
		}
	}
}
