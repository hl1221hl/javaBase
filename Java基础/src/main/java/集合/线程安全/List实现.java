package 集合.线程安全;

import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

import com.google.common.collect.Lists;


public class List实现 {
	
	/**
	 * CopyOnWriteArrayList是ArrayList 的一个线程安全的变体，其中所有可变操作（add、set等等）都是通过对底层数组进行一次新的复制来实现的
	 * CopyOnWriteArrayList中写操作需要大面积复制数组，所以性能肯定很差，
	 * 但是读操作因为操作的对象和写操作不是同一个对象，读之 间也不需要加锁，
	 * 读和写之间的同步处理只是在写完后通过一个简单的“=”将引用指向新的数组对象上来，
	 * 这个几乎不需要时间，这样读操作就很快很安全，适合 在多线程里使用，
	 * 绝对不会发生ConcurrentModificationException ，
	 * 所以最后得出结论：CopyOnWriteArrayList适合使用在读操作远远大于写操作的场景里，比如缓存
	 */
	@Test
	public void CopyOnWriteArrayList(){
		System.out.println("==========CopyOnWriteArrayList==========");
		CopyOnWriteArrayList<String> copyOnWriteArrayList = Lists.newCopyOnWriteArrayList();
		copyOnWriteArrayList.add("1");
		copyOnWriteArrayList.add("2");
		copyOnWriteArrayList.add("3");
		System.out.println(copyOnWriteArrayList.get(0));
		ListIterator<String> listIterator = copyOnWriteArrayList.listIterator();
		while(listIterator.hasNext()){
			String next = listIterator.next();
			System.out.println(next);
		}
		System.out.println(copyOnWriteArrayList);
	}
	
}
