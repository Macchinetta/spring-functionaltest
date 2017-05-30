/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.proxy;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <ul>
 * テスト用簡易Proxyサーバクラス。<br>
 * applicationContext.xml にて Bean定義して起動している。
 * </ul>
 */
public class InternalProxyServer {
    private static final Logger logger = LoggerFactory
            .getLogger(InternalProxyServer.class);

    private int port;

    private Server server = null;

    private InternalProxyServlet internalProxyServlet;

    public void setPort(int port) {
        this.port = port;
    }

    public void setInternalProxyServlet(
            InternalProxyServlet internalProxyServlet) {
        this.internalProxyServlet = internalProxyServlet;
    }

    /**
     * Proxy Server 起動
     * @throws Exception
     */
    public void startServer() throws Exception {
        logger.debug("call startServer");

        try {
            this.server = new Server(this.port);
            ServletHolder wsServletHolder = new ServletHolder(this.internalProxyServlet);
            // Setup proxy servlet
            ServletContextHandler context = new ServletContextHandler();
            context.addServlet(wsServletHolder, "/*");
            this.server.setHandler(context);
            this.server.start();
            logger.debug("RSCL Proxy Server started.");
        } catch (Exception e) {
            logger.error("RSCL用Proxyサーバ起動でエラー", e);
            throw e;
        }
    }

    /**
     * Proxy Server 停止
     * @throws Exception
     */
    public void stopServer() throws Exception {
        logger.debug("call stopServer");

        try {
            if (this.server != null) {
                this.server.stop();
            }
            logger.debug("RSCL Proxy Server stoped.");
        } catch (Exception e) {
            logger.error("RSCL用Proxyサーバ停止でエラー", e);
            throw e;
        }
    }
}
