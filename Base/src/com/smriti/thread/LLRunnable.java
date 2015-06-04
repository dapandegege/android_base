package com.smriti.thread;

import java.lang.ref.WeakReference;

import junit.framework.Assert;

import android.os.Looper;

/*
 * Runnable with RunnableListener and RunnableLauncher properties
 */

public interface LLRunnable extends Runnable {

	public Thread getThread();
	public  void setLooper(Looper looper);   
	
	public RunnableListener getRunnableListener();
	public RunnableLauncher getRunnableLauncher();
	
	
	public abstract class ALLRunnable implements LLRunnable{

		protected WeakReference<Looper> looperWR;
		
		@Override
		public final Thread getThread(){
			return looperWR.get().getThread();
		}
		
		@Override
		public final void setLooper(Looper looper) {
			// TODO Auto-generated method stub
			looperWR = new WeakReference<Looper>(looper);
			getRunnableListener().setLooper(looper);
		}
		
	}
	
	public class SLLRunnable implements LLRunnable{

		protected WeakReference<Looper> looperWR;
		
		protected WeakReference<RunnableLauncher> launcherWR;
		protected WeakReference<RunnableListener> listenerWR;
		
		public SLLRunnable(RunnableLauncher launcher, RunnableListener listener){
			
			this.launcherWR = new WeakReference<RunnableLauncher>(launcher);
			this.listenerWR = new  WeakReference<RunnableListener>(listener);
		}
		
		@Override
		public final Thread getThread(){
			return looperWR.get().getThread();
		}
		
		@Override
		public final void setLooper(Looper looper) {
			// TODO Auto-generated method stub
			looperWR = new WeakReference<Looper>(looper);
			getRunnableListener().setLooper(looper);
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Assert.assertTrue("you should override this method", false);
		}

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
	
	
	
}
