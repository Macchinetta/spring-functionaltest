/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.prmn;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.util.StringValueResolver;

public class EncryptedPropertySourcesPlaceholderConfigurer extends
                                                          PropertySourcesPlaceholderConfigurer { // (1)
    @Override
    protected void doProcessProperties(
            ConfigurableListableBeanFactory beanFactoryToProcess,
            StringValueResolver valueResolver) { // (2)
        super.doProcessProperties(beanFactoryToProcess,
                new EncryptedValueResolver(valueResolver)); // (3)
    }
}
