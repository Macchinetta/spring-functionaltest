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
package jp.co.ntt.fw.spring.functionaltest.ws.exception;

import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import jp.co.ntt.fw.spring.functionaltest.ws.webfault.WebFaultBean;
import jp.co.ntt.fw.spring.functionaltest.ws.webfault.WebFaultException;
import jp.co.ntt.fw.spring.functionaltest.ws.webfault.WebFaultType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.exception.SystemException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

@Component
public class WsExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @Autowired
    ExceptionLogger exceptionLogger;

    public void translateException(Exception e) throws WebFaultException {
        loggingException(e);
        WebFaultBean faultInfo = null;

        if (e instanceof AccessDeniedException) {
            faultInfo = new WebFaultBean(WebFaultType.AccessDeniedFault);
            faultInfo.addError(e.getClass().getName(), e.getMessage());
        } else if (e instanceof ConstraintViolationException) {
            faultInfo = new WebFaultBean(WebFaultType.ValidationFault);
            this.addErrors(faultInfo, ((ConstraintViolationException) e)
                    .getConstraintViolations());
        } else if (e instanceof ResourceNotFoundException) {
            faultInfo = new WebFaultBean(WebFaultType.ResourceNotFoundFault);
            this.addErrors(faultInfo, ((ResourceNotFoundException) e)
                    .getResultMessages());
        } else if (e instanceof BusinessException) {
            faultInfo = new WebFaultBean(WebFaultType.BusinessFault);
            this.addErrors(faultInfo, ((BusinessException) e)
                    .getResultMessages());
        } else {
            // not translate.
            throw new SystemException("e.sf.soap.9001", e);
        }

        throw new WebFaultException(e.getMessage(), faultInfo, e.getCause());
    }

    private void loggingException(Exception e) {

        exceptionLogger.log(e);

    }

    private void addErrors(WebFaultBean faultInfo,
            Set<ConstraintViolation<?>> constraintViolations) {
        for (ConstraintViolation<?> v : constraintViolations) {
            Iterator<Path.Node> pathIt = v.getPropertyPath().iterator();
            pathIt.next(); // method name node (skip)
            Path.Node methodArgumentNameNode = pathIt.next();
            faultInfo.addError(v.getConstraintDescriptor().getAnnotation()
                    .annotationType().getSimpleName(), v.getMessage(), pathIt
                            .hasNext() ? pathIt.next().toString()
                                    : methodArgumentNameNode.toString());
        }
    }

    private void addErrors(WebFaultBean faultInfo,
            ResultMessages resultMessages) {
        Locale locale = Locale.getDefault();
        for (ResultMessage message : resultMessages) {
            faultInfo.addError(message.getCode(), messageSource.getMessage(
                    message.getCode(), message.getArgs(), message.getText(),
                    locale));
        }
    }

}
