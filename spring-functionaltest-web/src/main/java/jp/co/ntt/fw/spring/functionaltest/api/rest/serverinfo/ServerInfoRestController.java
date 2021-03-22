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

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("serverinfo")
@RestController
public class ServerInfoRestController {

    /**
     * サーバ情報を返却する。
     * <ul>
     * <li>Java versionをシステムプロパティより取得してサーバ情報リソースに格納。</li>
     * </ul>
     * @return
     */
    @RequestMapping(value = "getServerInfo", method = { RequestMethod.GET })
    @ResponseStatus(HttpStatus.OK)
    public ServerInfoResource getServerInfo() {
        String[] version = System.getProperty("java.version").split("\\.");

        ServerInfoResource rsc = new ServerInfoResource();
        rsc.setJavaVersion(Integer.parseInt(version[1]));

        return rsc;
    }
}
