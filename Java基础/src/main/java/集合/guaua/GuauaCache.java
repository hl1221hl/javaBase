package 集合.guaua;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class GuauaCache {
	
	@Test
	public void cache() {
		LoadingCache<String, String> cache = CacheBuilder.newBuilder()
			/*
			 * 缓存大小, 作为高速缓存大小的增长接近最大时，缓存驱逐是不太可能被再次使用的条目。
			 * 例如，缓存可能驱逐的项目，因为它并没有被最近或经常使用。 
			 * 当大小为零，元素将被加载到高速缓存中后，立即被逐出。这可以在测试中是有用的，或暂时禁用缓存无需更改代码。
			 */
			.maximumSize(1000)
			.weakKeys()//弱引用key
			.weakValues()//弱引用value
			.softValues()//软引用value
			.expireAfterAccess(10000, TimeUnit.SECONDS)//这个方法是根据某个键值对最后一次访问之后多少时间后移除
			.expireAfterWrite(10000, TimeUnit.SECONDS)//这个方法是根据某个键值对被创建或值被替换后多少时间移除
			.build(new CacheLoader<String, String>() {
				/*
				 * 当cache中没有缓存时, 会使用本方法重新加载
				 */
				@Override
				public String load(String key) throws Exception {
					return null;
				}
			});
		
	}
}
