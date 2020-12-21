# big-data

### 技能点 大数据框架

    Hadoop MapReduce、YARN、HDFS、HBase、Hive、Zookeeper、Spark、Storm、Flink、Kafka


​    
    Hadoop Hive Spark Storm Flink HBase  Zookeeper Flume  Scala
    Kafka Sqoop Azkaban

HDFS 分布式文件系统 
MapReduce 分布式并行编程模型 
YARN 资源管理和调度器 
Tez 运行在YARN之上的下一代Hadoop查询处理框架 
Hive Hadoop上的数据仓库 
HBase Hadoop上的非关系型的分布式数据库 
Pig 一个基于Hadoop的大规模数据分析平台，提供类似SQL的查询语言Pig Latin 
Sqoop 用于在Hadoop与传统数据库之间进行数据传递 
Oozie Hadoop上的工作流管理系统 
Zookeeper 提供分布式协调一致性服务 
Storm 流计算框架 
Flume 一个高可用的，高可靠的，分布式的海量日志采集、聚合和传输的系统 
Ambari Hadoop快速部署工具，支持Apache Hadoop集群的供应、管理和监控 
Kafka 一种高吞吐量的分布式发布订阅消息系统，可以处理消费者规模的网站中的所有动作流数据 
Spark 类似于Hadoop MapReduce的通用并行框架 

