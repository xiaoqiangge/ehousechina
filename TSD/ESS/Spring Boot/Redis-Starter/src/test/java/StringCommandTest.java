import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import redis.simple.Startup;
/**
 * redis String命令在线文档：
 * http://doc.redisfans.com/string/index.html
 * @author 小强哥
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class StringCommandTest {
	@Autowired
	private StringRedisTemplate template;
	
	/**
	 * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。
		如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
	 */
	@Test
	public void testAppend() {
		int keySize=template.opsForValue().append(SimpleData.LISI.getKey(), "-test");
		log.info(">> {}",keySize);
	}
	/**
	 * 将字符串值 value 关联到 key 。
	 */
	@Test
	public void testSet() {
		/*
		 * 简单的设置一个k、v
		 */
		template.opsForValue().set(SimpleData.LISI.getKey(), SimpleData.LISI.getValue());
		/*
		 * 设置一个k、v，同时设置k、v的有效时间，对同一个k多次执行该操作不会增加多个key，只会
		 * 重置该k的时间
		 */
//		template.opsForValue().set("zhang","100",30,TimeUnit.SECONDS);
	}
	/**
	 * SETBIT
	 */
	@Test
	public void testSetbit() {
		boolean isOk=template.opsForValue().setBit(SimpleData.LISI.getKey(), 2, true);
		log.info(">> {}",isOk);
	}
	/**
	 * GETBIT
	 */
	@Test
	public void testGetbit() {
		boolean bool=template.opsForValue().getBit(SimpleData.LISI.getKey(), 1);
		log.info(">> {}",bool);
	}
	/**
	 * BITCOUNT
	 */
	@Test
	public void testBitCount() {
		// TODO
	}
	/**
	 * BITOP
	 */
	@Test
	public void testBitop() {
		// TODO
	}
	/**
	 * 将 key 中储存的数字值减一。
	 */
	@Test
	public void testDecr() {
		// TODO spring不提供decr功能，有待确认。
		long value=template.opsForValue().increment(SimpleData.LISI.getKey(), -1);
		log.info(">> {}",value);
	}
	/**
	 * 将 key 所储存的值减去减量 decrement 。
	 */
	@Test
	public void testDecrby() {
		// TODO spring不提供decrby功能，有待确认。
		long value=template.opsForValue().increment(SimpleData.LISI.getKey(), -1);
		log.info(">> {}",value);
	}
	
	/**
	 * 返回 key 所关联的字符串值。
		如果 key 不存在那么返回特殊值 nil 。
		假如 key 储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。
	 */
	@Test
	public void testGet() {
		String value=template.opsForValue().get(SimpleData.LISI.getKey());
		log.info(">> {}",value);
	}
	/**
	 * 返回 key 中字符串值的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
	 */
	@Test
	public void testGetrange() {
		String value=template.opsForValue().get(SimpleData.LISI.getKey(), 0, 1);
		log.info(">> {}",value);
	}
	/**
	 * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
	 */
	@Test
	public void testGetSet() {
		String value=template.opsForValue().getAndSet(SimpleData.LISI.getKey(), "hello redis");
		log.info(">> {}",value);
	}
	/**
	 * 将 key 中储存的数字值加一。
	 */
	@Test
	public void testIncr() {
		// TODO spring不提供incr功能，有待确认。
		long value=template.opsForValue().increment(SimpleData.LISI.getKey(), 1);
		log.info(">> {}",value);
	}
	/**
	 * 将 key 所储存的值加上增量 increment 。
	 */
	@Test
	public void testIcrby() {
		// TODO spring不提供incrby功能，有待确认。
		long value=template.opsForValue().increment(SimpleData.LISI.getKey(), 1);
		log.info(">> {}",value);
	}
	/**
	 * 为 key 中所储存的值加上浮点数增量 increment 。
	 */
	@Test
	public void testIncrbyfloat() {
		// TODO spring不提供incrby功能，有待确认。
		double value=template.opsForValue().increment(SimpleData.LISI.getKey(), 1.4);
		log.info(">> {}",value);
	}
	/**
	 * 返回所有(一个或多个)给定 key 的值。
	 */
	@Test
	public void testMget() {
		List<String> list=template.opsForValue().multiGet(new ArrayList<String>(){{
			add(SimpleData.ZHANGSAN.getKey());
			add(SimpleData.LISI.getKey());
			add(SimpleData.OTHER.getKey());// 库中不存在key，返回null
		}});
		for(String v:list){
			log.info(">> {}",v);
		}
	}
	/**
	 * 一次增加多个键值
	 */
	@Test
	public void testMset() {
		template.opsForValue().multiSet(new HashMap<String,String>(){{
			put(SimpleData.ZHANGSAN.getKey(), SimpleData.ZHANGSAN.getValue());
			put(SimpleData.LISI.getKey(), SimpleData.LISI.getValue());
		}});
	}
	/**
	 * 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。
	 */
	@Test
	public void testMsetnx() {
		boolean isOk=template.opsForValue().multiSetIfAbsent(new HashMap<String,String>(){{
			put(SimpleData.LISI.getKey(),SimpleData.OTHER.getValue());
			put(SimpleData.ZHANGSAN.getKey(),SimpleData.ZHANGSAN.getValue());
		}});
		log.info(">> {}",isOk);
	}
	/**
	 * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)。
	 */
	@Test
	public void testSetex() {
		// TODO
	}
	/**
	 * 将 key 的值设为 value ，当且仅当 key 不存在。
	 */
	@Test
	public void testSetnx() {
		boolean isOk=template.opsForValue().setIfAbsent(SimpleData.LISI.getKey(),SimpleData.LISI.getValue());
		log.info(">> {}",isOk);
	}
	/**
	 * 用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始。
	 */
	@Test
	public void testSetrange() {
		// TODO
	}
	/**
	 * 返回 key 所储存的字符串值的长度。
		当 key 储存的不是字符串值时，返回一个错误。
	 */
	@Test
	public void testStrlen() {
		long size=template.opsForValue().size(SimpleData.LISI.getKey());
		log.info(">> {}",size);
	}
}
