package com.smriti.utilites;

import java.util.Arrays;
import java.util.List;

public class DateUtil {
	
	static String[] bigMonths = { "1", "3", "5", "7", "8", "10", "12" };
	static String[] littleMonths = { "4", "6", "9", "11" };
	static  List<String> bigmMonthList = Arrays.asList(bigMonths);
	static  List<String> littleMonthsList = Arrays.asList(littleMonths);
	
	
	private DateUtil(){}
	
	
	public static Boolean isBigMonth(final int monthNum){
		return bigmMonthList.contains(String.valueOf(monthNum));
	}
	
	public static Boolean isLitteMonth(final int monthNum){
		return littleMonthsList.contains(String.valueOf(monthNum));
	}
	
	public static int getFebruaryDayNum(final int yearNum){
		int dayNum = 28;
		if (( (yearNum % 4 == 0) && (yearNum % 100 != 0) ) || (yearNum % 400 == 0) ) dayNum = 29;
		return dayNum;
	}
	
	public static int getDayNumUnderYearAdnMonth(final int yearNum, final int monthNum){
		int dayNum = 0;
		if (isBigMonth(monthNum)) {
			dayNum = 31;
		} else if (isLitteMonth(monthNum)) 
		{
			dayNum = 30;
		} else
		{
			dayNum = getFebruaryDayNum(yearNum);
		}
		return dayNum;
	}

}
