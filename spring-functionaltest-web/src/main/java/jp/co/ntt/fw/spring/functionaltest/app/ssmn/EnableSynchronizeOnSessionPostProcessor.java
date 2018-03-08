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
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

public class EnableSynchronizeOnSessionPostProcessor implements
                                                     BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(
            EnableSynchronizeOnSessionPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean,
            String beanName) throws BeansException {
        // NO-OP
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean,
            String beanName) throws BeansException {
        if (bean instanceof RequestMappingHandlerAdapter) {
            RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;
            logger.info("enable synchronizeOnSession => {}", adapter);
            adapter.setSynchronizeOnSession(true);
        }
        return bean;
    }

}
