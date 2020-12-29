### 查看Topology与停止Topology（命令行方式）

```shell
# 打包运行jar
storm jar storm-demo-0.0.1-SNAPSHOT.jar  com.storm.stormdemo.controller.ClusterWordCountApp

# 查看所有Topology
storm list

# 停止  storm kill topology-name [-w wait-time-secs]
storm kill ClusterWordCountApp -w 3

storm jar storm-hdfs-0.0.1-SNAPSHOT.jar com.storm.stormhdfs.mainapp.DataToHdfsApp cluster
```

java.lang.NoSuchMethodError: org.apache.hadoop.security.authentication.util.KerberosUtil.hasKerberosTicket(Ljavax/security/auth/Subject;)Z

jar包冲突



运行后，数据会储到 HDFS 的 `/storm-hdfs` 目录下。使用以下命令可以查看目录内容：

```shell
# 查看目录内容
hadoop fs -ls /storm-hdfs
# 监听文内容变化 
hadoop fs -tail -f /strom-hdfs/文件名
# 显示
eg:
Flink	Hadoop
Storm	Hive	Hadoop	Spark	HBase
Spark	Storm	Flink	Hadoop
Hadoop	Flink
HBase
HBase	Spark	Hadoop	Hive
Flink	Hive	Storm	Spark	HBase
HBase	Flink	Hadoop	Storm	Hive	Spark
HBase
Hive	Spark	Hadoop	Flink	HBase	Storm
Hive	Flink	Storm
HBase	Storm
Storm	Flink	Hadoop	Hive
Storm	Hive	Spark	HBase
Hive	Flink
Hive	Storm	Flink	Spark	Hadoop
Hive	Flink	Hadoop
Spark	Hadoop	Hive	HBase	Storm	Flink
Spark	Flink	HBase	Storm	Hadoop	Hive
Spark	Storm	Hive	Flink	Hadoop
Spark	HBase	Hadoop	Storm	Flink
Flink	Storm	Spark	Hadoop	Hive	HBase
Hive

```





## 