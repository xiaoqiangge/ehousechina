`Receive Task`是一个需要外部激活以后才能继续进行下一步的组件，这个组件本身什么逻辑，如下图，
![image](http://static.xiaoqiangge.com/image/4c6b8ff7-f2de-4f62-8e4c-334fbd664c35.png)

发起**请假**流程以后，流程进入等待**领导确认**环节，**领导确认**环节可以理解为一个挂起操作,自身没有多余的逻辑，只是在等待一个通知，通过如下代码示例代码进行确认，
```
Execution execution1 = runtimeService
                .createExecutionQuery()  
                .processInstanceId("5")//流程实例ID  
                .activityId("receivetask1")//当前活动的名称  
                .singleResult(); 
		runtimeService.signal(execution1.getId());
```
当**领导确认**完以后立刻进入**销假**环节。

参考：
http://blog.csdn.net/zjx86320/article/details/50385444