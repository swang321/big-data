### Dbeaver可视化工具

启动 hive2 服务 顺序

hive --service metastore &

hive --service hiveserver2 &

hadoop中  core-site.xml

    <property>
        <name>hadoop.proxyuser.root.hosts</name>
        <value>*</value>
    </property>
    <property>
        <name>hadoop.proxyuser.root.groups</name>
        <value>*</value>
    </property>
    
    <property>
        <name>hive.server2.webui.host</name>
        <value>master</value>
    </property>
    
    <property>
        <name>hive.server2.webui.port</name>
        <value>10002</value>
    </property>

下载对应 hive-jdbc.jar



grant all privileges on . to 'root'@'%' identified by 'root' with grant option; 

grant all privileges on . to 'root'@'%' identified by 'root' with grant option;

flush privileges; 刷新权限