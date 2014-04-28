package 集合.线程安全.Queue实现;

import java.util.Comparator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.collect.Queues;

public class 阻塞Queue {

	
	/**
	 * LinkedBlockingQueue是无界的，是一个无界缓存的等待队列。
	 * 基于链表的阻塞队列，内部维持着一个数据缓冲队列（该队列由链表构成）。
	 * 链接队列的吞吐量通常要高于基于数组的队列，但是在大多数并发应用程序中，其可预知的性能要低。
	 */
	@Test
	public void linkedBlockingQueue(){
		LinkedBlockingQueue<String> linkedBlockingQueue = Queues.newLinkedBlockingQueue();
		try {
			linkedBlockingQueue.put("1");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			linkedBlockingQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ArrayBlockingQueue是有界的，是一个有界缓存的等待队列。
	 * 基于数组的阻塞队列，同LinkedBlockingQueue类似，内部维持着一个定长数据缓冲队列（该队列由数组构成）
	 */
	@Test
	public void arrayBlaockingQueue(){
		ArrayBlockingQueue<String> arrayBlockingQueue = Queues.newArrayBlockingQueue(200);
		try {
			arrayBlockingQueue.put("1");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			arrayBlockingQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 一种阻塞队列，其中每个插入操作必须等待另一个线程的对应移除操作 ，反之亦然。
	 * 同步队列没有任何内部容量，甚至连一个队列的容量都没有。
	 * 
	 * 同步队列类似于 CSP 和 Ada 中使用的 rendezvous 信道。
	 * 它非常适合于传递性设计，在这种设计中，在一个线程中运行的对象要将某些信息、事件或任务传递给在另一个线程中运行的对象，它就必须与该对象同步。
	 */
	@Test
	public void synchronousQueue(){
		final SynchronousQueue<String> synchronousQueue = Queues.newSynchronousQueue();
		
		ExecutorService es = Executors.newFixedThreadPool(2);
		
		es.execute(new Runnable() {
			public void run() {
				try {
					synchronousQueue.put("1");//必须有线程获取才能put进去, 不然一直阻塞
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		es.execute(new Runnable() {
			public void run() {
				try {
					System.out.println(synchronousQueue.take());//必须有线程put才能获取, 不然一直阻塞
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 一个无界阻塞队列，它使用与类 PriorityQueue 相同的顺序规则，并且提供了阻塞获取操作。
	 * 
	 * 虽然此队列逻辑上是无界的，但是资源被耗尽时试图执行 add 操作也将失败（导致 OutOfMemoryError）。
	 * 
	 * 此类不允许使用 null 元素。依赖自然顺序的优先级队列也不允许插入不可比较的对象（这样做会导致抛出 ClassCastException）。
	 * 
	 * 在此类上进行的操作不保证具有同等优先级的元素的顺序
	 */
	@Test
	public void priorityBlockingQueue(){
		PriorityBlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<String>(200, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		priorityBlockingQueue.put("2");//此队列的put方法不阻塞
		priorityBlockingQueue.put("3");//此队列的put方法不阻塞
		priorityBlockingQueue.put("1");//此队列的put方法不阻塞
		try {
			System.out.println(priorityBlockingQueue.take());
			System.out.println(priorityBlockingQueue.take());
			System.out.println(priorityBlockingQueue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 内部是使用PriorityQueue实现的
	 * Delayed 元素的一个无界阻塞队列，只有在延迟期满时才能从中提取元素。
	 * 当一个元素的 getDelay(TimeUnit.NANOSECONDS) 方法返回一个小于等于 0 的值时，将发生到期。
	 * 
	 * 
	 * 适用场景:
	 * 	a) 关闭空闲连接。服务器中，有很多客户端的连接，空闲一段时间之后需要关闭之。
	 * 	b) 缓存。缓存中的对象，超过了空闲时间，需要从缓存中移出。
	 * 	c) 任务超时处理。在网络协议滑动窗口请求应答式交互时，处理超时未响应的请求
	 * 等...
	 */
	@Test
	public void delayQueue(){
		DelayQueue<TestDelay> priorityBlockingQueue = new DelayQueue<TestDelay>();
		priorityBlockingQueue.put(new TestDelay("1", TimeUnit.SECONDS.toMillis(1)));//此队列的put方法不阻塞
		priorityBlockingQueue.put(new TestDelay("2", TimeUnit.SECONDS.toMillis(3)));//此队列的put方法不阻塞
		priorityBlockingQueue.put(new TestDelay("3", TimeUnit.SECONDS.toMillis(5)));//此队列的put方法不阻塞
		try {
			System.out.println(priorityBlockingQueue.take());
			System.out.println(priorityBlockingQueue.take());
			System.out.println(priorityBlockingQueue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private class TestDelay implements Delayed{
		private String a;
		private long endTime;//绝对到期时间
		
		/**
		 * @param a
		 * @param lazy 相对到期时间
		 */
		public TestDelay(String a, long lazy) {
			this.a = a;
			endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(lazy, TimeUnit.MILLISECONDS);
		}

		/* (non-Javadoc)该方法提供与此接口的 getDelay 方法一致的排序
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(Delayed o) {
			long result =  this.endTime - ((TestDelay)o).endTime;
			return result>0?1:(result<0?-1:0);
		}

		/* (non-Javadoc)剩余多长时间到期
		 * @see java.util.concurrent.Delayed#getDelay(java.util.concurrent.TimeUnit)
		 */
		public long getDelay(TimeUnit unit) {
			return unit.convert(endTime - System.nanoTime(), TimeUnit.NANOSECONDS);
		}
		
		@Override
		public String toString() {
			return String.format("str:%s time:%s", a, endTime);
		}
	}
	
	
	
	/**
	 * 一个基于已链接节点的、任选范围的阻塞双端队列。
	 */
	@Test
	public void linkedBlockingDeque(){
		LinkedBlockingDeque<String> linkedBlockingDeque = Queues.newLinkedBlockingDeque();
		try {
			linkedBlockingDeque.put("2");//此队列的put方法不阻塞
			linkedBlockingDeque.put("3");//此队列的put方法不阻塞
			linkedBlockingDeque.put("1");//此队列的put方法不阻塞
			linkedBlockingDeque.putFirst("start");
			linkedBlockingDeque.putLast("end");
			System.out.println(linkedBlockingDeque.takeFirst());
			System.out.println(linkedBlockingDeque.takeLast());
			System.out.println(linkedBlockingDeque);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
