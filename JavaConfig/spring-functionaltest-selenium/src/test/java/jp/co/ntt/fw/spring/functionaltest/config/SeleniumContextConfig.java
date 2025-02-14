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
package jp.co.ntt.fw.spring.functionaltest.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import jp.co.ntt.fw.spring.functionaltest.selenium.DBLog;
import jp.co.ntt.fw.spring.functionaltest.selenium.DBLogAssertOperations;
import jp.co.ntt.fw.spring.functionaltest.selenium.DBLogCleaner;
import jp.co.ntt.fw.spring.functionaltest.selenium.PageSource;
import jp.co.ntt.fw.spring.functionaltest.selenium.RestLog;
import jp.co.ntt.fw.spring.functionaltest.selenium.ScreenCapture;
import jp.co.ntt.fw.spring.functionaltest.selenium.webdrivers.ChromeDriverFactoryBean;
import jp.co.ntt.fw.spring.functionaltest.selenium.webdrivers.EdgeDriverFactoryBean;
import jp.co.ntt.fw.spring.functionaltest.selenium.webdrivers.FirefoxDriverFactoryBean;
import jp.co.ntt.fw.spring.functionaltest.selenium.webdrivers.WebDriverCreator;

/**
 * Bean definition to SeleniumContext configure.
 */
@Configuration
@EnableTransactionManagement
public class SeleniumContextConfig {

    /**
     * selenium.dbHost property.
     */
    @Value("${selenium.logDbHost}")
    private String dbHost;

    /**
     * selenium.dbPort property.
     */
    @Value("${selenium.logDbPort}")
    private String dbPort;

    /**
     * selenium.restOperations.connectTimeout property.
     */
    @Value("${selenium.restOperations.connectTimeout:-1}")
    private int connectTimeout;

    /**
     * selenium.restOperations.readTimeout property.
     */
    @Value("${selenium.restOperations.readTimeout:-1}")
    private int readTimeout;

    /**
     * selenium.headless property.
     */
    @Value("${selenium.headless}")
    private boolean headless;

    /**
     * Configure {@link PropertySourcesPlaceholderConfigurer} bean.
     * 
     * @param properties Path where the property file is located
     * @return Bean of configured {@link PropertySourcesPlaceholderConfigurer}
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(
            @Value("classpath*:META-INF/spring/*.properties") Resource... properties) {
        PropertySourcesPlaceholderConfigurer bean = new PropertySourcesPlaceholderConfigurer();
        bean.setLocations(properties);
        return bean;
    }

    /**
     * Configure the {@link DataSource} bean.
     * 
     * @return Bean of configured {@link BasicDataSource}
     */
    @Bean(name = "dataSourceForLogging", destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource bean = new BasicDataSource();
        bean.setDriverClassName("org.h2.Driver");
        bean.setUrl("jdbc:h2:tcp://" + dbHost + ":" + dbPort + "/mem:spring-functionaltest");
        bean.setUsername("sa");
        bean.setPassword("");
        bean.setDefaultAutoCommit(false);
        return bean;
    }

    /**
     * Configure the {@link TransactionManager} bean.
     * 
     * @return Bean of configured {@link DataSourceTransactionManager}
     */
    @Bean("transactionManager")
    public TransactionManager transactionManager() {
        DataSourceTransactionManager bean = new DataSourceTransactionManager();
        bean.setDataSource(dataSource());
        bean.setRollbackOnCommitFailure(true);
        return bean;
    }

