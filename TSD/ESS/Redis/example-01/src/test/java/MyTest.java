import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MyTest {
	
	@Test
	public void test(){
		JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(50);
		jedisPoolConfig.setMaxIdle(10);
		jedisPoolConfig.setMaxWaitMillis(1000);
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setTestOnReturn(true);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig,"10.99.70.53",32853);
		Jedis jedis = jedisPool.getResource();
		jedis.set("test", "test");
		jedis.close();
		jedisPool.close();
	}
}
