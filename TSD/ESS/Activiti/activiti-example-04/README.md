# 并行网关

流程图如下，
![image](http://static.xiaoqiangge.com/image/f0439e75-f02e-4fca-aa70-e42c43e2f867.png)

用户发起转账申请，这时候会分成2个校验流程，1、校验账户金额是否大于0，2、校验目标账户是否正确，之后当这两个条件全部为`true`的时候，才触发最后的转账动作，==因此并行网关可以理解为`分-合`的过程==。


---
[点击这里，项目源码下载](https://github.com/xiaoqiangge/ehousechina/tree/master/TSD/ESS/Activiti/activiti-example-04)