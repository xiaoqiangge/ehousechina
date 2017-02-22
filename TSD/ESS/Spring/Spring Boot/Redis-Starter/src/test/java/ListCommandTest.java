import java.util.List;

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
 * redis List命令在线文档：
 * http://doc.redisfans.com/list/index.html
 * @author 小强哥
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class ListCommandTest {
	
	enum ListKey {
		LISTKEY {
			@Override
			public String toString() {
				return "userlist";
			}
		},test;
	}
	
	@Autowired
	private StringRedisTemplate template;

	@Test
	public void testBlpop() {
		// TODO
	}
	
	@Test
	public void testBrpop() {
		// TODO
	}
	
	@Test
	public void testBrpoplpush() {
		// TODO
	}
	/**
	 * 返回列表 key 中，下标为 index 的元素
	 */
	@Test
	public void testLindex() {
		String value=template.opsForList().index(ListKey.LISTKEY.toString(), 1);
		log.info(">> {}",value);
	}
	
	@Test
	public void testLinsert() {
		// TODO
	}
	
	/**
	 * 将一个或多个值 value 插入到列表 key 的表头
	 */
	@Test
	public void testLpush() {
		long value=template.opsForList().leftPush(ListKey.LISTKEY.toString(), SimpleData.LISI.getKey());
		log.info(">> {}",value);
	}
	/**
	 * 将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表。
	 */
	@Test
	public void testLpushx() {
		long value=template.opsForList().leftPushIfPresent(ListKey.LISTKEY.toString(), SimpleData.ZHANGSAN.getKey());
		log.info(">> {}",value);
	}
	/**
	 * 返回列表 key 的长度
	 */
	@Test
	public void testLlen() {
		long size=template.opsForList().size(ListKey.LISTKEY.toString());
		log.info(">> {}",size);
	}
	/**
	 * 移除并返回列表 key 的头元素。
	 */
	@Test
	public void testLpop() {
		String value=template.opsForList().leftPop(ListKey.LISTKEY.toString());
		log.info(">> {}",value);
	}
	/**
	 * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
	 */
	@Test
	public void testLrange() {
		List<String> list=template.opsForList().range(ListKey.LISTKEY.toString(), 0, 3);
		for(String v:list){
			log.info(">> {}",v);
		}
	}
	/**
	 * 根据参数 count 的值，移除列表中与参数 value 相等的元素。
	 */
	@Test
	public void testLrem() {
		long value=template.opsForList().remove(ListKey.LISTKEY.toString(), 0, SimpleData.ZHANGSAN.getKey());
		log.info(">> {}",value);
	}
	/**
	 * 将列表 key 下标为 index 的元素的值设置为 value 。
	 */
	@Test
	public void testLset() {
		template.opsForList().set(ListKey.LISTKEY.toString(), 1, SimpleData.OTHER.getKey());
	}
	/**
	 * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
	 */
	@Test
	public void testLtrim() {
		template.opsForList().trim(ListKey.LISTKEY.toString(), 0, 1);
	}
	/**
	 * 移除并返回列表 key 的尾元素。
	 */
	@Test
	public void testRpop() {
		String value=template.opsForList().rightPop(ListKey.LISTKEY.toString());
		log.info(">> {}",value);
	}
	@Test
	public void testRpoplpush() {
		// TODO
	}
	/**
	 * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
	 */
	@Test
	public void testRpush() {
		long value=template.opsForList().rightPush(ListKey.LISTKEY.toString(), SimpleData.LISI.getKey());
		log.info(">> {}",value);
	}
	/**
	 * 将值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表。
	 */
	@Test
	public void testRpushx() {
		long value=template.opsForList().rightPushIfPresent(ListKey.LISTKEY.toString(), SimpleData.LISI.getKey());
		log.info(">> {}",value);
	}
}
