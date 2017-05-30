/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
