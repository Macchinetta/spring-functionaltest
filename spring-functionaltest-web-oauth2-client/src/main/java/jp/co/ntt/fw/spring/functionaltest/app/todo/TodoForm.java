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
package jp.co.ntt.fw.spring.functionaltest.app.todo;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TodoForm implements Serializable {
    public static interface TodoCreate {
    };

    public static interface TodoFinish {
    };

    public static interface TodoDelete {
    }

    private static final long serialVersionUID = 1L;

    @NotEmpty(groups = {TodoFinish.class, TodoDelete.class})
    private String todoId;

    @NotNull(groups = {TodoCreate.class})
    @Size(min = 1, max = 30, groups = {TodoCreate.class})
    private String todoTitle;

    private String registrationId;

    private String resourceProtect;

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

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getResourceProtect() {
        return resourceProtect;
    }

    public void setResourceProtect(String resourceProtect) {
        this.resourceProtect = resourceProtect;
    }

}
