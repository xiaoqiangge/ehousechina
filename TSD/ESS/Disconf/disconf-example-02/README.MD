# 注解式分布式的配置文件（最佳实践）
这里以 disconf-demo/disconf-standalone-demo 某个程序片段为例，详细介绍了一个 分布式配置文件 的简单示例程序。

假设，我们的应用程序使用了Redis服务，我们将使用Jedis来进行编程。编程时，我们需要Redis的Host和Port，通常情况下，我们会把这两个参数放在配置文件里。

**本教程将以两个部分来进行**

- 第一部分讲解正常情况下（不使用Disconf）的写法，这是我们以前常做的事情 。
- 第二部分，会在第一部分的基础上，添加Disconf的支持。从这一部分，大家就可以看到Disconf的简洁性和低侵入性。 并且，大家也可以看到关闭和开启Disconf，原有程序（第一部分）都可以正确Work。


---

[详细参考地址](http://disconf.readthedocs.io/zh_CN/latest/tutorial-client/src/Tutorial1.html)
