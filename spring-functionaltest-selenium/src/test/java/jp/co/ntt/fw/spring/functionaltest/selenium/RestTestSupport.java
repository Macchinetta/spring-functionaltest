/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium;

import static com.jayway.restassured.config.LogConfig.logConfig;
import static com.jayway.restassured.config.RestAssuredConfig.config;

import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.inject.Inject;

import org.apache.commons.io.output.WriterOutputStream;
import org.junit.AfterClass;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.RestAssured;

public abstract class RestTestSupport extends FunctionTestSupport {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(RestTestSupport.class);

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
        RestAssured.config = config().logConfig(
                logConfig().enablePrettyPrinting(false));
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
            LOGGER.error("failed restLog capture.", t);
        }
    }

    @Override
    protected void saveFailedEvidence() {
        super.saveFailedEvidence();
        String subTitle = "failed";
        try {
            restLog.saveForced(writer, subTitle);
        } catch (Throwable t) {
            LOGGER.error("failed restLog capture.", t);
        }
    }

    @AfterClass
    public static void tearDownConfig() {
        RestAssured.reset();
    }

}
