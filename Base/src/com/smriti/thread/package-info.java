/**
 * 
 */
/**
 * @author wangjufan
 *
 */
package com.smriti.thread;


/*
JAVA多线程：执行一个异步任务
new Thread(new Runnable() {  
   
    @Override  
    public void run() {  
        // TODO Auto-generated method stub  
    }  
}).start();  
那你就out太多了，new Thread的弊端如下：
a. 每次new Thread新建对象性能差。
b. 线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机或oom。
c. 缺乏更多功能，如定时执行、定期执行、线程中断。
相比new Thread，Java提供的四种线程池的好处在于：
a. 重用存在的线程，减少对象创建、消亡的开销，性能佳。
b. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。
c. 提供定时执行、定期执行、单线程、并发数控制等功能。


Java通过Executors提供四种线程池，分别为：
new CachedThreadPool创建一个可缓存线程池：如果线程池长度超过处理需要，可灵活回收空闲线程；若无可重用，则新建线程。
new FixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
new ScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。

new SingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

.execute(new Runnable() {  
        @Override  
        public void run() {  
        }  
}); 

(1). newCachedThreadPool
创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下：
ExecutorService cachedThreadPool = Executors.newCachedThreadPool();  
for (int i = 0; i < 10; i++) {  
    final int index = i;  
    try {  
        Thread.sleep(index * 1000);  
    } catch (InterruptedException e) {  
        e.printStackTrace();  
    }  
    cachedThreadPool.execute(new Runnable() {  
   
        @Override  
        public void run() {  
            System.out.println(index);  
        }  
    });  
}  
线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。

(2). newFixedThreadPool
创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：
ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);  
for (int i = 0; i < 10; i++) {  
    final int index = i;  
    fixedThreadPool.execute(new Runnable() {  
        @Override  
        public void run() {  
            try {  
                System.out.println(index);  
                Thread.sleep(2000);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    });  
}  
因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()。可参考PreloadDataCache。

(3) newScheduledThreadPool
创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);  
scheduledThreadPool.schedule(new Runnable() {  
    @Override  
    public void run() {  
        System.out.println("delay 3 seconds");  
    }  
}, 3, TimeUnit.SECONDS);  
表示延迟3秒执行。

定期执行示例代码如下：
scheduledThreadPool.scheduleAtFixedRate(new Runnable() {  
    @Override  
    public void run() {  
        System.out.println("delay 1 seconds, and excute every 3 seconds");  
    }  
}, 1, 3, TimeUnit.SECONDS);  
表示延迟1秒后每3秒执行一次。
ScheduledExecutorService比Timer更安全，功能更强大，后面会有一篇单独进行对比。

(4)、newSingleThreadExecutor
创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下：
ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();  
for (int i = 0; i < 10; i++) {  
    final int index = i;  
    singleThreadExecutor.execute(new Runnable() {  
        @Override  
        public void run() {  
            try {  
                System.out.println(index);  
                Thread.sleep(2000);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    });  
}  
结果依次输出，相当于顺序执行各个任务。
现行大多数GUI程序都是单线程的。Android中单线程可用于数据库操作，文件操作，应用批量安装，
应用批量删除等不适合并发但可能IO阻塞性及影响UI线程响应的操作。
*/

/* android  在一个线程中执行一段特定的代码
如何通过实现 Runnable接口得到一个能在重写的Runnable.run()方法中执行一段代码的单独的线程。
另外你可以传递一个Runnable对象到另一个对象，然后这个对象可以把它附加到一个线程，并执行它。
一个或多个执行特定操作的Runnable对象有时也被称为一个任务。
Thread和Runnable只是两个基本的线程类，通过他们能发挥的作用有限，
但是他们是强大的Android线程类的基础类，例如Android中的HandlerThread, AsyncTask和IntentService都是以它们为基础。
Thread和Runnable同时也是ThreadPoolExecutor类的基础。
ThreadPoolExecutor类能自动管理线程和任务队列，甚至可以并行执行多个线程。
定义一个实现Runnable接口的类
直接了当的方法是通过实现Runnable接口去定义一个线程类。例如：
public class PhotoDecodeRunnable implements Runnable {
    @Override
    public void run() {
        ...
    }
}
实现run()方法
在一个类里，Runnable.run() 包含执行了的代码。通常在Runnable 中执行任何操作都是可以的，
但需要记住的是，因为Runnable 不会在UI线程中运行，所以它不能直接更新UI对象，例如View 对象。
为了与UI对象进行通信，你必须使用另一项技术，在与UI线程进行通信 这一课中我们会对其进行描述。
在Runnable.run())方法的开始的地方通过调用参数为THREAD_PRIORITY_BACKGROUND 
的Process.setThreadPriority()方法来设置线程使用的是后台运行优先级。 
这个方法减少了通过Runnable创建的线程和和UI线程之间的资源竞争。
你还应该通过在Runnable 自身中调用Thread.currentThread()来存储一个引用到Runnable对象的线程。
下面这段代码展示了如何创建run()方法：
class PhotoDecodeRunnable implements Runnable {
    @Override
    public void run() {
        // 把当前的线程变成后台执行的线程
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        mPhotoTask.setImageDecodeThread(Thread.currentThread());
    }
}
 */

/*
 
 */


