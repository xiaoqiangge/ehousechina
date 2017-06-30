# 0.0.6

增加snowflake算法整成分布式唯一Id，是否用方式如下，

```
	Snowflake s = SnowflakeUtil.getSnowflake();
	long id = s.next();
	System.out.println(i + "----" + id);
```

# 0.0.5
略