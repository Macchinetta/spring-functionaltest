INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Josh','000000000000000000000000000000000001','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88','Josh First','Josh Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Claire','000000000000000000000000000000000002','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88','Claire First','Claire Last',FALSE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Edword','000000000000000000000000000000000003','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88','Edword First','Edword Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Jenkins','000000000000000000000000000000000004','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88','Jenkins First','Jenkins Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Rock','000000000000000000000000000000000005','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88','Rock First','Rock Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Bob','000000000000000000000000000000000006','{bcrypt}$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu','Bob First','Bob Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Smith','000000000000000000000000000000000007','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu','Smith First','Smith Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Emily','000000000000000000000000000000000008','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88','Emily First','Emily Last',TRUE,TRUE);

INSERT INTO t_customer (customer_name,password,company_id,customer_address,enabled,authority) VALUES('Josh','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88','MASTER','san diego',TRUE,'ROLE_USER');
INSERT INTO t_customer (customer_name,password,company_id,customer_address,enabled,authority) VALUES('Claire','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88','CHEF','bostom',FALSE,'ROLE_USER');

INSERT INTO t_administrator (username,password,enabled,authority) VALUES('Tom','{bcrypt}$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu',TRUE,'ROLE_ADMIN');
INSERT INTO t_administrator (username,password,enabled,authority) VALUES('Dave','{scrypt}$100801$ZyMDYX73K5nwQrjBW1fmtg==$HoQpBu6xplxIViLNv+c0GPbKqohRmPswzs1rg7j6uus=',TRUE,'ROLE_ADMIN');
INSERT INTO t_administrator (username,password,enabled,authority) VALUES('Beretta','{argon2}$argon2id$v=19$m=16384,t=2,p=1$KkGVjCZx2/sAgKouKGZOvg$sEya19Y/WLeft9XdN15d8YUKXgEcIqaSpGNG2ykbqJo',TRUE,'ROLE_ADMIN');

commit;
