package com.smriti.utilites;

import android.annotation.SuppressLint;

public class ProtocolUtil {
	
	@SuppressLint("DefaultLocale")
	public static boolean isProtocol(String check, String target)
	{
		return check.toLowerCase().indexOf(target) == 0;
	}
	
	public static boolean isHttpProtocol(String check)
	{
		return isProtocol(check, "http://");
	}
	public static boolean isHttpsProtocol(String check)
	{
		return isProtocol(check, "https://");
	}
	public static boolean isHttpzProtocol(String check)
	{
		return isProtocol(check, "https://") || isProtocol(check, "http://");
	}
	
	public static boolean isFileProtocol(String check)
	{
		return isProtocol(check, "file://");
	}
	
	
	
}
