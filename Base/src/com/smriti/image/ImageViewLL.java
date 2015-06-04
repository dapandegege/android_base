package com.smriti.image;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;

import com.smriti.thread.LLRunnable;
import com.smriti.thread.RunnableListener;
import com.smriti.thread.RunnableManager;
import com.smriti.utilites.ProtocolUtil;

/*
 * ImageView as both a RunnableLauncher and a RunnableListener
 */

public class ImageViewLL extends ImageViewL implements RunnableListener {

	protected WeakReference<Handler> handlerWR;
	protected WeakReference<Looper> looperWR;
	 
	public ImageViewLL(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public ImageViewLL(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public ImageViewLL(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setData(Object object) {
		if (object != null) {
			if (!object.equals(this.uri)) {
				this.uri = (String) object;
				LLRunnable llRunnable = null;
				if (ProtocolUtil.isHttpzProtocol(this.uri)) {
					llRunnable = new ImageDown(this, this);	
				}else {
					llRunnable = new ImageLoader(this, this, 1024);
				}
				RunnableManager runnableManager =new RunnableManager(Looper.getMainLooper());
				runnableManager.execute(llRunnable);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.smriti.thread.RunnableListener#setLooper(android.os.Looper)
	 */
	@Override
	public void setLooper(Looper looper) {
		// TODO Auto-generated method stub
		looperWR = new WeakReference<Looper>(looper);
		
		handlerWR = new WeakReference<Handler>(new Handler(looper){
			@Override
			public void handleMessage(Message message) {
				if (message.arg1 == 200) {
	        		setImageBitmap((Bitmap) message.obj);
				}else {
					Log.w("Base",  "" + message.obj);
				}
            } 
		});
	}
	
	@Override
	public Handler getHandler(){
		return handlerWR.get();
	}
}
