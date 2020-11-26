zk

下载tar包解压

cp zoo-sample.cfg zoo.cfg

 vim zoo.cfg

```shell
dataDir=/usr/local/hadoop/zk/zk_data     ##  修改目录

## 2888原子广播端口，3888选举端口，zookeeper有几个节点，就配置几个server
server.1=master:2888:3888
server.2=slave1:2888:3888
server.3=slave2:2888:3888
```

在zk_data目录下面生成一个文件叫  **myid** 得文件

```shell
vim myid
# 这个 1 代表 节点  server.1  得1  另外得就配置 对应得节点  2     3 

```

配置zk环境变量

```shell
export ZOOKEEPER_HOME=/usr/local/hadoop/zk/apache-zookeeper-3.5.8-bin
export PATH=$ZOOKEEPER_HOME/bin:$PATH
```

zk 相关命令

```shell
#启动ZK服务: 
zkServer.sh start
#停止ZK服务: 
zkServer.sh stop
#重启ZK服务: 
zkServer.sh restart
#查看ZK服务状态: 
zkServer.sh status
```

