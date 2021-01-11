==========================
Creating target directory...
==========================

Command start time 2021-01-11 12:23:08

Connection to slave1 closed.
SSH command execution finished
host=slave1, exitcode=0
Command end time 2021-01-11 12:23:08

==========================
Copying ambari sudo script...
==========================

Command start time 2021-01-11 12:23:08

scp /var/lib/ambari-server/ambari-sudo.sh
host=slave1, exitcode=0
Command end time 2021-01-11 12:23:08

==========================
Copying common functions script...
==========================

Command start time 2021-01-11 12:23:08

scp /usr/lib/python2.6/site-packages/ambari_commons
host=slave1, exitcode=0
Command end time 2021-01-11 12:23:08

==========================
Copying OS type check script...
==========================

Command start time 2021-01-11 12:23:08

scp /usr/lib/python2.6/site-packages/ambari_server/os_check_type.py
host=slave1, exitcode=0
Command end time 2021-01-11 12:23:08

==========================
Running OS type check...
==========================

Command start time 2021-01-11 12:23:08
Cluster primary/cluster OS family is redhat7 and local/current OS family is redhat7

Connection to slave1 closed.
SSH command execution finished
host=slave1, exitcode=0
Command end time 2021-01-11 12:23:08

==========================
Checking 'sudo' package on remote host...
==========================

Command start time 2021-01-11 12:23:08

Connection to slave1 closed.
SSH command execution finished
host=slave1, exitcode=0
Command end time 2021-01-11 12:23:08

==========================
Copying repo file to 'tmp' folder...
==========================

Command start time 2021-01-11 12:23:08

scp /etc/yum.repos.d/ambari.repo
host=slave1, exitcode=0
Command end time 2021-01-11 12:23:09

==========================
Moving file to repo dir...
==========================

Command start time 2021-01-11 12:23:09

Connection to slave1 closed.
SSH command execution finished
host=slave1, exitcode=0
Command end time 2021-01-11 12:23:09

==========================
Changing permissions for ambari.repo...
==========================

Command start time 2021-01-11 12:23:09

Connection to slave1 closed.
SSH command execution finished
host=slave1, exitcode=0
Command end time 2021-01-11 12:23:09

==========================
Copying setup script file...
==========================

Command start time 2021-01-11 12:23:09

scp /usr/lib/python2.6/site-packages/ambari_server/setupAgent.py
host=slave1, exitcode=0
Command end time 2021-01-11 12:23:09

==========================
Running setup agent script...
==========================

Command start time 2021-01-11 12:23:09
('WARNING 2021-01-11 12:23:23,163 NetUtil.py:116 - Server at https://master:8440 is not reachable, sleeping for 10 seconds...
INFO 2021-01-11 12:23:23,164 HeartbeatHandlers.py:115 - Stop event received
INFO 2021-01-11 12:23:23,164 NetUtil.py:122 - Stop event received
INFO 2021-01-11 12:23:23,164 ExitHelper.py:53 - Performing cleanup before exiting...
INFO 2021-01-11 12:23:23,164 ExitHelper.py:67 - Cleanup finished, exiting with code:0
INFO 2021-01-11 12:23:23,668 main.py:223 - Agent died gracefully, exiting.
INFO 2021-01-11 12:23:23,668 ExitHelper.py:53 - Performing cleanup before exiting...
INFO 2021-01-11 12:23:23,931 main.py:90 - loglevel=logging.INFO
INFO 2021-01-11 12:23:23,931 main.py:90 - loglevel=logging.INFO
INFO 2021-01-11 12:23:23,931 main.py:90 - loglevel=logging.INFO
INFO 2021-01-11 12:23:23,932 DataCleaner.py:39 - Data cleanup thread started
INFO 2021-01-11 12:23:23,933 DataCleaner.py:120 - Data cleanup started
INFO 2021-01-11 12:23:23,933 DataCleaner.py:122 - Data cleanup finished
INFO 2021-01-11 12:23:23,937 PingPortListener.py:50 - Ping port listener started on port: 8670
INFO 2021-01-11 12:23:23,938 main.py:349 - Connecting to Ambari server at https://master:8440 (192.168.157.132)
INFO 2021-01-11 12:23:23,938 NetUtil.py:62 - Connecting to https://master:8440/ca
ERROR 2021-01-11 12:23:23,940 NetUtil.py:88 - EOF occurred in violation of protocol (_ssl.c:618)
ERROR 2021-01-11 12:23:23,940 NetUtil.py:89 - SSLError: Failed to connect. Please check openssl library versions. 
Refer to: https://bugzilla.redhat.com/show_bug.cgi?id=1022468 for more details.
WARNING 2021-01-11 12:23:23,940 NetUtil.py:116 - Server at https://master:8440 is not reachable, sleeping for 10 seconds...
', None)
('WARNING 2021-01-11 12:23:23,163 NetUtil.py:116 - Server at https://master:8440 is not reachable, sleeping for 10 seconds...
INFO 2021-01-11 12:23:23,164 HeartbeatHandlers.py:115 - Stop event received
INFO 2021-01-11 12:23:23,164 NetUtil.py:122 - Stop event received
INFO 2021-01-11 12:23:23,164 ExitHelper.py:53 - Performing cleanup before exiting...
INFO 2021-01-11 12:23:23,164 ExitHelper.py:67 - Cleanup finished, exiting with code:0
INFO 2021-01-11 12:23:23,668 main.py:223 - Agent died gracefully, exiting.
INFO 2021-01-11 12:23:23,668 ExitHelper.py:53 - Performing cleanup before exiting...
INFO 2021-01-11 12:23:23,931 main.py:90 - loglevel=logging.INFO
INFO 2021-01-11 12:23:23,931 main.py:90 - loglevel=logging.INFO
INFO 2021-01-11 12:23:23,931 main.py:90 - loglevel=logging.INFO
INFO 2021-01-11 12:23:23,932 DataCleaner.py:39 - Data cleanup thread started
INFO 2021-01-11 12:23:23,933 DataCleaner.py:120 - Data cleanup started
INFO 2021-01-11 12:23:23,933 DataCleaner.py:122 - Data cleanup finished
INFO 2021-01-11 12:23:23,937 PingPortListener.py:50 - Ping port listener started on port: 8670
INFO 2021-01-11 12:23:23,938 main.py:349 - Connecting to Ambari server at https://master:8440 (192.168.157.132)
INFO 2021-01-11 12:23:23,938 NetUtil.py:62 - Connecting to https://master:8440/ca
ERROR 2021-01-11 12:23:23,940 NetUtil.py:88 - EOF occurred in violation of protocol (_ssl.c:618)
ERROR 2021-01-11 12:23:23,940 NetUtil.py:89 - SSLError: Failed to connect. Please check openssl library versions. 
Refer to: https://bugzilla.redhat.com/show_bug.cgi?id=1022468 for more details.
WARNING 2021-01-11 12:23:23,940 NetUtil.py:116 - Server at https://master:8440 is not reachable, sleeping for 10 seconds...
', None)

Connection to slave1 closed.
SSH command execution finished
host=slave1, exitcode=0
Command end time 2021-01-11 12:23:26

Registering with the server...
Registration with the server failed.