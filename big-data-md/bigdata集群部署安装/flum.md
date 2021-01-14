

添加环境变量：

```
export FLUME_HOME=/usr/local/hadoop/apache-flume-1.9.0-bin
export PATH=$FLUME_HOME/bin:$PATH
```

### 修改配置

进入安装目录下的 `conf/` 目录，拷贝 Flume 的环境配置模板 `flume-env.sh.template`：

```
 cp flume-env.sh.template flume-env.sh
```

修改 `flume-env.sh`,指定 JDK 的安装路径：

```
export JAVA_HOME=/usr/lib/jvm/java-openjdk
```

###  验证

由于已经将 Flume 的 bin 目录配置到环境变量，直接使用以下命令验证是否配置成功：

```
flume-ng version
```



集群  slave1  slave2  agenthdfs.conf文件

```shell
#########################################################
##
##主要作用是监听文件中的新增数据，采集到数据之后，输出到avro
##    注意：Flume agent的运行，主要就是配置source channel sink
##  下面的a1就是agent的代号，source叫r1 channel叫c1 sink叫k1
#########################################################
a1.sources = r1
a1.sinks = k1
a1.channels = c1

#对于source的配置描述 监听文件中的新增数据 exec
a1.sources.r1.type = exec
a1.sources.r1.command  = tail -F /usr/local/hadoop/logs/flum-data/data-access.log

#对于sink的配置描述 使用avro日志做数据的消费
a1.sinks.k1.type = avro
a1.sinks.k1.hostname = master
a1.sinks.k1.port = 44444

#对于channel的配置描述 使用文件做数据的临时缓存 这种的安全性要高
a1.channels.c1.type = file
a1.channels.c1.checkpointDir =  /usr/local/hadoop/logs/flum-data/checkpoint
a1.channels.c1.dataDirs =  /usr/local/hadoop/logs/flum-data

#通过channel c1将source r1和sink k1关联起来
a1.sources.r1.channels = c1
a1.sinks.k1.channel = c1
```

master

```shell
#########################################################
##
##主要作用是监听avro，采集到数据之后，输出到hdfs
##    注意：Flume agent的运行，主要就是配置source channel sink
##  下面的a1就是agent的代号，source叫r1 channel叫c1 sink叫k1
#########################################################
a1.sources = r1
a1.sinks = k1
a1.channels = c1

#对于source的配置描述 监听avro
a1.sources.r1.type = avro
a1.sources.r1.bind = 0.0.0.0
a1.sources.r1.port = 44444

#对于sink的配置描述 使用log日志做数据的消费
a1.sinks.k1.type = hdfs
a1.sinks.k1.hdfs.path = /flumdata/input/data/access/%y/%m/%d
a1.sinks.k1.hdfs.filePrefix = flume
a1.sinks.k1.hdfs.fileSuffix = .log
a1.sinks.k1.hdfs.inUsePrefix = tmpFlume
a1.sinks.k1.hdfs.inUseSuffix = .tmp
a1.sinks.k1.hdfs.useLocalTimeStamp = true
a1.sinks.k1.hdfs.round = true
a1.sinks.k1.hdfs.roundValue = 10
a1.sinks.k1.hdfs.roundUnit = second
#配置下面两项后，保存到HDFS中的数据才是文本
#否则通过hdfs dfs -text查看时，显示的是经过压缩的16进制
a1.sinks.k1.hdfs.serializer = TEXT
a1.sinks.k1.hdfs.fileType = DataStream

#对于channel的配置描述 使用内存缓冲区域做数据的临时缓存
a1.channels.c1.type = memory
a1.channels.c1.capacity = 1000
a1.channels.c1.transactionCapacity = 100

#通过channel c1将source r1和sink k1关联起来
a1.sources.r1.channels = c1
a1.sinks.k1.channel = c1
```



```shell
flume-ng agent -n a1 -c conf --conf-file $FLUME_HOME/conf/agenthdfs.conf -Dflume.root.logger=INFO,console &
```