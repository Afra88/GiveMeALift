package it.unical.mat.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ParseDate {
	
	public static int compare(String d1,String d2){
		try {
			Date dd1=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse(d1);
			Date dd2=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse(d1);
			return dd2.compareTo(dd1);
			} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int getDay(String date){
		String[] d=date.split("/");
		GregorianCalendar c=new GregorianCalendar(Integer.parseInt(d[2]), (Integer.parseInt(d[1])-1) , Integer.parseInt(d[0]));
		return c.get(Calendar.DAY_OF_WEEK);
	}
	
	public static String getItalianFormat(String date){
		String[] d=date.split("-");
		return d[2]+"/"+d[1]+"/"+d[0];
	}

	public static String newDate() {
		GregorianCalendar g=new GregorianCalendar(Locale.ITALIAN);
		return g.get(Calendar.DAY_OF_MONTH)+"/"+g.get(Calendar.MONTH)+"/"+g.get(Calendar.YEAR);
	}
}
