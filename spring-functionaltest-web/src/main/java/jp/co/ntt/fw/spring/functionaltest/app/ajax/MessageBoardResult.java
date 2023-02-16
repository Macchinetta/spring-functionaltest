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
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import java.io.Serializable;
import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageBoardResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> resultMessages;

    public List<String> getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(List<String> resultMessages) {
        this.resultMessages = resultMessages;
    }

}
