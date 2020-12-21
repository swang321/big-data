修改 `conf/flink-conf.yaml` 文件，增加如下配置：

```shell
#### 配置使用zookeeper来开启高可用模式
 high-availability: zookeeper
#### # 配置zookeeper的地址，采用zookeeper集群时，可以使用逗号来分隔多个节点地址
 high-availability.zookeeper.quorum: master:2181,slave2:2181
#### # 在zookeeper上存储flink集群元信息的路径
 high-availability.zookeeper.path.root: /flink
#### # 集群id
 high-availability.cluster-id: /standalone_cluster_one
#### 持久化存储JobManager元数据的地址，zookeeper上存储的只是指向该元数据的指针信息
 high-availability.storageDir: hdfs://master:9000/flink/recovery
```

修改 `conf/masters` 文件，将 master 和 slave2 都配置为 master 节点：

```
master:8081
slave2 :8081
```

确保 Hadoop 和 ZooKeeper 已经启动后，使用以下命令来启动集群：

```
bin/start-cluster.sh
```

启动异常

```
Caused by: org.apache.flink.core.fs.UnsupportedFileSystemSchemeException: Could not find a file system implementation for scheme 'hdfs'. The scheme is not directly supported by Flink and no Hadoop file system to support this scheme could be loaded. For a full list of supported file systems, please see https://ci.apache.org/projects/flink/flink-docs-stable/ops/filesystems/.

```

![image-20201221104112109](C:\Users\虎克\AppData\Roaming\Typora\typora-user-images\image-20201221104112109.png)![image-20201221104131176](C:\Users\虎克\AppData\Roaming\Typora\typora-user-images\image-20201221104131176.png)

表示启动成功了