package com.smriti.image;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.Log;

import com.smriti.pool.MessagePool;
import com.smriti.thread.PLLRunnable.APLLRunnable;
import com.smriti.thread.RunnableLauncher;
import com.smriti.thread.RunnableListener;
import com.smriti.utilites.StreamUtil;

//http://photo.iautos.cn/carupload/photo/2014/1202/15/small/20141202153553452787.jpg

/* use instance:
  	ImageHolder imageHolder = (ImageHolderView) findViewById(R.id.imageView_holder);
	imageHolder.setUrlString(urlString);
	ImageDown  imageDonw = new ImageDown(imageHolder, this);
	new Thread(imageDonw){}.start();
 */

/*
 * message.arg1 = code;  conn.getResponseCode();
 * message.obj = bitmap; 图片信息 或是 conn.getResponseMessage();
 */

public class ImageDown extends APLLRunnable {

	protected WeakReference<RunnableLauncher> launcherWR;
	protected WeakReference<RunnableListener> listenerWR;
	
	public ImageDown(RunnableLauncher launcher, RunnableListener listener){
		this.launcherWR = new WeakReference<RunnableLauncher>(launcher);
		this.listenerWR = new  WeakReference<RunnableListener>(listener);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		HttpURLConnection conn = null;
		int code = -100;
		String info = null;
		try {
			
			URL url = new URL((String)this.launcherWR.get().getData(this));
			conn =  (HttpURLConnection) url.openConnection();  
			conn.setReadTimeout(25000);
			conn.setConnectTimeout(25000);
			conn.setRequestMethod("GET");
			conn.setDoOutput(false);
			 
			code = conn.getResponseCode();
			info = conn.getResponseMessage();
			Message message = MessagePool.getMessage();
			message.arg1 = code;
			
			if (code == 200) {
				 InputStream inputStream = conn.getInputStream();  
				 byte[] bytes = StreamUtil.inputStream2Bytes(inputStream);
				 Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length); 
				 message.obj = bitmap;
				 listenerWR.get().getHandler().sendMessage(message);
			 }else {
				 message.obj = info;
				 listenerWR.get().getHandler().sendMessage(message);
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
		}finally {

			Log.w("Base", "ImageDown code = " + code+ ";info = " + info);
			conn.disconnect();
		}
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
