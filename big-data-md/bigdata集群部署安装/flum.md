

添加环境变量：

```
export FLUME_HOME=/usr/local/hadoop/flume-1.4.0-cdh5.0.0-beta-1-bin
export PATH=$FLUME_HOME/bin:$PATH
```

### 修改配置

进入安装目录下的 `conf/` 目录，拷贝 Flume 的环境配置模板 `flume-env.sh.template`：

```
 cp flume-env.sh.template flume-env.sh
```

修改 `flume-env.sh`,指定 JDK 的安装路径：

```
export JAVA_HOME=/usr/lib/jvm/java-openjdk
```

###  验证

由于已经将 Flume 的 bin 目录配置到环境变量，直接使用以下命令验证是否配置成功：

```
flume-ng version
```