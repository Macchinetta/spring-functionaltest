CREATE TABLE system_date(now timestamp NOT NULL);
CREATE TABLE operation_date(
    operation_date_id int NOT NULL,
    diff bigint NOT NULL,
    constraint pk_operation_date_id primary key (operation_date_id)
);
