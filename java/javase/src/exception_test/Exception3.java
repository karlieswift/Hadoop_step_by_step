package exception_test;

public class Exception3 {
	static void methodA() {
		try {
			System.out.println("���뷽��A");
			throw new RuntimeException("�����쳣");
		} finally {
			System.out.println("��A������finally");
		}
	}

	/**
	 * ���뷽��A 
	 * ��A������finally 
	 * �����쳣 
	 * ���뷽��B 
	 * ����B������finally 
	 * 1
	 * @Function @return
	 */
	static int methodB() {
		try {
			System.out.println("���뷽��B");
			// throw new Exception();
			return 1;
		} catch (Exception e) {
			return 3;
		} finally {
			System.out.println("����B������finally");
			// return 2;
		}
	}

	public static void main(String[] args) {
		try {
			methodA();
		} catch (Exception e) {
			System.out.println(e.getMessage());// �����쳣
		}
		int i = methodB();
		System.out.println(i);
	}
}
