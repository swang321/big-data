```
新建  exec-memory-avro.conf 

​```shell
# Name the components on this agent
exec-memory-avro.sources = exec-source
exec-memory-avro.sinks = avro-sink
exec-memory-avro.channels = memory-channel

# Describe/configure the source
exec-memory-avro.sources.exec-source.type = exec
exec-memory-avro.sources.exec-source.command = tail -F /usr/local/hadoop/logs/data.log
exec-memory-avro.sources.exec-source.shell = /bin/sh -c

# Describe the sink
exec-memory-avro.sinks.avro-sink.type = avro
exec-memory-avro.sinks.avro-sink.hostname = master
exec-memory-avro.sinks.avro-sink.port = 44444

# Use a channel which buffers events in memory
exec-memory-avro.channels.memory-channel.type = memory

# Bind the source and sink to the channel
exec-memory-avro.sources.exec-source.channels = memory-channel
exec-memory-avro.sinks.avro-sink.channel = memory-channel
​```

新建  avro-memory-kafka.conf

​```shell
# name the components on this agent
avro-memory-kafka.sources = avro-source
avro-memory-kafka.sinks = kafka-sink
avro-memory-kafka.channels = memory-channel

# Describe/configure the source
avro-memory-kafka.sources.avro-source.type = avro
avro-memory-kafka.sources.avro-source.bind = master
avro-memory-kafka.sources.avro-source.port = 44444

# Describe the sink
avro-memory-kafka.sinks.kafka-sink.type = org.apache.flume.sink.kafka.KafkaSink
avro-memory-kafka.sinks.kafka-sink.brokerList = master:9092
avro-memory-kafka.sinks.kafka-sink.topic = kafka-topic
avro-memory-kafka.sinks.kafka-sink.batchSize = 5
avro-memory-kafka.sinks.kafka-sink.requiredAcks = 1

# Use a channel which buffers events in memory
avro-memory-kafka.channels.memory-channel.type = memory

# Bind the source and sink to the channel
avro-memory-kafka.sources.avro-source.channels = memory-channel
avro-memory-kafka.sinks.kafka-sink.channel = memory-channel
​```

启动 avro-memory-kafka.conf

​```
flume-ng agent --name a1 --conf $FLUME_HOME/conf --conf-file $FLUME_HOME/conf/userconf/avro-memory-kafka.conf -Dflume.root.logger=INFO,console
​```

启动 exec-memory-avro.conf

​```
flume-ng agent --name a1 --conf $FLUME_HOME/conf --conf-file $FLUME_HOME/conf/userconf/exec-memory-avro.conf -Dflume.root.logger=INFO,console
​```

查看 flume 进程

​```shell
[root@master config]# jps -m
62834 Application --name a1 --conf-file /usr/local/hadoop/flume-1.4.0-cdh5.0.0-beta-1-bin/conf/userconf/avro-memory-kafka.conf
64578 Application --name a1 --conf-file /usr/local/hadoop/flume-1.4.0-cdh5.0.0-beta-1-bin/conf/userconf/exec-memory-avro.conf
65287 Jps -m
8027 QuorumPeerMain /usr/local/hadoop/zk/apache-zookeeper-3.5.8-bin/bin/../conf/zoo.cfg
64494 Kafka /usr/local/hadoop/kafka_2.12-1.0.0/config/server.properties

​```

启动一个kafka的客户端来消费

​```
kafka-topics.sh --create --zookeeper master:2181,slave1:2181,slave2:2181 --replication-factor 1 --partitions 2 --topic testss 

kafka-console-producer.sh --broker-list master:2181 --topic testss --property parse.key=true

kafka-console-consumer.sh --bootstrap-server master:2181 --topic testss --from-beginning 
bin/kafka-console-producer.sh --bootstrap-server localhsot:9092 --topic topicName --property parse.key=true


​```
```