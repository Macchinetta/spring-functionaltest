INSERT ALL
INTO t_system_config (device,broadcast,ipaddr,netmask,network,onboot,device_type,gateway,owner) VALUES('eth0','192.168.10.255','192.168.10.10','255.255.255.0','192.168.10.0','yes','Ethernet','192.168.10.1','Jack')
INTO t_system_config (device,broadcast,ipaddr,netmask,network,onboot,device_type,gateway,owner) VALUES('eth1','192.168.11.255','192.168.11.10','255.255.255.0','192.168.11.0','yes','Ethernet','192.168.11.1','Ken')
INTO t_system_config (device,broadcast,ipaddr,netmask,network,onboot,device_type,gateway,owner) VALUES('eth2','192.168.12.255','192.168.12.10','255.255.255.0','19.168.12.0','yes','Ethernet','192.168.12.1','Josh')
SELECT * FROM DUAL
