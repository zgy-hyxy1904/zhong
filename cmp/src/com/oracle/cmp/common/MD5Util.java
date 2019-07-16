package com.oracle.cmp.common;


import java.security.MessageDigest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	/**
	 * MD5 º”√‹À„∑®
	 */
	public static String getMD5Code(String srcString) {
	MessageDigest messageDigest = null;
	try	{
		messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.reset();
		messageDigest.update(srcString.getBytes("UTF-8"));
	} catch (NoSuchAlgorithmException e) {
		
	} catch (UnsupportedEncodingException e) {
		
	}
	
	byte[] byteArray = messageDigest.digest();
	
	StringBuffer md5StrBuff = new StringBuffer();
	
	for (int i = 0; i < byteArray.length; i++) {
		if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
			md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
			return md5StrBuff.toString();
	}
	public static void main(String[] args) {
		System.out.println(getMD5Code("aaa"));
	}
}
	