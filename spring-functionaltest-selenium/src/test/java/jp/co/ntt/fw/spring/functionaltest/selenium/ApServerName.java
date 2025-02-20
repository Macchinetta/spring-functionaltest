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
package jp.co.ntt.fw.spring.functionaltest.selenium;

/**
 * APサーバを識別するための定数クラス。
 * <p>
 * application-env.propertiesにAPサーバ名が設定されていない場合は<br/>
 * UNKONWNが設定される。
 * </p>
 */
public enum ApServerName {
    UNKNOWN("UNKNOWN"), INTERSTAGE("INTERSTAGE"), JBOSS("JBOSS"), TOMCAT("APACHE TOMCAT"), WEBLOGIC(
            "WEBLOGIC SERVER"), WEBOTX(
                    "WEBOTX"), WEBSPHERELP("WEBSPHERELP"), WEBSPHERETR("WEBSPHERETR");

    private final String apServerName;

    private ApServerName(String apServerName) {
        this.apServerName = apServerName;
    }

    public String getApServerName() {
        return this.apServerName;
    }

    public static ApServerName getByCode(String code) {
        for (ApServerName apServerName : ApServerName.values()) {
            if (apServerName.getApServerName().equals(code)) {
                return apServerName;
            }
        }
        return ApServerName.UNKNOWN;
    }
}
