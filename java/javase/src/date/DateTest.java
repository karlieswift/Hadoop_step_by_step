package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) throws ParseException {
		Date date=new Date();
		System.out.println(date);//Mon Apr 06 13:17:36 CST 2020
		System.out.println(date.getTime());//”ÎcurrentTimeMillisœ‡Õ¨
		System.out.println(System.currentTimeMillis());//1586150256749
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time1=sdf.format(date);
		System.out.println(time1);//2020-04-06 01:17:36
		Date time2=sdf.parse("2020-04-01 12:20:18");
		System.out.println(time2);//Wed Apr 01 00:20:18 CST 2020
		
	}
}
 
