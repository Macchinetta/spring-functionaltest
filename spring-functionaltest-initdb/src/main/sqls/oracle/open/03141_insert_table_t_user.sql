INSERT ALL
INTO t_user (username,password,enabled,authority) VALUES('Jack','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',1,'ADMIN')
INTO t_user (username,password,enabled,authority) VALUES('Tom','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',0,'ADMIN')
SELECT * FROM DUAL
