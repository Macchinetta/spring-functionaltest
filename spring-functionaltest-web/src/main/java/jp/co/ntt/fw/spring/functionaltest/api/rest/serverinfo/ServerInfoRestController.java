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
package jp.co.ntt.fw.spring.functionaltest.api.rest.serverinfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("serverinfo")
@RestController
public class ServerInfoRestController {

    @Value("${application.server.name}")
    private String apServerName;

    @Value("${application.server.version}")
    private String apServerVersion;

    /**
     * サーバ情報を返却する。
     * <ul>
     * <li>Java versionをシステムプロパティから, APサーバの名前とバージョン情報をプロパティファイルから取得してサーバ情報リソースに格納。</li>
     * </ul>
     * @return
     */
    @GetMapping(value = "getServerInfo")
    @ResponseStatus(HttpStatus.OK)
    public ServerInfoResource getServerInfo() {
        String[] version = System.getProperty("java.version").split("\\.");

        ServerInfoResource rsc = new ServerInfoResource();
        rsc.setJavaVersion(Integer.parseInt(version[1]));
        rsc.setApServerName(apServerName);
        rsc.setApServerVersion(apServerVersion);

        return rsc;
    }
}
