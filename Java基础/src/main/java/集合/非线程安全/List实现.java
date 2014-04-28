package 集合.非线程安全;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import com.google.common.collect.Lists;


public class List实现 {
	
	/**
	 * 采用数组的结构方式，所以ArrayList所提供的随机访问方法很高效，
	 * 同时有列表最后位置添加和删除元素的效率也很高，不适合在其它位置频繁进行插入和删除元素操作，
	 * 因此它要对数组中很大部分元素要进行移动才能完成，这就使得插入和删除元素的代价为O(n)，n为元素个数
	 */
	@Test
	public void arrayList(){
		System.out.println("==========arrayList==========");
		List<String> arrayList = Lists.newArrayList("String", "1", "2");
		arrayList.add("3");
		System.out.println(arrayList.get(0));
		System.out.println(arrayList);
	}
	
	
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
}
