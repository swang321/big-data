## HBase简介

HBase 是一个构建在 Hadoop 文件系统之上的面向列的数据库管理系统。

+ 持复杂的事务，只支持行级事务，即单行数据的读写都是原子性的；
+ 由于是采用 HDFS 作为底层存储，所以和 HDFS 一样，支持结构化、半结构化和非结构化的存储；
+ 支持通过增加机器进行横向扩展；
+ 支持数据分片；
+ 支持 RegionServers 之间的自动故障转移；
+ 易于使用的 Java 客户端 API；
+ 支持 BlockCache 和布隆过滤器；
+ 过滤器支持谓词下推。

<div align="center"> <img  src="https://gitee.com/heibaiying/BigData-Notes/raw/master/pictures/HBase_table-iteblog.png"/> </div>

> *图片引用自 : HBase 是列式存储数据库吗* *https://www.iteblog.com/archives/2498.html*

Hbase 的表具有以下特点：

- 容量大：一个表可以有数十亿行，上百万列；

- 面向列：数据是按照列存储，每一列都单独存放，数据即索引，在查询时可以只访问指定列的数据，有效地降低了系统的 I/O 负担；

- 稀疏性：空 (null) 列并不占用存储空间，表可以设计的非常稀疏  ；	

- 数据多版本：每个单元中的数据可以有多个版本，按照时间戳排序，新的数据在最上面； 	

- 存储类型：所有数据的底层存储格式都是字节数组 (byte[])。



## Phoenix

Phoenix` 是 HBase 的开源 SQL 中间层，



<property>   <name>hbase.balancer.tablesOnMaster</name>   <value>hbase:meta</value> </property> <property>   <name>hbase.meta.table.suffix</name>   <value>cluster-id</value> </property>