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
package jp.co.ntt.fw.spring.functionaltest.app.pgnt;

import java.util.Collections;
import java.util.Set;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

public class PageInfoDialect implements IExpressionObjectDialect {

    private static final String PAGE_INFO_DIALECT_NAME = "pageInfo";

    private static final Set<String> EXPRESSION_OBJECT_NAMES = Collections
            .singleton(PAGE_INFO_DIALECT_NAME);

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return new IExpressionObjectFactory() {

            @Override
            public Set<String> getAllExpressionObjectNames() {
                return EXPRESSION_OBJECT_NAMES;
            }

            @Override
            public Object buildObject(IExpressionContext context,
                    String expressionObjectName) {
                if (PAGE_INFO_DIALECT_NAME.equals(expressionObjectName)) {
                    return new PageInfo();
                }
                return null;
            }

            @Override
            public boolean isCacheable(String expressionObjectName) {
                return true;
            }

        };
    }

    @Override
    public String getName() {
        return "Page Information Dialect";
    }

}
