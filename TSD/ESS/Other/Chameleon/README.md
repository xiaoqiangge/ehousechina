# 0.0.8

修复SnowflakeUtil在获得对象时使用单例模式，目前是内存占用地，效率高，线程安全，多线程操作原子性。

# 0.0.7

为了高度统一增加了获取时间戳的公共方法，如下，

```
DateUtil.getTimesTamp()
```

# 0.0.6

增加snowflake算法整成分布式唯一Id，是否用方式如下，

```
	Snowflake s = SnowflakeUtil.getSnowflake();
	long id = s.next();
	System.out.println(i + "----" + id);
```

# 0.0.5
略