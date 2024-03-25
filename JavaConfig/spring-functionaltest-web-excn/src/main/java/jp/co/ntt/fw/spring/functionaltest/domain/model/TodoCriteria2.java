/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.apache.ibatis.type.Alias;

@Alias("DailyTodo")
public class TodoCriteria2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dailyTodoTitle;

    private LocalDate createdAt;

    public String getDailyTodoTitle() {
        return dailyTodoTitle;
    }

    public void setDailyTodoTitle(String dailyTodoTitle) {
        this.dailyTodoTitle = dailyTodoTitle;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

}
