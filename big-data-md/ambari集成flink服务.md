下载  ambari-flink-service

/var/lib/ambari-server/resources/stacks/HDP/3.1/services/FLINK

/var/lib/ambari-server/resources/scripts

```
export FLINK_HOME=/var/www/html/ambari/service/flink-1.12.0
export PATH=PATH:FLINK_HOME/bin
export HADOOP_CLASSPATH=/usr/hdp/3.1.0.0-78/hadoop


#  hadoop  是集群名称   master  是 ambari 服务主机名
python configs.py -u admin -p admin -n hadoop -l master -t 8080 -a get -c cluster-env |grep -i ignore_groupsusers_create
python configs.py -u admin -p admin -n hadoop -l master -t 8080 -a get -c cluster-env -k ignore_groupsusers_create -v true

```

