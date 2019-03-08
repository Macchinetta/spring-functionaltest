INSERT ALL
INTO t_staff (username,password,enabled,authority) VALUES('Jack','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',1,'ROLE_ADMIN')
INTO t_staff (username,password,enabled,authority) VALUES('Ken','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',1,'ROLE_STAFF')
INTO t_staff (username,password,enabled,authority) VALUES('Josh','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',1,'ROLE_USER')
INTO t_staff (username,password,enabled,authority) VALUES('Tom','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',0,'ROLE_ADMIN')
INTO t_staff (username,password,enabled,authority) VALUES('Igor','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',0,'ROLE_STAFF')
INTO t_staff (username,password,enabled,authority) VALUES('Claire','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',0,'ROLE_USER')
INTO t_staff (username,password,enabled,authority) VALUES('Rock','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',1,'ROLE_ACCOUNT_MANAGER')
INTO t_staff (username,password,enabled,authority) VALUES('Sam','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',1,'ROLE_ACCOUNT_USER')
INTO t_staff (username,password,enabled,authority) VALUES('Ford','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',1,'ROLE_CONFIGURATION_MANAGER')
SELECT * FROM DUAL
