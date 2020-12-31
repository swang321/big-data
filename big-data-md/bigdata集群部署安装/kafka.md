进入解压目录的 `config` 目录下  修改对应配置

```shell
broker.id=1   # 机器 id节点
log.dirs=/usr/local/hadoop/kafka_2.12-1.0.0/logs
zookeeper.connect=master:2181,slave1:2181,slave2:2181
```

把 kafka 文件分发到其他机器

把  broker.id 改成对应机器id  机器id 自定义  不重复就行

配置环境变量

```
export KAFKA_HOME=/usr/local/hadoop/kafka_2.12-1.0.0
export PATH=$PATH:$KAFKA_HOME/bin
```

kafka命令 

```shell
#启动集群
kafka-server-start.sh -daemon /usr/local/hadoop/kafka_2.12-1.0.0/config/server.properties &
# 创建 topic 主题
# --replication-factor 2   复制两份
# --partitions 1         创建1个分区
# --topic whh          topic 名称
kafka-topics.sh --create --zookeeper master:2181,slave1:2181,slave2:2181 --replication-factor 1 --partitions 2 --topic kafka-test
# 查看已经存在的topic
kafka-topics.sh --list --zookeeper master:2181,slave1:2181,slave2:2181
# 查看 topic 主题得 消息
kafka-console-consumer.sh --bootstrap-server master:9092 --topic hello --from-beginning

# 对应得 版本不一样 命令也不一样
```

命令 

```shell

-rwxr-xr-x. 1 root root 1335 10月 27 2017 connect-distributed.sh
-rwxr-xr-x. 1 root root 1332 10月 27 2017 connect-standalone.sh
-rwxr-xr-x. 1 root root  861 10月 27 2017 kafka-acls.sh
-rwxr-xr-x. 1 root root  873 10月 27 2017 kafka-broker-api-versions.sh
-rwxr-xr-x. 1 root root  864 10月 27 2017 kafka-configs.sh
-rwxr-xr-x. 1 root root  945 10月 27 2017 kafka-console-consumer.sh
-rwxr-xr-x. 1 root root  944 10月 27 2017 kafka-console-producer.sh
-rwxr-xr-x. 1 root root  871 10月 27 2017 kafka-consumer-groups.sh
-rwxr-xr-x. 1 root root  948 10月 27 2017 kafka-consumer-perf-test.sh
-rwxr-xr-x. 1 root root  869 10月 27 2017 kafka-delete-records.sh
-rwxr-xr-x. 1 root root  863 10月 27 2017 kafka-log-dirs.sh
-rwxr-xr-x. 1 root root  862 10月 27 2017 kafka-mirror-maker.sh
-rwxr-xr-x. 1 root root  886 10月 27 2017 kafka-preferred-replica-election.sh
-rwxr-xr-x. 1 root root  959 10月 27 2017 kafka-producer-perf-test.sh
-rwxr-xr-x. 1 root root  874 10月 27 2017 kafka-reassign-partitions.sh
-rwxr-xr-x. 1 root root  868 10月 27 2017 kafka-replay-log-producer.sh
-rwxr-xr-x. 1 root root  874 10月 27 2017 kafka-replica-verification.sh
-rwxr-xr-x. 1 root root 7579 10月 27 2017 kafka-run-class.sh
-rwxr-xr-x. 1 root root 1376 10月 27 2017 kafka-server-start.sh
-rwxr-xr-x. 1 root root  975 10月 27 2017 kafka-server-stop.sh
-rwxr-xr-x. 1 root root  870 10月 27 2017 kafka-simple-consumer-shell.sh
-rwxr-xr-x. 1 root root  945 10月 27 2017 kafka-streams-application-reset.sh
-rwxr-xr-x. 1 root root  863 10月 27 2017 kafka-topics.sh
-rwxr-xr-x. 1 root root  958 10月 27 2017 kafka-verifiable-consumer.sh
-rwxr-xr-x. 1 root root  958 10月 27 2017 kafka-verifiable-producer.sh
-rwxr-xr-x. 1 root root 1722 10月 27 2017 trogdor.sh
drwxr-xr-x. 2 root root 4096 10月 27 2017 windows
-rwxr-xr-x. 1 root root  867 10月 27 2017 zookeeper-security-migration.sh
-rwxr-xr-x. 1 root root 1393 10月 27 2017 zookeeper-server-start.sh
-rwxr-xr-x. 1 root root  978 10月 27 2017 zookeeper-server-stop.sh
-rwxr-xr-x. 1 root root  968 10月 27 2017 zookeeper-shell.sh


Option                                   Description                            

------                                   -----------

--blacklist <String: blacklist>          Blacklist of topics to exclude from    
                                           consumption.                         
--bootstrap-server <String: server to    REQUIRED (unless old consumer is       
  connect to>                              used): The server to connect to.     
--consumer-property <String:             A mechanism to pass user-defined       
  consumer_prop>                           properties in the form key=value to  
                                           the consumer.                        
--consumer.config <String: config file>  Consumer config properties file. Note  
                                           that [consumer-property] takes       
                                           precedence over this config.         
--csv-reporter-enabled                   If set, the CSV metrics reporter will  
                                           be enabled                           
--delete-consumer-offsets                If specified, the consumer path in     
                                           zookeeper is deleted when starting up
--enable-systest-events                  Log lifecycle events of the consumer   
                                           in addition to logging consumed      
                                           messages. (This is specific for      
                                           system tests.)                       
--formatter <String: class>              The name of a class to use for         
                                           formatting kafka messages for        
                                           display. (default: kafka.tools.      
                                           DefaultMessageFormatter)             
--from-beginning                         If the consumer does not already have  
                                           an established offset to consume     
                                           from, start with the earliest        
                                           message present in the log rather    
                                           than the latest message.             
--group <String: consumer group id>      The consumer group id of the consumer. 
--isolation-level <String>               Set to read_committed in order to      
                                           filter out transactional messages    
                                           which are not committed. Set to      
                                           read_uncommittedto read all          
                                           messages. (default: read_uncommitted)
--key-deserializer <String:                                                     
  deserializer for key>                                                         
--max-messages <Integer: num_messages>   The maximum number of messages to      
                                           consume before exiting. If not set,  
                                           consumption is continual.            
--metrics-dir <String: metrics           If csv-reporter-enable is set, and     
  directory>                               this parameter isset, the csv        
                                           metrics will be output here          
--new-consumer                           Use the new consumer implementation.   
                                           This is the default, so this option  
                                           is deprecated and will be removed in 
                                           a future release.                    
--offset <String: consume offset>        The offset id to consume from (a non-  
                                           negative number), or 'earliest'      
                                           which means from beginning, or       
                                           'latest' which means from end        
                                           (default: latest)                    
--partition <Integer: partition>         The partition to consume from.         
                                           Consumption starts from the end of   
                                           the partition unless '--offset' is   
                                           specified.                           
--property <String: prop>                The properties to initialize the       
                                           message formatter.                   
--skip-message-on-error                  If there is an error when processing a 
                                           message, skip it instead of halt.    
--timeout-ms <Integer: timeout_ms>       If specified, exit if no message is    
                                           available for consumption for the    
                                           specified interval.                  
--topic <String: topic>                  The topic id to consume on.            
--value-deserializer <String:                                                   
  deserializer for values>                                                      
--whitelist <String: whitelist>          Whitelist of topics to include for     
                                           consumption.                         
--zookeeper <String: urls>               REQUIRED (only when using old          
                                           consumer): The connection string for 
                                           the zookeeper connection in the form 
                                           host:port. Multiple URLS can be      
                                           given to allow fail-over.   
```

