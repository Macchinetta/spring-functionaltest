/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
