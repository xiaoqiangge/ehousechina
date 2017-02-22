import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.SortParameters.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import redis.simple.Startup;
/**
 * redis Key命令在线文档：
 * http://doc.redisfans.com/key/index.html
 * @author 小强哥
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class KeyCommandTest {
	@Autowired
	private StringRedisTemplate template;
	
	/**
	 * 删除key，
	 * 1、删除单个key
	 * 2、删除多个key
	 */
	@Test
	public void testDel() {
		// 删除单个Key
		/*template.delete(SimpleData.LISI.getKey());*/
		// 删除多个Key
		template.delete(new ArrayList<String>() {
			{
				add(SimpleData.ZHANGSAN.getKey());
				add(SimpleData.LISI.getKey());
			}
		});
		 
	}

	/**
	 * 序列化给定 key ，并返回被序列化的值，使用 RESTORE 命令可以将这个值反序列化为 Redis 键。
	 * 场景？
	 */
	@Test
	public void testDump() {
		byte[] value=template.dump(SimpleData.LISI.getKey());
		log.info(">> {}",value);
	}

	/**
	 * 判断键是否存在
	 */
	@Test
	public void testExists() {
		boolean isHave=template.hasKey(SimpleData.ZHANGSAN.getKey());
		log.info(">> {}",isHave);
	}
	
	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
	 */
	@Test
	public void testExpire() {
		boolean isOk=template.expire(SimpleData.LISI.getKey(), 30, TimeUnit.SECONDS);
		log.info(">> {}",isOk);
	}
	
	/**
	 * 为给定 key 设置生存时间，在指定的时间自动删除
	 */
	@Test
	public void testExpireAt() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 1); // 加一天
		boolean isOk=template.expireAt(SimpleData.LISI.getKey(), cal.getTime());
		log.info(">> {}",isOk);
	}
	
	/**
	 *  查找所有符合给定模式 pattern 的 key 。
		KEYS * 匹配数据库中所有 key 。
		KEYS h?llo 匹配 hello ， hallo 和 hxllo 等。
		KEYS h*llo 匹配 hllo 和 heeeeello 等。
		KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。
	 */
	@Test
	public void testKeys() {
		// 查找全部
		Set<String> keys = template.keys("*");
		// 使用通配符
		/*Set<String> keys = template.keys("zhang*");*/
		for (String key : keys) {
			log.info(">> {}", key);
		}
	}
	/**
	 * migrate命令文档： http://doc.redisfans.com/key/migrate.html
	 * 我没有找到migrate命令对应的API
	 */
	@Test
	public void testMigrate() {
		// TODO
	}
	
	/**
	 * 将当前数据库的 key 移动到给定的数据库 db 当中。
	 */
	@Test
	public void testMove() {
		boolean isOk=template.move(SimpleData.LISI.getKey(), 14);
		log.info(">> {}",isOk);
	}
	/**
	 * 
	 */
	@Test
	public void testObject() {
		//TODO
	}
	/**
	 * 移除给定 key 的生存时间，将这个 key 从『易失的』(带生存时间 key )转换成『持久的』(一个不带生存时间、永不过期的 key )。
	 */
	@Test
	public void testPersist() {
		boolean isOk=template.persist(SimpleData.LISI.getKey());
		log.info(">> {}",isOk);
	}
	/**
	 * 这个命令和 EXPIRE 命令的作用类似，但是它以毫秒为单位设置 key 的生存时间，而不像 EXPIRE 命令那样，以秒为单位。
	 */
	@Test
	public void testPexpire() {
		boolean isOk=template.expire(SimpleData.LISI.getKey(), 30*1000, TimeUnit.MILLISECONDS);
		log.info(">> {}",isOk);
	}
	/**
	 * 这个命令和 EXPIREAT 命令类似，但它以毫秒为单位设置 key 的过期 unix 时间戳，而不是像 EXPIREAT 那样，以秒为单位。
	 */
	@Test
	public void testPexpireat() {
		boolean isOk=template.expire(SimpleData.LISI.getKey(), 30*1000, TimeUnit.MICROSECONDS);
		log.info(">> {}",isOk);
	}
	/**
	 * 这个命令类似于 TTL 命令，但它以毫秒为单位返回 key 的剩余生存时间，而不是像 TTL 命令那样，以秒为单位。
	 */
	@Test
	public void testPttl() {
		long milltiSecond=template.getExpire(SimpleData.LISI.getKey(),TimeUnit.MILLISECONDS);
		log.info(">> {}",milltiSecond);
	}
	/**
	 * 从当前数据库中随机返回(不删除)一个 key 。
	 */
	@Test
	public void testRandomkey() {
		String key=template.randomKey();
		log.info(">> {}",key);
	}
	/**
	 * 将 key 改名为 newkey 。
		当 key 和 newkey 相同，或者 key 不存在时，返回一个错误。
		当 newkey 已经存在时， RENAME 命令将覆盖旧值。
	 */
	@Test
	public void testRename() {
		// 当 key 不存在时，返回错误 ,ERR no such key，因此需要先has一下。
		boolean isHave=template.hasKey(SimpleData.LISI.getKey());
		if(isHave){
			template.rename(SimpleData.LISI.getKey(), SimpleData.OTHER.getKey());
		}
	}
	/**
	 * 当且仅当 newkey 不存在时，将 key 改名为 newkey 。
		当 key 不存在时，返回一个错误。
	 */
	@Test
	public void testRenamenx() {
		boolean isHave=template.hasKey(SimpleData.LISI.getKey());
		if(isHave){
			boolean isOk=template.renameIfAbsent(SimpleData.LISI.getKey(), SimpleData.OTHER.getKey());
			log.info(">> {}",isOk);
		}
	}
	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联。
		参数 ttl 以毫秒为单位为 key 设置生存时间；如果 ttl 为 0 ，那么不设置生存时间。
	 */
	@Test
	public void testRestore() {
		template.restore(SimpleData.LISI.getKey(), SimpleData.LISI.getValue().getBytes(), 0, TimeUnit.SECONDS);
		// TODO
	}
	/**
	 * 测试数据1，值全部都是数字
	 * lpush zhang 400 200 500 100 300
	 * 测试数据2，值全部都是字母
	 * lpush zhang ab bc ad ae cd aa
	 */
	@Test
	public void testSort() {
		// 使用测试数据1，测试数字排序
		/*SortQuery<String> sortQuery = SortQueryBuilder.sort("zhang").order(Order.DESC).build();
		List<String> list=template.sort(sortQuery);
		for(String v:list){
			log.info(">> {}",v);
		}*/
		
		// 使用测试数据2，测试字母排序
		/*SortQuery<String> sortQuery = SortQueryBuilder.sort("zhang").order(Order.DESC).alphabetical(true).build();
		List<String> list=template.sort(sortQuery);
		for(String v:list){
			log.info(">> {}",v);
		}*/
		
		// 测试使用limit限制返回结果
		/*SortQuery<String> sortQuery = SortQueryBuilder.sort("zhang").order(Order.DESC).alphabetical(true).limit(new Range(1,3)).build();
		List<String> list=template.sort(sortQuery);
		for(String v:list){
			log.info(">> {}",v);
		}*/
		
		// 测试保存排序结果
		/*SortQuery<String> sortQuery = SortQueryBuilder.sort("zhang").order(Order.DESC).alphabetical(true).limit(new Range(1,3)).build();
		long value=template.sort(sortQuery,"newzhang");
		log.info(">> {}",value);*/
		
		/*
		 * 将哈希表作为 GET 或 BY 的参数，
		 * hash数据如下：
			hmset link|1 title 谷歌 url http://www.google.com sort 1
			hmset link|2 title 百度 url http://www.baidu.com sort 2
			hmset link|3 title 网易 url http://www.163.com sort 3
			hmset link|4 title 腾讯 url http://www.qq.com sort 4
		   linkid数据如下：
		   	lpush linkid 1 2 3 4 
		 */
		// 单个GET，根据hash中的sort排序
		// SortQuery<String> sortQuery = SortQueryBuilder.sort("linkid").by("link|*->sort").order(Order.DESC).get("link|*->title").build();
		// 多个GET，根据hash中的sort排序
		SortQuery<String> sortQuery = SortQueryBuilder.sort("linkid").by("link|*->sort").order(Order.DESC).get("link|*->title").get("link|*->url").build();
		List<String> list=template.sort(sortQuery);
		for(String v:list){
			log.info(">> {}",v);
		}
	}
	/**
	 * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
	 */
	@Test
	public void testTtl() {
		long milltiSecond=template.getExpire(SimpleData.LISI.getKey(),TimeUnit.SECONDS);
		log.info(">> {}",milltiSecond);
	}
	/**
	 * 返回 key 所储存的值的类型。
	 */
	@Test
	public void testType() {
		DataType type=template.type(SimpleData.LISI.getKey());
		log.info(">> {}",type);
	}
	@Test
	public void testScan() {
	}
}
