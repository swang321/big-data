### 添加环境变量：

```
export HIVE_HOME=/usr/local/hadoop/hive/apache-hive-2.3.7-bin
export PATH=$HIVE_HOME/bin:$PATH
```

### 使得配置的环境变量立即生效：

```
# source /etc/profile
```



#### 配置文件重命名

将hive-defaultxml.template重命名为hive-site.xml
将hive-env.sh.template重命名为hive-env.sh
将hive-log4j.properties.template重命名为hive-log4j.properties

#### 修改hive-env.sh配置

```shell
JAVA_HOME=/usr/lib/jvm/java-openjdk
HADOOP_HOME=/usr/local/hadoop/hadoop-2.9.2
export HIVE_CONF_DIR=/usr/local/hadoop/hive/apache-hive-2.3.7-bin/conf    #  即hive的conf目录地址
```

### hive-site.xml

```
     <!-- 插入一下代码 -->
    <property>
        <name>javax.jdo.option.ConnectionUserName</name>用户名（这4是新添加的，记住删除配置文件原有的哦！）
        <value>root</value>
    </property>
    <property>
        <name>javax.jdo.option.ConnectionPassword</name>密码
        <value>root</value>
    </property>
   <property>
        <name>javax.jdo.option.ConnectionURL</name>mysql
        <value>jdbc:mysql://master:3306/hive</value>
    </property>
    <property>
        <name>javax.jdo.option.ConnectionDriverName</name>mysql驱动程序
        <value>com.mysql.jdbc.Driver</value>
    </property>
        <!-- 到此结束代码 -->
```

### 修改 hive.log配置文件

        hive.log.threshold=ALL
        hive.root.logger=WARN,DRFA
        hive.log.dir=/usr/local/hadoop/hive/apache-hive-2.3.7-bin/logs
        hive.log.file=hive.log
### 启动

```
hive
```