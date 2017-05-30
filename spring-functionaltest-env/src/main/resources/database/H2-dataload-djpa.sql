--INSERT INTO t_book (book_id,category_id,title,price,release_date,clob_code,blob_code) values ('0001','0000000001','title1',40,'2013-01-01','54455354','54455354');
--INSERT INTO t_book (book_id,category_id,title,price,release_date,clob_code,blob_code) values ('0002','0000000001','title2',40,'2013-01-02','54455322','54455322');

INSERT INTO m_category_lz (category_id, name) VALUES ('0000000001', 'A01');
INSERT INTO m_category_lz (category_id, name) VALUES ('0000000002', 'B01');
INSERT INTO m_category_lz (category_id, name) VALUES ('0000000003', 'C01');
INSERT INTO m_category_lz (category_id, name) VALUES ('0000000004', 'A02');
INSERT INTO m_category_lz (category_id, name) VALUES ('0000000005', 'B02');
INSERT INTO m_category_lz (category_id, name) VALUES ('0000000006', 'C02');
INSERT INTO m_category_lz (category_id, name) VALUES ('0000000007', 'Z');


INSERT INTO t_book_lz (book_id,category_id,title,price,release_date,clob_code,blob_code) values ('0001','0000000001','title1',40,'2013-01-01','54455354','3534343535333534');
INSERT INTO t_book_lz (book_id,category_id,title,price,release_date,clob_code,blob_code) values ('0002','0000000001','title2',40,'2013-01-02','54455322','3534343535333534');


INSERT INTO m_category_eg (category_id, name) VALUES (1, 'A01');
INSERT INTO m_category_eg (category_id, name) VALUES (2, 'B01');
INSERT INTO m_category_eg (category_id, name) VALUES (3, 'C01');
INSERT INTO m_category_eg (category_id, name) VALUES (4, 'A02');
INSERT INTO m_category_eg (category_id, name) VALUES (5, 'B02');
INSERT INTO m_category_eg (category_id, name) VALUES (6, 'C02');
INSERT INTO m_category_eg (category_id, name) VALUES (7, 'Z07');


INSERT INTO t_book_eg (book_id,category_id,title,price,release_date,clob_code,blob_code,version, created_by, created_date, last_modified_by ,last_modified_date) values (1,1,'Manual Title 1',40,'2013-01-01','54455354','3534343535333534',0, 'By InitDB', timestamp '2015-01-01 01:01:01', 'By InitDB', timestamp '2015-01-01 01:01:01');
INSERT INTO t_book_eg (book_id,category_id,title,price,release_date,clob_code,blob_code,version, created_by, created_date, last_modified_by ,last_modified_date) values (2,1,'Manual Title 2',40,'2013-01-02','54455322','3534343535333232',0, 'By InitDB', timestamp '2015-01-01 01:01:01', 'By InitDB', timestamp '2015-01-01 01:01:01');
--INSERT INTO t_book_eg (book_id,category_id,title,price,release_date,clob_code,blob_code) values ('0003','0000000002','title3',40,'2013-01-03','54455353','54455353');
--INSERT INTO t_book_eg (book_id,category_id,title,price,release_date,clob_code,blob_code) values ('0004','0000000006','title4',40,'2013-01-04','54455324','54455324');

-- Setup master tables
INSERT INTO m_item_jpa VALUES ('ITM0000001','Orange juice',100);
INSERT INTO m_item_jpa VALUES ('ITM0000002','NotePC',1000);
INSERT INTO m_item_jpa VALUES ('ITM0000003','Book',200);

-- Setup code tables
INSERT  INTO  c_order_status_jpa VALUES ('accepted','Order accepted');
INSERT  INTO  c_order_status_jpa VALUES ('checking','Stock checking');
INSERT  INTO  c_order_status_jpa VALUES ('shipped','Item Shipped');

-- Setup transaction tables
INSERT INTO t_order_jpa VALUES (1,'accepted','dummy1',11000,true);
INSERT INTO t_order_jpa VALUES (2,'checking','dummy2',20300, false);
INSERT INTO t_order_jpa VALUES (3,'shipped','dummy3',6000, true);
INSERT INTO t_order_jpa VALUES (4,'accepted','dummy4',4000, false);
INSERT INTO t_order_jpa VALUES (5,'checking','dummy5',50000, false);
INSERT INTO t_order_jpa VALUES (6,'shipped','dummy6',13100, false);
INSERT INTO t_order_jpa VALUES (7,'shipped','dummy7',2700, false);

INSERT INTO t_order_item_jpa VALUES (1,1,'ITM0000001',10, false);
INSERT INTO t_order_item_jpa VALUES (1,7,'ITM0000002',10, false);
INSERT INTO t_order_item_jpa VALUES (2,2,'ITM0000002',20, false);
INSERT INTO t_order_item_jpa VALUES (6,8,'ITM0000001',1, false);
INSERT INTO t_order_item_jpa VALUES (6,9,'ITM0000002',1, false);
INSERT INTO t_order_item_jpa VALUES (3,3,'ITM0000003',30, false);
INSERT INTO t_order_item_jpa VALUES (4,4,'ITM0000001',40, false);
INSERT INTO t_order_item_jpa VALUES (5,5,'ITM0000002',50, false);
INSERT INTO t_order_item_jpa VALUES (6,6,'ITM0000003',60, false);

INSERT INTO t_order_item_jpa VALUES (7,10,'ITM0000001',1, false);
INSERT INTO t_order_item_jpa VALUES (7,11,'ITM0000002',2, true);
INSERT INTO t_order_item_jpa VALUES (7,12,'ITM0000003',3, false);

 	



 	

