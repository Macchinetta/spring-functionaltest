INSERT ALL
INTO resource_ids(client_id, resource_id) VALUES('testClient', 'todoResource')
INTO resource_ids(client_id, resource_id) VALUES('testClientReadOnly', 'todoResource')
INTO resource_ids(client_id, resource_id) VALUES('testClientIllegalUri', 'todoResource')
INTO resource_ids(client_id, resource_id) VALUES('testClientIllegalResource', 'illegalResource')
INTO resource_ids(client_id, resource_id) VALUES('testClient2', 'todoResource')
SELECT * FROM DUAL
