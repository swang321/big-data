启动 hive 服务

hive --service metastore &

```shell
# 把 true  改为 false
<property>
    <name>hive.metastore.schema.verification</name>
    <value>false</value>
    <description>
      Enforce metastore schema version consistency.
      True: Verify that version information stored in is compatible with one from Hive jars.  Also disable automatic
            schema migration attempt. Users are required to manually migrate schema after Hive upgrade which ensures
            proper metastore schema migration. (Default)
      False: Warn if the version information stored in metastore doesn't match with one from in Hive jars.
    </description>
    
# 把 false  改为 true
<property>   
   <name>datanucleus.schema.autoCreateAll</name>   
   <value>true</value>
</property>
```

```sql

hive> show databases;
OK
default
hive_test
Time taken: 0.016 seconds, Fetched: 2 row(s)
hive> 
```

### 新建数据库 语法：

```
CREATE (DATABASE|SCHEMA) [IF NOT EXISTS] database_name   --DATABASE|SCHEMA 是等价的
  [COMMENT database_comment] --数据库注释
  [LOCATION hdfs_path] --存储在 HDFS 上的位置
  [WITH DBPROPERTIES (property_name=property_value, ...)]; --指定额外属性
```

###   示例：

```
CREATE DATABASE IF NOT EXISTS hive_test
COMMENT 'hive database for test'
WITH DBPROPERTIES ('create'='root');
```

```
hive> show databases;  #查看数据库
OK
default
hive_test
Time taken: 0.016 seconds, Fetched: 2 row(s)
hive> 
```

###  查看数据库信息

语法：

```
DESC DATABASE [EXTENDED] db_name; --EXTENDED 表示是否显示额外属性
```

示例：

```
hive> DESC DATABASE  EXTENDED hive_test;
OK
hive_test	hive database for test	hdfs://master:9000/user/hive/warehouse/hive_test.db	root	USER	{create=heibaiying}
Time taken: 0.02 seconds, Fetched: 1 row(s)
hive> 

```

## 创建表

### 建表语法

```sql
CREATE [TEMPORARY] [EXTERNAL] TABLE [IF NOT EXISTS] [db_name.]table_name     --表名
  [(col_name data_type [COMMENT col_comment],
    ... [constraint_specification])]  --列名 列数据类型
  [COMMENT table_comment]   --表描述
  [PARTITIONED BY (col_name data_type [COMMENT col_comment], ...)]  --分区表分区规则
  [
    CLUSTERED BY (col_name, col_name, ...) 
   [SORTED BY (col_name [ASC|DESC], ...)] INTO num_buckets BUCKETS
  ]  --分桶表分桶规则
  [SKEWED BY (col_name, col_name, ...) ON ((col_value, col_value, ...), (col_value, col_value, ...), ...)  
   [STORED AS DIRECTORIES] 
  ]  --指定倾斜列和值
  [
   [ROW FORMAT row_format]    
   [STORED AS file_format]
     | STORED BY 'storage.handler.class.name' [WITH SERDEPROPERTIES (...)]  
  ]  -- 指定行分隔符、存储文件格式或采用自定义存储格式
  [LOCATION hdfs_path]  -- 指定表的存储位置
  [TBLPROPERTIES (property_name=property_value, ...)]  --指定表的属性
  [AS select_statement];   --从查询结果创建表
```

```
内部表
外部表
分区表            PARTITIONED BY 子句创建分区表
分桶表		       CLUSTERED BY 指定分桶列    插入数据时需要用 （ CTAS 会触发 mapreduce ） 才会计算hash
倾斜表
临时表
复制表结构
加载数据到表
重命名表
修改列
新增列
清空表
删除表  
	   内部表：不仅会删除表的元数据，同时会删除 HDFS 上的数据；
        外部表：只会删除表的元数据，不会删除 HDFS 上的数据；
        删除视图引用的表时，不会给出警告（但视图已经无效了，必须由用户删除或重新创建）。

```

### Hive 在查询时候是不会自动去使用索引的 需要开启相关配置 hive 设置启动索引 

```yaml
SET hive.input.format=org.apache.hadoop.hive.ql.io.HiveInputFormat;
SET hive.optimize.index.filter=true;
SET hive.optimize.index.filter.compact.minsize=0;
```

同时按照[官方文档](https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Indexing) 的说明，Hive 会从 3.0 开始移除索引功能，主要基于以下两个原因：

- 具有自动重写的物化视图 (Materialized View) 可以产生与索引相似的效果（Hive 2.3.0 增加了对物化视图的支持，在 3.0 之后正式引入）。
- 使用列式存储文件格式（Parquet，ORC）进行存储时，这些格式支持选择性扫描，可以跳过不需要的文件或块。





eg

### 2.2 内部表

```sql
 CREATE TABLE emp(
     empno INT,     -- 员工表编号
     ename STRING,  -- 员工姓名
     job STRING,    -- 职位类型
     mgr INT,   
     hiredate TIMESTAMP,  --雇佣日期
     sal DECIMAL(7,2),  --工资
     comm DECIMAL(7,2),
     deptno INT)   --部门编号
    ROW FORMAT DELIMITED FIELDS TERMINATED BY "\t";
    
     -- 建表语句
 CREATE TABLE dept(
     deptno INT,   --部门编号
     dname STRING,  --部门名称
     loc STRING    --部门所在的城市
 )
 ROW FORMAT DELIMITED FIELDS TERMINATED BY "\t";
    
 CREATE EXTERNAL TABLE emp_partition(
    empno INT,
    ename STRING,
    job STRING,
    mgr INT,
    hiredate TIMESTAMP,
    sal DECIMAL(7,2),
    comm DECIMAL(7,2)
    )
    PARTITIONED BY (deptno INT)   -- 按照部门编号进行分区
    ROW FORMAT DELIMITED FIELDS TERMINATED BY "\t"
    LOCATION '/hive/emp_partition';
    
    
    CREATE TABLE emp_ptn(
    empno INT,
    ename STRING,
    job STRING,
    mgr INT,
    hiredate TIMESTAMP,
    sal DECIMAL(7,2),
    comm DECIMAL(7,2)
    )
    PARTITIONED BY (deptno INT)   -- 按照部门编号进行分区
    ROW FORMAT DELIMITED FIELDS TERMINATED BY "\t";
```



