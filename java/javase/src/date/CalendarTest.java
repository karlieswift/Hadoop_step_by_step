package date;

import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
	public static void main(String[] args) {

		/**
		 * Calendar��һ��������࣬����������������ֶ�֮���໥�����Ĺ��ܡ�
		 * 
		 * 
		 */

		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();// ��һ�� Calendar �����л�ȡ Date ����
		calendar.setTime(date);// ʹ�ø����� Date ���ô� Calendar ��ʱ��
		calendar.set(Calendar.DAY_OF_MONTH, 6);//Mon Apr 06 13:30:02 CST 2020
		System.out.println("��ǰʱ��������Ϊ8��,ʱ����:" + calendar.getTime());
		calendar.add(Calendar.HOUR, 2);//Mon Apr 06 15:30:02 CST 2020
		System.out.println("��ǰʱ���2Сʱ��,ʱ����:" + calendar.getTime());
		calendar.add(Calendar.MONTH, -2);//Thu Feb 06 15:30:02 CST 2020
		System.out.println("��ǰ���ڼ�2���º�,ʱ����:" + calendar.getTime());

	}
}
