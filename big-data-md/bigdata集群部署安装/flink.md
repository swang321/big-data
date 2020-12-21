修改 `conf/flink-conf.yaml` 文件，增加如下配置：

```shell
#开启HA，使用文件系统作为快照存储
state.backend: filesystem
#启用检查点，可以将快照保存到HDFS
state.backend.fs.checkpointdir: hdfs://master:9000/flink-checkpoints
#使用zookeeper搭建高可用
high-availability: zookeeper
# 存储JobManager的元数据到HDFS
high-availability.storageDir: hdfs://master:9000/flink/ha/
high-availability.zookeeper.quorum: master:2181,slave1:2181,slave2:2181
```

修改 `conf/masters` 文件，将 master 和 slave2 都配置为 master 节点：

```
master:8081
slave2 :8081
```

## vim zoo.cfg

server.1=master:2888:3888
server.2=slave1:2888:3888
server.3=slave2:2888:3888

确保 Hadoop 和 ZooKeeper 已经启动后，使用以下命令来启动集群：

```
bin/start-cluster.sh
```

```shell
## 启动异常 
Caused by: org.apache.flink.core.fs.UnsupportedFileSystemSchemeException: Could not find a file system implementation for scheme 'hdfs'. The scheme is not directly supported by Flink and no Hadoop file system to support this scheme could be loaded. For a full list of supported file systems, please see https://ci.apache.org/projects/flink/flink-docs-stable/ops/filesystems/.
## 解决方案 
下载对应得jar包  
可以从 Flink 官网下载对应版本的 Hadoop 组件包：  https://flink.apache.org/downloads.html
flink-shaded-hadoop-2-uber-2.8.3-10.0.jar
```



![image-20201221104112109](C:\Users\虎克\AppData\Roaming\Typora\typora-user-images\image-20201221104112109.png)![image-20201221104131176](C:\Users\虎克\AppData\Roaming\Typora\typora-user-images\image-20201221104131176.png)

表示启动成功了

分别访问    master:8081 slave2:8081

