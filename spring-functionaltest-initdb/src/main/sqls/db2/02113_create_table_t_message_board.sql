CREATE
    TABLE t_message_board(
        message_board_id NUMERIC(10) NOT NULL
        ,"comment" VARCHAR(200) NOT NULL
        ,CONSTRAINT pk_t_message_board PRIMARY KEY (message_board_id)
    )
