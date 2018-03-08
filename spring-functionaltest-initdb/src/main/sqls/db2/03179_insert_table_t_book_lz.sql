INSERT INTO
    t_book_lz (book_id,category_id,title,price,release_date,clob_code,blob_code)
VALUES
    (1,1,'title1',40,to_date('01-01-2013','DD-MM-YYYY'),'54455354',blob('54455354')),
    (2,1,'title2',40,to_date('02-01-2013','DD-MM-YYYY'),'54455322',blob('54455322'))
