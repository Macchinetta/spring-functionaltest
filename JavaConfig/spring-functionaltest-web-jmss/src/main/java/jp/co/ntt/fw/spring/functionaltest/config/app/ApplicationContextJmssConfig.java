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

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.destination.DestinationResolver;

import jp.co.ntt.fw.spring.functionaltest.app.jmss.JmsErrorHandler;

/**
 * Application context.
 */
@Configuration
@Import({ SpringFunctionaltestJmsInfraConfig.class })
public class ApplicationContextJmssConfig {

    /**
     * jms.listener.chace.level property.
     */
    @Value("${jms.listener.chace.level}")
    private Integer chaceLevel;

    /**
     * Configure {@link JmsErrorHandler} bean.
     * @return Bean of configured {@link JmsErrorHandler}
     */
    @Bean
    public JmsErrorHandler jmsErrorHandler() {
        return new JmsErrorHandler();
    }

    /**
     * Configure {@link DefaultJmsListenerContainerFactory} bean.
     * @param dynamicConnectionFactory Bean defined by SpringFunctionaltestJmsEnvConfig#dynamicConnectionFactory
     * @param destinationResolver Bean defined by SpringFunctionaltestJmsEnvConfig#destinationResolver
     * @param asyncReceiveJmsTransactionManager Bean defined by
     *            SpringFunctionaltestJmsInfraConfig#asyncReceiveJmsTransactionManager
     * @return Bean of configured {@link DefaultJmsListenerContainerFactory}
     */
    @Bean("topicContainerFactory")
    public DefaultJmsListenerContainerFactory topicContainerFactory(
            @Qualifier("dynamicConnectionFactory") ActiveMQConnectionFactory dynamicConnectionFactory,
            DestinationResolver destinationResolver,
            @Qualifier("asyncReceiveJmsTransactionManager") JmsTransactionManager asyncReceiveJmsTransactionManager) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(dynamicConnectionFactory);
        bean.setErrorHandler(jmsErrorHandler());
        bean.setDestinationResolver(destinationResolver);
        bean.setTransactionManager(asyncReceiveJmsTransactionManager);
        bean.setPubSubDomain(true);
        bean.setSessionTransacted(true);
        bean.setCacheLevel(this.chaceLevel);
        // bean.setCacheLevel(DefaultMessageListenerContainer.CACHE_CONSUMER);

