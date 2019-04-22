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
package jp.co.ntt.fw.spring.functionaltest.app.stpr;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class StringProcessing01Form implements Serializable {

    private static final long serialVersionUID = 1L;

    private String targetText;

    @Size(min = 1, max = 1)
    private String trimText;

    public String getTargetText() {
        return targetText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }

    public String getTrimText() {
        return trimText;
    }

    public void setTrimText(String trimText) {
        this.trimText = trimText;
    }

}
