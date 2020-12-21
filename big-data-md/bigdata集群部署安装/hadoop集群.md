
下载 hadoop tar 包 版本 hadoop-2.9.2.tar.gz

系统环境变量添加  hadoop


配置ssh免密 使用root 用户需要互相授权   （多节点互相免密授权）

     cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys;
     chmod 600 ~/.ssh/authorized_keys;


添加 java变量 （ hadoop-env.sh  mapred-env.sh  yarn-env.sh）

    export JAVA_HOME=/usr/lib/jvm/java-openjdk #java路径，一定要配，hadoop不会去读/etc/profile里面的java配置

slaves文件是指定HDFS上有哪些DataNode节点。

### core-site.xml   fs.defaultFS参数配置的是HDFS的地址

    <configuration>

        <property>
                <name>fs.default.name</name>
                <value>hdfs://master:9000</value>
        </property>

        <property>
                <name>fs.defaultFS</name>
                <value>hdfs://master:9000</value>
        </property>
        <!--默认情况下是在tmp目录，重启数据就会丢失-->
        <property>
                <name>hadoop.tmp.dir</name>
                <value>/usr/local/hadoop/tmp</value>
        </property>

        <property>
                <name>dfs.namenode.name.dir</name>
                <value>file://${hadoop.tmp.dir}/dfs/name</value>
        </property>
        <property>
                <name>dfs.datanode.data.dir</name>
                <value>file://${hadoop.tmp.dir}/dfs/data</value>
        </property>

    </configuration>



### hdfs-site.xml

        <configuration>
             <property>
                   <name>dfs.namenode.secondary.http-address</name>
                   <value>slave2:50090</value>
             </property>
              <property>
                    <name>dfs.permissions.enabled</name>
                    <value>false</value>
              </property>
        </configuration>
        
        

### yarn-site.xml

    <configuration>
        <property>
            <name>yarn.nodemanager.aux-services</name>
            <value>mapreduce_shuffle</value>
        </property>
         <!--如果yarn 不在主节点机器上  需要单独启动 -->
        <property>
            <name>yarn.resourcemanager.hostname</name>
            <value>master</value>
        </property>
        <property>
            <name>yarn.log-aggregation-enable</name>
            <value>true</value>
        </property>
        <property>
            <name>yarn.log-aggregation.retain-seconds</name>
            <value>106800</value>
        </property>
    </configuration>
    
### mapred-site.xml

    <configuration>
           <property>
               <name>mapreduce.framework.name</name>
               <value>yarn</value>
           </property>
           <property>
               <name>mapreduce.jobhistory.address</name>
               <value>master:10020</value>
           </property>
           <property>
               <name>mapreduce.jobhistory.webapp.address</name>
               <value>master:19888</value>
           </property>
    </configuration>
     
在主节点 启动 start-all.sh  

master                        slave1                     slave2

NodeManager                  
DataNode                      DataNode                    DataNode
ResourceManager               NodeManager                 NodeManager
NameNode                                                  SecondaryNameNode








scp -r /usr/local/hadoop/hadoop-2.9.2/etc/hadoop/ root@slave2:/usr/local/hadoop/hadoop-2.9.2/etc/hadoop/