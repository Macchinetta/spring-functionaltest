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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.terasoluna.gfw.common.exception.BusinessException;

@RequestMapping("serverinfo")
@RestController
public class ServerInfoRestController {

    @Inject
    ServletContext context;

    /**
     * サーバ情報を返却する。
     * <ul>
     * <li>Java versionをシステムプロパティから, APサーバの名前とバージョン情報をServletContextから取得してサーバ情報リソースに格納。</li>
     * </ul>
     * @return
     */
    @RequestMapping(value = "getServerInfo", method = { RequestMethod.GET })
    @ResponseStatus(HttpStatus.OK)
    public ServerInfoResource getServerInfo() {
        String[] version = System.getProperty("java.version").split("\\.");
        String info = context.getServerInfo();
        String apServerName = "UNKNOWN";
        String apServerVersion = "UNKNOWN";

        // ServletContextのgetServerInfoでAPサーバの情報を取得した場合、APサーバによって出力される内容が異なるので、各サーバ毎に必要な部分のみを抽出する。
        if (info.contains("Apache Tomcat")) {
            apServerName = "Apache Tomcat";
            Matcher matcher = Pattern.compile("\\d{1,2}.\\d.\\d{1,2}").matcher(
                    info);
            if (matcher.find()) {
                apServerVersion = matcher.group();
            }

        } else {
            throw new BusinessException("If you are using a new application server, add the setting to ServerInfoRestController.");
        }

        ServerInfoResource rsc = new ServerInfoResource();
        rsc.setJavaVersion(Integer.parseInt(version[1]));
        rsc.setApServerName(apServerName);
        rsc.setApServerVersion(apServerVersion);

        return rsc;
    }
}
