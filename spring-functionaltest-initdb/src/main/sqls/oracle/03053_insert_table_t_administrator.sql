INSERT ALL
INTO t_administrator (username,password,enabled,authority) VALUES('Tom','{bcrypt}$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu','1','ROLE_ADMIN')
INTO t_administrator (username,password,enabled,authority) VALUES('Dave','{scrypt}$e0801$eDOEcSvnrBGXhhwnzOti9ZLXRtdeB5lSY1MU+L5hOkDs5XHNwcXjR4nzL6kwFbQ7f7HGPZ5wACFujzQFY/q4OQ==$w6C9wjmUWSiwWz0FXbGwGjU4QERxY10wbiYlHshdXRU=','1','ROLE_ADMIN')
SELECT * FROM DUAL