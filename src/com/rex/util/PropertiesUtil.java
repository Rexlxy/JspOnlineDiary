package com.rex.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static String getValue(String key){
		Properties prop = new Properties();
		InputStream inStream = new PropertiesUtil().getClass().getResourceAsStream("/diary.properties");
		try {
			prop.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String)prop.get(key);
	}
}
