-- Setup master tables
INSERT INTO m_item_mb3 VALUES ('ITM0000001','Orange juice',100);
INSERT INTO m_item_mb3 VALUES ('ITM0000002','NotePC',100000);
INSERT INTO m_item_mb3 VALUES ('ITM0000003','Car',10000000);

INSERT INTO m_category_mb3 VALUES ('CTG0000001','Drink');
INSERT INTO m_category_mb3 VALUES ('CTG0000002','PC');
INSERT INTO m_category_mb3 VALUES ('CTG0000003','Hot selling');

INSERT INTO m_item_category_mb3 VALUES ('ITM0000001','CTG0000001');
INSERT INTO m_item_category_mb3 VALUES ('ITM0000002','CTG0000002');
INSERT INTO m_item_category_mb3 VALUES ('ITM0000003','CTG0000003');

-- Setup code tables
INSERT  INTO  c_order_status_mb3 VALUES ('accepted','Order accepted');
INSERT  INTO  c_order_status_mb3 VALUES ('checking','Stock checking');
INSERT  INTO  c_order_status_mb3 VALUES ('shipped','Item Shipped');

-- Setup transaction tables
INSERT INTO t_order_mb3 VALUES (1,'accepted','dummy1',null);
INSERT INTO t_order_mb3 VALUES (2,'checking',null,null);
INSERT INTO t_order_mb3 VALUES (3,'shipped','dummy3',null);
INSERT INTO t_order_mb3 VALUES (4,'accepted','dummy4',null);
INSERT INTO t_order_mb3 VALUES (5,'checking','dummy5',null);
INSERT INTO t_order_mb3 VALUES (6,'shipped','dummy6',null);
INSERT INTO t_order_mb3 VALUES (7,'accepted','dummy7',null);

INSERT INTO t_order_item_mb3 VALUES (1,'ITM0000001',10);
INSERT INTO t_order_item_mb3 VALUES (2,'ITM0000002',20);
INSERT INTO t_order_item_mb3 VALUES (3,'ITM0000003',30);
INSERT INTO t_order_item_mb3 VALUES (4,'ITM0000001',40);
INSERT INTO t_order_item_mb3 VALUES (5,'ITM0000002',50);
INSERT INTO t_order_item_mb3 VALUES (6,'ITM0000003',60);
INSERT INTO t_order_item_mb3 VALUES (7,'ITM0000001',70);
INSERT INTO t_order_item_mb3 VALUES (7,'ITM0000002',70);

INSERT INTO t_order_history_mb3(order_id,biko,upd_time) VALUES(1,'preInstall','2014-11-07 12:13:14');
INSERT INTO t_order_history_mb3(order_id,biko,upd_time) VALUES(2,'preInstall','2014-11-07 12:13:14');
INSERT INTO t_order_history_mb3(order_id,biko,upd_time) VALUES(3,'preInstall',null);
INSERT INTO t_order_history_mb3(order_id,biko,upd_time) VALUES(4,'preInstall','2014-11-07 12:13:14');
INSERT INTO t_order_history_mb3(order_id,biko,upd_time) VALUES(5,'preInstall','2014-11-07 12:13:14');
INSERT INTO t_order_history_mb3(order_id,biko,upd_time) VALUES(6,'preInstall','2014-11-07 12:13:14');
INSERT INTO t_order_history_mb3(order_id,biko,upd_time) VALUES(7,'preInstall','2014-11-07 12:13:14');
