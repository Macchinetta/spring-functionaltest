INSERT ALL
INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/')
INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClientReadOnly', 'spring-functionaltest-web/')
INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClientIllegalUri', 'illegal-host-web/')
INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClientIllegalResource', 'spring-functionaltest-web/')
INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient2', 'spring-functionaltest-web/')
SELECT * FROM DUAL
