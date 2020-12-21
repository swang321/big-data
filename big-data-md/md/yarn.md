##  yarn基本架构和工作机制

（0）Mr 程序提交到客户端所在的节点。

（1）Yarnrunner 向 Resourcemanager 申请一个 Application。

（2）rm 将该应用程序的资源路径返回给 yarnrunner。

（3）该程序将运行所需资源提交到 HDFS 上。

（4）程序资源提交完毕后，申请运行 mrAppMaster。

（5）RM 将用户的请求初始化成一个 task。

（6）其中一个 NodeManager 领取到 task 任务。

（7）该 NodeManager 创建容器 Container，并产生 MRAppmaster。

（8）Container 从 HDFS 上拷贝资源到本地。

（9）MRAppmaster 向 RM 申请运行 maptask 资源。

（10）RM 将运行 maptask 任务分配给另外两个 NodeManager，另两个 NodeManager 分

别领取任务并创建容器。

（11）MR 向两个接收到任务的 NodeManager 发送程序启动脚本，这两个 NodeManager

分别启动 maptask，maptask 对数据分区排序。

（12）MrAppMaster 等待所有 maptask 运行完毕后，向 RM 申请容器，运行 reduce task。

（13）reduce task 向 maptask 获取相应分区的数据。

（14）程序运行完毕后，MR 会向 RM 申请注销自己。

### YARN架构

[![img](https://raw.githubusercontent.com/Thpffcj/Thpffcj.github.io/master/picture/Big-Data-Getting-Started/YARN%E6%9E%B6%E6%9E%84.png)](https://raw.githubusercontent.com/Thpffcj/Thpffcj.github.io/master/picture/Big-Data-Getting-Started/YARN架构.png)

**1. ResourceManager：RM**

- 整个集群同一时间提供服务的RM只有一个，负责集群资源的统一管理和调度
- 处理客户端的请求：提交一个作业，杀死一个作业
- 监控我们的NodeManager，一旦某个NM挂了，那么该NM上运行的任务需要告诉我们的AM来如何进行处理

**2. NodeManager：NM**

- 整个集群中有多个，负责自己本身节点资源管理和使用
- 定时向RM汇报本节点的资源使用情况
- 接收并处理来自RM的各种命令：启动Container
- 处理来自AM的命令
- 单个节点的资源管理

**3. ApplicationMaster：AM**

- 每个应用程序对应一个：MR，Spark，负责应用程序的管理
- 为应用程序向RM申请资源(core，memory)，分配给内部task
- 需要与NM通信：启动/停止task，task是运行在Container里面，AM也是运行在Container里面

**4. Container**

- 封装了CPU，Memory等资源的一个容器
- 是一个任务运行环境的抽象

**5. Client**

- 提交作业
- 查看作业的运行进度
- 杀死作业

### YARN执行流程

- Client请求Resource Manager运行一个Application Master实例
- Resource Manager选择一个Node Manager，启动一个Container并运行Application Master实例
- Application Master根据实际需要向Resource Manager请求更多的Container资源
- Application Master通过获取到的Container资源执行分布式计算