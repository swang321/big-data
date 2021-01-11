==========================
Creating target directory...
==========================

Command start time 2021-01-09 21:36:05

Connection to master closed.
SSH command execution finished
host=master, exitcode=0
Command end time 2021-01-09 21:36:05

==========================
Copying ambari sudo script...
==========================

Command start time 2021-01-09 21:36:05

scp /var/lib/ambari-server/ambari-sudo.sh
host=master, exitcode=0
Command end time 2021-01-09 21:36:06

==========================
Copying common functions script...
==========================

Command start time 2021-01-09 21:36:06

scp /usr/lib/python2.6/site-packages/ambari_commons
host=master, exitcode=0
Command end time 2021-01-09 21:36:06

==========================
Copying OS type check script...
==========================

Command start time 2021-01-09 21:36:06

scp /usr/lib/python2.6/site-packages/ambari_server/os_check_type.py
host=master, exitcode=0
Command end time 2021-01-09 21:36:06

==========================
Running OS type check...
==========================

Command start time 2021-01-09 21:36:06
Cluster primary/cluster OS family is redhat7 and local/current OS family is redhat7

Connection to master closed.
SSH command execution finished
host=master, exitcode=0
Command end time 2021-01-09 21:36:07

==========================
Checking 'sudo' package on remote host...
==========================

Command start time 2021-01-09 21:36:07

Connection to master closed.
SSH command execution finished
host=master, exitcode=0
Command end time 2021-01-09 21:36:07

==========================
Copying repo file to 'tmp' folder...
==========================

Command start time 2021-01-09 21:36:07

scp /etc/yum.repos.d/ambari.repo
host=master, exitcode=0
Command end time 2021-01-09 21:36:07

==========================
Moving file to repo dir...
==========================

Command start time 2021-01-09 21:36:07

Connection to master closed.
SSH command execution finished
host=master, exitcode=0
Command end time 2021-01-09 21:36:08

==========================
Changing permissions for ambari.repo...
==========================

Command start time 2021-01-09 21:36:08

Connection to master closed.
SSH command execution finished
host=master, exitcode=0
Command end time 2021-01-09 21:36:08

==========================
Copying setup script file...
==========================

Command start time 2021-01-09 21:36:08

scp /usr/lib/python2.6/site-packages/ambari_server/setupAgent.py
host=master, exitcode=0
Command end time 2021-01-09 21:36:08

==========================
Running setup agent script...
==========================

Command start time 2021-01-09 21:36:08
('WARNING 2021-01-09 21:36:18,267 NetUtil.py:116 - Server at https://master:8440 is not reachable, sleeping for 10 seconds...
INFO 2021-01-09 21:36:18,268 HeartbeatHandlers.py:115 - Stop event received
INFO 2021-01-09 21:36:18,268 NetUtil.py:122 - Stop event received
INFO 2021-01-09 21:36:18,268 ExitHelper.py:53 - Performing cleanup before exiting...
INFO 2021-01-09 21:36:18,268 ExitHelper.py:67 - Cleanup finished, exiting with code:0
INFO 2021-01-09 21:36:19,658 main.py:223 - Agent died gracefully, exiting.
INFO 2021-01-09 21:36:19,659 ExitHelper.py:53 - Performing cleanup before exiting...
INFO 2021-01-09 21:36:19,910 main.py:90 - loglevel=logging.INFO
INFO 2021-01-09 21:36:19,910 main.py:90 - loglevel=logging.INFO
INFO 2021-01-09 21:36:19,910 main.py:90 - loglevel=logging.INFO
INFO 2021-01-09 21:36:19,911 DataCleaner.py:39 - Data cleanup thread started
INFO 2021-01-09 21:36:19,911 DataCleaner.py:120 - Data cleanup started
INFO 2021-01-09 21:36:19,912 DataCleaner.py:122 - Data cleanup finished
INFO 2021-01-09 21:36:19,915 PingPortListener.py:50 - Ping port listener started on port: 8670
INFO 2021-01-09 21:36:19,916 main.py:349 - Connecting to Ambari server at https://master:8440 (192.168.157.132)
INFO 2021-01-09 21:36:19,916 NetUtil.py:62 - Connecting to https://master:8440/ca
ERROR 2021-01-09 21:36:19,920 NetUtil.py:88 - EOF occurred in violation of protocol (_ssl.c:618)
ERROR 2021-01-09 21:36:19,920 NetUtil.py:89 - SSLError: Failed to connect. Please check openssl library versions. 
Refer to: https://bugzilla.redhat.com/show_bug.cgi?id=1022468 for more details.
WARNING 2021-01-09 21:36:19,920 NetUtil.py:116 - Server at https://master:8440 is not reachable, sleeping for 10 seconds...
', None)
('WARNING 2021-01-09 21:36:18,267 NetUtil.py:116 - Server at https://master:8440 is not reachable, sleeping for 10 seconds...
INFO 2021-01-09 21:36:18,268 HeartbeatHandlers.py:115 - Stop event received
INFO 2021-01-09 21:36:18,268 NetUtil.py:122 - Stop event received
INFO 2021-01-09 21:36:18,268 ExitHelper.py:53 - Performing cleanup before exiting...
INFO 2021-01-09 21:36:18,268 ExitHelper.py:67 - Cleanup finished, exiting with code:0
INFO 2021-01-09 21:36:19,658 main.py:223 - Agent died gracefully, exiting.
INFO 2021-01-09 21:36:19,659 ExitHelper.py:53 - Performing cleanup before exiting...
INFO 2021-01-09 21:36:19,910 main.py:90 - loglevel=logging.INFO
INFO 2021-01-09 21:36:19,910 main.py:90 - loglevel=logging.INFO
INFO 2021-01-09 21:36:19,910 main.py:90 - loglevel=logging.INFO
INFO 2021-01-09 21:36:19,911 DataCleaner.py:39 - Data cleanup thread started
INFO 2021-01-09 21:36:19,911 DataCleaner.py:120 - Data cleanup started
INFO 2021-01-09 21:36:19,912 DataCleaner.py:122 - Data cleanup finished
INFO 2021-01-09 21:36:19,915 PingPortListener.py:50 - Ping port listener started on port: 8670
INFO 2021-01-09 21:36:19,916 main.py:349 - Connecting to Ambari server at https://master:8440 (192.168.157.132)
INFO 2021-01-09 21:36:19,916 NetUtil.py:62 - Connecting to https://master:8440/ca
ERROR 2021-01-09 21:36:19,920 NetUtil.py:88 - EOF occurred in violation of protocol (_ssl.c:618)
ERROR 2021-01-09 21:36:19,920 NetUtil.py:89 - SSLError: Failed to connect. Please check openssl library versions. 
Refer to: https://bugzilla.redhat.com/show_bug.cgi?id=1022468 for more details.
WARNING 2021-01-09 21:36:19,920 NetUtil.py:116 - Server at https://master:8440 is not reachable, sleeping for 10 seconds...
', None)

Connection to master closed.
SSH command execution finished
host=master, exitcode=0
Command end time 2021-01-09 21:36:22

Registering with the server...
Registration with the server failed.