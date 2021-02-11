package core.archive;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.springframework.boot.task.TaskSchedulerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class RunnerBackgroundMode {
	
	@Bean(name = "deamonThread")
	public Executor deamonThread() {
		return new ThreadPoolTaskExecutor();
	}
	
//    private List<Runnable> tasks;
//    
//	public RunnerBackgroundMode(List<Runnable> tasks) {
//		super();
//		this.tasks = tasks;
//	}
//
//	@Override
//	public void run() {
//		ExecutorService ex;
//		for(int i = 0; i<tasks.size(); i++) {
//			ex = Executors.newCachedThreadPool(new ThreadFactory() {
//				@Override
//				public Thread newThread(Runnable r) {
//					Thread thread = new Thread(r);
//					thread.setName("DeamonThread");
//					thread.setDaemon(true);
//					return thread;
//				}
//			});
//			ex.execute(tasks.get(0));
//		}
//	}
	
}
