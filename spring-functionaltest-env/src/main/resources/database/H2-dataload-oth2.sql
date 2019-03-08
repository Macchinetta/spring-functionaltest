/* load the records. */
/* Resource Owner Records. password=demo */
INSERT INTO users(username, password, enabled) VALUES('demo', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', true);
INSERT INTO users(username, password, enabled) VALUES('demo2', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', true);
INSERT INTO authorities(username, authority) VALUES('demo', 'READ');
INSERT INTO authorities(username, authority) VALUES('demo2', 'READ');

/* Client Records. */
INSERT INTO clients(client_id, client_secret, client_name, access_token_validity, refresh_token_validity) VALUES('testClient', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', 'hogehoge', 15, 30);
INSERT INTO clients(client_id, client_secret, client_name, access_token_validity, refresh_token_validity) VALUES('testClientReadOnly', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', 'hogehoge', 15, 30);
INSERT INTO clients(client_id, client_secret, client_name, access_token_validity, refresh_token_validity) VALUES('testClientIllegalUri', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', 'hogehoge', 15, 30);
INSERT INTO clients(client_id, client_secret, client_name, access_token_validity, refresh_token_validity) VALUES('testClientIllegalResource', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', 'hogehoge', 15, 30);
INSERT INTO clients(client_id, client_secret, client_name, access_token_validity, refresh_token_validity) VALUES('testClient2', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', 'hogehoge', 15, 30);


INSERT INTO scopes(client_id, scope) VALUES('testClient', 'READ');
INSERT INTO scopes(client_id, scope) VALUES('testClient', 'CREATE');
INSERT INTO scopes(client_id, scope) VALUES('testClient', 'UPDATE');
INSERT INTO scopes(client_id, scope) VALUES('testClient', 'DELETE');
INSERT INTO scopes(client_id, scope) VALUES('testClientReadOnly', 'READ');
INSERT INTO scopes(client_id, scope) VALUES('testClientIllegalUri', 'READ');
INSERT INTO scopes(client_id, scope) VALUES('testClientIllegalResource', 'READ');
INSERT INTO scopes(client_id, scope) VALUES('testClient2', 'READ');
INSERT INTO scopes(client_id, scope) VALUES('testClient2', 'CREATE');
INSERT INTO scopes(client_id, scope) VALUES('testClient2', 'UPDATE');
INSERT INTO scopes(client_id, scope) VALUES('testClient2', 'DELETE');

INSERT INTO resource_ids(client_id, resource_id) VALUES('testClient', 'todoResource');
INSERT INTO resource_ids(client_id, resource_id) VALUES('testClientReadOnly', 'todoResource');
INSERT INTO resource_ids(client_id, resource_id) VALUES('testClientIllegalUri', 'todoResource');
INSERT INTO resource_ids(client_id, resource_id) VALUES('testClientIllegalResource', 'illegalResource');
INSERT INTO resource_ids(client_id, resource_id) VALUES('testClient2', 'todoResource');

INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClient', 'authorization_code');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClient', 'password');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClient', 'client_credentials');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClient', 'refresh_token');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClient', 'implicit');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClientReadOnly', 'authorization_code');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClientReadOnly', 'password');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClientReadOnly', 'client_credentials');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClientReadOnly', 'refresh_token');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClientReadOnly', 'implicit');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClientIllegalUri', 'authorization_code');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClientIllegalResource', 'authorization_code');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClient2', 'authorization_code');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClient2', 'password');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClient2', 'client_credentials');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClient2', 'refresh_token');
INSERT INTO authorized_grant_types(client_id, authorized_grant_type) VALUES('testClient2', 'implicit');

INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/01/001/get');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/01/002/post');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/01/003/put/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/01/004/delete/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/01/010/delete/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/02/001/get');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/02/002/post');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/02/003/put');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/02/004/delete');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/02/010/delete');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/03/002/post/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/03/003/put/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/03/004/delete/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/01/03/005/delete/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/02/01/001/get/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/02/01/002/post/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/02/01/004/delete/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/02/01/003/put/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/03/01/001/get/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/03/01/002/post/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/03/01/003/put/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/03/01/004/delete/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/03/01/005/get/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/03/01/006/get/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/05/02/001/get/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/11/01/002/get/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/11/01/004/get/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient', 'spring-functionaltest-web/oth2/client/11/01/006/get');

INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClientReadOnly', 'spring-functionaltest-web/');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClientReadOnly', 'spring-functionaltest-web/oth2/client/01/01/010/delete/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClientReadOnly', 'spring-functionaltest-web/oth2/client/01/02/004/delete');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClientIllegalUri', 'illegal-host-web/');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClientIllegalResource', 'spring-functionaltest-web/');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClientIllegalResource', 'spring-functionaltest-web/oth2/client/11/01/004/get/123');
INSERT INTO web_server_redirect_uris(client_id, web_server_redirect_uri) VALUES('testClient2', 'spring-functionaltest-web/');

COMMIT;