        return bean;
    }

    /**
     * Configure {@link DefaultJmsListenerContainerFactory} bean.
     * @param dynamicConnectionFactory Bean defined by SpringFunctionaltestJmsEnvConfig#dynamicConnectionFactory
     * @return Bean of configured {@link DefaultJmsListenerContainerFactory}
     */
    @Bean("conCacheDynamicNoTranContainerFactory")
    public DefaultJmsListenerContainerFactory conCacheDynamicNoTranContainerFactory(
            @Qualifier("dynamicConnectionFactory") ActiveMQConnectionFactory dynamicConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(dynamicConnectionFactory);
        bean.setErrorHandler(jmsErrorHandler());

        return bean;
    }

    /**
     * Configure {@link DefaultJmsListenerContainerFactory} bean.
     * @param dynamicConnectionFactory Bean defined by SpringFunctionaltestJmsEnvConfig#dynamicConnectionFactory
     * @param destinationResolver Bean defined by SpringFunctionaltestJmsEnvConfig#destinationResolver
     * @return Bean of configured {@link DefaultJmsListenerContainerFactory}
     */
    @Bean("jmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
            @Qualifier("dynamicConnectionFactory") ActiveMQConnectionFactory dynamicConnectionFactory,
            DestinationResolver destinationResolver) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(dynamicConnectionFactory);
        bean.setErrorHandler(jmsErrorHandler());
        bean.setDestinationResolver(destinationResolver);
        bean.setSessionTransacted(true);

        return bean;
    }

    /**
     * Configure {@link DefaultJmsListenerContainerFactory} bean.
     * @param dynamicConnectionFactory Bean defined by SpringFunctionaltestJmsEnvConfig#dynamicConnectionFactory
     * @param destinationResolver Bean defined by SpringFunctionaltestJmsEnvConfig#destinationResolver
     * @return Bean of configured {@link DefaultJmsListenerContainerFactory}
     */
    @Bean("conCacheNoTranContainerFactory")
    public DefaultJmsListenerContainerFactory conCacheNoTranContainerFactory(
            @Qualifier("dynamicConnectionFactory") ActiveMQConnectionFactory dynamicConnectionFactory,
            DestinationResolver destinationResolver) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(dynamicConnectionFactory);
        bean.setErrorHandler(jmsErrorHandler());
        bean.setDestinationResolver(destinationResolver);
        return bean;
    }

    /**
     * Configure {@link DefaultJmsListenerContainerFactory} bean.
     * @param dynamicConnectionFactory Bean defined by SpringFunctionaltestJmsEnvConfig#dynamicConnectionFactory
     * @param destinationResolver Bean defined by SpringFunctionaltestJmsEnvConfig#destinationResolver
     * @return Bean of configured {@link DefaultJmsListenerContainerFactory}
     */
    @Bean("conCacheTryCatchContainerFactory")
    public DefaultJmsListenerContainerFactory conCacheTryCatchContainerFactory(
            @Qualifier("dynamicConnectionFactory") ActiveMQConnectionFactory dynamicConnectionFactory,
            DestinationResolver destinationResolver) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(dynamicConnectionFactory);
        bean.setDestinationResolver(destinationResolver);

        return bean;
    }

    /**
     * Configure {@link DefaultJmsListenerContainerFactory} bean.
     * @param dynamicConnectionFactory Bean defined by SpringFunctionaltestJmsEnvConfig#dynamicConnectionFactory
     * @param destinationResolver Bean defined by SpringFunctionaltestJmsEnvConfig#destinationResolver
     * @return Bean of configured {@link DefaultJmsListenerContainerFactory}
     */
    @Bean("conCacheConcurrentSingleContainerFactory")
    public DefaultJmsListenerContainerFactory conCacheConcurrentSingleContainerFactory(
            @Qualifier("dynamicConnectionFactory") ActiveMQConnectionFactory dynamicConnectionFactory,
            DestinationResolver destinationResolver) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(dynamicConnectionFactory);
        bean.setConcurrency("1");
        bean.setErrorHandler(jmsErrorHandler());
        bean.setDestinationResolver(destinationResolver);

        return bean;
    }

    /**
     * Configure {@link DefaultJmsListenerContainerFactory} bean.
     * @param dynamicConnectionFactory Bean defined by SpringFunctionaltestJmsEnvConfig#dynamicConnectionFactory
     * @param destinationResolver Bean defined by SpringFunctionaltestJmsEnvConfig#destinationResolver
     * @return Bean of configured {@link DefaultJmsListenerContainerFactory}
     */
    @Bean("conCacheConcurrentMultipleContainerFactory")
    public DefaultJmsListenerContainerFactory conCacheConcurrentMultipleContainerFactory(
            @Qualifier("dynamicConnectionFactory") ActiveMQConnectionFactory dynamicConnectionFactory,
            DestinationResolver destinationResolver) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(dynamicConnectionFactory);
        bean.setConcurrency("5-10");
        bean.setErrorHandler(jmsErrorHandler());
        bean.setDestinationResolver(destinationResolver);
        bean.setCacheLevel(DefaultMessageListenerContainer.CACHE_NONE);

        return bean;
    }

    /**
     * Configure {@link DefaultJmsListenerContainerFactory} bean.
     * @param dynamicConnectionFactory Bean defined by SpringFunctionaltestJmsEnvConfig#dynamicConnectionFactory
     * @param destinationResolver Bean defined by SpringFunctionaltestJmsEnvConfig#destinationResolver
     * @return Bean of configured {@link DefaultJmsListenerContainerFactory}
     */
    @Bean("conCacheChainedTranContainerFactory")
    public DefaultJmsListenerContainerFactory conCacheChainedTranContainerFactory(
            @Qualifier("dynamicConnectionFactory") ActiveMQConnectionFactory dynamicConnectionFactory,
            DestinationResolver destinationResolver) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(dynamicConnectionFactory);
        bean.setErrorHandler(jmsErrorHandler());
        bean.setDestinationResolver(destinationResolver);
        bean.setSessionTransacted(true);

        return bean;
    }

}
