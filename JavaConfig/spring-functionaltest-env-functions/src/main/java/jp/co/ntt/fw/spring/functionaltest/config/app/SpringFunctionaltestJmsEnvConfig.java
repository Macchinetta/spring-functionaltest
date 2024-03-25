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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.core.config.ConfigurationUtils;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.impl.ActiveMQServerImpl;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.apache.activemq.artemis.spi.core.security.ActiveMQJAASSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jndi.JndiObjectFactoryBean;

import jakarta.jms.JMSException;
import jp.co.ntt.fw.spring.functionaltest.app.jmss.ExtendedActiveMQConnectionFactory;

/**
 * Define settings for your JMS environment.
 */
@Configuration
public class SpringFunctionaltestJmsEnvConfig {

    /**
     * jms.mq.host property.
     */
    @Value("${jms.mq.host}")
    private String mqHost;

    /**
     * jms.mq.port property.
     */
    @Value("${jms.mq.port}")
    private String mqPort;

    /**
     * jndi.prefix property.
     */
    @Value("${jndi.prefix}")
    private String jndiPrefix;

    /**
     * Configure {@link CachingConnectionFactory} bean.
     * @return Bean of configured {@link CachingConnectionFactory}
     * @throws JMSException JMS exception that occurs when creating a connection.
     */
    @Bean("cachingConnectionFactory")
    public CachingConnectionFactory cachingConnectionFactory() throws JMSException {
        CachingConnectionFactory bean = new CachingConnectionFactory();
        bean.setTargetConnectionFactory(connectionFactory());
        bean.setSessionCacheSize(3);
        bean.setCacheConsumers(false);
        return bean;
    }

    /**
     * Configure {@link ActiveMQConnectionFactory} bean.
     * @return Bean of configured {@link ActiveMQConnectionFactory}
     * @throws JMSException JMS exception that occurs when creating a connection.
     */
    @Bean("dynamicConnectionFactory")
    public ActiveMQConnectionFactory dynamicConnectionFactory() throws JMSException {
        ActiveMQConnectionFactory bean = new ExtendedActiveMQConnectionFactory();
        bean.setBrokerURL("tcp://" + mqHost + ":" + mqPort
                + "?jms.blobTransferPolicy.uploadUrl=file:/tmp");
        bean.setDeserializationWhiteList(
                "jp.co.ntt.fw.spring.functionaltest.domain.model,java.time");
        return bean;
    }

    /**
     * Configure {@link ActiveMQServer} bean.
     * @return Bean of configured {@link ActiveMQServer}
     */
    @Profile({ "default", "nonMqServer" })
    @Bean(name = "activeMQServer", initMethod = "start", destroyMethod = "stop")
    public ActiveMQServer activeMQServer() {
        return new ActiveMQServerImpl(config(), activeMQJAASSecurityManager());
    }

    /**
     * Configure {@link ConfigurationImpl} bean.
     * @return Bean of configured {@link ConfigurationImpl}
     */
    @Profile({ "default", "nonMqServer" })
    @Bean(name = "config")
    public ConfigurationImpl config() {
        ConfigurationImpl bean = new ConfigurationImpl();
        bean.setAcceptorConfigurations(acceptorConfigurations());
        bean.setSecurityEnabled(false);
        return bean;
    }

    /**
     * Configure {@link ActiveMQQueue} bean.
     * @return Bean of configured {@link ActiveMQQueue}
     */
    @Profile({ "default", "nonMqServer" })
    @Bean(name = "testQueue0403003B")
    public ActiveMQQueue testQueue0403003B() {
        return new ActiveMQQueue("TestQueue0403003B");
    }

    /**
     * Configure {@link TransportConfiguration} bean.
     * @return Bean of configured {@link TransportConfiguration}
     */
    @Profile({ "default", "nonMqServer" })
    @Bean("acceptorConfigurations")
    public Set<TransportConfiguration> acceptorConfigurations() {
        List<TransportConfiguration> configurations = ConfigurationUtils
                .parseAcceptorURI("tcp", "tcp://" + mqHost + ":" + mqPort);
        return new HashSet<TransportConfiguration>(configurations);
    }

    /**
     * Configure {@link ActiveMQConnectionFactory} bean.
     * @return Bean of configured {@link ActiveMQConnectionFactory}
     * @throws JMSException JMS exception that occurs when creating a connection.
     */
    @Profile({ "default", "nonMqServer" })
    @Bean("connectionFactory")
    public ActiveMQConnectionFactory connectionFactory() throws JMSException {
        ActiveMQConnectionFactory bean = new ExtendedActiveMQConnectionFactory();
        bean.setBrokerURL("tcp://" + mqHost + ":" + mqPort
                + "?jms.blobTransferPolicy.uploadUrl=file:/tmp");
        bean.setDeserializationWhiteList(
                "jp.co.ntt.fw.spring.functionaltest.domain.model,java.time");
        return bean;
    }

    /**
     * Configure {@link ActiveMQJAASSecurityManager} bean.
     * @return Bean of configured {@link ActiveMQJAASSecurityManager}
     */
    @Profile({ "default", "nonMqServer" })
    @Bean(name = "activeMQJAASSecurityManager")
    public ActiveMQJAASSecurityManager activeMQJAASSecurityManager() {
        return new ActiveMQJAASSecurityManager();
    }

    /**
     * Configure {@link ActiveMQConnectionFactory} bean.
     * @return Bean of configured {@link ActiveMQConnectionFactory}
     * @throws NamingException JNDI Lookup failed.
     */
    @Profile({ "mqServer" })
    @Bean(name = "connectionFactory")
    public ActiveMQConnectionFactory connectionFactoryMqServer() throws NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName(jndiPrefix + "jms/NormalConnectionFactory");
        bean.setResourceRef(true);
        bean.afterPropertiesSet();
        return (ActiveMQConnectionFactory) bean.getObject();
    }

    /**
     * Configure {@link ActiveMQQueue} bean.
     * @return Bean of configured {@link ActiveMQQueue}
     * @throws NamingException JNDI Lookup failed.
     */
    @Profile({ "mqServer" })
    @Bean(name = "testQueue0403003B")
    public ActiveMQQueue testQueue0403003BMqServer() throws NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName(jndiPrefix + "jms/queue/TestQueue0403003B");
        bean.setResourceRef(true);
        bean.afterPropertiesSet();
        return (ActiveMQQueue) bean.getObject();
    }

}
