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
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.bind.JAXB;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

import org.apache.commons.io.output.StringBuilderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

/**
 * <ul>
 * テスト用簡易HTTPSサーバクラス。<br>
 * rsclContext.xml にて Bean定義して起動している。
 * </ul>
 */
@SuppressWarnings("restriction")
public class RSCLHttpsServer {
    private static final Logger logger = LoggerFactory.getLogger(
            RSCLHttpsServer.class);

    @Value("${rscl.keystore.filename}")
    String keyStoreFileName;

    @Value("${rscl.keystore.password}")
    char[] keyStorePassword;

    @Value("${rscl.httpsserver.port}")
    int port;

    private HttpsServer server = null;

    public void startServer() {
        logger.debug("call startServer");

        try {
            this.server = HttpsServer.create(new InetSocketAddress(this.port),
                    0);

            // SSL利用の設定
            SSLContext sslContext = SSLContext.getInstance("TLS");

            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(this.getClass().getClassLoader().getResourceAsStream(
                    this.keyStoreFileName), this.keyStorePassword);

            KeyManagerFactory kmf = KeyManagerFactory.getInstance(
                    KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, this.keyStorePassword);

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ks);

            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            this.server.setHttpsConfigurator(new HttpsConfigurator(sslContext));

            this.server.createContext("/api/v1/rscl", new HttpHandler() {
                @Override
                public void handle(
                        HttpExchange httpexchange) throws IOException {
                    logger.debug("receive");

                    // userのXMｌ化
                    UserResource user = new UserResource();
                    user.setName("test");
                    user.setAge(20);
                    StringBuilderWriter writer = new StringBuilderWriter();
                    JAXB.marshal(user, writer);

                    // 送信データ
                    String sendStr = writer.toString();

                    // ヘッダ設定
                    httpexchange.getResponseHeaders().add("Content-Type",
                            MediaType.APPLICATION_XML_VALUE);
                    httpexchange.sendResponseHeaders(200, sendStr.length());

                    // ボディ設定
                    OutputStream os = httpexchange.getResponseBody();
                    os.write(sendStr.getBytes());
                    os.flush();
                    os.close();
                }
            });

            this.server.setExecutor(null);
            this.server.start();

            logger.debug("RSCL HTTPS Server started.");
        } catch (IOException | NoSuchAlgorithmException | KeyStoreException | CertificateException | UnrecoverableKeyException | KeyManagementException e) {
            logger.error("RSCL用HTTPSサーバ起動でエラー", e);
        }
    }

    public void stopServer() {
        logger.debug("call stopServer");

        if (this.server != null) {
            this.server.stop(0);
        }
    }

}
