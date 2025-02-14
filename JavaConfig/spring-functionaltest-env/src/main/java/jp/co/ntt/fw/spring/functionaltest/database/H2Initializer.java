/*
 * Copyright(c) 2025 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import org.apache.commons.lang3.StringUtils;
import org.h2.tools.Server;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * H2Initializer is a ServletContextListener that initializes and shuts down an H2 database when the
 * web application starts and stops.
 * 
 * <p>
 * This class checks if the specified port is available and starts the H2 database server if it is
 * not already running. It also sets up a database connection and stores it in the ServletContext.
 * </p>
 * 
 * <p>
 * When the web application is stopped, it shuts down the H2 database and closes the connection.
 * </p>
 * 
 * <p>
 * Example usage in web.xml:
 * </p>
 * 
 * <pre>
 * {@code
 * <listener>
 *     <listener-class>jp.co.ntt.fw.spring.functionaltest.database.H2Initializer</listener-class>
 * </listener>
 * <context-param>
 *     <param-name>db.url</param-name>
 *     <param-value>jdbc:h2:mem:testdb</param-value>
 * </context-param>
 * <context-param>
 *     <param-name>db.user</param-name>
 *     <param-value>sa</param-value>
 * </context-param>
 * <context-param>
 *     <param-name>db.password</param-name>
 *     <param-value></param-value>
 * </context-param>
 * <context-param>
 *     <param-name>db.tcpServer</param-name>
 *     <param-value>-tcp -tcpAllowOthers</param-value>
 * </context-param>
 * <context-param>
 *     <param-name>db.port</param-name>
 *     <param-value>9092</param-value>
 * </context-param>
 * }
 * </pre>
 */
public class H2Initializer implements ServletContextListener {

    private Connection conn = null;

    private Server server = null;

    /**
     * Initializes the H2 database when the web application starts.
     * 
     * @param servletContextEvent the ServletContextEvent containing the ServletContext
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        String port = servletContext.getInitParameter("db.port");

        // If the port is not free, it is assumed that H2DB has already started.
        if (PortChecker.isCustomPortInUse(Integer.valueOf(port))) {
            servletContext.setAttribute("h2InitializeStatus", "false");
            return;
        }

        String url = servletContext.getInitParameter("db.url");
        String user = servletContext.getInitParameter("db.user");
        String password = servletContext.getInitParameter("db.password");
        String serverParams = servletContext.getInitParameter("db.tcpServer");

        try {
            if (serverParams != null) {
                String[] params = StringUtils.splitByWholeSeparator(serverParams, null);
                this.server = Server.createTcpServer(params);
                this.server.start();
            }
            // The following description is written according to the class Doc of org.h2.Driver
            Class.forName("org.h2.Driver");
            this.conn = DriverManager.getConnection(url, user, password);

            servletContext.setAttribute("connection", this.conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        servletContext.setAttribute("h2InitializeStatus", "true");
    }

    /**
     * Shuts down the H2 database and closes the connection when the web application stops.
     * 
     * @param servletContextEvent the ServletContextEvent containing the ServletContext
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        if (this.conn != null) {
            try (Statement stat = this.conn.createStatement()) {
                stat.execute("SHUTDOWN");
                this.conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.server != null) {
            this.server.stop();
            this.server = null;
        }

        // The application server will close it, but you will get a warning, so close it explicitly.
        Collections.list(DriverManager.getDrivers()).forEach(driver -> {
            if (driver instanceof org.h2.Driver d) {
                try {
                    DriverManager.deregisterDriver(d);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
