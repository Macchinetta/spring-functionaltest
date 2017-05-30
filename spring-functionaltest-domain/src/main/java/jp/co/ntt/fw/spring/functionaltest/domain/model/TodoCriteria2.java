/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("DailyTodo")
public class TodoCriteria2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dailyTodoTitle;

    private Date createdAt;

    public String getDailyTodoTitle() {
        return dailyTodoTitle;
    }

    public void setDailyTodoTitle(String dailyTodoTitle) {
        this.dailyTodoTitle = dailyTodoTitle;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
