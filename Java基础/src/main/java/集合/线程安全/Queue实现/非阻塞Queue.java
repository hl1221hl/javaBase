package 集合.线程安全.Queue实现;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

import com.google.common.collect.Queues;

public class 非阻塞Queue {
	/**
	 * 一个基于链接节点的无界线程安全队列。
	 * 此队列按照 FIFO（先进先出）原则对元素进行排序。
	 * 此队列不允许使用 null 元素。
	 * 
	 * 警告: 与大多数 collection 不同，size 方法不是 一个固定时间操作。由于这些队列的异步特性，确定当前元素的数量需要遍历所有元素
	 */
	@Test
	public void concurrentLinkedQueue(){
		ConcurrentLinkedQueue<String> concurrentLinkedQueue = Queues.newConcurrentLinkedQueue();
		concurrentLinkedQueue.offer("1");
		concurrentLinkedQueue.offer("2");
		concurrentLinkedQueue.offer("3");
		System.out.println(concurrentLinkedQueue.peek());
		System.out.println(concurrentLinkedQueue);
		System.out.println(concurrentLinkedQueue.poll());
		System.out.println(concurrentLinkedQueue);
		
	}
}
