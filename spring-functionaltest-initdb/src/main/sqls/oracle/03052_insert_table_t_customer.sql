INSERT ALL
INTO t_customer (customer_name,password,company_id,customer_address,enabled,authority) VALUES('Josh','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88','MASTER','san diego',1,'ROLE_USER')
INTO t_customer (customer_name,password,company_id,customer_address,enabled,authority) VALUES('Claire','{pbkdf2}71f4e90a11d47feb98f8a458033e01cee5a3a38dd47255a5a2db45f26d60193aeccb9906410542660f12ef7f7fb0dd88','CHEF','bostom',0,'ROLE_USER')
SELECT * FROM DUAL