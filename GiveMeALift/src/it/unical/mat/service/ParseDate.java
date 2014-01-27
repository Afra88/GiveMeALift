package it.unical.mat.service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ParseDate {
	
	public final static String[] months=new String[]{"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
		"Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
	
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
	
	public static String getTimeHM(Time time){
		String[] d=time.toString().split(":");
		return d[0]+":"+d[1];
	}

	public static String newDate() {
		GregorianCalendar g=new GregorianCalendar(Locale.ITALIAN);
		return g.get(Calendar.DAY_OF_MONTH)+"/"+g.get(Calendar.MONTH)+"/"+g.get(Calendar.YEAR);
	}
	
	public static Calendar getUtilDateFormat(String date, int goingHour, int goingMins){
		String[] d=date.split("/");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,goingHour);
		cal.set(Calendar.MINUTE,goingMins);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(d[0]));
		cal.set(Calendar.MONTH, Integer.parseInt(d[1])-1);
		cal.set(Calendar.YEAR, Integer.parseInt(d[2]));
		return cal;
	}
}
