import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import redis.simple.Startup;
/**
 * redis connection命令在线文档：
 * http://doc.redisfans.com/connection/index.html
 * @author 小强哥
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class ConnectionCommandTest {
	
 	@Autowired
	private StringRedisTemplate template;
 	/**
 	 * 1、redis执行选库命令select需要JedisConnectionFactory中API，因此这地方还是需要注入
 	 * 一下的，这点设计不是很好。
 	 */
 	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
 	
 	/**
 	 * 切换到指定的数据库，数据库索引号 index 用数字值指定，以 0 作为起始索引值。
		默认使用 0 号数据库。
 	 */
 	@Test
	public void testSelect() {
 		/*
 		 * 使用下面的这方式是不能进行选库的，还得我还看了好久，能力有限嘛。
 		 * 参考
 		 * https://github.com/ChangKeunYou/spring.mvc/blob/master/src/main/java/com/wemakeprice/dao/redis/RedisDaoImpl.java
 		 */
 		// template.getConnectionFactory().getConnection().select(14);
 		/*
 		 * 这才是正确的选库的方式，需要对jedisConnectionFactory进行注入
 		 */
// 		this.jedisConnectionFactory.setDatabase(14);
 		template.opsForValue().set("test", "111");
 	}
 	/**
 	 * 
 	 */
 	@Test
	public void testPing() {
 		String value=template.getConnectionFactory().getConnection().ping();
 		log.info(">> {}",value);
 	}
}
