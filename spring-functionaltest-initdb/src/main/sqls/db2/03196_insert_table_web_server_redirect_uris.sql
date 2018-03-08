INSERT INTO 
    web_server_redirect_uris(client_id, web_server_redirect_uri) 
VALUES
    ('testClient', 'spring-functionaltest-web/'),
    ('testClientReadOnly', 'spring-functionaltest-web/'),
    ('testClientIllegalUri', 'illegal-host-web/'),
    ('testClientIllegalResource', 'spring-functionaltest-web/'),
    ('testClient2', 'spring-functionaltest-web/')
