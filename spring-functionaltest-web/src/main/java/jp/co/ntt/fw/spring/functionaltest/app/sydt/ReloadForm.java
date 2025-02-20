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
package jp.co.ntt.fw.spring.functionaltest.app.sydt;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

public class ReloadForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer diffValue;

    public Integer getDiffValue() {
        return diffValue;
    }

    public void setDiffValue(Integer diffValue) {
        this.diffValue = diffValue;
    }
}
