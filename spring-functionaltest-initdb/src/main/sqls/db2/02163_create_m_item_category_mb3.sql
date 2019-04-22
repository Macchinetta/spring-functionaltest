CREATE TABLE m_item_category_mb3 (
    item_code CHAR(10) NOT NULL,
    category_code CHAR(10) NOT NULL,
    CONSTRAINT m_item_category_mb3_pk PRIMARY KEY(item_code, category_code),
    CONSTRAINT m_item_category_mb3_fk1 FOREIGN KEY(item_code) REFERENCES m_item_mb3(code),
    CONSTRAINT m_item_category_mb3_fk2 FOREIGN KEY(category_code) REFERENCES m_category_mb3(code)
)
