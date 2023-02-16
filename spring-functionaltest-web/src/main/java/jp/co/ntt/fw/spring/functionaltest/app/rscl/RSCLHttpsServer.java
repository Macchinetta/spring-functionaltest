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
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.io.output.StringBuilderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

import jakarta.xml.bind.JAXB;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

/**
 * <ul>
 * テスト用簡易HTTPSサーバクラス。<br>
 * applicationContext-rscl.xml にて Bean定義して起動している。
 * </ul>
 */
public class RSCLHttpsServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            RSCLHttpsServer.class);

    private String keyStoreFileName;

    private char[] keyStorePassword;

    private int port;

    private HttpsServer server = null;

    public void startServer() {
        LOGGER.debug("call startServer");

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

            this.server.createContext("/api/v1/rscl", new RSCLHttpHandler(0));
            this.server.createContext("/api/v1/rscl2", new RSCLHttpHandler(6));
            this.server.createContext("/api/v1/rscl3", new RSCLHttpHandler(6));
            this.server.createContext("/api/v1/rscl4", new RSCLHttpHandler(20));

            this.server.setExecutor(null);
            this.server.start();

            LOGGER.debug("RSCL HTTPS Server started.");
        } catch (IOException | NoSuchAlgorithmException | KeyStoreException | CertificateException | UnrecoverableKeyException | KeyManagementException e) {
            LOGGER.error("RSCL用HTTPSサーバ起動でエラー", e);
        }
    }

    public void stopServer() {
        LOGGER.debug("call stopServer");

        if (this.server != null) {
            this.server.stop(0);
        }
    }

    public void setKeyStoreFileName(String keyStoreFileName) {
        this.keyStoreFileName = keyStoreFileName;
    }

    public void setKeyStorePassword(char[] keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private class RSCLHttpHandler implements HttpHandler {
        private static final Logger LOGGER = LoggerFactory.getLogger(
                RSCLHttpHandler.class);

        private long sleepSeconds;

        public RSCLHttpHandler(long sleepSeconds) {
            this.sleepSeconds = sleepSeconds;
        }

        @Override
        public void handle(HttpExchange httpexchange) throws IOException {
            LOGGER.debug("receive");

            try {
                TimeUnit.SECONDS.sleep(this.sleepSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // userのXML化
            UserResource user = new UserResource("test", 20);
            StringBuilderWriter writer = new StringBuilderWriter();
            JAXB.marshal(user, writer);

            // 送信データ
            String sendStr = writer.toString();

            // ヘッダ設定
            httpexchange.getResponseHeaders().add("Content-Type",
                    MediaType.APPLICATION_XML_VALUE);
            httpexchange.sendResponseHeaders(200, sendStr.length());

            // ボディ設定
            try (OutputStream os = httpexchange.getResponseBody()) {
                os.write(sendStr.getBytes());
                os.flush();
            }
        }
    }

}
