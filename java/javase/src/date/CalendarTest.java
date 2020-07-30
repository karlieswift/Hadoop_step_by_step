package date;

import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
	public static void main(String[] args) {

		/**
		 * Calendar是一个抽象基类，主用用于完成日期字段之间相互操作的功能。
		 * 
		 * 
		 */

		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();// 从一个 Calendar 对象中获取 Date 对象
		calendar.setTime(date);// 使用给定的 Date 设置此 Calendar 的时间
		calendar.set(Calendar.DAY_OF_MONTH, 6);//Mon Apr 06 13:30:02 CST 2020
		System.out.println("当前时间日设置为8后,时间是:" + calendar.getTime());
		calendar.add(Calendar.HOUR, 2);//Mon Apr 06 15:30:02 CST 2020
		System.out.println("当前时间加2小时后,时间是:" + calendar.getTime());
		calendar.add(Calendar.MONTH, -2);//Thu Feb 06 15:30:02 CST 2020
		System.out.println("当前日期减2个月后,时间是:" + calendar.getTime());

	}
}
