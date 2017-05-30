/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_category")
public class JPACategory {

    /**
     * m_category ( category_id CHAR(10) ,name VARCHAR(1024) ,CONSTRAINT pk_category PRIMARY KEY (category_id)
     */

    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "category_id", nullable = false, length = 10)
    private String categoryId;

    @Column(name = "name", nullable = true, length = 1024)
    private String categoryName;

    public JPACategory() {

    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoriId) {
        this.categoryId = categoriId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
