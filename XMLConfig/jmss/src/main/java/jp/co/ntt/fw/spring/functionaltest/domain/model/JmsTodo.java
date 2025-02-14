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
import java.time.LocalDateTime;
import jakarta.validation.constraints.Null;

public class JmsTodo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jmsTodoId;

    @Null
    private String description;

    private LocalDateTime datetime;

    private boolean finished;

    public String getJmsTodoId() {
        return jmsTodoId;
    }

    public void setJmsTodoId(String jmsTodoId) {
        this.jmsTodoId = jmsTodoId;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "JmsTodo [jmsTodoId=" + jmsTodoId + ", description=" + description + ", datetime="
                + datetime + ", finished=" + finished + "]";
    }
}
