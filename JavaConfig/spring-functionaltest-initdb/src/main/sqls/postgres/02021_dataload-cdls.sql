/* load the records. */
INSERT INTO t_authority (authority_id,authority_name) VALUES('01','STAFF_MANAGEMENT from DB');
INSERT INTO t_authority (authority_id,authority_name) VALUES('02','MASTER_MANAGEMENT from DB');
INSERT INTO t_authority (authority_id,authority_name) VALUES('03','STOCK_MANAGEMENT from DB');
INSERT INTO t_authority (authority_id,authority_name) VALUES('04','ORDER_MANAGEMENT from DB');
INSERT INTO t_authority (authority_id,authority_name) VALUES('05','SHOW_SHOPPING_CENTER from DB');

INSERT INTO t_price (locale,code,label) VALUES('en','0','unlimited from DB');
INSERT INTO t_price (locale,code,label) VALUES('en','10000','Less than \10,000 from DB');
INSERT INTO t_price (locale,code,label) VALUES('en','20000','Less than \20,000 from DB');
INSERT INTO t_price (locale,code,label) VALUES('en','30000','Less than \30,000 from DB');
INSERT INTO t_price (locale,code,label) VALUES('ja','0','上限なし DBから取得');
INSERT INTO t_price (locale,code,label) VALUES('ja','10000','10,000円以下 DBから取得');
INSERT INTO t_price (locale,code,label) VALUES('ja','20000','20,000円以下 DBから取得');
INSERT INTO t_price (locale,code,label) VALUES('ja','30000','30,000円以下 DBから取得');

commit;