- - #### Hadoop

    - [分布式文件存储系统 —— HDFS](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hadoop-HDFS.md)
    - [分布式计算框架 —— MapReduce](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hadoop-MapReduce.md)
    - [集群资源管理器 —— YARN](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hadoop-YARN.md)
    - [Hadoop 单机伪集群环境搭建](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/Hadoop单机环境搭建.md)
    - [Hadoop 集群环境搭建](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/Hadoop集群环境搭建.md)
    - [HDFS 常用 Shell 命令](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/HDFS常用Shell命令.md)
    - [HDFS Java API 的使用](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/HDFS-Java-API.md)
    - [基于 Zookeeper 搭建 Hadoop 高可用集群](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/基于Zookeeper搭建Hadoop高可用集群.md)

  - #### Hive

    - [Hive 简介及核心概念](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hive简介及核心概念.md)
    - [Linux 环境下 Hive 的安装部署](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/Linux环境下Hive的安装部署.md)
    - [Hive CLI 和 Beeline 命令行的基本使用](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/HiveCLI和Beeline命令行的基本使用.md)
    - [Hive 常用 DDL 操作](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hive常用DDL操作.md)
    - [Hive 分区表和分桶表](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hive分区表和分桶表.md)
    - [Hive 视图和索引](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hive视图和索引.md)
    - [Hive 常用 DML 操作](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hive常用DML操作.md)
    - [Hive 数据查询详解](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hive数据查询详解.md)

  - Spark

    - Spark Core
      - [Spark 简介](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Spark简介.md)
      - [Spark 开发环境搭建](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/Spark开发环境搭建.md)
      - [弹性式数据集 RDD](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Spark_RDD.md)
      - [RDD 常用算子详解](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Spark_Transformation和Action算子.md)
      - [Spark 运行模式与作业提交](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Spark部署模式与作业提交.md)
      - [Spark 累加器与广播变量](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Spark累加器与广播变量.md)
      - [基于 Zookeeper 搭建 Spark 高可用集群](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/Spark集群环境搭建.md)
    - Spark SQL
      - [DateFrame 和 DataSet](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/SparkSQL_Dataset和DataFrame简介.md)
      - [Structured API 的基本使用](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Spark_Structured_API的基本使用.md)
      - [Spark SQL 外部数据源](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/SparkSQL外部数据源.md)
      - [Spark SQL 常用聚合函数](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/SparkSQL常用聚合函数.md)
      - [Spark SQL JOIN 操作](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/SparkSQL联结操作.md)
    - Spark Streaming
      - [Spark Streaming 简介](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Spark_Streaming与流处理.md)
      - [Spark Streaming 基本操作](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Spark_Streaming基本操作.md)
      - [Spark Streaming 整合 Flume](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Spark_Streaming整合Flume.md)
      - [Spark Streaming 整合 Kafka](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Spark_Streaming整合Kafka.md)

  - Storm

    - [Storm 和流处理简介](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Storm和流处理简介.md)
    - [Storm 核心概念详解](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Storm核心概念详解.md)
    - [Storm 单机环境搭建](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/Storm单机环境搭建.md)
    - [Storm 集群环境搭建](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/Storm集群环境搭建.md)
    - [Storm 编程模型详解](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Storm编程模型详解.md)
    - [Storm 项目三种打包方式对比分析](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Storm三种打包方式对比分析.md)
    - [Storm 集成 Redis 详解](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Storm集成Redis详解.md)
    - [Storm 集成 HDFS/HBase](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Storm集成HBase和HDFS.md)
    - [Storm 集成 Kafka](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Storm集成Kakfa.md)

  - Flink

    - [Flink 核心概念综述](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Flink核心概念综述.md)
    - [Flink 开发环境搭建](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Flink开发环境搭建.md)
    - [Flink Data Source](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Flink_Data_Source.md)
    - [Flink Data Transformation](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Flink_Data_Transformation.md)
    - [Flink Data Sink](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Flink_Data_Sink.md)
    - [Flink 窗口模型](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Flink_Windows.md)
    - [Flink 状态管理与检查点机制](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Flink状态管理与检查点机制.md)
    - [Flink Standalone 集群部署](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/Flink_Standalone_Cluster.md)

  - Hbase

    - [Hbase 简介](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hbase简介.md)
    - [HBase 系统架构及数据结构](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hbase系统架构及数据结构.md)
    - [HBase 基本环境搭建 (Standalone /pseudo-distributed mode)](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/HBase单机环境搭建.md)
    - [HBase 集群环境搭建](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/HBase集群环境搭建.md)
    - [HBase 常用 Shell 命令](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hbase_Shell.md)
    - [HBase Java API](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hbase_Java_API.md)
    - [HBase 过滤器详解](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hbase过滤器详解.md)
    - [HBase 协处理器详解](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hbase协处理器详解.md)
    - [HBase 容灾与备份](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hbase容灾与备份.md)
    - [HBase的 SQL 中间层 —— Phoenix](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Hbase的SQL中间层_Phoenix.md)
    - [Spring/Spring Boot 整合 Mybatis + Phoenix](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Spring+Mybtais+Phoenix整合.md)

  - Kafka

    - [Kafka 简介](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Kafka简介.md)
    - [基于 Zookeeper 搭建 Kafka 高可用集群](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/基于Zookeeper搭建Kafka高可用集群.md)
    - [Kafka 生产者详解](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Kafka生产者详解.md)
    - [Kafka 消费者详解](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Kafka消费者详解.md)
    - [深入理解 Kafka 副本机制](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Kafka深入理解分区副本机制.md)

  - Zookeeper

    - [Zookeeper 简介及核心概念](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Zookeeper简介及核心概念.md)
    - [Zookeeper 单机环境和集群环境搭建](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/Zookeeper单机环境和集群环境搭建.md)
    - [Zookeeper 常用 Shell 命令](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Zookeeper常用Shell命令.md)
    - [Zookeeper Java 客户端 —— Apache Curator](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Zookeeper_Java客户端Curator.md)
    - [Zookeeper ACL 权限控制](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Zookeeper_ACL权限控制.md)

  - Flume

    - [Flume 简介及基本使用](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Flume简介及基本使用.md)
    - [Linux 环境下 Flume 的安装部署](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/Linux下Flume的安装.md)
    - [Flume 整合 Kafka](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Flume整合Kafka.md)

  - Sqoop

    - [Sqoop 简介与安装](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Sqoop简介与安装.md)
    - [Sqoop 的基本使用](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Sqoop基本使用.md)

  - Azkaban

    - [Azkaban 简介](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Azkaban简介.md)
    - [Azkaban3.x 编译及部署](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/installation/Azkaban_3.x_编译及部署.md)
    - [Azkaban Flow 1.0 的使用](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Azkaban_Flow_1.0_的使用.md)
    - [Azkaban Flow 2.0 的使用](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/Azkaban_Flow_2.0_的使用.md)

  - 公共内容

    - [大数据应用常用打包方式](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/大数据应用常用打包方式.md)
    - [资料分享与开发工具推荐](https://github.com/Lcsbs/technology-summary/blob/master/docs/bigdata/notes/资料分享与工具推荐.md)

- 工具

  - [Java](https://github.com/Lcsbs/technology-summary/blob/master/docs/overview/tools/Java.md)
  - [Git](https://github.com/Lcsbs/technology-summary/blob/master/docs/overview/tools/Git.md)
  - [Docker](https://github.com/Lcsbs/technology-summary/blob/master/docs/overview/tools/Docker.md)