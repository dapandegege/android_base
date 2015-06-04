package com.smriti.thread;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.smriti.utilites.BatteryUtil;
import com.smriti.utilites.BatteryUtil.BatteryListener;

import android.os.Handler;
import android.os.Looper;

/*
 * 
public interface BlockingQueue<E> extends Queue<E>
public class ArrayBlockingQueue<E> extends AbstractQueue<E>
        implements BlockingQueue<E>, java.io.Serializable
public class LinkedBlockingQueue<E> extends AbstractQueue<E>
        implements BlockingQueue<E>, java.io.Serializable
        
public class PriorityBlockingQueue<E> extends AbstractQueue<E>
    implements BlockingQueue<E>, java.io.Serializable

*/

/*
public interface BlockingDeque<E> extends BlockingQueue<E>, Deque<E>
public class LinkedBlockingDeque<E>
    extends AbstractQueue<E>
    implements BlockingDeque<E>, java.io.Serializable

 */


public final class RunnableManager implements BatteryListener {
	
	static Map<String, ThreadPoolExecutor> threadPoolExecutors = new HashMap<String, ThreadPoolExecutor>();
	static Map<String, BlockingQueue<Runnable>> blockingQueues = new HashMap<String, BlockingQueue<Runnable>>();
	
	public static BlockingQueue<Runnable> getBlockingQueue(Looper looper, boolean isPriority){
		synchronized (blockingQueues) {
			BlockingQueue<Runnable> blockingQueue = blockingQueues.get(looper.hashCode()+":p:"+isPriority);
			if (blockingQueue == null) {
				if (isPriority) {
					blockingQueue  =  new PriorityBlockingQueue<Runnable>();
				}else {
					blockingQueue  =  new LinkedBlockingQueue<Runnable>();
				}
				blockingQueues.put(looper.hashCode()+":p:"+isPriority, blockingQueue);
			}
			return blockingQueue;
		}
	}
	
	public static ThreadPoolExecutor getThreadPoolExecutor(Looper looper, boolean isPriority){
		synchronized (threadPoolExecutors) {
			ThreadPoolExecutor threadPoolExecutor = threadPoolExecutors.get(looper.hashCode()+":p:"+isPriority);
			if (threadPoolExecutor == null){
				BlockingQueue<Runnable> blockingQueue = getBlockingQueue(looper,isPriority);
				threadPoolExecutor = new ThreadPoolExecutor(
										NUMBER_OF_CORES,       // Initial pool size
										NUMBER_OF_CORES,   // Max pool size
										KEEP_ALIVE_TIME,
										KEEP_ALIVE_TIME_UNIT,
										blockingQueue);
				threadPoolExecutors.put(looper.hashCode()+":p:"+isPriority, threadPoolExecutor);
				
			}
			return threadPoolExecutor;
		}
	}
	
	protected static final int KEEP_ALIVE_TIME = 1;
	protected static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
	protected static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
	
	protected Handler handler;//attached to main thread, and post massege to it when needed
	protected BlockingQueue<Runnable> workQueue;
	protected ThreadPoolExecutor threadPool;
	protected boolean isBatterryChanged;
		
	protected WeakReference<Looper> looperWR;
	
	/*
	 * constructors
	 */
	public RunnableManager(Looper looper){
		initRunnableManager(looper, false);
	}
	
	public RunnableManager(){
		initRunnableManager(Looper.getMainLooper(), false);
	}
	
	public RunnableManager(Looper looper, boolean isPriority){
		initRunnableManager(looper, isPriority);
	}
	
	public RunnableManager(boolean isPriority){
		initRunnableManager(Looper.getMainLooper(), isPriority);
	}
	
	private void initRunnableManager(Looper looper, boolean isPriority){
		looperWR = new WeakReference<Looper>(looper);
		isBatterryChanged = false;
		BatteryUtil.getBatteryUtil().addListener(this);
		workQueue = getBlockingQueue(looperWR.get(), isPriority);
		threadPool = getThreadPoolExecutor(looperWR.get(), isPriority);
	}

	public void execute(LLRunnable runnable){
		runnable.setLooper(looperWR.get());
		synchronized (this) {
			threadPool.execute(runnable);
		}
	 }
	
	/*
	 * (non-Javadoc)
	 * @see com.smriti.utilites.BatteryUtil.BatteryListener#batteryChanged(float)
	 */
	@Override
	public void batteryChanged(float percentage) {
		// TODO Auto-generated method stub
		synchronized(threadPool){
			isBatterryChanged = true;
			if (readyToReconfigPool()) {
				reconfigPool();
				isBatterryChanged = false;
			}
		}
	}

	@Override
	public void batteryLow() {
		// TODO Auto-generated method stub
		synchronized(threadPool){
			isBatterryChanged = true;
			if (readyToReconfigPool()) {
				reconfigPool();
				isBatterryChanged = false;
			}
		}
	}

	@Override
	public void batteryOK() {
		// TODO Auto-generated method stub
		synchronized(threadPool){
			isBatterryChanged = true;
			if (readyToReconfigPool()) {
				reconfigPool();
				isBatterryChanged = false;
			}
		}
	}
	
	private boolean readyToReconfigPool(){
		return workQueue.peek() == null;
	}
	
	private void reconfigPool(){
		NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
		threadPool.setCorePoolSize(NUMBER_OF_CORES);
	}
}
