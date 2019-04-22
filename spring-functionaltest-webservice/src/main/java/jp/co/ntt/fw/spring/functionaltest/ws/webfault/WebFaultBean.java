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
package jp.co.ntt.fw.spring.functionaltest.ws.webfault;

import java.util.ArrayList;
import java.util.List;

public class WebFaultBean {

    private WebFaultType type;

    private List<ErrorBean> errors = new ArrayList<ErrorBean>();

    public WebFaultBean() {

    }

    public WebFaultBean(WebFaultType type) {
        this.type = type;
    }

    public void addError(String code, String message) {
        addError(code, message, null);
    }

    public void addError(String code, String message, String path) {
        errors.add(new ErrorBean(code, message, path));
    }

    public WebFaultType getType() {
        return type;
    }

    public void setType(WebFaultType type) {
        this.type = type;
    }

    public List<ErrorBean> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorBean> errors) {
        this.errors = errors;
    }

}
