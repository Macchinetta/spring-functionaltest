CREATE OR REPLACE 
	PROCEDURE DROP_TYPE(typeName VARCHAR2)
 	IS
 		cnt int := 0; 
	BEGIN
 		SELECT count(*) INTO cnt
  		FROM ALL_OBJECTS
 		WHERE OBJECT_NAME = UPPER(typeName); 
 			if cnt > 0 
 			THEN
 				execute IMMEDIATE 'DROP TYPE ' || UPPER(typeName);
 			end if; 
 	END;