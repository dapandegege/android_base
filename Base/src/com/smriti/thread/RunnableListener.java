package com.smriti.thread;

import java.lang.ref.WeakReference;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public interface RunnableListener {
	/*
	 * a RunnableListener contains an Hander,
	 * which was created when the setLooper method was called
	 */
	public Handler getHandler();			
	public void setLooper(Looper looper);	
	
	class PlainRunnableListener implements RunnableListener{
		
		protected WeakReference<Handler> handlerWR;
		protected WeakReference<Looper> looperWR;

		@Override
		public Handler getHandler() {
			// TODO Auto-generated method stub
			return handlerWR.get();
		}

		@Override
		public void setLooper(Looper looper) {
			// TODO Auto-generated method stub
			looperWR = new WeakReference<Looper>(looper);
			handlerWR = new WeakReference<Handler>(new Handler(looper){
				@Override
				public void handleMessage(Message message) {
	            	Log.w("Base", "in PlainRunnableListener"+ message.obj.toString());
	            } 
			});
		}
		
	}
}



