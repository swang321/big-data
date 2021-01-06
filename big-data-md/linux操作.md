date:2020-12-07 2020-12-13

  hdfs  和 mr  的 理解  和实践 然后总结  画图 他的执行过程



```apache

vim /etc/sysconfig/network-scripts/ifcfg-ens33

BOOTPROTO=static 

DNS1=114.114.114.114    #这个是国内的DNS地址，是固定的；
IPADDR=192.168.126.130      #你想要设置的固定IP，理论上192.168.2.2-255之间都可以，请自行验证；
NETMASK=255.255.255.0   #子网掩码，不需要修改；
GATEWAY=192.168.126.2     #网关

service network restart
```

yum卸载

```
[root@linux-node3 ~]# wget http://mirror.centos.org/centos/7/os/x86_64/Packages/yum-3.4.3-167.el7.centos.noarch.rpm
[root@linux-node3 ~]# wget http://mirror.centos.org/centos/7/os/x86_64/Packages/yum-metadata-parser-1.1.4-10.el7.x86_64.rpm
[root@linux-node3 ~]# wget http://mirror.centos.org/centos/7/os/x86_64/Packages/yum-plugin-fastestmirror-1.1.31-53.el7.noarch.rpm




```

最小化安装ip

```shell
cd /etc/sysconfig/network-scripts/
vi ifcfg-eno*****
# 找到ONBOOT=no，修改为ONBOOT=yes，然后保存退出
service network restart
ip addr
yum provides ifconfig
yum install net-tools
ifconfig
```

