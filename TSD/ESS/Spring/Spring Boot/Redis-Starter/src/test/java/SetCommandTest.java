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
 * redis set命令在线文档：
 * http://doc.redisfans.com/set/index.html
 * @author 小强哥
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class SetCommandTest {
	
	enum SetKey {
		SETKEY {
			@Override
			public String toString() {
				return "userset";
			}
		},
		OTHER {
			@Override
			public String toString() {
				return "other";
			}
		},
		DEST {
			@Override
			public String toString() {
				return "dest";
			}
		}
	}
	
 	@Autowired
	private StringRedisTemplate template;
	
 	/**
 	 * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
 	 */
 	@Test
	public void testSadd() {
		long value=template.opsForSet().add(SetKey.SETKEY.toString(),SimpleData.ZHANGSAN.getKey());
		log.info(">> {}",value);
	}
 	/**
 	 * 返回集合 key 的基数(集合中元素的数量)。
 	 */
 	@Test
	public void testScard() {
		long value=template.opsForSet().size(SetKey.SETKEY.toString());
		log.info(">> {}",value);
	}
 	/**
 	 *返回一个集合的全部成员，该集合是所有给定集合之间的差集。 
 	 */
 	@Test
	public void testSdiff() {
 		Set<String> set=template.opsForSet().difference(SetKey.SETKEY.toString(),SetKey.OTHER.toString());
 		/*Set<String> set=template.opsForSet().difference(SetKey.SETKEY.toString(),new ArrayList<String>(){{
 			add("key1");
 			add("key2");
 			add("key3");
 			add("...");
 		}});*/
 		for(String v:set){
 			log.info(">> {}",v);
 		}
	}
 	/**
 	 * 这个命令的作用和 SDIFF 类似，但它将结果保存到 destination 集合，而不是简单地返回结果集。
 	 */
 	@Test
	public void testSdiffstore() {
 		long value=template.opsForSet().differenceAndStore(SetKey.SETKEY.toString(),SetKey.OTHER.toString(), SetKey.DEST.toString());
 		/*long value=template.opsForSet().differenceAndStore(SetKey.SETKEY.toString(),new ArrayList<String>(){{
 			add("key1");
 			add("key2");
 			add("key3");
 			add("...");
 		}}, SetKey.DEST.toString());*/
 		log.info(">> {}",value);
	}
 	/**
 	 * 返回一个集合的全部成员，该集合是所有给定集合的交集。
 	 */
 	@Test
	public void testSinter() {
 		Set<String> set=template.opsForSet().intersect(SetKey.SETKEY.toString(),SetKey.OTHER.toString());
 		/*Set<String> set=template.opsForSet().intersect(SetKey.SETKEY.toString(),new ArrayList<String>(){{
 			add("key1");
 			add("key2");
 			add("key3");
 			add("...");
 		}});*/
 		for(String v:set){
 			log.info(">> {}",v);
 		}
	}
 	/**
 	 * 这个命令类似于 SINTER 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集。
 	 */
 	@Test
	public void testSinterstore() {
 		long value=template.opsForSet().intersectAndStore(SetKey.SETKEY.toString(),SetKey.OTHER.toString(), SetKey.DEST.toString());
 		/*long value=template.opsForSet().intersectAndStore(SetKey.SETKEY.toString(),new ArrayList<String>(){{
 			add("key1");
 			add("key2");
 			add("key3");
 			add("...");
 		}}, SetKey.DEST.toString());*/
 		log.info(">> {}",value);
	}
 	/**
 	 * 判断 member 元素是否集合 key 的成员。
 	 */
 	@Test
	public void testSismember() {
 		boolean isHave=template.opsForSet().isMember(SetKey.SETKEY.toString(), SimpleData.LISI.getKey());
 		log.info(">> {}",isHave);
	}
 	/**
 	 * 返回集合 key 中的所有成员。
 	 */
 	@Test
	public void testSmembers() {
 		Set<String> set=template.opsForSet().members(SetKey.SETKEY.toString());
 		for(String v:set){
 			log.info(">> {}",v);
 		}
	}
 	/**
 	 * 将 member 元素从 source 集合移动到 destination 集合。
 	 */
 	@Test
	public void testSmove() {
 		boolean isOk=template.opsForSet().move(SetKey.SETKEY.toString(), SimpleData.LISI.getKey(), SetKey.DEST.toString());
 		log.info(">> {}",isOk);
	}
 	/**
 	 * 移除并返回集合中的一个随机元素。
 	 */
 	@Test
	public void testSpop() {
 		String value=template.opsForSet().pop(SetKey.SETKEY.toString());
 		log.info(">> {}",value);
	}
 	@Test
	public void testSrandmember() {
 		// TODO
 	}
 	/**
 	 * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
 	 */
 	@Test
	public void testSrem() {
 		long value=template.opsForSet().remove(SetKey.SETKEY.toString(), SimpleData.LISI.getKey());
 		/*long value=template.opsForSet().remove(SetKey.SETKEY.toString(), new ArrayList<String>(){{
 			add("key1");
 			add("key2");
 			add("key3");
 			add("...");
 		}});*/
 		log.info(">> {}",value);
	}
 	/**
 	 * 返回一个集合的全部成员，该集合是所有给定集合的并集。
 	 */
 	@Test
	public void testSunion() {
 		Set<String> set=template.opsForSet().union(SetKey.SETKEY.toString(), SetKey.OTHER.toString());
 		/*Set<String> set=template.opsForSet().union(SetKey.SETKEY.toString(), new ArrayList<String>(){{
 			add("key1");
 			add("key2");
 			add("key3");
 			add("...");
 		}});*/
 		for(String v:set){
 			log.info(">> {}",v);
 		}
	}
 	/**
 	 * 这个命令类似于 SUNION 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集。
 	 */
 	@Test
	public void testSunionstore() {
 		long value=template.opsForSet().unionAndStore(SetKey.SETKEY.toString(), SetKey.OTHER.toString(),SetKey.DEST.toString());
 		/*long value=template.opsForSet().unionAndStore(SetKey.SETKEY.toString(),new ArrayList<String>(){{
 			add("key1");
 			add("key2");
 			add("key3");
 			add("...");
 		}}, SetKey.OTHER.toString());*/
 		log.info(">> {}",value);
	}
 	/**
 	 * 
 	 */
 	@Test
	public void testSscan() {
 		// TODO
 	}
}
