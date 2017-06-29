import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.eju.ess.common.utils.seri.SnowflakeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MyTest {
	
	@Test
	public void test(){
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		SnowflakeUtil sf = new SnowflakeUtil();
		
		JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(50);
		jedisPoolConfig.setMaxIdle(10);
		jedisPoolConfig.setMaxWaitMillis(1000);
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setTestOnReturn(true);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig,"10.99.70.53",32853);
		Jedis jedis = jedisPool.getResource();
		try{
			while(true){
				executor.execute(new Runnable() {
					@Override
					public void run() {
						long id = sf.nextId();
						long value = jedis.setnx(String.valueOf(id), "");
						if(value==0){
							 System.out.println("1111111111111111111111");
						}
					}
				});
			}
		}catch(Exception e){
			e.printStackTrace();
		}
//		long value = jedis.setnx("test", "test");
//		System.out.println(value);
		/*jedis.close();
		jedisPool.close();*/
	}
}

//private ExecutorService pool = new ThreadPoolExecutor(
//		corePoolSize,
//		Integer.MAX_VALUE,
//		5, 
//		TimeUnit.MILLISECONDS, 
//		new LinkedBlockingQueue<Runnable>()
//);
