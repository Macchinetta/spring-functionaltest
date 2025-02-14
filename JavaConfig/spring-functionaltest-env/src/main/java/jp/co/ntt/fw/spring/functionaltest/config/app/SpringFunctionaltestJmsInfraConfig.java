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
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Defines the infrastructure layer of the JMS environment.
 */
@SuppressWarnings("deprecation")
@Configuration
@Import({SpringFunctionaltestJmsEnvConfig.class})
public class SpringFunctionaltestJmsInfraConfig {

    /**
     * jms.mq.priority property.
     */
    @Value("${jms.mq.priority}")
    private int priority;

    /**
     * jms.mq.deliveryMode property.
     */
    @Value("${jms.mq.deliveryMode}")
    private int deliveryMode;

    /**
     * Configure {@link JmsTransactionManager} bean.
     * @param cachingConnectionFactory Bean defined by
     *        SpringFunctionaltestJmsEnvConfig#cachingConnectionFactory()
     * @return Bean of configured {@link JmsTransactionManager}
     */
    @Bean("sendJmsTransactionManager")
    public JmsTransactionManager sendJmsTransactionManager(
            @Qualifier("cachingConnectionFactory") CachingConnectionFactory cachingConnectionFactory) {
        JmsTransactionManager bean = new JmsTransactionManager();
        bean.setConnectionFactory(cachingConnectionFactory);
        return bean;
    }

    /**
     * Configure {@link JmsTransactionManager} bean.
     * @param cachingConnectionFactory Bean defined by
     *        SpringFunctionaltestJmsEnvConfig#cachingConnectionFactory()
     * @return Bean of configured {@link JmsTransactionManager}
     */
    @Bean("asyncReceiveJmsTransactionManager")
    public JmsTransactionManager asyncReceiveJmsTransactionManager(
            @Qualifier("cachingConnectionFactory") CachingConnectionFactory cachingConnectionFactory) {
        JmsTransactionManager bean = new JmsTransactionManager();
        bean.setConnectionFactory(cachingConnectionFactory);
        return bean;
    }

    /**
     * Configure {@link JmsTransactionManager} bean.
     * @param dynamicConnectionFactory Bean defined by
     *        SpringFunctionaltestJmsEnvConfig#dynamicConnectionFactory()
     * @return Bean of configured {@link JmsTransactionManager}
     */
    @Bean("jmsTopicTransactionManager")
    public JmsTransactionManager jmsTopicTransactionManager(
            @Qualifier("dynamicConnectionFactory") ActiveMQConnectionFactory dynamicConnectionFactory) {
        JmsTransactionManager bean = new JmsTransactionManager();
        bean.setConnectionFactory(dynamicConnectionFactory);
        return bean;
    }

    /**
     * Configure {@link ChainedTransactionManager} bean.
     * @param sendJmsTransactionManager Bean defined by #sendJmsTransactionManager()
     * @param transactionManager Bean defined by SpringFunctionaltestEnvConfig#transactionManager()
     * @return Bean of configured {@link ChainedTransactionManager}
     */
    // ChainedTransactionManagerは非推奨だが代替が無いためそのまま使用する。
    @Bean("sendChainedTransactionManager")
    public ChainedTransactionManager sendChainedTransactionManager(
            @Qualifier("sendJmsTransactionManager") JmsTransactionManager sendJmsTransactionManager,
            @Qualifier("transactionManager") TransactionManager transactionManager) {
        ChainedTransactionManager bean = new ChainedTransactionManager(sendJmsTransactionManager,
                (DataSourceTransactionManager) transactionManager);
        return bean;
    }

    /**
     * Configure {@link JmsTransactionManager} bean.
     * @param dynamicConnectionFactory Bean defined by
     *        SpringFunctionaltestJmsEnvConfig#dynamicConnectionFactory()
     * @return Bean of configured {@link JmsTransactionManager}
     */
    @Bean("dynamicJmsTemplate")
    public JmsTemplate dynamicJmsTemplate(
            @Qualifier("dynamicConnectionFactory") ActiveMQConnectionFactory dynamicConnectionFactory) {
        JmsTemplate bean = new JmsTemplate();
        bean.setConnectionFactory(dynamicConnectionFactory);
        bean.setPubSubDomain(false);
        bean.setReceiveTimeout(20000);
        return bean;
    }

    /**
     * Configure {@link JmsMessagingTemplate} bean.
     * @param dynamicConnectionFactory Bean defined by #dynamicJmsTemplate()
     * @return Bean of configured {@link JmsMessagingTemplate}
     */
    @Bean("dynamicJmsMessagingTemplate")
    public JmsMessagingTemplate dynamicJmsMessagingTemplate(
            @Qualifier("dynamicJmsTemplate") JmsTemplate dynamicJmsTemplate) {
        JmsMessagingTemplate bean = new JmsMessagingTemplate();
        bean.setJmsTemplate(dynamicJmsTemplate);
        return bean;
    }

