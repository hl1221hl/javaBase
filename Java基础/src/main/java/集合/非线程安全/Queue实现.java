package 集合.非线程安全;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;

public class Queue实现 {
	
	/**
	 * Linked List方式是双向链表的实现方式，List和Deque接口均由LinkedList类实现。
	 * LinkedList由于采用了双向链表的实现形式，它与ArrayList有着互补的特性。
	 * 它支持高效的频繁插入和删除元素操作，但随机访问的效率却很低。
	 * LinkedList也实现了Deque接口，LinkedList除了是链表，同时也是队列和双端队列，并且也可以把它看作栈（它提供了栈操作的两个方法）。
	 * LinkedList作为队列来说，它在两端的插入和删除元素操作的代价同样是O(1)，但由于里面要维护双向链表的数结构，与ArrayDeque相比，效率稍低一点。
	 */
	@Test
	public void linkedList(){
		System.out.println("==========linkedList==========");
		LinkedList<String> linkedList = Lists.newLinkedList();
		linkedList.add("1");
		linkedList.add("2");
		//Deque
		linkedList.offerFirst("start");
		linkedList.offerLast("end");
		linkedList.poll();
		linkedList.pollFirst();
		linkedList.pollLast();
		linkedList.peek();
		System.out.println(linkedList.getFirst());
		ListIterator<String> listIterator = linkedList.listIterator();
		while(listIterator.hasNext()){
			String next = listIterator.next();
			listIterator.add("1.1");
		}
		System.out.println(linkedList);
	}
	
	/**
	 * Deque 接口的大小可变数组的实现。
	 * 数组双端队列没有容量限制；它们可根据需要增加以支持使用。
	 * 它们不是线程安全的；在没有外部同步时，它们不支持多个线程的并发访问。
	 * 禁止 null 元素。此类很可能在用作堆栈时快于 Stack，在用作队列时快于 LinkedList。
	 */
	@Test
	public void arrayDeque(){
		System.out.println("==========ArrayDeque==========");
		ArrayDeque<String> arrayDeque = Queues.newArrayDeque();
		arrayDeque.offer("1");
		arrayDeque.offer("2");
		arrayDeque.offerFirst("start");
		arrayDeque.offerLast("end");
		System.out.println(arrayDeque.pollFirst());
		System.out.println(arrayDeque);
	}
	
	/**
	 * 一个基于优先级堆的无界优先级队列。
	 * 优先级队列的元素按照其自然顺序进行排序，或者根据构造队列时提供的 Comparator 进行排序，具体取决于所使用的构造方法。
	 * 优先级队列不允许使用 null 元素。
	 * 依靠自然顺序的优先级队列还不允许插入不可比较的对象（这样做可能导致 ClassCastException）。
	 * 非线程安全
	 */
	@Test
	public void priorityQueue(){
		System.out.println("==========PriorityQueue==========");
		PriorityQueue<String> priorityQueue = new PriorityQueue<String>(11, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
//		PriorityQueue<String> priorityQueue = new PriorityQueue<String>();
		priorityQueue.offer("3");
		priorityQueue.offer("1");
		priorityQueue.offer("2");
		System.out.println(priorityQueue.peek());
		System.out.println(priorityQueue);
		System.out.println(priorityQueue.poll());
		System.out.println(priorityQueue.poll());
		System.out.println(priorityQueue);
	}
	
}
