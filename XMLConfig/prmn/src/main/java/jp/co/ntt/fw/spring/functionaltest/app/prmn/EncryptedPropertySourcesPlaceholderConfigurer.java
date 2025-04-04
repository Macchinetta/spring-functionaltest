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
package jp.co.ntt.fw.spring.functionaltest.app.prmn;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.util.StringValueResolver;

public class EncryptedPropertySourcesPlaceholderConfigurer
        extends PropertySourcesPlaceholderConfigurer { // (1)
    @Override
    protected void doProcessProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
            StringValueResolver valueResolver) { // (2)
        super.doProcessProperties(beanFactoryToProcess, new EncryptedValueResolver(valueResolver)); // (3)
    }
}
