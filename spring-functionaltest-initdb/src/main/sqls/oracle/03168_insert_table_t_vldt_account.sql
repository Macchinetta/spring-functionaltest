INSERT ALL
INTO t_vldt_account (username,password,enabled,authority) VALUES('Josh','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',1,'ROLE_USER')
INTO t_vldt_account (username,password,enabled,authority) VALUES('Claire','{pbkdf2}d5ac2df97c0ba57c6bfa78f15b6fef7eafd5f3b82ad47dbe3992799b4f3e098c6170ab7359fe0da8',0,'ROLE_USER')
SELECT * FROM DUAL
