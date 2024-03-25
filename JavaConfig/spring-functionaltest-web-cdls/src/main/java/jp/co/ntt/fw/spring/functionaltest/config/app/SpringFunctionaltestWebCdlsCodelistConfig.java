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
package jp.co.ntt.fw.spring.functionaltest.config.app;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.terasoluna.gfw.common.codelist.CodeList;
import org.terasoluna.gfw.common.codelist.EnumCodeList;
import org.terasoluna.gfw.common.codelist.JdbcCodeList;
import org.terasoluna.gfw.common.codelist.NumberRangeCodeList;
import org.terasoluna.gfw.common.codelist.SimpleMapCodeList;
import org.terasoluna.gfw.common.codelist.i18n.SimpleI18nCodeList;
import org.terasoluna.gfw.common.time.ClockFactory;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderStatus;
import jp.co.ntt.fw.spring.functionaltest.domain.service.cdls.DepYearCodeList;

/**
 * Bean definition regarding CodeLists.
 */
@Configuration
@EnableScheduling
public class SpringFunctionaltestWebCdlsCodelistConfig implements
                                                       SchedulingConfigurer {

    /**
     * JDBC fetchSize property.
     */
    @Value("${codelist.jdbc.fetchSize:1000}")
    private Integer fetchSize;

    /**
     * cron.codelist.refreshTime property.
     */
    @Value("${cdls.cron.codelist.refreshTime}")
    private String codelistRefreshTime;

    /**
     * Bean of DataSource.
     */
    @Inject
    private DataSource dataSource;

    /**
     * Configure {@link JdbcTemplate} bean.
     * @return Bean of configured {@link JdbcTemplate}
     */
    @Bean("jdbcTemplateForCodeList")
    public JdbcTemplate jdbcTemplateForCodeList() {
        JdbcTemplate bean = new JdbcTemplate();
        bean.setDataSource(dataSource);
        bean.setFetchSize(fetchSize);
        return bean;
    }

    /**
     * Common processing of {@link JdbcCodeList}.
     * @return Bean of configured {@link JdbcCodeList}
     */
    private JdbcCodeList abstractJdbcCodeList() {
        JdbcCodeList bean = new JdbcCodeList();
        bean.setJdbcTemplate(jdbcTemplateForCodeList());
        return bean;
    }

    //Example for usage of AbstractJdbcCodeList

//    @Bean("CL_SAMPLE")
// public JdbcCodeList clSample() {
// JdbcCodeList jdbcCodeList = abstractJdbcCodeList();
//        jdbcCodeList.setQuerySql(
//                "SELECT code, code_name FROM t_sample_codes ORDER BY code");
//        jdbcCodeList.setValueColumn("code");
//        jdbcCodeList.setLabelColumn("code_name");
//        return jdbcCodeList;
//    }

    /**
     * Configure {@link SimpleMapCodeList} bean.
     * @return Bean of configured {@link SimpleMapCodeList}
     */
    @Bean("CL_ORDERSTATUS")
    public SimpleMapCodeList clOrderstatus() {
        Map<String, String> codeMap = new LinkedHashMap<String, String>();
        codeMap.put("1", "Received");
        codeMap.put("2", "Sent");
        codeMap.put("3", "Cancelled");
        SimpleMapCodeList bean = new SimpleMapCodeList();
        bean.setMap(codeMap);
        return bean;
    }

    /**
     * Configure {@link NumberRangeCodeList} bean.
     * @return Bean of configured {@link NumberRangeCodeList}
     */
    @Bean("CL_MONTH_ASC")
    public NumberRangeCodeList clMonthAsc() {
        NumberRangeCodeList bean = new NumberRangeCodeList();
        bean.setFrom(1);
        bean.setTo(12);
        bean.setValueFormat("%d");
        bean.setLabelFormat("%02d");
        bean.setInterval(1);
        return bean;
    }

    /**
     * Configure {@link NumberRangeCodeList} bean.
     * @return Bean of configured {@link NumberRangeCodeList}
     */
    @Bean("CL_MONTH_DES")
    public NumberRangeCodeList clMonthDes() {
        NumberRangeCodeList bean = new NumberRangeCodeList();
        bean.setFrom(12);
        bean.setTo(1);
        bean.setValueFormat("%d");
        bean.setLabelFormat("%02d");
        bean.setInterval(1);
        return bean;
    }
    
    /**
     * Configure {@link NumberRangeCodeList} bean.
     * @return Bean of configured {@link NumberRangeCodeList}
     */
    @Bean("CL_NUMBER_WITHIN_RANGE_INTERVAL")
    public NumberRangeCodeList cLNumberWithinRangeInterval() {
        NumberRangeCodeList bean = new NumberRangeCodeList();
        bean.setFrom(10);
        bean.setTo(50);
        bean.setInterval(10);
        return bean;
    }

    /**
     * Configure {@link NumberRangeCodeList} bean.
     * @return Bean of configured {@link NumberRangeCodeList}
     */
    @Bean("CL_NUMBER_WITHOUT_RANGE_INTERVAL")
    public NumberRangeCodeList cLNumberWithoutRangeInterval() {
        NumberRangeCodeList bean = new NumberRangeCodeList();
        bean.setFrom(10);
        bean.setTo(55);
        bean.setInterval(10);
        return bean;
    }

    /**
     * Configure {@link JdbcCodeList} bean.
     * @return Bean of configured {@link JdbcCodeList}
     */
    @Bean("CL_AUTHORITIES")
    public JdbcCodeList cLAuthorities() {
        JdbcCodeList jdbcCodeList = abstractJdbcCodeList();
        jdbcCodeList.setQuerySql("SELECT authority_id, authority_name FROM t_authority ORDER BY authority_id");
        jdbcCodeList.setValueColumn("authority_id");
        jdbcCodeList.setLabelColumn("authority_name");
        return jdbcCodeList;
    }

    /**
     * Configure {@link EnumCodeList} bean.
     * @return Bean of configured {@link EnumCodeList}
     */
    @Bean("CL_ENUM_ORDERSTATUS")
    public EnumCodeList cLEnumOrderstatus() {
        return new EnumCodeList(OrderStatus.class);
    }

    /**
     * Configure {@link SimpleI18nCodeList} bean.
     * @return Bean of configured {@link SimpleI18nCodeList}
     */
    @Bean("CL_I18N_PRICE")
    public SimpleI18nCodeList cLI18nPrice() {
        Map<Locale, CodeList> rows =  new LinkedHashMap<Locale, CodeList>();
        rows.put(Locale.ENGLISH, cLPriceEn());
        rows.put(Locale.JAPANESE, cLPriceJa());
        SimpleI18nCodeList bean = new SimpleI18nCodeList();
        bean.setRowsByCodeList(rows);
        bean.setFallbackTo(Locale.ENGLISH);
        return bean;
    }

    /**
     * Configure {@link SimpleMapCodeList} bean.
     * @return Bean of configured {@link SimpleMapCodeList}
     */
    @Bean("CL_PRICE_EN")
    public SimpleMapCodeList cLPriceEn() {
        Map<String, String> enMap = new LinkedHashMap<String, String>();
        enMap.put("0", "unlimited");
        enMap.put("10000", "Less than \\10,000");
        enMap.put("20000", "Less than \\20,000");
        enMap.put("30000", "Less than \\30,000");
        SimpleMapCodeList bean = new SimpleMapCodeList();
        bean.setMap(enMap);
        return bean;
    }

    /**
     * Configure {@link SimpleMapCodeList} bean.
     * @return Bean of configured {@link SimpleMapCodeList}
     */
    @Bean("CL_PRICE_JA")
    public SimpleMapCodeList cLPriceJa() {
        Map<String, String> jaMap = new LinkedHashMap<String, String>();
        jaMap.put("0", "上限なし");
        jaMap.put("10000", "10,000円以下");
        jaMap.put("20000", "20,000円以下");
        jaMap.put("30000", "30,000円以下");
        SimpleMapCodeList bean = new SimpleMapCodeList();
        bean.setMap(jaMap);
        return bean;
    }

    /**
     * Configure {@link SimpleI18nCodeList} bean.
     * @return Bean of configured {@link SimpleI18nCodeList}
     */
    @Bean("CL_I18N_DB_PRICE")
    public SimpleI18nCodeList cLI18nDbPrice() {
        Map<Locale, CodeList> rows = new LinkedHashMap<Locale, CodeList>();
        rows.put(Locale.ENGLISH, cLDbPriceEn());
        rows.put(Locale.JAPANESE, cLDbPriceJa());
        SimpleI18nCodeList bean = new SimpleI18nCodeList();
        bean.setRowsByCodeList(rows);
        bean.setFallbackTo(Locale.ENGLISH);
        return bean;
    }

    /**
     * Configure {@link JdbcCodeList} bean.
     * @return Bean of configured {@link JdbcCodeList}
     */
    @Bean("CL_DB_PRICE_JA")
    public JdbcCodeList cLDbPriceJa() {
        JdbcCodeList jdbcCodeList = abstractJdbcCodeList();
        jdbcCodeList.setQuerySql(
                "SELECT code, label FROM t_price WHERE locale = 'ja' ORDER BY code");
        jdbcCodeList.setValueColumn("code");
        jdbcCodeList.setLabelColumn("label");
        return jdbcCodeList;
    }

    /**
     * Configure {@link JdbcCodeList} bean.
     * @return Bean of configured {@link JdbcCodeList}
     */
    @Bean("CL_DB_PRICE_EN")
    public JdbcCodeList cLDbPriceEn() {
        JdbcCodeList jdbcCodeList = abstractJdbcCodeList();
        jdbcCodeList.setQuerySql(
                "SELECT code, label FROM t_price WHERE locale = 'en' ORDER BY code");
        jdbcCodeList.setValueColumn("code");
        jdbcCodeList.setLabelColumn("label");
        return jdbcCodeList;
    }

    /**
     * Configure {@link SimpleI18nCodeList} bean.
     * @return Bean of configured {@link SimpleI18nCodeList}
     */
    @Bean("CL_I18N_PRICE_MAP_LOCALE")
    public SimpleI18nCodeList cLI18nPriceMapLocale() {
        Map<Locale, Map<String, String>> rowsMap = new LinkedHashMap<Locale, Map<String, String>>();
        Map<String, String> enMap = new LinkedHashMap<String, String>();
        enMap.put("0", "unlimited");
        enMap.put("10000", "Less than \\10,000");
        enMap.put("20000", "Less than \\20,000");
        enMap.put("30000", "Less than \\30,000");
        Map<String, String> jaMap = new LinkedHashMap<String, String>();
        jaMap.put("0", "上限なし");
        jaMap.put("10000", "10,000円以下");
        jaMap.put("20000", "20,000円以下");
        jaMap.put("30000", "30,000円以下");
        rowsMap.put(Locale.ENGLISH, enMap);
        rowsMap.put(Locale.JAPANESE, jaMap);
        SimpleI18nCodeList bean = new SimpleI18nCodeList();
        bean.setRows(rowsMap);
        bean.setFallbackTo(Locale.ENGLISH);
        return bean;
    }

    /**
     * Configure {@link SimpleI18nCodeList} bean.
     * @return Bean of configured {@link SimpleI18nCodeList}
     */
    @Bean("CL_I18N_PRICE_MAP_CODE")
    public SimpleI18nCodeList cLI18nPriceMapCode() {
        Map<String, Map<Locale, String>> columnsMap = new LinkedHashMap<String, Map<Locale, String>>();
        Map<Locale, String> key1Map = new LinkedHashMap<Locale, String>();
        key1Map.put(Locale.ENGLISH, "unlimited");
        key1Map.put(Locale.JAPANESE, "上限なし");
        Map<Locale, String> key2Map = new LinkedHashMap<Locale, String>();
        key2Map.put(Locale.ENGLISH, "Less than \\10,000");
        key2Map.put(Locale.JAPANESE, "10,000円以下");
        Map<Locale, String> key3Map = new LinkedHashMap<Locale, String>();
        key3Map.put(Locale.ENGLISH, "Less than \\20,000");
        key3Map.put(Locale.JAPANESE, "20,000円以下");
        Map<Locale, String> key4Map = new LinkedHashMap<Locale, String>();
        key4Map.put(Locale.ENGLISH, "Less than \\30,000");
        key4Map.put(Locale.JAPANESE, "30,000円以下");

        columnsMap.put("0", key1Map);
        columnsMap.put("10000", key2Map);
        columnsMap.put("20000", key3Map);
        columnsMap.put("30000", key4Map);
        SimpleI18nCodeList bean = new SimpleI18nCodeList();
        bean.setColumns(columnsMap);
        bean.setFallbackTo(Locale.ENGLISH);
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());
        taskRegistrar.addTriggerTask(() -> cLCronRefreshCodelist().refresh(),
                new CronTrigger(codelistRefreshTime));
    }

    /**
     * Configure {@link Executor} bean.
     * @return Bean of configured {@link Executor}
     */
    @Bean("taskScheduler")
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(10);
    }

    /**
     * Configure {@link JdbcCodeList} bean.
     * @return Bean of configured {@link JdbcCodeList}
     */
    @Bean("CL_CRON_REFRESH_CODELIST")
    public JdbcCodeList cLCronRefreshCodelist() {
        JdbcCodeList jdbcCodeList = abstractJdbcCodeList();
        jdbcCodeList.setQuerySql(
                "SELECT authority_id, authority_name FROM t_authority ORDER BY authority_id");
        jdbcCodeList.setValueColumn("authority_id");
        jdbcCodeList.setLabelColumn("authority_name");
        return jdbcCodeList;
    }

    /**
     * Configure {@link JdbcCodeList} bean.
     * @return Bean of configured {@link JdbcCodeList}
     */
    @Bean("CL_REFRESH_CODELIST")
    public JdbcCodeList cLRefreshCodeList() {
        JdbcCodeList jdbcCodeList = abstractJdbcCodeList();
        jdbcCodeList.setQuerySql(
                "SELECT authority_id, authority_name FROM t_authority ORDER BY authority_id");
        jdbcCodeList.setValueColumn("authority_id");
        jdbcCodeList.setLabelColumn("authority_name");
        return jdbcCodeList;
    }

    /**
     * Configure {@link DepYearCodeList} bean.
     * @param clockFactory Bean generated by SpringFunctionaltestJsr310Config#clockFactory
     * @return Bean of configured {@link DepYearCodeList}
     */
    @Bean("CL_YEAR_CODELIST")
    public DepYearCodeList cLYearCodelist(
            @Qualifier("clockFactory") ClockFactory clockFactory) {
        DepYearCodeList bean = new DepYearCodeList();
        bean.setClockFactory(clockFactory);
        return bean;
    }
}
