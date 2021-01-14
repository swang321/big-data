```
### Flume实战

**1. 实战一**

- 需求：从指定网络端口采集数据输出到控制台
- 使用Flume的关键就是写配置文件
  - 配置source
  - 配置channel
  - 配置sink
  - 把以上三个组件串起来

- 从官网上拷贝一个例子，进行修改
  - a1：agent名称
  - r1：source的名称
  - k1：sink的名称
  - c1：channel的名称
  - A netcat-like source that listens on a given port and turns each line of text into an event.
    - type – The component type name, needs to be netcat
    - bind – Host name or IP address to bind to
    - port – Port # to bind to
  - Logs event at INFO level. Typically useful for testing/debugging purpose.
    - type – The component type name, needs to be logger
  - The events are stored in an in-memory queue with configurable max size.
    - type – The component type name, needs to be memory

新建配置文件 直接放在conf目录下

配置文件模：在 conf目录里面新建一个文件，文件名自定义

​```
＃example.conf：单节点Flume配置
＃命名Agent a1的组件
a1.sources = r1
a1.sinks = k1
a1.channels = c1

＃描述/配置Source
a1.sources.r1.type = netcat  Source 类型（还有其他很多）
a1.sources.r1.bind = 0.0.0.0  绑定ip

a1.sources.r1.port = 44444   端口号

＃描述Sink
a1.sinks.k1.type = logger    sink类型

＃描述内存Channel
a1.channels.c1.type = memory
a1.channels.c1.capacity = 1000
a1.channels.c1.transactionCapacity = 100

＃为Channle绑定Source和Sink
a1.sources.r1.channels = c1
a1.sinks.k1.channel = c1
注意：
（1）一个配置文件中可以配置多个Agent，一个Agent中可以包含多个Source、Sink、Channel。
（2）一个Source 可以绑定到多个通道，但一个Sink只能绑定到一个通道
​```

### 通过flume的工具启动agent

$ flume-ng agent --conf ../conf --conf-file ../conf/example.conf --name a1 -Dflume.root.logger=INFO,console

通过打开另外一个窗口监听  

​```shell
# 窗口1
.......
2021-01-04 10:57:06,909 (lifecycleSupervisor-1-4) [INFO - org.apache.flume.source.NetcatSource.start(NetcatSource.java:164)] Created serverSocket:sun.nio.ch.ServerSocketChannelImpl[/192.168.126.128:44444]
2021-01-04 10:57:54,056 (SinkRunner-PollingRunner-DefaultSinkProcessor) [INFO - org.apache.flume.sink.LoggerSink.process(LoggerSink.java:70)] Event: { headers:{} body: 68 65 6C 6C 6F 0D                               hello. }
2021-01-04 10:58:11,680 (SinkRunner-PollingRunner-DefaultSinkProcessor) [INFO - org.apache.flume.sink.LoggerSink.process(LoggerSink.java:70)] Event: { headers:{} body: 77 6F 72 64 0D                                  word. }

telnet master 44444  # a1.sources.r1.bind = master 是上面配置得 ip a1.sources.r1.port = 44444是端口
# 窗口2
[root@master ~]# telnet master 44444
Trying 192.168.126.128...
Connected to master.
Escape character is '^]'.
hello
OK
word
OK


​```

**实战二**

- 需求：监控一个文件实时采集新增的数据输出到控制台
- Agent选型：exec source + memory channel + logger sink
  - Exec source runs a given Unix command on start-up and expects that process to continuously produce data on standard out (stderr is simply discarded, unless property logStdErr is set to true).
    - type – The component type name, needs to be exec
    - command – The command to execute

新建一个 exec-memory-logger.conf  配置文件 和  data.log  文件 

​```shell
# Name the components on this agent
a1.sources = r1
a1.sinks = k1
a1.channels = c1

# Describe/configure the source
a1.sources.r1.type = exec
a1.sources.r1.command = tail -F /usr/local/hadoop/logs/data.log
a1.sources.r1.shell = /bin/sh -c

# Describe the sink
a1.sinks.k1.type = logger

# Use a channel which buffers events in memory
a1.channels.c1.type = memory

# Bind the source and sink to the channel
a1.sources.r1.channels = c1
a1.sinks.k1.channel = c1
​```

启动

​```shell
flume-ng agent --name a1 --conf $FLUME_HOME/conf --conf-file $FLUME_HOME/conf/userconf/exec-memory-logger.conf -Dflume.root.logger=INFO,console

# 添加内容
echo hello >> data.log
echo hi >> data.log


.....
ounter group for type: SOURCE, name: r1: Successfully registered new MBean.
2021-01-04 11:16:28,753 (lifecycleSupervisor-1-4) [INFO - org.apache.flume.instrumentation.MonitoredCounterGroup.start(MonitoredCounterGroup.java:95)] Component type: SOURCE, name: r1 started
2021-01-04 11:17:14,789 (SinkRunner-PollingRunner-DefaultSinkProcessor) [INFO - org.apache.flume.sink.LoggerSink.process(LoggerSink.java:70)] Event: { headers:{} body: 68 65 6C 6C 6F                                  hello }
2021-01-04 11:17:21,133 (SinkRunner-PollingRunner-DefaultSinkProcessor) [INFO - org.apache.flume.sink.LoggerSink.process(LoggerSink.java:70)] Event: { headers:{} body: 68 69                                           hi }


​```
```