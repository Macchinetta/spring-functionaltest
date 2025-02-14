DROP TABLE IF EXISTS t_staff;
DROP TABLE IF EXISTS t_system_config;

CREATE TABLE t_staff (
    username VARCHAR(30) NOT NULL
    ,password VARCHAR(110) NOT NULL
    ,enabled BOOLEAN NOT NULL
    ,authority VARCHAR(30) NOT NULL
    ,CONSTRAINT pk_t_staff PRIMARY KEY (username)
);

CREATE TABLE t_system_config (
    device VARCHAR(30) NOT NULL
    ,broadcast VARCHAR(100) NOT NULL
    ,ipaddr VARCHAR(30) NOT NULL
    ,netmask VARCHAR(30) NOT NULL
    ,network VARCHAR(30) NOT NULL
    ,onboot VARCHAR(30) NOT NULL
    ,device_type VARCHAR(30) NOT NULL
    ,gateway VARCHAR(30) NOT NULL
    ,owner VARCHAR(30) NOT NULL
    ,CONSTRAINT pk_t_system_config PRIMARY KEY (device)
);