    /**
     * Configure {@link JmsTemplate} bean.
     * @param connectionFactory Bean defined by SpringFunctionaltestJmsEnvConfig#connectionFactory()
     * @param destinationResolver Bean defined by
     *        SpringFunctionaltestJmsEnvConfig#destinationResolver()
     * @return Bean of configured {@link JmsTemplate}
     */
    @Bean("jndiJmsTemplate")
    public JmsTemplate jndiJmsTemplate(
            @Qualifier("connectionFactory") ActiveMQConnectionFactory connectionFactory,
            @Qualifier("destinationResolver") DestinationResolver destinationResolver) {
        JmsTemplate bean = new JmsTemplate();
        bean.setConnectionFactory(connectionFactory);
        bean.setReceiveTimeout(20000);
        bean.setDestinationResolver(destinationResolver);
        return bean;
    }

    /**
     * Configure {@link JmsMessagingTemplate} bean.
     * @param jndiJmsTemplate Bean defined by #jndiJmsTemplate()
     * @return Bean of configured {@link JmsMessagingTemplate}
     */
    @Bean("jndiJmsMessagingTemplate")
    public JmsMessagingTemplate jndiJmsMessagingTemplate(
            @Qualifier("jndiJmsTemplate") JmsTemplate jndiJmsTemplate) {
        JmsMessagingTemplate bean = new JmsMessagingTemplate();
        bean.setJmsTemplate(jndiJmsTemplate);
        return bean;
    }

    /**
     * Configure {@link JmsTemplate} bean.
     * @param cachingConnectionFactory Bean defined by
     *        SpringFunctionaltestJmsEnvConfig#cachingConnectionFactory()
     * @param destinationResolver Bean defined by
     *        SpringFunctionaltestJmsEnvConfig#destinationResolver()
     * @return Bean of configured {@link JmsTemplate}
     */
    @Bean("jndiConCacheJmsTemplate")
    public JmsTemplate jndiConCacheJmsTemplate(
            @Qualifier("cachingConnectionFactory") CachingConnectionFactory cachingConnectionFactory,
            @Qualifier("destinationResolver") DestinationResolver destinationResolver) {
        JmsTemplate bean = new JmsTemplate();
        bean.setConnectionFactory(cachingConnectionFactory);
        bean.setReceiveTimeout(20000);
        bean.setDestinationResolver(destinationResolver);
        return bean;
    }

    /**
     * Configure {@link JmsMessagingTemplate} bean.
     * @param jndiConCacheJmsTemplate Bean defined by #jndiConCacheJmsTemplate()
     * @return Bean of configured {@link JmsMessagingTemplate}
     */
    @Bean("jndiConCacheJmsMessagingTemplate")
    public JmsMessagingTemplate jndiConCacheJmsMessagingTemplate(
            @Qualifier("jndiConCacheJmsTemplate") JmsTemplate jndiConCacheJmsTemplate) {
        JmsMessagingTemplate bean = new JmsMessagingTemplate();
        bean.setJmsTemplate(jndiConCacheJmsTemplate);
        return bean;
    }

    /**
     * Configure {@link JmsTemplate} bean.
     * @param cachingConnectionFactory Bean defined by
     *        SpringFunctionaltestJmsEnvConfig#cachingConnectionFactory()
     * @param destinationResolver Bean defined by
     *        SpringFunctionaltestJmsEnvConfig#destinationResolver()
     * @return Bean of configured {@link JmsTemplate}
     */
    @Bean("topicJmsTemplate")
    public JmsTemplate topicJmsTemplate(
            @Qualifier("cachingConnectionFactory") CachingConnectionFactory cachingConnectionFactory,
            @Qualifier("destinationResolver") DestinationResolver destinationResolver) {
        JmsTemplate bean = new JmsTemplate();
        bean.setConnectionFactory(cachingConnectionFactory);
        bean.setPubSubDomain(true);
        bean.setReceiveTimeout(20000);
        bean.setDestinationResolver(destinationResolver);
        return bean;
    }

    /**
     * Configure {@link JmsMessagingTemplate} bean.
     * @param topicJmsTemplate Bean defined by #topicJmsTemplate()
     * @return Bean of configured {@link JmsMessagingTemplate}
     */
    @Bean("topicJmsMessagingTemplate")
    public JmsMessagingTemplate topicJmsMessagingTemplate(
            @Qualifier("topicJmsTemplate") JmsTemplate topicJmsTemplate) {
        JmsMessagingTemplate bean = new JmsMessagingTemplate();
        bean.setJmsTemplate(topicJmsTemplate);
        return bean;
    }

