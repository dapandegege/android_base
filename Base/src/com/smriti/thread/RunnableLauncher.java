package com.smriti.thread;

/*
 * the launcher of the Runnable
 * and provide data to process in the Runnable
 */

public interface RunnableLauncher {
	
	public Object getData(LLRunnable runnable);
	public void setData(Object object);
	
}

