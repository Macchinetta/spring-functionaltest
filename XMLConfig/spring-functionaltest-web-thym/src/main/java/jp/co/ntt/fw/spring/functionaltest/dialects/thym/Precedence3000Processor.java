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
package jp.co.ntt.fw.spring.functionaltest.dialects.thym;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class Precedence3000Processor extends AbstractAttributeTagProcessor {

    public Precedence3000Processor(final String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, null, false, "p3000", true,
                3000, true);
    }

    @Override
    protected void doProcess(ITemplateContext context,
            IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue,
            IElementTagStructureHandler structureHandler) {

        for (IAttribute attribute : tag.getAllAttributes()) {
            String name = attribute.getAttributeCompleteName();
            if (!"id".equals(name)) {
                structureHandler.removeAttribute(attribute
                        .getAttributeCompleteName());
            }
        }

        structureHandler.setBody(attributeValue, false);

    }

}
