package com.rex.util;

public class StringUtil {

	public static boolean isEmpty(String str){
		if("".equals(str)||str==null){
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}
		return false;
	}
}
