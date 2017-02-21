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
 * redis server命令在线文档：
 * http://doc.redisfans.com/server/index.html
 * @author 小强哥
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class ServerCommandTest {
	@Autowired
	private StringRedisTemplate template;
	
	/**
	 * 
	 */
 	@Test
	public void testTime() {
 		long value=template.getConnectionFactory().getConnection().time();
 		log.info(">> {}",value);
 	}
}
