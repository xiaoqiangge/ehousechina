# 注解式分布式的配置文件高级篇: 配置更新的通知（最佳实践）
> 在 Tutorial 1 里,我们实现了一个简单的Redis服务程序，它使用分布式配置进行管理，此Redis的配置文件存储在分布式服务器 disconf-web 上。

也许有一天，我们需要更新Redis的Host和Port配置数据。由于 redis是根据配置生成的实例，因此，这种情况下，你有三种选择：

- 不使用Disconf（Tutorial 1 里第一部分的使用方法）。那么你需要 更改线上机器的配置文件 redis.properties，重启服务就可以了。
- 使用Disconf, 采用 Tutorial 1 里第二部分的使用的方案。那么你需要 更改 分布式服务器 disconf-web 上的 redis.properties 文件。 然后重启服务就可以了。和第一种方法的区别在于，不需要更改线上机器的配置文件。
- 使用Disconf，采用 Tutorial 1 里第二部分的使用的方案，并额外加上本Tutorial的方案，那么你 只需要 更改 分布式服务器 disconf-web 上的 redis .properties 文件。然后服务的配置自动生效。此过程无需要重新启动服务。


---


[详细参考内容](http://disconf.readthedocs.io/zh_CN/latest/tutorial-client/src/Tutorial2.html)