/*
 * Copyright 2014-2017 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

import org.joda.time.DateTime;

public class TodoMB3 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String todoId;

    private String todoTitle;

    private CategoryMB3 category;

    private boolean finished;

    private Date createdAt;

    private long version;

    private DateTime completeAt;

    private transient InputStream desc1;

    @Transient
    private String normDesc1;

    private transient Reader desc2;

    @Transient
    private String normDesc2;

    public Reader getDesc2() {
        return desc2;
    }

    public void setDesc2(Reader desc2) {
        this.desc2 = desc2;
    }

    public String getNormDesc2() {
        return normDesc2;
    }

    public void setNormDesc2(String normDesc2) {
        this.normDesc2 = normDesc2;
    }

    public String getNormDesc1() {
        return normDesc1;
    }

    public void setNormDesc1(String normDesc1) {
        this.normDesc1 = normDesc1;
    }

    public InputStream getDesc1() {
        return desc1;
    }

    public void setDesc1(InputStream desc1) {
        this.desc1 = desc1;
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public long getVersion() {
        return version;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public CategoryMB3 getCategory() {
        return category;
    }

    public void setCategory(CategoryMB3 category) {
        this.category = category;
    }

    public DateTime getCompleteAt() {
        return completeAt;
    }

    public void setCompleteAt(DateTime completeAt) {
        this.completeAt = completeAt;
    }

}
