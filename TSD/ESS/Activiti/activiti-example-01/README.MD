一个基本的流程，如下，
![image](http://static.xiaoqiangge.com/image/d62cd229-7539-4f20-984f-823c4f7f3474.png)

bpmn文件在`/activiti-example-01/src/main/resources/processes/demo.bpmn`目录。

右键`Startup` > `run as` 运行项目。

在浏览器输入`http://127.0.0.1:8080/`可以看到管理界面，如下
![image](http://static.xiaoqiangge.com/image/88b5f681-47d0-42ad-b3f6-06c6a10fa995.png)

点击`启动`可以启动当前流程，在后台consule可以看到运行结果，如下，

```
18:50:02.295 [http-nio-8080-exec-4] INFO  com.eju.ess.service.WorkService - >> 调用服务1
18:50:02.296 [http-nio-8080-exec-4] INFO  com.eju.ess.service.WorkService - >> 调用服务2
```


---
