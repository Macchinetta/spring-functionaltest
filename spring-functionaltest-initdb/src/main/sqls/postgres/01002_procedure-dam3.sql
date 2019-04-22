DROP FUNCTION IF EXISTS findTodo(CHAR);


CREATE FUNCTION findTodo(pTodoId CHAR)
RETURNS TABLE(
    todo_id CHAR,
	category_id CHAR,
    todo_title VARCHAR,
    finished BOOLEAN,
    created_at DATE,
    complete_at TIMESTAMP,
    version INTEGER
) AS $$ BEGIN RETURN QUERY
SELECT
    t.todo_id,
	t.category_id,
    t.todo_title,
    t.finished,
    t.created_at,
    t.complete_at,
    t.version
FROM
    t_todo t
WHERE
    t.todo_id = pTodoId;
END;
$$ LANGUAGE plpgsql;