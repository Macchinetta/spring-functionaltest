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
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.util.ErrorHandler;
import org.terasoluna.gfw.common.exception.SystemException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.ReceivedEvent;

public class JmsErrorHandler implements ErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(
            JmsErrorHandler.class);

    @Inject
    ApplicationEventPublisher eventPublisher;

    @Override
    public void handleError(Throwable t) {

        if (t.getCause() instanceof MethodArgumentNotValidException) {
            // Validation Error

            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) t
                    .getCause();

            log.error("Validation Error!");

        } else if (t.getCause() instanceof SystemException) {
            // SystemException

            log.error("SystemException Error!");
            // messageに設定したTodoIdを引数にして受信完了を待っているスレッドの待機を解除するためにイベントを通知する
            eventPublisher.publishEvent(new ReceivedEvent<String>(this, t
                    .getCause().getMessage()));
        } else {
            // Other Error

            log.error("Other Error!", t.getCause());

        }

    }

}
