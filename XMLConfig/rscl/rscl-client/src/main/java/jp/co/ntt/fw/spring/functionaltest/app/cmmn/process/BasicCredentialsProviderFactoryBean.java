/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.process;

import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;

public class BasicCredentialsProviderFactoryBean implements FactoryBean<BasicCredentialsProvider> {

    @Value("${rscl.http.proxyHost}")
    String host;

    @Value("${rscl.http.proxyPort}")
    int port;

    String userName;

    String password;

    /**
     * コンストラクタ
     * 
     * @param userName ユーザ名
     * @param password パスワード
     */
    public BasicCredentialsProviderFactoryBean(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public BasicCredentialsProvider getObject() throws Exception {

        var authScope = new AuthScope(this.host, this.port);

        char[] passwordCharArray = this.password == null ? null : this.password.toCharArray();
        var usernamePasswordCredentials = new UsernamePasswordCredentials(this.userName,
                passwordCharArray);

        var credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(authScope, usernamePasswordCredentials);
        return credentialsProvider;
    }

    @Override
    public Class<?> getObjectType() {
        return BasicCredentialsProvider.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
