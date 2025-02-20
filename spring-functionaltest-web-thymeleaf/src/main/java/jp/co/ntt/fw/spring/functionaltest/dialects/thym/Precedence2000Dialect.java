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

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

public class Precedence2000Dialect extends AbstractProcessorDialect {

    public Precedence2000Dialect() {
        super("Precedence 2000", "d2000", 2000);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {

        final Set<IProcessor> processors = new HashSet<IProcessor>();

        processors.add(new Precedence2000Processor(dialectPrefix));
        processors.add(new Precedence3000Processor(dialectPrefix));

        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));

        return processors;

    }

}
