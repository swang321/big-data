Redis有三种集群模式，分别是：

```
* 主从模式
* Sentinel模式
* Cluster模式
```



Sentinel模式集群

```shell
daemonize yes
logfile "/usr/local/hadoop/redis-6.0.9/sentinel.log"
dir "/usr/local/hadoop/redis-6.0.9/sentinel"
sentinel monitor mymaster master 6379 2
sentinel auth-pass mymaster root
sentinel down-after-milliseconds mymaster 30000


```

