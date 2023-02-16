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
package jp.co.ntt.fw.spring.functionaltest.selenium;

import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.config.RestAssuredConfig.config;

import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.output.WriterOutputStream;
import org.junit.AfterClass;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.RestAssured;
import jakarta.inject.Inject;

public abstract class RestTestSupport extends FunctionTestSupport {

    private static final Logger logger = LoggerFactory.getLogger(
            RestTestSupport.class);

    @Inject
    private RestLog restLog;

    protected StringWriter writer;

    protected PrintStream captor;

    protected RestTestSupport() {
        super();
        disableDefaultWebDriver();
    }

    @Before
    public final void setUpRestEvidence() {
        restLog.setUp(evidenceSavingDirectory);
    }

    @Before
    public final void setUpConfig() {

        // Initialization of applicationContextUrl
        RestAssured.baseURI = applicationContextUrl
                + "/api/v1/DEFAULT_VIEW_INCLUSION-enable";
        RestAssured.config = config().logConfig(logConfig()
                .enablePrettyPrinting(false));
        writer = new StringWriter();
        captor = new PrintStream(new WriterOutputStream(writer, StandardCharsets.UTF_8), true);
    }

    @Override
    protected void saveSucceededEvidence() {
        super.saveSucceededEvidence();
        String subTitle = "succeeded";
        try {
            restLog.save(writer, subTitle);
        } catch (Throwable t) {
            logger.error("failed restLog capture.", t);
        }
    }

    @Override
    protected void saveFailedEvidence() {
        super.saveFailedEvidence();
        String subTitle = "failed";
        try {
            restLog.saveForced(writer, subTitle);
        } catch (Throwable t) {
            logger.error("failed restLog capture.", t);
        }
    }

    @AfterClass
    public static void tearDownConfig() {
        RestAssured.reset();
    }

    protected ApServerName getApServerName() {
        return ApServerName.valueOf(given().when().get(
                "/serverinfo/getServerInfo").then().extract().jsonPath()
                .getString("apServerName").toUpperCase());
    }

    protected String getApServerVersion() {
        return given().when().get("/serverinfo/getServerInfo").then().extract()
                .jsonPath().getString("apServerVersion");
    }

    protected int getJavaVersion() {
        return given().when().get("/serverinfo/getServerInfo").then().extract()
                .jsonPath().getInt("javaVersion");
    }

}
