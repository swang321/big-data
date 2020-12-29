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

  

  ## 三、HBase Table

  HBase 是一个面向 ` 列 ` 的数据库管理系统，这里更为确切的而说，HBase 是一个面向 ` 列族 ` 的数据库管理系统。表 schema 仅定义列族，表具有多个列族，每个列族可以包含任意数量的列，列由多个单元格（cell ）组成，单元格可以存储多个版本的数据，多个版本数据以时间戳进行区分。

  下图为 HBase 中一张表的：

  + RowKey 为行的唯一标识，所有行按照 RowKey 的字典序进行排序；
  + 该表具有两个列族，分别是 personal 和 office;
  + 其中列族 personal 拥有 name、city、phone 三个列，列族 office 拥有 tel、addres 两个列。

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