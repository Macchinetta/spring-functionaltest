INSERT ALL
INTO clients(client_id, client_secret, client_name, access_token_validity, refresh_token_validity) VALUES ('testClient', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', 'hogehoge', 75, 90)
INTO clients(client_id, client_secret, client_name, access_token_validity, refresh_token_validity) VALUES ('testClientReadOnly', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', 'hogehoge', 75, 90)
INTO clients(client_id, client_secret, client_name, access_token_validity, refresh_token_validity) VALUES ('testClientIllegalUri', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', 'hogehoge', 75, 90)
INTO clients(client_id, client_secret, client_name, access_token_validity, refresh_token_validity) VALUES ('testClientIllegalResource', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', 'hogehoge', 75, 90)
INTO clients(client_id, client_secret, client_name, access_token_validity, refresh_token_validity) VALUES ('testClient2', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', 'hogehoge', 75, 90)
SELECT * FROM DUAL
