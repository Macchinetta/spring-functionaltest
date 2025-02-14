INSERT ALL
INTO t_administrator (username,password,enabled,authority) VALUES('Tom','{bcrypt}$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu',TRUE,'ROLE_ADMIN')
INTO t_administrator (username,password,enabled,authority) VALUES('Dave','{scrypt}$100801$ZyMDYX73K5nwQrjBW1fmtg==$HoQpBu6xplxIViLNv+c0GPbKqohRmPswzs1rg7j6uus=',TRUE,'ROLE_ADMIN')
INTO t_administrator (username,password,enabled,authority) VALUES('Beretta','{argon2}$argon2id$v=19$m=16384,t=2,p=1$KkGVjCZx2/sAgKouKGZOvg$sEya19Y/WLeft9XdN15d8YUKXgEcIqaSpGNG2ykbqJo',TRUE,'ROLE_ADMIN')
SELECT * FROM DUAL