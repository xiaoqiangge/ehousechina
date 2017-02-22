import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import redis.simple.Startup;
/**
 * redis Hash命令在线文档：
 * http://doc.redisfans.com/hash/index.html
 * @author 小强哥
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class HashCommandTest {
	
	enum HashKey {
		HASHKEY {
			@Override
			public String toString() {
				return "userhash";
			}
		}

	}
	
 	@Autowired
	private StringRedisTemplate template;
	
	/**
	 * 将哈希表 key 中的域 field 的值设为 value 。
	 */
	@Test
	public void testHset() {
		template.opsForHash().put(HashKey.HASHKEY.toString(), SimpleData.ZHANGSAN.getKey(), SimpleData.ZHANGSAN.getValue());
	}
	/**
	 * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
	 */
	@Test
	public void testHdel() {
		template.opsForHash().delete(HashKey.HASHKEY.toString(), SimpleData.LISI.getKey());
	}
	/**
	 * 查看哈希表 key 中，给定域 field 是否存在。
	 */
	@Test
	public void testHexists() {
		boolean isHas=template.opsForHash().hasKey(HashKey.HASHKEY.toString(), SimpleData.ZHANGSAN.getKey());
		log.info(">> {}",isHas);
	}
	/**
	 * 返回哈希表 key 中给定域 field 的值。
	 */
	@Test
	public void testHget() {
		String value=(String)template.opsForHash().get(HashKey.HASHKEY.toString(), SimpleData.LISI.getKey());
		log.info(">> {}",value);
	}
	/**
	 * 将哈希表 key 中的域 field 的值设为 value 。
	 */
	@Test
	public void testHgetall() {
		// TODO spring没有提供getall命令函数，后面验证
		/*List list=template.opsForHash().multiGet(HashKey.HASHKEY.toString(), new ArrayList(){{
			add(SimpleData.LISI.getKey());
			add(SimpleData.ZHANGSAN.getKey());
		}});
		for(int i=0;i<list.size();i++){
			log.info(">> {}",list.get(i));
		}*/
		List list=template.opsForHash().multiGet("link|2", new ArrayList(){{
			add("title");
			add("ctime");
			add("url");
		}});
		
		for(int i=0;i<list.size();i++){
			log.info(">> {}",list.get(i));
		}
	}
	/**
	 * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。
	 */
	@Test
	public void testHsetnx() {
		boolean isOk=template.opsForHash().putIfAbsent(HashKey.HASHKEY.toString(), SimpleData.LISI.getKey(), SimpleData.LISI.getValue());
		log.info(">> {}",isOk);
	}
	/**
	 * 返回哈希表 key 中所有域的值。
	 */
	@Test
	public void testHvals() {
		List list=template.opsForHash().values(HashKey.HASHKEY.toString());
		for(int i=0;i<list.size();i++){
			log.info(">> {}",list.get(i));
		}
	}
	/**
	 * 为哈希表 key 中的域 field 的值加上增量 increment 。
	 */
	@Test
	public void testHincrby() {
		long value=template.opsForHash().increment(HashKey.HASHKEY.toString(), SimpleData.LISI.getKey(), 1);
//		long value=template.opsForHash().increment(HashKey.HASHKEY.toString(), SimpleData.LISI.getKey(), -1);
		log.info(">> {}",value);
	}
	/**
	 * 为哈希表 key 中的域 field 加上浮点数增量 increment 。
	 */
	@Test
	public void testHincrbyfloat() {
		double value=template.opsForHash().increment(HashKey.HASHKEY.toString(), SimpleData.LISI.getKey(), 1.1);
//		long value=template.opsForHash().increment(HashKey.HASHKEY.toString(), SimpleData.LISI.getKey(), -1.1);
		log.info(">> {}",value);
	}
	/**
	 * 返回哈希表 key 中的所有域。
	 */
	@Test
	public void testHkeys() {
		Set set=template.opsForHash().keys(HashKey.HASHKEY.toString());
		for(Object key:set){
			log.info(">> {}",key);
		}
	}
	/**
	 * 返回哈希表 key 中域的数量。
	 */
	@Test
	public void testHlen() {
		long size=template.opsForHash().size(HashKey.HASHKEY.toString());
		log.info(">> {}",size);
	}
	/**
	 * 返回哈希表 key 中，所有的域和值。
	 */
	@Test
	public void testHmget() {
		List list=template.opsForHash().multiGet(HashKey.HASHKEY.toString(), new ArrayList(){{
			add(SimpleData.LISI.getKey());
			add(SimpleData.ZHANGSAN.getKey());
		}});
		for(int i=0;i<list.size();i++){
			log.info(">> {}",list.get(i));
		}
	}
	/**
	 * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
	 */
	@Test
	public void testHmset() {
		template.opsForHash().putAll(HashKey.HASHKEY.toString(), new HashMap(){{
			put(SimpleData.LISI.getKey(),SimpleData.LISI.getValue());
			put(SimpleData.ZHANGSAN.getKey(),SimpleData.ZHANGSAN.getValue());
		}});
	}
	@Test
	public void testHscan() {
		// TODO
	}
}
