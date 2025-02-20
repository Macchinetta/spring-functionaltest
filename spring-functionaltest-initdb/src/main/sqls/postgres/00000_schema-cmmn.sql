DROP TABLE IF EXISTS system_date;
DROP TABLE IF EXISTS operation_date;

CREATE TABLE system_date(now timestamp NOT NULL);
CREATE TABLE operation_date(diff bigint NOT NULL);
