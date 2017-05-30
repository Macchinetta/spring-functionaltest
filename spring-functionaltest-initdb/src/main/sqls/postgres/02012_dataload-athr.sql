INSERT INTO t_staff (username,password,enabled,authority) VALUES('Jack','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu',TRUE,'ROLE_ADMIN');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Ken','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu',TRUE,'ROLE_STAFF');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Josh','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu',TRUE,'ROLE_USER');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Tom','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu',TRUE,'ROLE_ADMIN');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Igor','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu',TRUE,'ROLE_STAFF');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Claire','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu',TRUE,'ROLE_USER');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Rock','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu',TRUE,'ROLE_ACCOUNT_MANAGER');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Sam','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu',TRUE,'ROLE_ACCOUNT_USER');
INSERT INTO t_staff (username,password,enabled,authority) VALUES('Ford','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu',TRUE,'ROLE_CONFIGURATION_MANAGER');

INSERT INTO t_system_config (device,broadcast,ipaddr,netmask,network,onboot,device_type,gateway,owner) VALUES('eth0','192.168.10.255','192.168.10.10','255.255.255.0','192.168.10.0','yes','Ethernet','192.168.10.1','Jack');
INSERT INTO t_system_config (device,broadcast,ipaddr,netmask,network,onboot,device_type,gateway,owner) VALUES('eth1','192.168.11.255','192.168.11.10','255.255.255.0','192.168.11.0','yes','Ethernet','192.168.11.1','Ken');
INSERT INTO t_system_config (device,broadcast,ipaddr,netmask,network,onboot,device_type,gateway,owner) VALUES('eth2','192.168.12.255','192.168.12.10','255.255.255.0','19.168.12.0','yes','Ethernet','192.168.12.1','Josh');

commit;
