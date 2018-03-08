INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Josh','000000000000000000000000000000000001','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu','Josh First','Josh Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Claire','000000000000000000000000000000000002','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu','Claire First','Claire Last',FALSE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Edword','000000000000000000000000000000000003','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu','Edword First','Edword Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Jenkins','000000000000000000000000000000000004','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu','Jenkins First','Jenkins Last',TRUE,TRUE);
INSERT INTO t_account (username,user_uuid,password,first_name,last_name,enabled,admin) VALUES('Rock','000000000000000000000000000000000005','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu','Rock First','Rock Last',TRUE,TRUE);

INSERT INTO t_customer (customer_name,password,company_id,customer_address,enabled,authority) VALUES('Josh','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu','MASTER','san diego',TRUE,'ROLE_USER');
INSERT INTO t_customer (customer_name,password,company_id,customer_address,enabled,authority) VALUES('Claire','$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu','CHEF','bostom',FALSE,'ROLE_USER');

commit;
