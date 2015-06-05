package com.smriti;

import java.util.HashMap;

import com.smriti.image.ImageDown;
import com.smriti.image.ImageUp;
import com.smriti.image.ImageUpPara;
import com.smriti.image.ImageViewL;
import com.smriti.image.ImageViewLL;
import com.smriti.thread.LLRunnable;
import com.smriti.thread.RunnableLauncher;
import com.smriti.thread.RunnableListener;
import com.smriti.thread.RunnableManager;
import com.smriti.user.Login;
import com.smriti.user.Login.LoginModel;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

@SuppressLint("ClickableViewAccessibility")
public class MainActivity extends Activity implements RunnableListener, RunnableLauncher,OnTouchListener {
	
	ImageViewLL imageHolder ;
	int coutn = 0;
	RunnableManager runnableManager;
	TextView senderTextView;
	
	Bitmap bitmap;
	
	String urlString = "http://photo.iautos.cn/carupload/photo/2014/1202/15/small/20141202153553452787.jpg";
	
	Handler handler ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		 runnableManager =new RunnableManager(Looper.getMainLooper(), true);
		 imageHolder = (ImageViewLL) findViewById(R.id.imageView_holder);
		senderTextView = (TextView) findViewById(R.id.textView1);
		senderTextView.setOnTouchListener(this);
			
		 Login login = new Login(this);
		runnableManager.execute(login);
		
		ImageDown imageDonw = new ImageDown(this, this);		
		runnableManager.execute(imageDonw);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		setContentView(R.layout.activity_main);		
		
		 runnableManager =new RunnableManager(Looper.getMainLooper(), true);
		 imageHolder = (ImageViewLL) findViewById(R.id.imageView_holder);
			senderTextView = (TextView) findViewById(R.id.textView1);
			senderTextView.setOnTouchListener(this);
				
			 Login login = new Login(this);
			runnableManager.execute(login);
			
			ImageDown imageDonw = new ImageDown(this, this);		
			runnableManager.execute(imageDonw);
			imageDonw = new ImageDown(this, this);		
			runnableManager.execute(imageDonw);

		return true;
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	 * (non-Javadoc)
	 * @see com.smriti.thread.RunnableExeListener#getHandler()
	 */
	@Override
	public Handler getHandler() {
		// TODO Auto-generated method stub
		return handler;
	}

	@Override
	public void setLooper(Looper looper) {
		// TODO Auto-generated method stub
		
		if (handler!= null) {
			return;
		}
		handler = new Handler(looper){
			@Override
			public void handleMessage(Message message) {
				
				
	        	
				Log.w("Base",  "" + message.obj +"count =" + coutn++);
				
	        	if (message.arg1 == 200) {
	        		if (message.obj.getClass().equals(Bitmap.class)) {
	        			imageHolder.setImageBitmap((Bitmap) message.obj);
	        			bitmap = (Bitmap) message.obj;
					}
	        		
				}else {
					
				}
	        } 
		};
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Log.w("Base", "onTouch");
		
		ImageUp uper = new ImageUp(this, this);
		runnableManager.execute(uper);
		
		return false;
	}

	@Override
	public Object getData(LLRunnable runnable) {
		// TODO Auto-generated method stub
		if (runnable.getClass().equals(ImageUp.class)) {
			
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("sessionId", LoginModel.get().sessionId);
			
			hashMap.put("cid", "7");
			
//			String url = "http://192.168.1.118:7080/carmall-inspect/messenger/uploadPic";
			String url = "http://192.168.1.250:7080/carmall-inspect/messenger/uploadPic";
//			hashMap.put("file", "file");
//			hashMap.put("filename", "filename");
//			
			ImageUpPara para = new ImageUpPara(url, null, this.bitmap, hashMap);
			
			return para;
		}
		Log.w("Base", runnable.toString());
		return urlString;
	}

	@Override
	public void setData(Object object) {
		// TODO Auto-generated method stub
	}
}
