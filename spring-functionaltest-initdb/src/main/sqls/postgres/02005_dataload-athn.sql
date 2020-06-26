INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Josh','000000000000000000000000000000000001','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8','Josh First','Josh Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Claire','000000000000000000000000000000000002','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8','Claire First','Claire Last',FALSE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Edword','000000000000000000000000000000000003','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8','Edword First','Edword Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Jenkins','000000000000000000000000000000000004','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8','Jenkins First','Jenkins Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Rock','000000000000000000000000000000000005','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8','Rock First','Rock Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Bob','000000000000000000000000000000000006','{bcrypt}$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu','Bob First','Bob Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Smith','000000000000000000000000000000000007','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu','Smith First','Smith Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Emily','000000000000000000000000000000000008','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8','Emily First','Emily Last',TRUE,TRUE);


INSERT INTO t_customer (customer_name,password,company_id,customer_address,enabled,authority) VALUES('Josh','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8','MASTER','san diego',TRUE,'ROLE_USER');
INSERT INTO t_customer (customer_name,password,company_id,customer_address,enabled,authority) VALUES('Claire','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8','CHEF','bostom',FALSE,'ROLE_USER');

INSERT INTO t_administrator (username,password,enabled,authority) VALUES('Tom','{bcrypt}$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu',TRUE,'ROLE_ADMIN');
INSERT INTO t_administrator (username,password,enabled,authority) VALUES('Dave','{scrypt}$e0801$eDOEcSvnrBGXhhwnzOti9ZLXRtdeB5lSY1MU+L5hOkDs5XHNwcXjR4nzL6kwFbQ7f7HGPZ5wACFujzQFY/q4OQ==$w6C9wjmUWSiwWz0FXbGwGjU4QERxY10wbiYlHshdXRU=',TRUE,'ROLE_ADMIN');
INSERT INTO t_administrator (username,password,enabled,authority) VALUES('Beretta','{argon2}$argon2id$v=19$m=4096,t=3,p=1$y9EroK2xFwxnV1MaZOmPrQ$HkCBcr0uOKLvYlP1ywnlgLVdjix/+3ify/ftjLIz+oE',TRUE,'ROLE_ADMIN');

commit;

