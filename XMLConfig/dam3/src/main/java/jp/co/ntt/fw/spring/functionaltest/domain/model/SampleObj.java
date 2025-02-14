/*
 * Copyright(c) 2025 NTT Corporation.
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

public class SampleObj implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private HandlerObj handlerObj;

    public SampleObj() {}

    public SampleObj(String id, HandlerObj handlerObj) {
        this.id = id;
        this.handlerObj = handlerObj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HandlerObj getHandlerObj() {
        return handlerObj;
    }

    public void setHandlerObj(HandlerObj handlerObj) {
        this.handlerObj = handlerObj;
    }

    public String toString() {
        return "HandlerObj [id=" + id + ", handlerObj=" + handlerObj + "]";
    }
}
