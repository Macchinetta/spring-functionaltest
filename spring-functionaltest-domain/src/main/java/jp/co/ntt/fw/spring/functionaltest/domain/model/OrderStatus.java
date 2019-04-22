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
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import org.terasoluna.gfw.common.codelist.EnumCodeList.CodeListItem;

public enum OrderStatus implements CodeListItem {
    RECEIVED("1", "Received"), SENT("2", "Sent"), CANCELLED("3", "Cancelled");

    private final String value;

    private final String label;

    private OrderStatus(String codeValue, String codeLabel) {
        this.value = codeValue;
        this.label = codeLabel;
    }

    @Override
    public String getCodeValue() {
        return value;
    }

    @Override
    public String getCodeLabel() {
        return label;
    }
}
