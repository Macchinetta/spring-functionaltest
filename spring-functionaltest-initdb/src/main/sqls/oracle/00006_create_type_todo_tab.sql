--------------------
-- DROP TABLES --
--------------------
create or replace 
type     todo_tab is object
( todo_id CHAR(10)
   ,category_id char(10)
   ,todo_title VARCHAR(1064)
   ,finished NUMBER
  ,created_at DATE
  ,complete_at TIMESTAMP
  ,VERSION INTEGER
    );


