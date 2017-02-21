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
 * redis tran命令在线文档：
 * http://doc.redisfans.com/transaction/index.html
 * @author 小强哥
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class TranCommandTest {
	
 	@Autowired
	private StringRedisTemplate template;
	/**
	 * 标记一个事务块的开始。
		事务块内的多条命令会按照先后顺序被放进一个队列当中，最后由 EXEC 命令原子性(atomic)地执行。
	 * redis事物操作
	 * redis事物其实是一个命令批处理过程，如果中间的命令出现错误，已经执行的命令不会回滚。
	 */
 	@Test
	public void testMulti() {
 		/*
 		 * 需要启用事物支持，要不然会出现下面错误
 		 * Cannot use Jedis when in Multi. Please use Transation or reset jedis state.
 		 */
 		template.setEnableTransactionSupport(true);
 		/*
 		 * 标记一个事务块的开始。
 			事务块内的多条命令会按照先后顺序被放进一个队列当中，最后由 EXEC 命令原子性(atomic)地执行。
 		 */
 		template.multi();
 		/*
 		 * 执行redis命令
 		 */
 		template.opsForValue().increment("a", 1);
 		template.opsForValue().increment("b", 1);
 		/*
 		 * 执行所有事务块内的命令。
			假如某个(或某些) key 正处于 WATCH 命令的监视之下，且事务块中有和这个(或这些) key 相关的命令，
			那么 EXEC 命令只在这个(或这些) key 没有被其他命令所改动的情况下执行并生效，否则该事务被打断(abort)。
 		 */
 		template.exec();
 	}
 	/**
		执行所有事务块内的命令。
		假如某个(或某些) key 正处于 WATCH 命令的监视之下，且事务块中有和这个(或这些) key 相关的命令，
		那么 EXEC 命令只在这个(或这些) key 没有被其他命令所改动的情况下执行并生效，否则该事务被打断(abort)。
	 * redis事物操作
	 * redis事物其实是一个命令批处理过程，如果中间的命令出现错误，已经执行的命令不会回滚。
	 */
 	@Test
	public void testExec() {
 		/*
 		 * 需要启用事物支持，要不然会出现下面错误
 		 * Cannot use Jedis when in Multi. Please use Transation or reset jedis state.
 		 */
 		template.setEnableTransactionSupport(true);
 		/*
 		 * 标记一个事务块的开始。
 			事务块内的多条命令会按照先后顺序被放进一个队列当中，最后由 EXEC 命令原子性(atomic)地执行。
 		 */
 		template.multi();
 		/*
 		 * 执行redis命令
 		 */
 		template.opsForValue().increment("a", 1);
 		template.opsForValue().increment("b", 1);
 		/*
 		 * 执行所有事务块内的命令。
			假如某个(或某些) key 正处于 WATCH 命令的监视之下，且事务块中有和这个(或这些) key 相关的命令，
			那么 EXEC 命令只在这个(或这些) key 没有被其他命令所改动的情况下执行并生效，否则该事务被打断(abort)。
 		 */
 		template.exec();
 	}
 	/**
 	 * 取消事务，放弃执行事务块内的所有命令。
 	 */
 	@Test
	public void testDiscard() {
 		/*
 		 * 需要启用事物支持，要不然会出现下面错误
 		 * Cannot use Jedis when in Multi. Please use Transation or reset jedis state.
 		 */
 		template.setEnableTransactionSupport(true);
 		/*
 		 * 标记一个事务块的开始。
 			事务块内的多条命令会按照先后顺序被放进一个队列当中，最后由 EXEC 命令原子性(atomic)地执行。
 		 */
 		template.multi();
 		/*
 		 * 执行redis命令
 		 */
 		template.opsForValue().increment("a", 1);
 		template.opsForValue().increment("b", 1);
 		/*
 		 * 取消事务，放弃执行事务块内的所有命令。
 		 */
 		template.discard();
 		/**
 		 * 执行exec无任何操作
 		 */
 		template.exec();
 	}
 	/**
 	 * 取消 WATCH 命令对所有 key 的监视。
		如果在执行 WATCH 命令之后， EXEC 命令或 DISCARD 命令先被执行了的话，那么就不需要再执行 UNWATCH 了。
		因为 EXEC 命令会执行事务，因此 WATCH 命令的效果已经产生了；而 DISCARD 命令在取消事务的同时也会取消所有对 key 的监视，因此这两个命令执行之后，就没有必要执行 UNWATCH 了。
 	 */
 	@Test
	public void testWatch() {
 		template.setEnableTransactionSupport(true);
 		template.watch("a");
 		template.multi();
 		template.opsForValue().increment("a", 1);
 		template.exec();
 	}
}
