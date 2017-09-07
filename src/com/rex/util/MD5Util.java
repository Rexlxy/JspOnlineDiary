package com.rex.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.eclipse.jdt.internal.compiler.batch.Main;

import sun.misc.BASE64Encoder;

public class MD5Util {
	public static String encodePwdByMD5(String pw) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64encode = new BASE64Encoder();
		return base64encode.encode(md5.digest(pw.getBytes("utf-8")));
	}
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(encodePwdByMD5("1234"));
		System.out.println(encodePwdByMD5("l1x2y3456"));
		System.out.println(encodePwdByMD5("l22222"));
	}
}
