package com.smriti.image;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Assert;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.Log;
import android.util.Size;

import com.smriti.pool.MessagePool;
import com.smriti.thread.LLRunnable.ALLRunnable;
import com.smriti.thread.RunnableLauncher;
import com.smriti.thread.RunnableListener;
import com.smriti.utilites.BitmapUtil;
import com.smriti.utilites.StreamUtil;


@SuppressLint("NewApi")
public class ImageLoader extends ALLRunnable {
	
	static int ImageLoader_Failed = -200;
	
	protected WeakReference<RunnableLauncher> launcherWR;
	protected WeakReference<RunnableListener> listenerWR;
	
	public ImageLoader(RunnableLauncher launcher, RunnableListener listener, Size size){
		this.launcherWR = new WeakReference<RunnableLauncher>(launcher);
		this.listenerWR = new  WeakReference<RunnableListener>(listener);
	}
	
	public ImageLoader(RunnableLauncher launcher, RunnableListener listener, int width){
		this.launcherWR = new WeakReference<RunnableLauncher>(launcher);
		this.listenerWR = new  WeakReference<RunnableListener>(listener);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Message message = MessagePool.getMessage();
		try {
			
			String uri = (String)this.launcherWR.get().getData(this);
			Bitmap bitmap = BitmapUtil.loadBitmapToWidth(uri, 1024);
			message.arg1 = 200;
			message.obj = bitmap;
			
		} catch (Exception e) {
			setErrorInfo(message, e);
		}finally {

			listenerWR.get().getHandler().sendMessage(message);
		}
	}
	
	private void setErrorInfo(Message message, Exception exception){
		message.obj = exception.toString();
		message.arg1 = ImageLoader_Failed;
		exception.printStackTrace();
	}

	/*
	 * (non-Javadoc)
	 * @see com.smriti.thread.LLRunnable#getRunnableListener()
	 */
	@Override
	public RunnableListener getRunnableListener() {
		// TODO Auto-generated method stub
		return listenerWR.get();
	}
	@Override
	public RunnableLauncher getRunnableLauncher() {
		// TODO Auto-generated method stub
		return launcherWR.get();
	}
	
}

