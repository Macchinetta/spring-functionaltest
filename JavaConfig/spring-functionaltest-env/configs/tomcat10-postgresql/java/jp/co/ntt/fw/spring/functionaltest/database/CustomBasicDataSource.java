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

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * CustomBasicDataSource extends the BasicDataSource to provide custom configurations for the H2
 * database connection, including setting a custom TCP port and URL.
 * 
 * <p>
 * This class overrides the {@code setUrl} method to connect in server mode if the custom TCP port
 * is already in use.
 * </p>
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * {@code
 * CustomBasicDataSource dataSource = new CustomBasicDataSource();
 * dataSource.setTcpPort(9202);
 * dataSource.setTcpUrl("jdbc:h2:tcp://localhost:9202/mem:spring-functionaltest");
 * dataSource.setUrl("jdbc:h2:mem:test");
 * }
 * </pre>
 * 
 * @see org.apache.commons.dbcp2.BasicDataSource
 */
public class CustomBasicDataSource extends BasicDataSource {

    private static int tcpPort = 9202;

    private static String tcpUrl = "jdbc:h2:tcp://localhost:9202/mem:spring-functionaltest";

    /**
     * Sets the custom TCP port for the H2 database connection.
     * 
     * @param port the TCP port to set
     */
    public static void setTcpPort(int port) {
        tcpPort = port;
    }

    /**
     * Sets the custom TCP URL for the H2 database connection.
     * 
     * @param url the TCP URL to set
     */
    public static void setTcpUrl(String url) {
        tcpUrl = url;
    }

    /**
     * Sets the URL for the database connection. If the custom TCP port is in use, it connects using
     * the custom TCP URL in server mode.
     * 
     * @param url the database URL to set
     */
    @Override
    public synchronized void setUrl(String url) {
        // Connect in server mode if already started with TCP.
        if (PortChecker.isCustomPortInUse(tcpPort)) {
            super.setUrl(tcpUrl);
        } else {
            super.setUrl(url);
        }
    }

}
