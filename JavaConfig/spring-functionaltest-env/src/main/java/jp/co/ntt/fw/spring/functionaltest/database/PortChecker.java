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

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

/**
 * The PortChecker class provides utility methods to check whether a specified port is in use.
 */
public class PortChecker {

    private PortChecker() {
        // noop
    }

    /**
     * Check whether the specified port is in use.
     * 
     * @param port Target port number
     * @return true if the port is in use, false otherwise
     */
    public static boolean isCustomPortInUse(int port) {
        boolean result = true;
        try {
            ServerSocket socket = new ServerSocket(port);
            socket.close();
            result = false;
        } catch (BindException e) {
            // If the port is in use, a BindException is raised.
            // In this case, the connection can be made in server mode.
            // Since it is also called at logback initialization, the logger is not used and the
            // standard output is used.
            System.out.println("Port " + port + " is already in use.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
