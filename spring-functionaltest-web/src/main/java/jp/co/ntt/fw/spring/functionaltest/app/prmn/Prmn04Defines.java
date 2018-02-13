/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.prmn;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Prmn04Defines implements Serializable {

    private static final long serialVersionUID = 2572679991365788905L;

    @Value("${i.sf.prmn.0401001}")
    private String propertyValue0401001;

    public String getPropertyValue0401001() {
        return propertyValue0401001;
    }

    public void setPropertyValue0401001(String propertyValue0401001) {
        this.propertyValue0401001 = propertyValue0401001;
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
