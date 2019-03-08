/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.DateTime;

public class TodoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String todoId;

    private String todoTitle;

    private String todoCategory;

    private boolean finished;

    private Date createdAt;

    private long version;

    private String todoIds;

    private Date deleteDate;

    private DateTime completeAt;

    private String description1;

    private String description2;

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoCategory() {
        return todoCategory;
    }

    public void setTodoCategory(String todoCategory) {
        this.todoCategory = todoCategory;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getTodoIds() {
        return todoIds;
    }

    public void setTodoIds(String todoIds) {
        this.todoIds = todoIds;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public DateTime getCompleteAt() {
        return completeAt;
    }

    public void setCompleteAt(DateTime completeAt) {
        this.completeAt = completeAt;
    }

}
