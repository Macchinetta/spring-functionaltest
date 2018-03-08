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
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

public enum Gender {

    UNKNOWN("0"), MEN("1"), WOMEN("2");

    private static final Map<String, Gender> genderMap;

    static {
        Map<String, Gender> map = new HashMap<>();
        for (Gender gender : values()) {
            map.put(gender.code, gender);
        }
        genderMap = Collections.unmodifiableMap(map);
    }

    private final String code;

    private Gender(String code) {
        this.code = code;
    }

    public static Gender getByCode(String code) {
        Gender gender = genderMap.get(code);
        Assert.notNull(gender, "gender code is invalid. code : " + code);
        return gender;
    }

    public String getCode() {
        return code;
    }
}
