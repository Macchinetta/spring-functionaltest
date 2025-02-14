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
package jp.co.ntt.fw.spring.functionaltest.app.server;

import java.io.InputStream;
import java.security.KeyStore;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <ul>
 * Test HTTPS server.<br>
 * </ul>
 */
public class RSCLHttpsServer {
    private static final Logger logger = LoggerFactory.getLogger(RSCLHttpsServer.class);

    private String keyStoreFileName;

    private char[] keyStorePassword;

    private int port;

    private Server server = null;

    public void setKeyStoreFileName(String keyStoreFileName) {
        this.keyStoreFileName = keyStoreFileName;
    }

    public void setKeyStorePassword(char[] keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void startServer() {
        logger.debug("call startServer");

        try {
            this.server = createServer(this.keyStoreFileName, this.keyStorePassword, this.port);
            this.server.start();

            logger.debug("RSCL HTTPS Server started.");
        } catch (Exception e) {
            logger.error("Error starting HTTPS server.", e);
        }
    }

    public void stopServer() {
        logger.debug("call stopServer");

        try {
            if (this.server != null) {
                this.server.stop();
            }

            logger.debug("RSCL HTTPS Server stopped.");
        } catch (Exception e) {
            logger.error("Error stopping HTTPS server.", e);
        }
    }

    // Create a server
    private Server createServer(String ksFileName, char[] ksPassword, int httpsPort)
            throws Exception {
        Server httpsServer = new Server();

        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStore(createKeyStore(ksFileName, ksPassword));
        sslContextFactory.setKeyStorePassword(String.valueOf(ksPassword));

        HttpConfiguration config = new HttpConfiguration();
        config.setSecureScheme("https");
        config.addCustomizer(new SecureRequestCustomizer());

        ServerConnector connector = new ServerConnector(httpsServer, sslContextFactory,
                new HttpConnectionFactory(config));
        connector.setPort(httpsPort);
        httpsServer.addConnector(connector);

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.addHandler(createContext("/api/v1/rscl1", new RSCLHttpsServlet(0)));
        contexts.addHandler(createContext("/api/v1/rscl2", new RSCLHttpsServlet(6)));
        contexts.addHandler(createContext("/api/v1/rscl3", new RSCLHttpsServlet(6)));
        contexts.addHandler(createContext("/api/v1/rscl4", new RSCLHttpsServlet(20)));
        httpsServer.setHandler(contexts);

        return httpsServer;
    }

    // Create a keystore
    private KeyStore createKeyStore(String ksFileName, char[] ksPassword) throws Exception {

        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        try (InputStream is = this.getClass().getResourceAsStream("/" + ksFileName)) {
            if (is == null) {
                throw new RuntimeException("Keystore not found. path: /" + ksFileName);
            }
            keystore.load(is, ksPassword);
        }
        return keystore;
    }

    // Create a context for each servlet
    private ServletContextHandler createContext(String contextPath, RSCLHttpsServlet servlet) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath(contextPath);
        context.addServlet(new ServletHolder(servlet), "/*");
        return context;
    }
}
