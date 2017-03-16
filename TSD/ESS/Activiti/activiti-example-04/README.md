# 并行网关

如下图，![image](http://static.xiaoqiangge.com/image/9ba9bfb8-e61d-420b-a8cb-a01763e808ec.png)

启动该任务流，在`ACT_RU_EXECUTION`中可以看到如下数据，![image](http://static.xiaoqiangge.com/image/696a4f63-c1b0-493f-b65b-08e494a830bc.png)
在`ACT_RU_EXECUTION`中插入了3条执行动作，说明任务在在并行网关处被分成了2个任务流执行，其中`usertask2`和`usertask1`的`parent_id`为`5`说明是子任务；

在`ACT_RU_TASK`中可以看到数据，如下，
![image](http://static.xiaoqiangge.com/image/9cc18e9f-1583-4bf8-9896-05e17e0b8a2b.png)

执行下面代码完成一个子任务**需要领导审**批这个步骤，
```
public void test4(){
		taskService.complete("11");
	}
```
再次观察数据`ACT_RU_EXECUTION`
![image](http://static.xiaoqiangge.com/image/4d781279-b626-4c0b-a0c9-c3d0f3565587.png)
从图上可以看到`usertask1`变成了`parallelgateway2`,说明子任务已经完成等待在`parallelgateway2`处了。

观察`ACT_RU_TASK`
![image](http://static.xiaoqiangge.com/image/563c487e-aa91-4d69-b8c6-68b4abf1d8e2.png)
可以看到任务数据少了一条。

由此可以看到并发网关实际上是**分-合**的过程，先将任务分成若干个可以同时执行的子任务，先完成的在某处进行等待，等待其他的任务完成然后再进行下一步，这个过程有点类似java开发中的`CyclicBarrier`。
