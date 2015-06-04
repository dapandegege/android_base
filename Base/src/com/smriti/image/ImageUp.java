package com.smriti.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import android.graphics.Bitmap;
import android.text.InputFilter.LengthFilter;
import android.util.Log;


import com.smriti.thread.PLLRunnable.SPLLRunnable;

import com.smriti.thread.LLRunnable.SLLRunnable;
import com.smriti.thread.RunnableLauncher;
import com.smriti.thread.RunnableListener;

public class ImageUp extends SPLLRunnable {
	
	static int ImageUp_TimeOut_Connection = 1000*60;
	static int ImageUp_TimeOut_Read = 1000*10;
	
	private static final String CONTENT_TYPE = "multipart/form-data"; // 内容类型  
    private static final String BOUNDARY =  UUID.randomUUID().toString(); // 边界标识 随机生成  

	public ImageUp(RunnableLauncher launcher, RunnableListener listener) {
		super(launcher, listener);
		// TODO Auto-generated constructor stub
	}

	public void run() {
		// TODO Auto-generated method stub
		HttpURLConnection connection = null; 
	    String result = null;  
          
        long requestTime = System.currentTimeMillis();  
        long responseTime = 0;  
  
        try {  
        	ImageUpPara para = (ImageUpPara) this.launcherWR.get().getData(this);        	
        	Log.w("Base", para.url);
        	URL url = new URL(para.url);
        	connection = (HttpURLConnection) url.openConnection();  
	        
        	connection.setConnectTimeout(ImageUp_TimeOut_Connection);  
        	connection.setReadTimeout(ImageUp_TimeOut_Read);  
        	
        	connection.setDoInput(true);
        	connection.setDoOutput(true);  
        	connection.setUseCaches(false); 
        	connection.setRequestMethod("POST"); 
        	
        	connection.setRequestProperty("Charset", "utf-8"); // 设置编码  
        	connection.setRequestProperty("connection", "keep-alive");  
        	connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
        	connection.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);  
        	              
        	StringBuilder builder = new StringBuilder();
        	
        	for (Iterator<String> iterator = para.keyValues.keySet().iterator(); iterator.hasNext();) {
        		
        		String name = (String) iterator.next();
				String value = para.keyValues.get(name);
        		builder.append("__");
        		builder.append(BOUNDARY);
        		builder.append("\r\n");
        		builder.append("Content-Disposition:form-data;name=\""+name+"\"\r\n\r\n");
        		builder.append(URLEncoder.encode(value,"UTF-8"));
        		builder.append("\r\n");
			}
//        	builder.append("__");
//    		builder.append(BOUNDARY);
//    		builder.append("\r\n");
//    		builder.append("Content-Disposition:form-data;name=\""+para.filename+"\";filename=\""+para.filePath+"\"\r\n");
//    		builder.append("Content-Type:application/octet-stream\r\n\r\n");
    		byte[] data = builder.toString().getBytes();
    		
//    		Bitmap bitmap = para.bitmap;
//    		byte[] filedata =bitmap.toString().getBytes();
    		
//    		StringBuilder endBuilder = new StringBuilder();
//    		endBuilder.append("\r\n");
//    		endBuilder.append("__");
//    		endBuilder.append(BOUNDARY);
//    		endBuilder.append("__");
//    		endBuilder.append("\r\n");
//    		byte[] end = endBuilder.toString().getBytes();
    		
    		String length = String.valueOf(data.length );
//    		String length = String.valueOf(data.length + filedata.length + end.length);
    		connection.setRequestProperty("Content-Length", length);
    		
    		OutputStream outputStream = connection.getOutputStream(); 
    		outputStream.write(data);
//    		outputStream.write(end);
//    		outputStream.write(filedata);
            outputStream.flush();  
            outputStream.close();

            Log.w("Base", "will read !!");
            
            int res = connection.getResponseCode();  
            Log.w("Base", "code ="+connection.getResponseCode()+"");
            Log.w("Base", "message ="+connection.getResponseMessage());
            
            responseTime = System.currentTimeMillis();  
            requestTime = (int) ((responseTime-requestTime)/1000);  
            if (res == 200) 
            {  
                InputStream input = connection.getInputStream();  
                StringBuffer sb1 = new StringBuffer();  
                int ss;  
                while ((ss = input.read()) != -1) {  
                    sb1.append((char) ss);  
                }  
                result = sb1.toString();  
                
                Log.w("Base", "result string =" +result);
                
                 
                return;  
            } else {  
                return;  
            }  
            
	
        } catch (IOException e) {  
            e.printStackTrace();  
            return;  
        }  
        
	}
	
}
//if (para.bitmap == null) {
//	outputStream = new DataOutputStream(outputStream); 
//	InputStream inputStream = new FileInputStream(para.filePath);
//	 byte[] bytes = new byte[1024];   
//   int len = 0;   
//   while((len=inputStream.read(bytes))!=-1)   outputStream.write(bytes, 0, len);   
//   inputStream.close();
//}

