添加环境变量：

```
export STORM_HOME=/usr/local/hadoop/storm-2.2.0
export PATH=$STORM_HOME/bin:$PATH
```

修改 `${STORM_HOME}/conf/storm.yaml` 文件，配置如下：

```shell
# Zookeeper集群的主机列表
storm.zookeeper.servers:
     - "master"
     - "slave1"
     - "slave2"

# Nimbus的节点列表
nimbus.seeds: ["master","slave1"]

# Nimbus和Supervisor需要使用本地磁盘上来存储少量状态（如jar包，配置文件等）
storm.local.dir: "/usr/local/hadoop/storm_dir"

# workers进程的端口，每个worker进程会使用一个端口来接收消息
supervisor.slots.ports:
     - 6700
     - 6701
     - 6702
     - 6703
# 设置 启动ui界面端口  默认 8080  被 zk 占用了
ui.port: 8089
```

`supervisor.slots.ports` 参数用来配置 workers 进程接收消息的端口，默认每个 supervisor 节点上会启动 4 个 worker，当然你也可以按照自己的需要和服务器性能进行设置，假设只想启动 2 个 worker 的话，此处配置 2 个端口即可。

### 启动Storm集群

进入到 `${STORM_HOME}/bin` 目录下，执行下面的命令：

master & slave1 ：

```shell
# 启动主节点 nimbus
nohup sh storm nimbus &
# 启动从节点 supervisor 
nohup sh storm supervisor &
# 启动UI界面 ui  
nohup sh storm ui &
# 启动日志查看服务 logviewer 
nohup sh storm logviewer &

nohup sh storm nimbus & && nohup sh storm supervisor & && nohup sh storm ui &  && nohup sh storm logviewer &
```

slave2 ：

slave2 上只需要启动 `supervisor` 服务和 `logviewer` 服务：

```shell
# 启动从节点 supervisor 
nohup sh storm supervisor &
# 启动日志查看服务 logviewer 
nohup sh storm logviewer &
```

进入 master:8089 查看storm web 显示三台机器得状态

### 模拟高可用

kill掉 master中得 Nimbus 进程  

![image-20201221160427991](C:\Users\虎克\AppData\Roaming\Typora\typora-user-images\image-20201221160427991.png)

master 就变成  offline   slave1 就变成  leader  