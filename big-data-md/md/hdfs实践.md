demo测试

```shell
hdfs dfs -mkdir /input           ## 在HDFS创建输入目录input
hdfs dfs -put wc.input /input/wc.input    ## 将wc.input上传到HDFS
hadoop fs -ls /input                        ## 查看目录信息 -ls
```

