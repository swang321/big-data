#### HBase 安装

1	需要先有一个java环境

2	要有一个HDFS集群，并正常运行； regionserver应该跟hdfs中的datanode在一起

3	还需要一个zookeeper集群，并正常运行



#### HBASE是一个分布式系统

其中有一个管理角色：  HMaster(一般2台，一台active，一台backup)

其他的数据节点角色：  HRegionServer(很多台，看数据容量

#### 修改hbase-env.sh

```
export JAVA_HOME=/usr/lib/jvm/java-openjdk
export HBASE_MANAGES_ZK=false
```



#### 修改hbase-site.xml

```
<configuration>
<!-- 指定hbase是分布式的 -->
        <property>
                <name>hbase.cluster.distributed</name>
                <value>true</value>
        </property>
<!-- 指定hbase在HDFS上存储的路径 -->
        <property>
                <name>hbase.rootdir</name>
                <value>hdfs://master:9000/hbase</value>
        </property>
<!-- 指定zk的地址，多个用“,”分割 -->
        <property>
                <name>hbase.zookeeper.quorum</name>
                <value>master:2181,slave1:2181,slave2:2181</value>
        </property>
</configuration>
```

#### 修改 regionservers

```
master
slave1
slave2
```

配置hbase环境变量

```shell
export HBASE_HOME=/usr/local/hadoop/hbase/hbase-2.2.6
export PATH=$PATH:$HBASE_HOME/bin
```

#### 启动 hbase

```
start-hbase.sh
stop-hbase.sh
```

habse 相关 shell命令

```shell
list   # 查看表
status   #查看集群状态
version   # 查看集群版本
```



**启动顺序**

Hadoop及hbase集群**启动顺序 zookeepeer -> hadoop -> hbase**

#### **停止顺序**

Hadoop及hbase集群**关闭顺序 hbase -> hadoop -> zookeepeer**