    /**
     * Configure {@link JmsTemplate} bean.
     * @param cachingConnectionFactory Bean defined by
     *        SpringFunctionaltestJmsEnvConfig#cachingConnectionFactory()
     * @param destinationResolver Bean defined by
     *        SpringFunctionaltestJmsEnvConfig#destinationResolver()
     * @return Bean of configured {@link JmsTemplate}
     */
    @Bean("selectedJmsTemplate")
    public JmsTemplate selectedJmsTemplate(
            @Qualifier("cachingConnectionFactory") CachingConnectionFactory cachingConnectionFactory,
            @Qualifier("destinationResolver") DestinationResolver destinationResolver) {
        JmsTemplate bean = new JmsTemplate();
        bean.setConnectionFactory(cachingConnectionFactory);
        bean.setPubSubDomain(false);
        bean.setReceiveTimeout(20000);
        bean.setExplicitQosEnabled(true);
        bean.setPriority(priority);
        bean.setDeliveryMode(deliveryMode);
        bean.setTimeToLive(4000);
        bean.setDestinationResolver(destinationResolver);
        return bean;
    }

    /**
     * Configure {@link JmsMessagingTemplate} bean.
     * @param selectedJmsTemplate Bean defined by #selectedJmsTemplate()
     * @return Bean of configured {@link JmsMessagingTemplate}
     */
    @Bean("selectedMessagingJmsTemplate")
    public JmsMessagingTemplate selectedMessagingJmsTemplate(
            @Qualifier("selectedJmsTemplate") JmsTemplate selectedJmsTemplate) {
        JmsMessagingTemplate bean = new JmsMessagingTemplate();
        bean.setJmsTemplate(selectedJmsTemplate);
        return bean;
    }

    /**
     * Configure {@link JmsTemplate} bean.
     * @param cachingConnectionFactory Bean defined by
     *        SpringFunctionaltestJmsEnvConfig#cachingConnectionFactory()
     * @return Bean of configured {@link JmsTemplate}
     */
    @Bean("queuePurgeJmsTemplate")
    public JmsTemplate queuePurgeJmsTemplate(
            @Qualifier("cachingConnectionFactory") CachingConnectionFactory cachingConnectionFactory) {
        JmsTemplate bean = new JmsTemplate();
        bean.setConnectionFactory(cachingConnectionFactory);
        bean.setPubSubDomain(false);
        bean.setReceiveTimeout(10);
        return bean;
    }

    /**
     * Configure {@link JmsTemplate} bean.
     * @param cachingConnectionFactory Bean defined by
     *        SpringFunctionaltestJmsEnvConfig#cachingConnectionFactory()
     * @return Bean of configured {@link JmsTemplate}
     */
    @Bean("topicPurgeJmsTemplate")
    public JmsTemplate topicPurgeJmsTemplate(
            @Qualifier("cachingConnectionFactory") CachingConnectionFactory cachingConnectionFactory) {
        JmsTemplate bean = new JmsTemplate();
        bean.setConnectionFactory(cachingConnectionFactory);
        bean.setPubSubDomain(true);
        bean.setReceiveTimeout(10);
        return bean;
    }

    /**
     * Configure {@link TransactionTemplate} bean.
     * @param sendJmsTransactionManager Bean defined by #sendJmsTransactionManager()
     * @return Bean of configured {@link TransactionTemplate}
     */
    @Bean("transactionTemplate")
    public TransactionTemplate transactionTemplate(
            @Qualifier("sendJmsTransactionManager") JmsTransactionManager sendJmsTransactionManager) {
        TransactionTemplate bean = new TransactionTemplate();
        bean.setTransactionManager(sendJmsTransactionManager);
        bean.setPropagationBehaviorName("PROPAGATION_REQUIRED");
        return bean;
    }

    /**
     * Configure {@link TransactionTemplate} bean.
     * @param sendChainedTransactionManager Bean defined by #sendChainedTransactionManager()
     * @return Bean of configured {@link TransactionTemplate}
     */
    @Bean("chainedTransactionTemplate")
    public TransactionTemplate chainedTransactionTemplate(
            @Qualifier("sendChainedTransactionManager") ChainedTransactionManager sendChainedTransactionManager) {
        TransactionTemplate bean = new TransactionTemplate();
        bean.setTransactionManager(sendChainedTransactionManager);
        bean.setPropagationBehaviorName("PROPAGATION_REQUIRED");
        return bean;
    }
}
