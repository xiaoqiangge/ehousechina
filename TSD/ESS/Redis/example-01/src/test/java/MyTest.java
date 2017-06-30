import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.eju.ess.common.utils.md5.MD5Util;
import com.eju.ess.common.utils.seri.SnowflakeUtil;
import com.relops.snowflake.Snowflake;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MyTest {

	volatile int i = 0;
	private ExecutorService pool = new ThreadPoolExecutor(100, Integer.MAX_VALUE, 5, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	@Test
	public void test1() {

		Snowflake s = SnowflakeUtil.getSnowflake();

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(500);
		jedisPoolConfig.setMaxIdle(500);
		jedisPoolConfig.setMaxWaitMillis(-1);
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setTestOnReturn(true);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, "10.99.70.53", 32853);
		try {
			while (true) {
				i++;
				long id = s.next();
				System.out.println(i + "----" + id);
				// pool.execute(new Runnable() {
				// @Override
				// public void run() {
				// i++;
				// Jedis jedis = jedisPool.getResource();
				// long id = s.next();
				// System.out.println(i+"----"+id);
				// long value = jedis.setnx(String.valueOf(id), "");
				// if(value==0){
				// System.out.println("1111111111111111111111");
				// }
				// jedis.close();
			}
			// });
			// TimeUnit.NANOSECONDS.sleep(1);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedisPool.close();
		}
		// long value = jedis.setnx("test", "test");
		// System.out.println(value);
		/*
		 * jedis.close(); jedisPool.close();
		 */
	}

	@Test
	public void test2() {
		String md5Str = MD5Util.getMD5Str(String.valueOf(System.currentTimeMillis()));
		System.out.println(StrToBinstr(md5Str));
		// try {
		// InetAddress addr=InetAddress.getLocalHost();
		// System.out.println(addr.getHostAddress());
		// } catch (UnknownHostException e) {
		// e.printStackTrace();
		// }
		// int a = 192.168.31.10;
		// int a = 10;
		// System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.parseInt("0110110111", 2));
		// int i=0;
		// SnowflakeUtil sf = new SnowflakeUtil();
		// while(true){
		// i++;
		// System.out.println(i+"---"+sf.nextId());
		// }
	}

	private String StrToBinstr(String str) {
		char[] strChar = str.toCharArray();
		String result = "";
		for (int i = 0; i < strChar.length; i++) {
			result += Integer.toBinaryString(strChar[i]);
		}
		return result;
	}

	@Test
	public void test3() {/*
							 * int node = 1; Snowflake s = new Snowflake(506);
							 * long id = s.next();
							 */
		String str = "1100011110110111000110001110111110010110001111001011001011101011100010110001011000111011011010011100111001101100101100001100101100001110010111100011000011011011000101100011101001101001100011100110110111";
		String test = str.substring(str.length() - 10, str.length());
		System.out.println(test);
	}
	
	@Test
	public void test4(){
		Snowflake s = SnowflakeUtil.getSnowflake();
		long id = s.next();
		System.out.println(i + "----" + id);
	}
}

// private ExecutorService pool = new ThreadPoolExecutor(
// corePoolSize,
// Integer.MAX_VALUE,
// 5,
// TimeUnit.MILLISECONDS,
// new LinkedBlockingQueue<Runnable>()
// );
