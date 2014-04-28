package 集合.guaua;

import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;

public class GuauaMulti {
	
	/**
	 * Multiset是什么？顾名思义，Multiset和Set的区别就是可以保存多个相同的对象。
	 * 
	 * 在JDK中，List和Set有一个基本的区别，就是List可以包含多个相同对象，且是有顺序的，
	 * 而Set不能有重复，且不保证顺序（有些实现有顺序，例如LinkedHashSet和SortedSet等）
	 * 所以Multiset占据了List和Set之间的一个灰色地带：允许重复，但是不保证顺序。 
	 * 
	 * 常见使用场景：Multiset有一个有用的功能，就是跟踪每种对象的数量，所以你可以用来进行数字统计。 常见的普通实现方式如下：
	 *
	 * Multiset的实现　
	 * Map					Corresponding Multiset		Supports null elements
	 * ----------------------------------------------------------------------------
	 * HashMap				HashMultiset				Yes
	 * TreeMap				TreeMultiset				Yes (if the comparator does)
	 * LinkedHashMap		LinkedHashMultiset			Yes
	 * ConcurrentHashMap	ConcurrentHashMultiset		No
	 * ImmutableMap			ImmutableMultiset			No
	 */
	@Test
	public void multiSet(){
		System.out.println("=============Multiset===============");
		Multiset<String> sets = HashMultiset.create();
		sets.addAll(ImmutableList.of("1","2","2","3"));
		for(String s : sets){
			System.out.println(String.format("value:%s count:%s", s, sets.count(s)));
		}
		System.out.println(sets);
	}
		
	/**
	　* Guava的Multimap就提供了一个方便地把一个键对应到多个值的数据结构。
	　* 
	　*  Multimap提供了丰富的实现，所以你可以用它来替代程序里的Map<K, Collection<V>>，具体的实现如下：
	
	　*  Implementation				Keys 的行为类似		Values的行为类似
	　*  -------------------------------------------------------------
	　*  ArrayListMultimap			HashMap				ArrayList
	　*  HashMultimap				HashMap				HashSet
	　*  LinkedListMultimap			LinkedHashMap*		LinkedList*
	　*  LinkedHashMultimap			LinkedHashMap		LinkedHashSet
	　*  TreeMultimap				TreeMap				TreeSet
	　*  ImmutableListMultimap		ImmutableMap		ImmutableList
	　*  ImmutableSetMultimap		ImmutableMap		ImmutableSet
	 */
	@Test
	public void multiMap(){
		
		System.out.println("============Multimap================");
		Multimap<String, String> multimap = ArrayListMultimap.create();
		multimap.put("1", "1");
		multimap.put("1", "2");
		multimap.put("1", "3");
		multimap.put("2", "2");
		multimap.put("3", "3");
		for(String key : multimap.keys()){
			System.out.println(String.format("key:%s  value:%s", key, multimap.get(key)));
		}
		System.out.println(multimap);
	}
	
	/**BiMap提供了一种新的集合类型，它提供了key和value的双向关联的数据结构。*/
	@Test
	public void biMap(){
		System.out.println("============BiMap================");
		BiMap<String, Integer> biMap = HashBiMap.create();
		biMap.put("1", 1);
		biMap.forcePut("1", 2);
		BiMap<Integer, String> inverseView = biMap.inverse();
		System.out.println(inverseView.get(2));
		System.out.println(biMap);
	}
	
	
	/**
	 *  为Map<FirstName, Map<LastName, Person>> Guava提供了一个新的集合类型－Table集合类型，
	 *  来支持这种数据结构的使用场景。Table支持“row”和“column”，而且提供多种视图。　
	 *  
	 *  Table有以下实现： 
	 *  -----------------------------------------------------------
	 *  HashBasedTable：基于HashMap<R, HashMap<C, V>>的实现。 
	 *  TreeBasedTable：基于TreeMap<R, TreeMap<C, V>>的实现。 
	 *  ImmutableTable：基于ImmutableMap<R, ImmutableMap<C, V>>的实现。（注意，ImmutableTable已对稀疏和密集集合做了优化） 
	 *  ArrayTable：ArrayTable是一个需要在构建的时候就需要定下行列的表格。这种表格由二维数组实现，这样可以在密集数据的表格的场合，提高时间和空间的效率。
	 */
	@Test
	public void table(){
		System.out.println("===========Table=================");
		Table<String, Integer, String> table = HashBasedTable.create();
		table.put("r1", 1, "v1");
		table.put("r1", 2, "v2");
		table.put("r1", 3, "v3");
		table.put("r2", 1, "v11");
		table.put("r2", 2, "v22");
		table.put("r2", 3, "v33");
		table.put("r2", 4, "v33");
		
		System.out.println(table.row("r1"));
		System.out.println(table.column(1));
		System.out.println(table.column(1).get("r1"));
		System.out.println(table.column(4));
		System.out.println(table);
	}
}
