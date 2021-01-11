#### ip安装

```
cd /etc/sysconfig/network-scripts/
vi ifcfg-eno*****
ONBOOT=no，修改为ONBOOT=yes
service network restart
ip addr
yum provides ifconfig
yum install net-tools
ifconfig
```

更改 yum 源

```
yum -y install wget
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo_bak
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
yum makecache
yum repolist
```

安装关闭 firewalld

```
yum install firewalld
systemctl status firewalld.service
systemctl stop firewalld.service
systemctl disable firewalld.service
```

yum insatll jdk

```
yum list java*
yum install java-1.8.0-openjdk.x86_64 -y
yum install java-1.8.0-openjdk-devel.x86_64 -y
```

配置 jdk  环境

```
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-0.el7_9.x86_64
```

配置 hostname  和  hosts  文件 映射

```
192.168.157.132 master
192.168.157.129 slave1
192.168.157.130 slave2
192.168.157.131 slave3
```

安装httpd服务

```
yum -y install httpd
systemctl start httpd
systemctl enable httpd
systemctl status httpd.service
```

ambari安装

```
wget http://public-repo-1.hortonworks.com/ambari/centos7/2.x/updates/2.6.1.0/ambari.repo  #  
yum clean all
yum install ambari-server

```

yum install mysql

```
rpm -ivh http://repo.mysql.com/yum/mysql-5.7-community/el/7/x86_64/mysql57-community-release-el7-10.noarch.rpm
yum install mysql-community-server -y
systemctl start mysqld
systemctl enable mysqld
mysql -uroot -p$(awk '/temporary password/{print $NF}' /var/log/mysqld.log)
set global validate_password_policy=0;
set global validate_password_length=1;
set password for root@localhost = password('root');
mysql -uroot -proot
```

ssh 免密

```shell
ssh-keygen
cat id_rsa.pub >> authorized_keys
chmod 700 .ssh
ssh-copy-id -i slave1
ssh-copy-id -i slave2
ssh-copy-id -i slave3

yum install ambari-server.x86_64 -y
yum remove ambari-server  #（卸载使用）
```

自制镜像

ambari.repo

```
#VERSION_NUMBER=2.7.3.0-139
[ambari-2.7.3.0-139]
name=ambari Version - ambari-2.7.3.0-139
baseurl=http://192.168.157.132/ambari/ambari/centos7/2.7.3.0-139
gpgcheck=1
gpgkey=http://192.168.157.132/ambari/ambari/centos7/2.7.3.0-139/RPM-GPG-KEY/RPM-GPG-KEY-Jenkins
enabled=1
priority=1
```

hdp.repo

```
[HDP-3.1.0.0-78]
name=Hortonworks Data Platform Version - HDP-3.1.0.0-78
baseurl=http://192.168.157.132/ambari/HDP/centos7/3.1.0.0-78/
gpgcheck=0
gpgkey=http://192.168.157.132/ambari/HDP/centos7/3.1.0.0-78/RPM-GPG-KEY/RPM-GPG-KEY-Jenkins
enabled=1
priority=1
```

hdp-util.repo

```
[HDP-UTILS-1.1.0.22]
name=Hortonworks Data Platform Version - HDP-UTILS-1.1.0.22
baseurl=http://192.168.157.132/ambari/HDP-UTILS/centos7/1.1.0.22/
gpgcheck=0
gpgkey=http://192.168.157.132/ambari/HDP-UTILS/centos7/1.1.0.22/HDP-UTILS/RPM-GPG-KEY/RPM-GPG-KEY-Jenkins
enabled=1
priority=1

```

hdp-gpl.repo

```

[HDP-GPL-3.1.0.0-78]
name=Hortonworks Data Platform Version - HDP-GPL-3.1.0.0-78
baseurl=http://192.168.157.132/ambari/HDP-GPL/centos7/3.1.0.0-78/
gpgcheck=0
gpgkey=http://192.168.157.132/ambari/HDP-GPL/centos7/3.1.0.0-78/RPM-GPG-KEY/RPM-GPG-KEY-Jenkins
enabled=1
priority=1
```

```
yum install yum-plugin-priorities
yum clean all
yum makecache
yum repolist
yum install -y ambari-server
```

修改配置文件

```
vim /etc/python/cert-verification.cfg
verify=disable
vim /etc/ambari-agent/conf/ambari-agent.ini
[security] 
force_https_protocol=PROTOCOL_TLSv1_2
```

```
master
slave1
slave2
slave3

ambari-server setup
ambari-server stop
ambari-server start

centos7：

修改vim /etc/locale.conf
LANG="en_US.UTF-8"
LANGUAGE="en_US:en"

yum update nss
```

