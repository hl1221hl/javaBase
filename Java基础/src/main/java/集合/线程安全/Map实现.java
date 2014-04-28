package 集合.线程安全;

import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.junit.Test;

public class Map实现 {

	/**
	 * ConcurrentHashMap采用了锁分离的技术来实现确保线程安全的情况下达到较好的性能。
	 * 它把整个hash table分成好多个小的hash table（即Segment），
	 * 每个Segment都有自己的锁来保证线程安全，这样就使得各个Segment都可以独立地进行管理，而不需要争用锁。
	 * 
	 * 默认支持16个并发写
	 */
	@Test
	public void concurrentHashMap(){
		System.out.println("==========ConcurrentHashMap==========");
		ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
		concurrentHashMap.put("1", "1");
		concurrentHashMap.put("2", "2");
		concurrentHashMap.put("3", "3");
		System.out.println(concurrentHashMap);
	}
	
	/**
	 * ConcurrentSkipListMap是线程安全的有序的集合，适用于高并发的场景。
	 * 因为ConcurrentSkipListMap是基于“跳跃列表（skip list）”实现的，只要多个线程没有同时修改集合的同一个部分，那么在正常读、写集合的操作中不会出现竞争现象。
	 * ConcurrentSkipListSet和TreeMap类似,使用元素的自然顺序对元素进行排序，或者根据创建 map 时提供的 Comparator 进行排序，具体取决于使用的构造方法。
	 */
	@Test
	public void concurrentSkipListMap(){
		System.out.println("==========ConcurrentSkipListMap==========");
		ConcurrentSkipListMap<String, String> concurrentSkipListMap = new ConcurrentSkipListMap<String, String>(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		concurrentSkipListMap.put("1", "1");
		concurrentSkipListMap.put("3", "3");
		concurrentSkipListMap.put("2", "2");
		concurrentSkipListMap.put("4", "4");
		concurrentSkipListMap.put("0", "0");
		
		System.out.println(concurrentSkipListMap.lastKey());
		System.out.println(concurrentSkipListMap.firstKey());
		//小于
		System.out.println(concurrentSkipListMap.lowerKey("2"));
		//小于等于
		System.out.println(concurrentSkipListMap.floorKey("2"));
		//大于
		System.out.println(concurrentSkipListMap.higherKey("2"));
		//大于等于
		System.out.println(concurrentSkipListMap.ceilingKey("2"));
		System.out.println(concurrentSkipListMap.subMap("2", "4"));
		System.out.println(concurrentSkipListMap);
	}
}
