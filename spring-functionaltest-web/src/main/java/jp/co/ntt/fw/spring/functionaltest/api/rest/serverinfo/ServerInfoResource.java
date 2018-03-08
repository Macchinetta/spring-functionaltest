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
package jp.co.ntt.fw.spring.functionaltest.api.rest.serverinfo;

import java.io.Serializable;

public class ServerInfoResource implements Serializable {
    private static final long serialVersionUID = 1L;

    private int javaVersion;

    private String apServerName;

    private String apServerVersion;

    public int getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(int javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getApServerName() {
        return apServerName;
    }

    public void setApServerName(String apServerName) {
        this.apServerName = apServerName;
    }

    public String getApServerVersion() {
        return apServerVersion;
    }

    public void setApServerVersion(String apServerVersion) {
        this.apServerVersion = apServerVersion;
    }

}
