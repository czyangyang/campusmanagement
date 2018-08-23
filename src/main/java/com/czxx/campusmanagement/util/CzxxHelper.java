package com.czxx.campusmanagement.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CzxxHelper {
	public CzxxHelper()
	{
		
	}
	public static String DateToString(Date date)
	{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
	}
	
	public static Date StringToDate(String dataStr) throws ParseException
	{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(dataStr);
	}
}
