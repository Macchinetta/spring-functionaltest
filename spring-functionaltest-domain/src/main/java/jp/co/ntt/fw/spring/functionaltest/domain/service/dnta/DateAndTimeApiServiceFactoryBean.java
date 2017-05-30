/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dnta;

import javax.inject.Inject;

import org.terasoluna.gfw.common.date.DefaultClassicDateFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

//JDK7向けのテスト実行時に、インスタンス生成の際のNoClassDefFoundErrorを回避するためにFactoryBeanを作成している
@Component
public class DateAndTimeApiServiceFactoryBean implements
                                             FactoryBean<DateAndTimeApiService> {

    @Inject
    DefaultClassicDateFactory dateFactory;

    @Override
    public DateAndTimeApiService getObject() throws Exception {
        DateAndTimeApiServiceImpl instance = new DateAndTimeApiServiceImpl();
        instance.dateFactory = dateFactory;
        return instance;
    }

    @Override
    public Class<?> getObjectType() {
        return DateAndTimeApiService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
