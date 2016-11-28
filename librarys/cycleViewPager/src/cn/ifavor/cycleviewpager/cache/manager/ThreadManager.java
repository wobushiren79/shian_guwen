package cn.ifavor.cycleviewpager.cache.manager;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadManager {
	private static ThreadManager instance = new ThreadManager();

	private ThreadManager() {
	}

	public static ThreadManager getInstance() {
		return instance;
	}

	private ThreadPoolProxy shortPool;
	private ThreadPoolProxy longPool;


	public synchronized ThreadPoolProxy createShortPool() {
		if (shortPool == null) {
			shortPool = new ThreadPoolProxy(3, 3, 5000);
		}
		return shortPool;
	}


	public synchronized ThreadPoolProxy creatLongPool() {
		if (longPool == null) {
			longPool = new ThreadPoolProxy(5, 5, 5000);
		}
		return longPool;
	}


	public class ThreadPoolProxy {
		private ThreadPoolExecutor mThreadPoolExecutor;
		private int corePoolSize;
		private int maximumPoolSize;
		private long keepAliveTime;

		public ThreadPoolProxy(int corePoolSize, int maximumPoolSize,
				long keepAliveTime) {
			this.corePoolSize = corePoolSize;
			this.maximumPoolSize = maximumPoolSize;
			this.keepAliveTime = keepAliveTime;
		}


		public void execute(Runnable runnable) {
			if (mThreadPoolExecutor == null) {


				mThreadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
						maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
						new LinkedBlockingDeque<Runnable>(10));
			}

			mThreadPoolExecutor.execute(runnable);

		}


		public void cancel(Runnable runnable) {
			if (mThreadPoolExecutor != null && !mThreadPoolExecutor.isShutdown() &&!mThreadPoolExecutor.isTerminated()) {
				mThreadPoolExecutor.remove(runnable);
			}
			
		}
	}

}
