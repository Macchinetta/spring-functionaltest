/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 各プロファイルのapplication-env.propertiesで定義された値をenvironmentに登録するクラス。
 * <p>
 * JSPを通じてWebDriverにAPサーバ情報を渡し、APサーバ毎にアサートを変えられるようにする。
 * </p>
 */
@Configuration
@PropertySource("classpath:/META-INF/spring/application-env.properties")
public class PropertySourceConfig {
}
