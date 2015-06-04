package com.smriti.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Message;

import com.smriti.pool.MessagePool;
import com.smriti.thread.LLRunnable.ALLRunnable;
import com.smriti.thread.RunnableLauncher;
import com.smriti.thread.RunnableListener;
import com.smriti.utilites.StreamUtil;

/*
 * It's useful in network connect state detection
 */

public class NetworkDetector extends ALLRunnable {

	private String urlString;
	protected WeakReference<RunnableListener> listener;
	
	public NetworkDetector(String urlString, RunnableListener listener){
		this.urlString = urlString;
		this.listener = new  WeakReference<RunnableListener>(listener);
	}
	
	public NetworkDetector(RunnableListener listener){
		this.urlString = "http://www.baidu.com/";
		this.listener = new  WeakReference<RunnableListener>(listener);
	}
	
	public void run() {
		// TODO Auto-generated method stub
		try {
			Message message = MessagePool.getMessage();
			
	        URL url; url = new URL(this.urlString);
	        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	        try {
	          InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
	          String ddString = StreamUtil.inputStream2ByteArrayOutputStream(inputStream).toString();
	          
	          message.obj = ddString;
	          listener.get().getHandler().sendMessage(message);
	        }catch(Exception exception)
	        {
	        	message.obj = exception.toString();
	        	listener.get().getHandler().sendMessage(message);
	        }
	        
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	@Override
	public RunnableListener getRunnableListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RunnableLauncher getRunnableLauncher() {
		// TODO Auto-generated method stub
		return null;
	}

}
