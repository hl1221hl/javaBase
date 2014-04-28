package 集合.线程安全;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Set实现 {
	
	/**
	 * 同copyOnWriteArrayList一样, 仅仅在add/addAll的时候检测元素是否存在，如果存在就不加入集合中
	 */
	@Test
	public void copyOnWriteArraySet(){
		System.out.println("==========CopyOnWriteArraySet==========");
		CopyOnWriteArraySet<String> copyOnWriteArraySet = Sets.newCopyOnWriteArraySet();
		copyOnWriteArraySet.add("1");
		copyOnWriteArraySet.add("2");
		copyOnWriteArraySet.add("3");
		Iterator<String> iterator = copyOnWriteArraySet.iterator();
		while(iterator.hasNext()){
			String next = iterator.next();
			System.out.println(next);
		}
		System.out.println(copyOnWriteArraySet);
	}
	
	
	/**
	 * ConcurrentSkipListSet是线程安全的有序的集合，适用于高并发的场景。
	 * 因为ConcurrentSkipListSet是基于“跳跃列表（skip list）”实现的，只要多个线程没有同时修改集合的同一个部分，那么在正常读、写集合的操作中不会出现竞争现象。
	 * ConcurrentSkipListSet和TreeSet类似,使用元素的自然顺序对元素进行排序，或者根据创建 set 时提供的 Comparator 进行排序，具体取决于使用的构造方法。
	 * ConcurrentSkipListSet是通过ConcurrentSkipListMap实现的
	 * 
	 */
	@Test
	public void concurrentSkipListSet(){
		System.out.println("==========concurrentSkipListSet==========");
		final ConcurrentSkipListSet<String> concurrentSkipListSet = new  ConcurrentSkipListSet<String>(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		ExecutorService pool = Executors.newCachedThreadPool();
		List<Future> fs = Lists.newArrayList();
		for(int i=0;i<10;i++){
			final int ii = i;
			fs.add(pool.submit(new Runnable() {
				public void run() {
					concurrentSkipListSet.add(String.valueOf(ii));
				}
			}));
		}
		for(Future f : fs){
			try {
				f.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		Iterator<String> iterator = concurrentSkipListSet.iterator();
//		while(iterator.hasNext()){
//			String next = iterator.next();
//			System.out.println(next);
//		}
		System.out.println(concurrentSkipListSet.last());
		System.out.println(concurrentSkipListSet.first());
		//小于
		System.out.println(concurrentSkipListSet.lower("3"));
		//小于等于
		System.out.println(concurrentSkipListSet.floor("3"));
		//大于
		System.out.println(concurrentSkipListSet.higher("3"));
		//大于等于
		System.out.println(concurrentSkipListSet.ceiling("3"));
		System.out.println(concurrentSkipListSet.subSet("2", "4"));
		System.out.println(concurrentSkipListSet);
	}
}
