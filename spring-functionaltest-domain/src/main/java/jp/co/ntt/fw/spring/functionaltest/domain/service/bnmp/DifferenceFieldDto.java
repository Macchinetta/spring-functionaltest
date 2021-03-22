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
package jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp;

import java.io.Serializable;
import java.time.LocalDate;

public class DifferenceFieldDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String myoji;

    private String namae;

    private String age;

    private LocalDate tanjobi;

    public String getMyoji() {
        return myoji;
    }

    public void setMyoji(String myoji) {
        this.myoji = myoji;
    }

    public String getNamae() {
        return namae;
    }

    public void setNamae(String namae) {
        this.namae = namae;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public LocalDate getTanjobi() {
        return tanjobi;
    }

    public void setTanjobi(LocalDate tanjobi) {
        this.tanjobi = tanjobi;
    }
}
