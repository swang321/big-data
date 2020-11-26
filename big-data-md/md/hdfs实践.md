demo测试

```shell
hdfs dfs -mkdir /input           ## 在HDFS创建输入目录input
hdfs dfs -put wc.input /input/wc.input    ## 将wc.input上传到HDFS
hadoop fs -ls /input                        ## 查看目录信息 -ls
```

#### 错误一: 

hadoop 权限错误 Permission denied: user=root, access=WRITE, inode="/":hdfs:super

```
### 在 hdfs-site.xml  中添加下面属性配置  运气非 
    <property>
        <name>dfs.permissions.enabled</name>
        <value>false</value>
    </property>

```