    /**
     * Configure the {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("restOperations")
    public RestTemplate restOperations() {
        RestTemplate bean = new RestTemplate();
        bean.setRequestFactory(simpleClientHttpRequestFactory());
        return bean;
    }

    /**
     * Configure the {@link SimpleClientHttpRequestFactory} bean.
     * 
     * @return Bean of configured {@link SimpleClientHttpRequestFactory}
     */
    @Bean
    public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory bean = new SimpleClientHttpRequestFactory();
        bean.setConnectTimeout(connectTimeout);
        bean.setReadTimeout(readTimeout);
        return bean;
    }

    /**
     * Configure the {@link DBLogAssertOperations} bean.
     * 
     * @return Bean of configured {@link DBLogAssertOperations}
     */
    @Bean("dbLogAssertOperations")
    public DBLogAssertOperations dbLogAssertOperations() {
        return new DBLogAssertOperations(jdbcTemplate());
    }

    /**
     * Configure the {@link JdbcTemplate} bean.
     * 
     * @return Bean of configured {@link JdbcTemplate}
     */
    @Bean("jdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate bean = new JdbcTemplate();
        bean.setDataSource(dataSource());
        bean.setFetchSize(100);
        return bean;
    }

    /**
     * Configure the {@link ScreenCapture}.
     * 
     * @return Bean of configured {@link ScreenCapture}
     */
    @Bean("screenCapture")
    public ScreenCapture screenCapture() {
        return new ScreenCapture();
    }

    /**
     * Configure the {@link DBLog} bean.
     * 
     * @return Bean of configured {@link DBLog}
     */
    @Bean("dbLog")
    public DBLog dbLog() {
        DBLog bean = new DBLog();
        bean.setDataSource(dataSource());
        return bean;
    }

    /**
     * Configure the {@link PageSource}.
     * 
     * @return Bean of configured {@link PageSource}
     */
    @Bean("pageSource")
    public PageSource pageSource() {
        return new PageSource();
    }

    /**
     * Configure the {@link RestLog}.
     * 
     * @return Bean of configured {@link RestLog}
     */
    @Bean("restLog")
    public RestLog restLog() {
        return new RestLog();
    }

    /**
     * Configure the {@link DBLogCleaner} bean.
     * 
     * @return Bean of configured {@link DBLogCleaner}
     */
    @Bean("dbLogCleaner")
    public DBLogCleaner dbLogCleaner() {
        DBLogCleaner bean = new DBLogCleaner();
        bean.setDataSource(dataSource());
        return bean;
    }

    /**
     * Configure the {@link WebDriverCreator} bean.
     * 
     * @return Bean of configured {@link WebDriverCreator}
     */
    @Bean
    public WebDriverCreator webDriverCreator() {
        WebDriverCreator bean = new WebDriverCreator();
        bean.setPropertyFileLocation("wdm.properties");
        bean.setHeadless(this.headless);
        return bean;
    }

    /**
     * Configure the {@link FirefoxDriverFactoryBean} bean.
     * 
     * @return Bean of configured {@link FirefoxDriverFactoryBean}
     */
    @Bean("webDriver")
    @Profile({"firefox", "default"})
    @Scope("prototype")
    public FirefoxDriverFactoryBean firefoxDriverFactoryBean() {
        FirefoxDriverFactoryBean bean = new FirefoxDriverFactoryBean();
        bean.setPropertyFileLocation("wdm.properties");
        bean.setHeadless(this.headless);
        return bean;
    }

    /**
     * Configure the {@link EdgeDriverFactoryBean} bean.
     * 
     * @return Bean of configured {@link EdgeDriverFactoryBean}
     */
    @Bean("webDriver")
    @Profile({"edge"})
    @Scope("prototype")
    public EdgeDriverFactoryBean edgeDriverFactoryBean() {
        EdgeDriverFactoryBean bean = new EdgeDriverFactoryBean();
        bean.setPropertyFileLocation("wdm.properties");
        bean.setHeadless(this.headless);
        return bean;
    }

    /**
     * Configure the {@link ChromeDriverFactoryBean} bean.
     * 
     * @return Bean of configured {@link ChromeDriverFactoryBean}
     */
    @Bean("webDriver")
    @Profile({"chrome"})
    @Scope("prototype")
    public ChromeDriverFactoryBean chromeDriverFactoryBean() {
        ChromeDriverFactoryBean bean = new ChromeDriverFactoryBean();
        bean.setPropertyFileLocation("wdm.properties");
        bean.setHeadless(this.headless);
        return bean;
    }

}
