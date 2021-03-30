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

import org.springframework.util.StringUtils;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class InputFormProcessor extends AbstractAttributeTagProcessor {

    public InputFormProcessor(final String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, null, false, "form-input", true,
                1000, true);
    }

    @Override
    protected void doProcess(ITemplateContext context,
            IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue,
            IElementTagStructureHandler structureHandler) {

        String classValue = tag.getAttributeValue("class");

        if (StringUtils.hasText(classValue)) {
            structureHandler.removeAttribute("class");
            structureHandler.setAttribute("class", classValue + " form-input");
        } else {
            structureHandler.setAttribute("class", "form-input");
        }

        IModelFactory modelFactory = context.getModelFactory();
        IModel model = modelFactory.createModel();

        String label = getLabel(attributeValue);

        model.add(modelFactory.createOpenElementTag("label", "id", label
                + "Label"));
        model.add(modelFactory.createText(label));
        model.add(modelFactory.createCloseElementTag("label"));

        model.add(modelFactory.createStandaloneElementTag("input", "th:field",
                attributeValue));

        model.add(modelFactory.createOpenElementTag("span", "th:errors",
                attributeValue));
        model.add(modelFactory.createCloseElementTag("span"));

        structureHandler.setBody(model, true);

    }

    private String getLabel(String attributeValue) {
        String[] value = StringUtils.split(attributeValue, ".");
        String label = value == null ? attributeValue : value[value.length - 1];
        return StringUtils.deleteAny(label, "*${}");
    }

}
