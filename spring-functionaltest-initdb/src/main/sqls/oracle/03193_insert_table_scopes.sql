INSERT ALL
INTO scopes(client_id, scope) VALUES('testClient', 'READ')
INTO scopes(client_id, scope) VALUES('testClient', 'CREATE')
INTO scopes(client_id, scope) VALUES('testClient', 'UPDATE')
INTO scopes(client_id, scope) VALUES('testClient', 'DELETE')
INTO scopes(client_id, scope) VALUES('testClientReadOnly', 'READ')
INTO scopes(client_id, scope) VALUES('testClientIllegalUri', 'READ')
INTO scopes(client_id, scope) VALUES('testClientIllegalResource', 'READ')
INTO scopes(client_id, scope) VALUES('testClient2', 'READ')
INTO scopes(client_id, scope) VALUES('testClient2', 'CREATE')
INTO scopes(client_id, scope) VALUES('testClient2', 'UPDATE')
INTO scopes(client_id, scope) VALUES('testClient2', 'DELETE')
SELECT * FROM DUAL
