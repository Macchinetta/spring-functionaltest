/*
 * Copyright(c) 2014 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.dialects.thym;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

public class DateFormatSlashDialect implements IExpressionObjectDialect {

    private static final Logger logger = LoggerFactory.getLogger(
            DateFormatSlashDialect.class);

    @SuppressWarnings("serial")
    private Set<String> names = new HashSet<String>() {
        {
            add("dateformatslash");
            add("nocachedateformat");
        }
    };

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return new IExpressionObjectFactory() {

            @Override
            public Set<String> getAllExpressionObjectNames() {
                return names;
            }

            @Override
            public Object buildObject(IExpressionContext context,
                    String expressionObjectName) {
                if ("dateformatslash".equals(expressionObjectName)
                        || "nocachedateformat".equals(expressionObjectName)) {
                    logger.info("Create '" + expressionObjectName + "' Object");
                    return new DateFormatSlash();
                }
                return null;
            }

            @Override
            public boolean isCacheable(String expressionObjectName) {
                if ("nocachedateformat".equals(expressionObjectName)) {
                    return false;
                }
                return true;
            }

        };
    }

    @Override
    public String getName() {
        return "Date Format(yyyy/MM/dd) Dialect";
    }

}
