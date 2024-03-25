INSERT INTO t_staff (username,password,enabled,authority) VALUES('Jack','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88',TRUE,'ROLE_ADMIN');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Ken','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88',TRUE,'ROLE_STAFF');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Josh','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88',TRUE,'ROLE_USER');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Tom','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88',TRUE,'ROLE_ADMIN');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Igor','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88',TRUE,'ROLE_STAFF');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Claire','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88',TRUE,'ROLE_USER');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Rock','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88',TRUE,'ROLE_ACCOUNT_MANAGER');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Sam','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88',TRUE,'ROLE_ACCOUNT_USER');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Ford','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88',TRUE,'ROLE_CONFIGURATION_MANAGER');



INSERT INTO t_system_config (device,broadcast,ipaddr,netmask,network,onboot,device_type,gateway,owner) VALUES('eth0','192.168.10.255','192.168.10.10','255.255.255.0','192.168.10.0','yes','Ethernet','192.168.10.1','Jack');
INSERT INTO t_system_config (device,broadcast,ipaddr,netmask,network,onboot,device_type,gateway,owner) VALUES('eth1','192.168.11.255','192.168.11.10','255.255.255.0','192.168.11.0','yes','Ethernet','192.168.11.1','Ken');
INSERT INTO t_system_config (device,broadcast,ipaddr,netmask,network,onboot,device_type,gateway,owner) VALUES('eth2','192.168.12.255','192.168.12.10','255.255.255.0','19.168.12.0','yes','Ethernet','192.168.12.1','Josh');

commit;
