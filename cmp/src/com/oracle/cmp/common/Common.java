package com.oracle.cmp.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.log.Log;

public class Common {
	public static final int PAGESIZE = 5;
	public static double getDouble(String str){
		if(str==null||"".equals(str)) return 0;
		return Double.parseDouble(str);
	}
	public static int getInt(String str){
		if(str==null||"".equals(str)) return 0;
		return Integer.parseInt(str);
	}	
	public static Date getDate(String str){
		if(str==null||"".equals(str)) return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
