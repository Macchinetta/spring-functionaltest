INSERT ALL
INTO t_book_eg (book_id,category_id,title,price,release_date,clob_code,blob_code,version, created_by, created_date, last_modified_by ,last_modified_date) values (1,1,'Manual Title 1',40,to_date('01-01-2013','DD-MM-YYYY'),'54455354',utl_raw.cast_to_raw('54455354'),0, 'By InitDB', timestamp '2015-01-01 01:01:01', 'By InitDB', timestamp '2015-01-01 01:01:01')
INTO t_book_eg (book_id,category_id,title,price,release_date,clob_code,blob_code,version, created_by, created_date, last_modified_by ,last_modified_date) values (2,1,'Manual Title 2',40,to_date('02-01-2013','DD-MM-YYYY'),'54455322',utl_raw.cast_to_raw('54455322'),0, 'By InitDB', timestamp '2015-01-01 01:01:01', 'By InitDB', timestamp '2015-01-01 01:01:01')
SELECT * FROM DUAL
