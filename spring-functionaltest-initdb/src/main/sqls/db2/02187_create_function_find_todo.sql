CREATE
    FUNCTION findTodo (pTodoId CHAR(10))
    RETURNS TABLE(
        todo_id CHAR(10)
        ,category_id CHAR(10)
        ,todo_title VARCHAR(1064)
        ,finished NUMERIC(1)
        ,created_at DATE
        ,complete_at TIMESTAMP
        ,VERSION INTEGER
    )
    LANGUAGE SQL
    READS SQL DATA
    NO EXTERNAL ACTION
    DETERMINISTIC
RETURN
    SELECT todo_id, category_id, todo_title,finished,created_at,complete_at,version
        FROM t_todo
        WHERE todo_id = findTodo.pTodoId
