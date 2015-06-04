package com.smriti.utilites;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtil {
	
	private StreamUtil(){
	}
	
	public static byte[] inputStream2Bytes(InputStream inputStream) throws Exception{  
        ByteArrayOutputStream bOutputStream = inputStream2ByteArrayOutputStream(inputStream);  
        byte[] result = bOutputStream.toByteArray();  
        return  result;  
    }  

	public static String inputStream2String(InputStream inputStream) throws Exception{ 
		ByteArrayOutputStream bOutputStream = inputStream2ByteArrayOutputStream(inputStream); 
	    String result = bOutputStream.toString();  
	    return  result;
	}  
	
   public static ByteArrayOutputStream inputStream2ByteArrayOutputStream(InputStream inputStream) throws Exception{  
		
        ByteArrayOutputStream bOutputStream = new ByteArrayOutputStream();  
        
        byte[] rBuffer = new byte[1024];  
        int len = inputStream.read(rBuffer);  
        while(len !=-1){  
        	bOutputStream.write(rBuffer, 0, len);  
        	len = inputStream.read(rBuffer);
        }  
        inputStream.close();    
        bOutputStream.flush(); 
        bOutputStream.close();

        return  bOutputStream;  
    }

}
