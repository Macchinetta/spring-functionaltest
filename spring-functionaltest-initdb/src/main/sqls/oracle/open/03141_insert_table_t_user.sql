INSERT ALL
INTO t_user (username,password,enabled,authority) VALUES('Jack','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88',1,'ADMIN')
INTO t_user (username,password,enabled,authority) VALUES('Tom','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88',0,'ADMIN')
SELECT * FROM DUAL
