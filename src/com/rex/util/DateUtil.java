/**
 * 
 */
package com.rex.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Xingyu Liu
 *
 */
public class DateUtil {

	public static String dateToString(Date date, String format){
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if(date != null){
			result = sdf.format(date);
		}
		return result;
	}
	
	public static Date StringToDate(String str, String format) throws ParseException{
		if(StringUtil.isEmpty(str)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(str);
	}
}
