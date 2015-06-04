package com.smriti.utilites;

public class MemoryUtil {

	
	public static long getMaxMemoryInKB(){
		return (long) (Runtime.getRuntime().maxMemory() / 1024);  
	}
	
	public static float getMaxMemoryInMB(){
		return Runtime.getRuntime().maxMemory() / 1024 / 1024;  
	}
	
	public static long getMaxMemoryInByte(){
		return Runtime.getRuntime().maxMemory() ;  
	}
	
}
