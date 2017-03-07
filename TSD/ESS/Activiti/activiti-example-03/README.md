> activiti中实现条件判断有2中方式，==第一种在流程中指定条件，第二种使用排它网关==。

如下，简单流程

bpmn文件：`/activiti-example-03/src/main/resources/processes/demo1.bpmn`
![image](http://static.xiaoqiangge.com/image/5fcba195-54e4-48f3-8f8a-45f859cc4cf4.png)

# 在流程中指定条件

选中流程那条线，然后在`main config > condition`中输入条件，意思就是满足定义条件时执行该流程，其中`result`是`转账java service`的返回值,这里定义`${result>0}`，这表达式类似jsp的el表达式。

![image](http://static.xiaoqiangge.com/image/3d229247-e224-414b-bba1-4755a9bc1873.png)


当result小于等于0，如下，

```
16:41:54.992 [http-nio-8080-exec-1] INFO  com.eju.ess.service.WorkService - >> 调用服务1
```
当result大于0，如下，

```
16:44:49.863 [http-nio-8080-exec-1] INFO  com.eju.ess.service.WorkService - >> 调用服务1
16:44:49.869 [http-nio-8080-exec-1] INFO  com.eju.ess.service.WorkService - >> 调用服务2
16:44:49.869 [http-nio-8080-exec-1] INFO  com.eju.ess.service.WorkService - 100,在show2中显示
```
# 排它网关

bpmn文件：`/activiti-example-03/src/main/resources/processes/demo2.bpmn`

一个简单的排它网关如下，
![image](http://static.xiaoqiangge.com/image/87dcf8c7-49de-403a-a8e7-56cce1bee15c.png)

设置方式也是在流程图线上进行设定，如下，
![image](http://static.xiaoqiangge.com/image/d1a338e9-3cf2-4e2d-b28c-6bf6a6d9c3db.png)

==排它网关会过滤左右条件，直到找到第一个为`true`的进行执行，有点`if else`==