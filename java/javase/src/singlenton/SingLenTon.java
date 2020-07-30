package singlenton;

public class SingLenTon {

	public static void main(String[] args) {
		System.out.println("�����ģʽ����ʽ������");
		Sing_1 s_1_1 = Sing_1.getSing_1();
		Sing_1 s_1_2 = Sing_1.getSing_1();
		System.out.println(s_1_1 == s_1_2);
		System.out.println("�����ģʽ����ʽ������");
		Sing_2 s_2_1 = Sing_2.getSing_2();
		Sing_2 s_2_2 = Sing_2.getSing_2();
		System.out.println(s_2_1 == s_2_2);
	}

}

/**
 * 
 * @Description: <����ʽ>
 * @author karlieswift
 * @date 2020��3��28��
 * @version "13.0.1"
 */
class Sing_1 {
	// ˽�л�������
	private Sing_1() {

	}
	// �ڲ�������Ķ���
	private static Sing_1 instance_1 = new Sing_1();

	
	/**
	 *  �ṩ��������
	 * @Function @return
	 */
	public static Sing_1 getSing_1() {
		return instance_1;
	}
}

/**
 * 
 * @Description: <����ʽ>
 * @author karlieswift
 * @date 2020��3��28��
 * @version "13.0.1"
 */
class Sing_2 {
	// ˽�л�������
	private Sing_2() {

	}

	// �ڲ�������Ķ���
	private static Sing_2 instance_2 = null;


	/**
	 *  �ṩ��������
	 * @Function @return
	 */
	public static Sing_2 getSing_2() {
		if (instance_2 == null) {
			instance_2 = new Sing_2();
		}
		return instance_2;
	}

}
