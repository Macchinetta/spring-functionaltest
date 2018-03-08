CREATE OR REPLACE 
FUNCTION     findTodo(pTodoId CHAR)
RETURN my_todo_tab  
IS
ret_val my_todo_tab;
BEGIN
SELECT todo_tab(TODO_ID, CATEGORY_ID, TODO_TITLE,FINISHED,CREATED_AT,COMPLETE_AT,VERSION)
    bulk collect INTO ret_val
    FROM t_todo
    WHERE todo_id = pTodoId;
    return ret_val;
END;
