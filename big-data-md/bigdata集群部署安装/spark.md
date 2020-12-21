添加环境变量：

```shell

export SCALA_HOME=/usr/local/hadoop/scala-2.11.7
export  PATH=${SCALA_HOME}/bin:$PATH

export SPARK_HOME=/usr/local/hadoop/spark-2.4.7-bin-hadoop2.7
export  PATH=${SPARK_HOME}/bin:$PATH

# 使生效
source /etc/profile
```

进入 `${SPARK_HOME}/conf` 目录，拷贝配置样本进行修改：

```
mv spark-env.sh.template spark-env.sh

# 配置JDK安装位置
JAVA_HOME=/usr/lib/jvm/java-openjdk
# 配置hadoop配置文件的位置
HADOOP_CONF_DIR= /usr/local/hadoop/hadoop-2.9.2/etc/hadoop
# 配置zookeeper地址
SPARK_DAEMON_JAVA_OPTS="-Dspark.deploy.recoveryMode=ZOOKEEPER -Dspark.deploy.zookeeper.url=master:2181,slave1:2181,slave2:2181 -Dspark.deploy.zookeeper.dir=/spark"

### mv slaves.template slaves
vim slaves
    master
    slave1
    slave2


```

### 启动Spark集群

进入 master 的 `${SPARK_HOME}/sbin` 目录下，执行下面命令启动集群。执行命令后，会在 master 上启动 `Maser` 服务，会在 `slaves` 配置文件中配置的所有节点上启动 `Worker` 服务。

```
start-all.sh
```

分别在 slave1和 slave2上执行下面的命令，启动备用的 `Master` 服务：

```
# ${SPARK_HOME}/sbin 下执行
start-master.sh
```

### 启动Spark集群 顺序

### ZooKeeper  --->   Hadoop(dfs服务,yarn服务)   --->  Spark集群