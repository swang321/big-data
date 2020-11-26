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