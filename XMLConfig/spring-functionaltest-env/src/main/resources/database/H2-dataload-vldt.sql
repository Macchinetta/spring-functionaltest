INSERT INTO t_vldt_account (username,password,enabled,authority) VALUES('Josh','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88',TRUE,'ROLE_USER');
INSERT INTO t_vldt_account (username,password,enabled,authority) VALUES('Claire','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88',FALSE,'ROLE_USER');

commit;
