/*
 * Copyright 2014-2018 NTT Corporation.
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
 */
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.bean.ErrorResult;

@XmlRootElement
public class ErrorResults implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ErrorResult> errorResults = new ArrayList<ErrorResult>();

    public List<ErrorResult> getErrorResults() {
        return errorResults;
    }

    public void setErrorResults(List<ErrorResult> errorResults) {
        this.errorResults = errorResults;
    }

    public ErrorResults add(String code, String message) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setCode(code);
        errorResult.setMessage(message);
        errorResults.add(errorResult);
        return this;
    }

    public ErrorResults add(String code, String message, String itemPath) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setCode(code);
        errorResult.setMessage(message);
        errorResult.setItemPath(itemPath);
        errorResults.add(errorResult);
        return this;
    }
}
