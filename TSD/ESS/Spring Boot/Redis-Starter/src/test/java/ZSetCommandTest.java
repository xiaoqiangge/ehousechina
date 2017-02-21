import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import redis.simple.Startup;
/**
 * redis zset命令在线文档：
 * http://doc.redisfans.com/sorted_set/index.html
 * @author 小强哥
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
//@SpringApplicationConfiguration(classes = Startup.class)
@SpringBootTest(classes = Startup.class)
public class ZSetCommandTest {
	
	enum ZSetKey {
		ZSETKEY {
			@Override
			public String toString() {
				return "zuserset";
			}
		},
		ZOHTERKEY {
			@Override
			public String toString() {
				return "zother";
			}
		},
		DESTKEY {
			@Override
			public String toString() {
				return "dest";
			}
		}
	}
	
 	@Autowired
	private StringRedisTemplate template;
	
 	/**
 	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
 	 */
 	@Test
	public void testZadd() {
 		boolean isOk=template.opsForZSet().add(ZSetKey.ZSETKEY.toString(), SimpleData.ZHANGSAN.getKey(), 2);
 		log.info(">> {}",isOk);
 	}
 	/**
 	 * 返回有序集 key 的基数。
 	 */
 	@Test
	public void testZcard() {
 		long value=template.opsForZSet().size(ZSetKey.ZSETKEY.toString());
 		log.info(">> {}",value);
 	}
 	/**
 	 * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。
 	 */
 	@Test
	public void testZcount() {
 		long value=template.opsForZSet().count(ZSetKey.ZSETKEY.toString(), 1, 2);
 		log.info(">> {}",value);
 	}
 	/**
 	 * 为有序集 key 的成员 member 的 score 值加上增量 increment 。
 	 */
 	@Test
	public void testZincrby() {
 		double value=template.opsForZSet().incrementScore(ZSetKey.ZSETKEY.toString(), SimpleData.ZHANGSAN.getKey(), 1);
 		log.info(">> {}",value);
 	}
 	/**
 	 * 返回有序集 key 中，指定区间内的成员。
 	 */
 	@Test
	public void testZrange() {
 		Set<String> set=template.opsForZSet().range(ZSetKey.ZSETKEY.toString(), 1, 4);
 		for(String v:set){
 			log.info(">> {}",v);
 		}
 		/*Set<TypedTuple<String>> set=template.opsForZSet().rangeWithScores(ZSetKey.ZSETKEY.toString(), 1, 4);
 		for(TypedTuple v:set){
 			log.info(">> {}",v.getScore());
 		}*/
 	}
 	/**
 	 * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score 值递增(从小到大)次序排列。
 	 */
 	@Test
	public void testZrangebyscore() {
 		/*Set<String> set=template.opsForZSet().rangeByScore(ZSetKey.ZSETKEY.toString(), 1, 4);
 		for(String v:set){
 			log.info(">> {}",v);
 		}*/
 		Set<TypedTuple<String>> set=template.opsForZSet().rangeByScoreWithScores(ZSetKey.ZSETKEY.toString(), 1, 4);
 		for(TypedTuple v:set){
 			log.info(">> {}-{}",v.getValue(),v.getScore());
 		}
 	}
 	/**
 	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。
 	 */
 	@Test
	public void testZrank() {
 		long value=template.opsForZSet().rank(ZSetKey.ZSETKEY.toString(), SimpleData.ZHANGSAN.getKey());
 		log.info(">> {}",value);
 	}
 	/**
 	 * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
 	 */
 	@Test
	public void testZrem() {
 		long value=template.opsForZSet().remove(ZSetKey.ZSETKEY.toString(), SimpleData.ZHANGSAN.getKey());
 		/*long value=template.opsForZSet().remove(ZSetKey.ZSETKEY.toString(), new ArrayList<String>(){{
 			add("key1");
 			add("key2");
 			add("...");
 		}});*/
 		log.info(">> {}",value);
 	}
 	/**
 	 * 移除有序集 key 中，指定排名(rank)区间内的所有成员。
 	 */
 	@Test
	public void testZremrangebyrank() {
 		// TODO
 	}
 	/**
 	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。
 	 */
 	@Test
	public void testZremrangebyscore() {
 		long value=template.opsForZSet().removeRangeByScore(ZSetKey.ZSETKEY.toString(), 1, 4);
 		log.info(">> {}",value);
 	}
 	/**
 	 * 返回有序集 key 中，指定区间内的成员。
 	 */
 	@Test
	public void testZrevrange() {
 		/*long value=template.opsForZSet().reverseRank(ZSetKey.ZSETKEY.toString(), SimpleData.ZHANGSAN.getKey());
 		log.info(">> {}",value);*/
 		Set<TypedTuple<String>> set=template.opsForZSet().reverseRangeWithScores(ZSetKey.ZSETKEY.toString(), 1, 4);
 		for(TypedTuple v:set){
 			log.info(">> {}",v.getScore());
 		}
 	}
 	/**
 	 * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。有序集成员按 score 值递减(从大到小)的次序排列。
 	 */
 	@Test
	public void testZrevrangebyscore() {
 		/*Set<String> set=template.opsForZSet().reverseRangeByScore(ZSetKey.ZSETKEY.toString(), 1, 4);
 		for(String v:set){
 			log.info(">> {}",v);
 		}*/
 		Set<TypedTuple<String>> set=template.opsForZSet().reverseRangeByScoreWithScores(ZSetKey.ZSETKEY.toString(), 1, 4);
 		for(TypedTuple v:set){
 			log.info(">> {}-{}",v.getValue(),v.getScore());
 		}
 	}
 	/**
 	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。
 	 */
 	@Test
	public void testZrevrank() {
 		long value=template.opsForZSet().reverseRank(ZSetKey.ZSETKEY.toString(), SimpleData.ZHANGSAN.getKey());
 		log.info(">> {}",value);
 	}
 	/**
 	 * 返回有序集 key 中，成员 member 的 score 值。
 	 */
 	@Test
	public void testZscore() {
 		double value=template.opsForZSet().score(ZSetKey.ZSETKEY.toString(), SimpleData.ZHANGSAN.getKey());
 		log.info(">> {}",value);
 	}
 	/**
 	 * 计算给定的一个或多个有序集的并集，其中给定 key 的数量必须以 numkeys 参数指定，并将该并集(结果集)储存到 destination 。
		默认情况下，结果集中某个成员的 score 值是所有给定集下该成员 score 值之 和 。
 	 */
 	@Test
	public void testZunionstore() {
 		// TODO
 	}
 	/**
 	 * 计算给定的一个或多个有序集的交集，其中给定 key 的数量必须以 numkeys 参数指定，并将该交集(结果集)储存到 destination 。
		默认情况下，结果集中某个成员的 score 值是所有给定集下该成员 score 值之和.
 	 */
 	@Test
	public void testZinterstore() {
 		// TODO
 	}
 	@Test
	public void testZscan() {
 		// TODO
 	}
}
