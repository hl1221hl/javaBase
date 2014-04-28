package 集合.非线程安全;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.WeakHashMap;

import org.junit.Test;

import com.google.common.collect.Maps;

public class Map实现 {

	/**
	 * 基于hash表的实现。（用它来代替Hashtable。）提供时间恒定的插入与查询。
	 * 它不保证迭代顺序；
	 * 可以通过构造函数来调节其性能
	 * 
	 * 如果hashcode函数返回一个定值，即所有对象的hashcode相同时，
	 * 当尝试插入第二个数据（或以上）时，会发现程序还会去调用equals方法，
	 * 并且该函数返回false时才回插入hashset(在同一个hashcode桶下形成一个链)；
	 * 当hashcode返回不同时会忽略equals函数并直接插入hashset。
	 */
	@Test
	public void hashMap(){
		System.out.println("==========HashMap==========");
		HashMap<String, String> hashMap = Maps.newHashMap();
		hashMap.put("1", "1");
		hashMap.put("2", "2");
		hashMap.put("3", "4");
		System.out.println(hashMap);
	}
	
	/**
	 * 很像HashMap，但是用Iterator进行遍历的时候，它会按插入顺序或最先使用的顺序（least-recently-used(LRU)order）进行访问。
	 * 除了用Iterator外，其他情况下，只是比HashMap稍慢一点。
	 * 用Iterator的情况下，由于是使用链表来保存内部顺序，因此速度会更快
	 */
	@Test
	public void linkedHashMap(){
		System.out.println("==========LinkedHashMap==========");
		LinkedHashMap<String, String> linkedHashMap = Maps.newLinkedHashMap();
		
		linkedHashMap.put("1", "1");
		linkedHashMap.put("3", "4");
		linkedHashMap.put("2", "2");
		System.out.println(linkedHashMap);
	}
	
	/**
	 * 基于红黑树数据结构的实现。
	 * 当你查看键或pair时，会发现它们时按顺序（根据Comparable或Comparator，我们过一会讲）排列的。
	 * TreeMap的特点是，你所得到的是一个有序的Map。
	 * TreeMap是Map中唯一有subMap()方法的实现。这个方法能让你获取这个树中的一部分
	 */
	@Test
	public void treeMap(){
		System.out.println("==========TreeMap==========");
		TreeMap<String, String> treeMap = Maps.newTreeMap(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		treeMap.put("1", "1");
		treeMap.put("3", "3");
		treeMap.put("2", "2");
		treeMap.put("4", "4");
		System.out.println(treeMap.lastKey());
		System.out.println(treeMap.firstKey());
		//小于
		System.out.println(treeMap.lowerKey("2"));
		//小于等于
		System.out.println(treeMap.floorKey("2"));
		//大于
		System.out.println(treeMap.higherKey("2"));
		//大于等于
		System.out.println(treeMap.ceilingKey("2"));
		System.out.println(treeMap.subMap("2", "4"));
		System.out.println(treeMap);
	}
	
	/**
	 * Key是WeakReference的HashMap
	 * 此种Map的特点是，当除了自身有对key的引用外，此key没有其他引用那么此map会自动丢弃此值
	 * 
	 * 清除动作在WeakHashMap是主要通过expungeStaleEntries这个函数的来实现的。
	 * 基本上只要对WeakHashMap的内容进行访问就会调用这个函数，从而达到清除其内部不在为外部引用的条目
	 * 
	 */
	@Test
	public void weakHashMap(){
		System.out.println("==========WeakHashMap==========");
		WeakHashMap<String, String> weakHashMap = new WeakHashMap<String, String>();
		weakHashMap.put(new String("1"), "1");
		weakHashMap.put("3", "4");//"3"在常量池中, 会一直有强连接, 无法清理
		
		String key2 = new String("2");
		weakHashMap.put(key2, "2");//只有使用weakHashMap时才会触发清理动作
		System.out.println(weakHashMap);
		System.gc();
		
		System.out.println(weakHashMap.get("1"));
		System.out.println(weakHashMap);
	}
}
