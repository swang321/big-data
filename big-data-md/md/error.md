

#### 错误一:   

 **Failed to locate the winutils binary in the hadoop binary path**

配置hadoop环境变量  并设置  winutils.exe环境

#### 错误二  :

**Can not resolve promote.cache-dns.local, please check your network**

配置hosts文件   虚拟机 IP 对应的 hosename  配置

192.168.126.128 master
192.168.126.129 slave1
192.168.126.130 slave2

#### 错误三: 

hadoop 权限错误 Permission denied: user=root, access=WRITE, inode="/":hdfs:super

```
### 在 hdfs-site.xml  中添加下面属性配置  运气非 
    <property>
        <name>dfs.permissions.enabled</name>
        <value>false</value>
    </property>

```

#### 错误四 ：

**java.lang.UnsatisfiedLinkError: org.apache.hadoop.util.NativeCrc32.nativeComputeChunkedSumsByteArray(II[BI[BIILjava/lang/String;JZ)V**

下载对应的  hadoop.dll  winutils.exe  版本 放到 hadoop  bin 目录下面 并且在   C:\Windows\System32  放一份

 