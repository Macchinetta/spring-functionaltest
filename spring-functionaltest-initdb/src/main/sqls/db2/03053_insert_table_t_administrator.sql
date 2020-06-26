INSERT INTO
    t_administrator (username,password,enabled,authority)
VALUES
    ('Tom','{bcrypt}$2a$10$tlaAnDOg/sdKE7H6vBQDlO7FBy2LdaAPgatWsIgm1J.JGgDPVNlPu',TRUE,'ROLE_ADMIN')
    ('Dave','{scrypt}$e0801$eDOEcSvnrBGXhhwnzOti9ZLXRtdeB5lSY1MU+L5hOkDs5XHNwcXjR4nzL6kwFbQ7f7HGPZ5wACFujzQFY/q4OQ==$w6C9wjmUWSiwWz0FXbGwGjU4QERxY10wbiYlHshdXRU=',TRUE,'ROLE_ADMIN')
    ('Beretta','{argon2}$argon2id$v=19$m=4096,t=3,p=1$y9EroK2xFwxnV1MaZOmPrQ$HkCBcr0uOKLvYlP1ywnlgLVdjix/+3ify/ftjLIz+oE',TRUE,'ROLE_ADMIN')
