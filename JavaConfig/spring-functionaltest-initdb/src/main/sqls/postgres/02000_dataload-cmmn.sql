INSERT INTO system_date(now) VALUES (to_timestamp('2013/12/09 13:50:12.100', 'yyyy/MM/dd HH24:mi:ss.ms'));
INSERT INTO operation_date(operation_date_id, diff) VALUES (1, 3600);
INSERT INTO operation_date(operation_date_id, diff) VALUES (2, -3600);
commit;
