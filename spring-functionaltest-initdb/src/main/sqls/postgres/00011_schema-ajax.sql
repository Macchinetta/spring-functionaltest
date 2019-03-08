DROP TABLE IF EXISTS t_personal_computer;
DROP TABLE IF EXISTS t_message_board;
DROP SEQUENCE IF EXISTS s_message_board;

CREATE TABLE t_personal_computer (
    personal_computer_id integer NOT NULL
    ,personal_computer_name CHAR(50) NOT NULL
    ,os CHAR(50) NOT NULL
    ,cpu CHAR(50) NOT NULL
    ,ram CHAR(50) NOT NULL
    ,videocard CHAR(50) NOT NULL
    ,hdd CHAR(50) NOT NULL
    ,power CHAR(50) NOT NULL
    ,price integer NOT NULL
    ,CONSTRAINT pk_t_personal_computer PRIMARY KEY (personal_computer_id)
);

CREATE SEQUENCE s_message_board;

CREATE TABLE t_message_board (
    message_board_id integer NOT NULL
    ,comment CHAR(200) NOT NULL
    ,CONSTRAINT pk_t_message_board PRIMARY KEY (message_board_id)
);