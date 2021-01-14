### 新建  文本   data.txt 内容为

ver=1&en=e_pv&pl=website&sdk=js&b_rst=1920*1080&u_ud=12GH4079-223E-4A57-AC60-C1A04D8F7A2F&l=zh-CN&u_sd=8E9559B3-DA35-44E1-AC98-85EB37D1F263&c_time=1610593053464&p_url=http://list.iqiyi.com/www/21/---.html

```shell
[root@master logs] cat data.txt | while read line  # 实时读取
> do
> echo "$line"
> sleep 1
> done

[root@master logs] cat data.txt | while read line  # 实时写入
> do
> echo "$line" >> data.log
> sleep 0.5
> done

#! /bin/bash # employ bash shell

do
echo "$line" >> data.log
sleep 0.5
done

```

### flume发送数据到kafka

1. 1. ### 需求

      - A、B两台机器实时生产日志主要类型为`access.log`、`ugcheader.log`、`ugctail.log` , **要求**：

      1. 把A、B ,C 机器中的access.log、ugcheader.log、ugctail.log 汇总到C机器上然后统一收集到hdfs和Kafka中。
      2. 在hdfs中要求的目录为：用作离线统计。
         **/source/access/2016-01-01/**
         **/source/ugcheader/2016-01-01/**
         **/source/ugctail/2016-01-01/**
      3. Kafka分topic , 用作实时分析。



1.hdfs创建对应的目录

2.创建对应的 topic

```shell
kafka-topics.sh --create --zookeeper master:2181,slave1:2181,slave2:2181 --replication-factor 3 --partitions 3 --topic access
kafka-topics.sh --create --zookeeper master:2181,slave1:2181,slave2:2181 --replication-factor 3 --partitions 3 --topic ugchead
kafka-topics.sh --create --zookeeper master:2181,slave1:2181,slave2:2181 --replication-factor 3 --partitions 3 --topic ugctail
```

c机器  hdfs_kafka.conf

```shell
#################################### c 机器 #########################################
#################################### 两个channel、两个sink ##########################
# Name the components on this agent
a1.sources = r1
a1.sinks = kfk fs
a1.channels = c1 c2

# varo source
a1.sources.r1.type = avro
a1.sources.r1.bind = 0.0.0.0
a1.sources.r1.port = 44444

# source r1定义拦截器，为消息添加时间戳
a1.sources.r1.interceptors = i1
a1.sources.r1.interceptors.i1.type = org.apache.flume.interceptor.TimestampInterceptor$Builder

# kfk sink
a1.sinks.kfk.type = org.apache.flume.sink.kafka.KafkaSink
#a1.sinks.kfk.topic = mytopic
a1.sinks.kfk.brokerList = master:9092,slave1:9092,slave2:9092

# hdfs sink
a1.sinks.fs.type = hdfs
a1.sinks.fs.hdfs.path = hdfs://master:9000/source/%{type}/%Y%m%d
a1.sinks.fs.hdfs.filePrefix = events-
a1.sinks.fs.hdfs.fileType = DataStream
#a1.sinks.fs.hdfs.fileType = CompressedStream
#a1.sinks.fs.hdfs.codeC = gzip
#不按照条数生成文件
a1.sinks.fs.hdfs.rollCount = 0
#如果压缩存储的话HDFS上的文件达到64M时生成一个文件注意是压缩前大小为64生成一个文件，然后压缩存储。
a1.sinks.fs.hdfs.rollSize = 67108864
a1.sinks.fs.hdfs.rollInterval = 0

# Use a channel which buffers events in memory
a1.channels.c1.type = memory
a1.channels.c1.capacity = 10000
a1.channels.c1.transactionCapacity = 1000

a1.channels.c2.type = memory
a1.channels.c2.capacity = 10000
a1.channels.c2.transactionCapacity = 1000

# Bind the source and sink to the channel
a1.sources.r1.channels = c1 c2
a1.sinks.kfk.channel = c1
a1.sinks.fs.channel = c2
```

启动 c

```
flume-ng agent --conf $FLUME_HOME/conf --conf-file $FLUME_HOME/conf/job/hdfs_kafka.conf --name a1 -Dflume.root.logger=INFO,console &
```

a b机器   hdfs_kafka.conf

```shell
#################################### A机器 #########################################
#################################### 3个source #####################################
#################################### 2个拦截器 ######################################
# Name the components on this agent
a1.sources = access ugchead ugctail
a1.sinks = k1
a1.channels = c1

# 三个sources
a1.sources.access.type = exec
a1.sources.access.command = tail -F /usr/local/hadoop/logs/access.log

a1.sources.ugchead.type = exec
a1.sources.ugchead.command = tail -F /usr/local/hadoop/logs/ugchead.log

a1.sources.ugctail.type = exec
a1.sources.ugctail.command = tail -F /usr/local/hadoop/logs/ugctail.log

# sink
a1.sinks.k1.type = avro
a1.sinks.k1.hostname = master
a1.sinks.k1.port = 44444

# channel
a1.channels.c1.type = memory
a1.channels.c1.capacity = 10000
a1.channels.c1.transactionCapacity = 1000

# interceptor
a1.sources.access.interceptors = i1 i2
a1.sources.access.interceptors.i1.type=static
a1.sources.access.interceptors.i1.key = type
a1.sources.access.interceptors.i1.value = access
a1.sources.access.interceptors.i2.type=static
a1.sources.access.interceptors.i2.key = topic
a1.sources.access.interceptors.i2.value = access

a1.sources.ugchead.interceptors = i1 i2
a1.sources.ugchead.interceptors.i1.type=static
a1.sources.ugchead.interceptors.i1.key = type
a1.sources.ugchead.interceptors.i1.value = ugchead
a1.sources.ugchead.interceptors.i2.type=static
a1.sources.ugchead.interceptors.i2.key = topic
a1.sources.ugchead.interceptors.i2.value = ugchead

a1.sources.ugctail.interceptors = i1 i2
a1.sources.ugctail.interceptors.i1.type=static
a1.sources.ugctail.interceptors.i1.key = type
a1.sources.ugctail.interceptors.i1.value = ugctail
a1.sources.ugctail.interceptors.i2.type=static
a1.sources.ugctail.interceptors.i2.key = topic
a1.sources.ugctail.interceptors.i2.value = ugctail

# Bind the source and sink to the channel
a1.sources.access.channels = c1
a1.sources.ugchead.channels = c1
a1.sources.ugctail.channels = c1
a1.sinks.k1.channel = c1
```

启动 a  b

```
flume-ng agent --conf $FLUME_HOME/conf --conf-file $FLUME_HOME/conf/job/hdfs_kafka.conf --name a1 -Dflume.root.logger=INFO,console &
```

