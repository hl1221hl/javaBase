package 集合.非线程安全;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import org.junit.Test;

import com.google.common.collect.Sets;

public class Set实现 {
	
	/**
	 * 为优化查询速度而设计的Set, 此类实现 Set 接口，由哈希表（实际上是一个 HashMap 实例）支持。
	 * 它不保证 set 的迭代顺序；
	 * 此类允许使用 null 元素。
	 * 
	 * 如果hashcode函数返回一个定值，即所有对象的hashcode相同时，
	 * 当尝试插入第二个数据（或以上）时，会发现程序还会去调用equals方法，
	 * 并且该函数返回false时才回插入hashset(在同一个hashcode桶下形成一个链)；
	 * 当hashcode返回不同时会忽略equals函数并直接插入hashset。
	 * 
	 * 此实现不是同步的 
	 */
	@Test
	public void hashSet(){
		System.out.println("==========hashSet==========");
		HashSet<String> hashSet = Sets.newHashSet("1", "2", "3");
		hashSet.add("3");
		hashSet.add("1");
		hashSet.add("2");
		System.out.println(hashSet);
	}
	
	/**
	 * 一个在内部使用链表的Set，既有HashSet的查询速度，又能保存元素被加进去的顺序（插入顺序）。
	 * 用Iterator遍历Set的时候，它是按插入顺序进行访问的。
	 */
	@Test
	public void linkedHashSet(){
		System.out.println("==========linkedHashSet==========");
		LinkedHashSet<String> treeSet = Sets.newLinkedHashSet();
		treeSet.add("3");
		treeSet.add("1");
		treeSet.add("2");
		System.out.println(treeSet);
	}
	
	/**
	 * 基于 TreeMap 的 NavigableSet的(红黑树)实现，（实际上是一个 TreeMap 实例）。
	 * 使用元素的自然顺序对元素进行排序，或者根据创建 set 时提供的 Comparator 进行排序，具体取决于使用的构造方法。
	 * 此实现不是同步的
	 */
	@Test
	public void treeSet(){
		System.out.println("==========TreeSet==========");
		TreeSet<String> treeSet = Sets.newTreeSet(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		treeSet.add("3");
		treeSet.add("1");
		treeSet.add("2");
		treeSet.add("4");
		
		System.out.println(treeSet.last());
		System.out.println(treeSet.first());
		//小于
		System.out.println(treeSet.lower("2"));
		//小于等于
		System.out.println(treeSet.floor("2"));
		//大于
		System.out.println(treeSet.higher("2"));
		//大于等于
		System.out.println(treeSet.ceiling("2"));
		System.out.println(treeSet.subSet("2", "4"));
		
		System.out.println(treeSet);
	}
}
