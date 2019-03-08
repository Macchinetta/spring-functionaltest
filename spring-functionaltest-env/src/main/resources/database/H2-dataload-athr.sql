INSERT INTO t_staff (username,password,enabled,authority) VALUES('Jack','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',TRUE,'ROLE_ADMIN');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Ken','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',TRUE,'ROLE_STAFF');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Josh','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',TRUE,'ROLE_USER');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Tom','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',TRUE,'ROLE_ADMIN');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Igor','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',TRUE,'ROLE_STAFF');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Claire','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',TRUE,'ROLE_USER');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Rock','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',TRUE,'ROLE_ACCOUNT_MANAGER');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Sam','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',TRUE,'ROLE_ACCOUNT_USER');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Ford','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',TRUE,'ROLE_CONFIGURATION_MANAGER');



INSERT INTO t_system_config (device,broadcast,ipaddr,netmask,network,onboot,device_type,gateway,owner) VALUES('eth0','192.168.10.255','192.168.10.10','255.255.255.0','192.168.10.0','yes','Ethernet','192.168.10.1','Jack');
INSERT INTO t_system_config (device,broadcast,ipaddr,netmask,network,onboot,device_type,gateway,owner) VALUES('eth1','192.168.11.255','192.168.11.10','255.255.255.0','192.168.11.0','yes','Ethernet','192.168.11.1','Ken');
INSERT INTO t_system_config (device,broadcast,ipaddr,netmask,network,onboot,device_type,gateway,owner) VALUES('eth2','192.168.12.255','192.168.12.10','255.255.255.0','19.168.12.0','yes','Ethernet','192.168.12.1','Josh');

commit;
