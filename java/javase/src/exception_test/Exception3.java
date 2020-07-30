package exception_test;

public class Exception3 {
	static void methodA() {
		try {
			System.out.println("进入方法A");
			throw new RuntimeException("制造异常");
		} finally {
			System.out.println("用A方法的finally");
		}
	}

	/**
	 * 进入方法A 
	 * 用A方法的finally 
	 * 制造异常 
	 * 进入方法B 
	 * 调用B方法的finally 
	 * 1
	 * @Function @return
	 */
	static int methodB() {
		try {
			System.out.println("进入方法B");
			// throw new Exception();
			return 1;
		} catch (Exception e) {
			return 3;
		} finally {
			System.out.println("调用B方法的finally");
			// return 2;
		}
	}

	public static void main(String[] args) {
		try {
			methodA();
		} catch (Exception e) {
			System.out.println(e.getMessage());// 制造异常
		}
		int i = methodB();
		System.out.println(i);
	}
}
