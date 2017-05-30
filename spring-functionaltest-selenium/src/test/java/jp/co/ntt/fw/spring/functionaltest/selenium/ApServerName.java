/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium;

/**
 * APサーバを識別するための定数クラス。
 * <p>
 * application-env.propertiesにAPサーバ名が設定されていない場合は<br/>
 * UNKONWNが設定される。
 * </p>
 */
public enum ApServerName {
    UNKNOWN, INTERSTAGE, JBOSS, TOMCAT, WEBLOGIC, WEBOTX, WEBSPHERELP, WEBSPHERETR
}
