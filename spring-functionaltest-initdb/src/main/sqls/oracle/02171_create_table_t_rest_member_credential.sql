CREATE
	TABLE t_rest_member_credential (
	    member_id CHAR(10) NOT NULL
	    ,sign_id VARCHAR(256) NOT NULL
	    ,password CHAR(60) NOT NULL
	    ,previous_password CHAR(60)
	    ,password_last_changed_at TIMESTAMP
	    ,last_modified_at TIMESTAMP NOT NULL
	    ,version integer NOT NULL
	    ,CONSTRAINT pk_t_rest_member_credential PRIMARY KEY (member_id)
	    ,CONSTRAINT uk_t_rest_member_credential UNIQUE (sign_id)
	    ,CONSTRAINT fk_t_rest_member_credential FOREIGN KEY (member_id) REFERENCES t_rest_member(member_id)
    )
