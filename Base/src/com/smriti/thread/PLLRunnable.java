package com.smriti.thread;

import java.lang.ref.WeakReference;

import junit.framework.Assert;

import android.os.Looper;

/*
 * Runnable with PLLRunnable, RunnableListener and RunnableLauncher properties
 */

public interface PLLRunnable extends LLRunnable, Comparable<PLLRunnable>{
	
	public int getPriority() ;
	public void setPriority(int priority);

	public abstract class APLLRunnable implements PLLRunnable{	
		
		private int priority;
		protected WeakReference<Looper> looperWR;
		 
		public APLLRunnable(){
			setPriority(Thread.NORM_PRIORITY);
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
			public final int compareTo(PLLRunnable another){
				return priority - another.getPriority();
			}

			public final int getPriority() {
				return priority;
			}

			public final void setPriority(int priority) {
				this.priority = priority;
			}
			
	}
	
	public class SPLLRunnable implements PLLRunnable{	
		
		private int priority;
		protected WeakReference<Looper> looperWR;
		protected WeakReference<RunnableLauncher> launcherWR;
		protected WeakReference<RunnableListener> listenerWR;
		
		public SPLLRunnable(RunnableLauncher launcher, RunnableListener listener){
			setPriority(Thread.NORM_PRIORITY);
			
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
			public final int compareTo(PLLRunnable another){
				return priority - another.getPriority();
			}

			public final int getPriority() {
				return priority;
			}

			public final void setPriority(int priority) {
				this.priority = priority;
			}

			@Override
			public final RunnableListener getRunnableListener() {
				// TODO Auto-generated method stub
				return listenerWR.get();
			}

			@Override
			public final RunnableLauncher getRunnableLauncher() {
				// TODO Auto-generated method stub
				return launcherWR.get();
			}

			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Assert.assertTrue("you should override this method", false);
			}
			
	}
	
}


