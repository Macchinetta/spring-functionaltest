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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.icegreen.greenmail.spring.GreenMailBean;

import jakarta.mail.Session;

/**
 * Define email preferences.
 */
@Configuration
public class SpringFunctionaltestMailConfig {

    /**
     * mail.noauth.smtp.host property.
     */
    @Value("${mail.noauth.smtp.host}")
    private String noAuthHost;

    /**
     * mail.noauth.smtp.port property.
     */
    @Value("${mail.noauth.smtp.port}")
    private int noAuthPort;

    /**
     * mail.noauth.smtp.host property.
     */
    @Value("${mail.auth.smtp.host}")
    private String authHost;

    /**
     * mail.noauth.smtp.port property.
     */
    @Value("${mail.auth.smtp.port}")
    private int authPort;

    /**
     * mail.noauth.smtp.port property.
     */
    @Value("${mail.auth.smtp.user}")
    private String authUsername;

    /**
     * mail.noauth.smtp.port property.
     */
    @Value("${mail.auth.smtp.password}")
    private String authPassword;

    /**
     * mail.auth.smtp.password.invalid property.
     */
    @Value("${mail.auth.smtp.password.invalid}")
    private String authPasswordInvalid;

    /**
     * mail.noauth.smtp.port.invalid property.
     */
    @Value("${mail.noauth.smtp.port.invalid}")
    private int noAuthPortInvalid;

    /**
     * Configure {@link JavaMailSender} bean.
     * @return Bean of configured {@link JavaMailSender}
     */
    @Bean("mailSenderSession")
    public JavaMailSender mailSenderSession() {
        JavaMailSenderImpl bean = new JavaMailSenderImpl();
        bean.setHost(noAuthHost);
        bean.setPort(noAuthPort);
        return bean;
    }

    /**
     * Configure {@link JavaMailSender} bean.
     * @return Bean of configured {@link JavaMailSender}
     */
    @Bean("mailSenderNoAuth")
    public JavaMailSender mailSenderNoAuth() {
        JavaMailSenderImpl bean = new JavaMailSenderImpl();
        bean.setHost(noAuthHost);
        bean.setPort(noAuthPort);
        return bean;
    }

    /**
     * Configure {@link JavaMailSender} bean.
     * @return Bean of configured {@link JavaMailSender}
     */
    @Bean("mailSenderAuth")
    public JavaMailSender mailSenderAuth() {
        JavaMailSenderImpl bean = new JavaMailSenderImpl();
        bean.setHost(authHost);
        bean.setPort(authPort);
        bean.setUsername(authUsername);
        bean.setPassword(authPassword);

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.auth", "true");
        bean.setJavaMailProperties(prop);
        return bean;
    }

    /**
     * Configure {@link JavaMailSender} bean.
     * @return Bean of configured {@link JavaMailSender}
     */
    @Bean("mailSenderAuthError")
    public JavaMailSender mailSenderAuthError() {
        JavaMailSenderImpl bean = new JavaMailSenderImpl();
        bean.setHost(authHost);
        bean.setPort(authPort);
        bean.setUsername(authUsername);
        bean.setPassword(authPasswordInvalid);

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.auth", "true");
        bean.setJavaMailProperties(prop);
        return bean;
    }

    /**
     * Configure {@link JavaMailSender} bean.
     * @return Bean of configured {@link JavaMailSender}
     */
    @Bean("mailSenderConnError")
    public JavaMailSender mailSenderConnError() {
        JavaMailSenderImpl bean = new JavaMailSenderImpl();
        bean.setHost(noAuthHost);
        bean.setPort(noAuthPortInvalid);
        return bean;
    }

    /**
     * Configure {@link Session} bean.
     * @return Bean of configured {@link Session}
     * @throws NamingException JNDI Lookup failed.
     */
    @Bean(name = "mailServerContainerPrefixSession")
    public Session mailServerContainerPrefixSession() throws NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("mail/session");
        bean.setResourceRef(true);
        bean.afterPropertiesSet();
        return (Session) bean.getObject();
    }

    /**
     * Configure {@link JavaMailSender} bean.
     * @return Bean of configured {@link JavaMailSender}
     * @throws NamingException JNDI Lookup failed.
     */
    @Bean("mailServerContainerSender")
    public JavaMailSender mailServerContainerSender() throws NamingException {
        JavaMailSenderImpl bean = new JavaMailSenderImpl();
        bean.setSession(mailServerContainerPrefixSession());
        return bean;
    }

    /**
     * Configure {@link Session} bean.
     * @return Bean of configured {@link Session}
     * @throws NamingException JNDI Lookup failed.
     */
    @Bean(name = "mailServerContainerOriginSession")
    public Session mailServerContainerOriginSession() throws NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:comp/env/mail/session");
        bean.afterPropertiesSet();
        return (Session) bean.getObject();
    }

    /**
     * Configure {@link JavaMailSender} bean.
     * @return Bean of configured {@link JavaMailSender}
     * @throws NamingException JNDI Lookup failed.
     */
    @Bean("mailServerOriginSender")
    public JavaMailSender mailServerOriginSender() throws NamingException {
        JavaMailSenderImpl bean = new JavaMailSenderImpl();
        bean.setSession(mailServerContainerOriginSession());
        return bean;
    }

    /**
     * Configure {@link GreenMailBean} bean.
     * @return Bean of configured {@link GreenMailBean}
     */
    @Profile({"default", "nonMailServer"})
    @Bean("greenMailBean")
    public GreenMailBean greenMailBean() {
        GreenMailBean bean = new GreenMailBean();
        bean.setAutostart(true);
        bean.setSmtpProtocol(true);
        bean.setPop3Protocol(true);
        bean.setPortOffset(7000);
        bean.setHostname("127.0.0.1");

        List<String> users = new ArrayList<String>();
        users.add("test:Ntt01@noauth-example.com");
        users.add("hoge:Ntt01@noauth-example.com");
        users.add("hoge2:Ntt01@noauth-example.com");
        users.add("info:Ntt01@noauth-example.com");
        users.add("foo:Ntt01@noauth-example.com");
        users.add("foo2:Ntt01@noauth-example.com");
        users.add("bar:Ntt01@noauth-example.com");
        users.add("bar2:Ntt01@noauth-example.com");
        users.add("reply:Ntt01@noauth-example.com");
        bean.setUsers(users);
        return bean;
    }
}
