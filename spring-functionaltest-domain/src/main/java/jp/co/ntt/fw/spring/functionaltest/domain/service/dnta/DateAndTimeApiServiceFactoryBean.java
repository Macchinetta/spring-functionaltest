/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
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
