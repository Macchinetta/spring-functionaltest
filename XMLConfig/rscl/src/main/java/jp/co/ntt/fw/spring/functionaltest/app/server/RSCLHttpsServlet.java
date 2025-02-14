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
package jp.co.ntt.fw.spring.functionaltest.app.server;

import java.util.concurrent.TimeUnit;
import org.apache.commons.io.output.StringBuilderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXB;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;

/**
 * <ul>
 * Test servlet that returns a fixed response.<br>
 * </ul>
 */
public class RSCLHttpsServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RSCLHttpsServlet.class);

    private static final long serialVersionUID = 1L;

    private final long sleepSeconds;

    public RSCLHttpsServlet(long sleepSeconds) {
        this.sleepSeconds = sleepSeconds;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {

        try {
            TimeUnit.SECONDS.sleep(this.sleepSeconds);

            // Convert to XML
            UserResource user = new UserResource("test", 20);
            StringBuilderWriter writer = new StringBuilderWriter();
            JAXB.marshal(user, writer);

            // Transmission data
            String sendStr = writer.toString();

            // Response settings
            res.setContentType(MediaType.APPLICATION_XML_VALUE);
            res.setContentLength(sendStr.length());
            res.setStatus(200);
            res.getWriter().println(sendStr);

        } catch (Exception e) {
            logger.error("Error processing request.", e);
        }
    }

}
