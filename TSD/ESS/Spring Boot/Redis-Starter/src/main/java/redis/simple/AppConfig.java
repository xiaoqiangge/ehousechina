//package redis.simple;
//
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//import redis.clients.jedis.JedisPoolConfig;
///**
// * 在某些情况下，需要从远程读取配置，然后本地环境使用配置，例如：从zk上读取dev、alpha配置等
// * @author zhaoqiang
// *
// */
//@Slf4j
//@Configuration
//public class AppConfig {
//	@Bean
//	public RedisConnectionFactory redisConnectionFactory() {
//		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
//		redisConnectionFactory.setHostName("192.168.28.9");
//		redisConnectionFactory.setPort(6379);
//		redisConnectionFactory.setDatabase(15);
//		JedisPoolConfig poolConfig = new JedisPoolConfig();
//		poolConfig.setMaxTotal(1000);
//		poolConfig.setMaxIdle(100);
//		poolConfig.setMaxIdle(10);
//		redisConnectionFactory.setPoolConfig(poolConfig);
//		redisConnectionFactory.setUsePool(true);
//		return redisConnectionFactory;
//	}
//
//	@Bean
//	public StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
//		return new StringRedisTemplate(connectionFactory);
//	}
//}
