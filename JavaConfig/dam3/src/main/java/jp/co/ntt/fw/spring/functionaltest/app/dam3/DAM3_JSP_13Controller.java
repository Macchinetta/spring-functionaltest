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
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.HandlerObj;
import jp.co.ntt.fw.spring.functionaltest.domain.model.SampleObj;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dam3.CustomTypeHandlerService;

@Controller
@RequestMapping("jsp")
public class DAM3_JSP_13Controller {

    private static final Logger logger = LoggerFactory.getLogger(DAM3_JSP_13Controller.class);

    @Inject
    private CustomTypeHandlerService typeHandlerService;

    @GetMapping(value = "1301/001")
    public String handle1301001() {
        return "redirect:/jsp/todo/list";
    }

    @GetMapping(value = "1304/001")
    public String handle1304001() {
        return "redirect:/jsp/show/date";
    }

    @GetMapping(value = "1305/001")
    public String handle1305001() {

        String id = "0000000001";
        HandlerObj handlerObj = new HandlerObj("value1", "value2", 1);

        typeHandlerService.create(new SampleObj(id, handlerObj));

        SampleObj result = typeHandlerService.findOneById(id);

        logger.info(result.toString());

        typeHandlerService.delete(id);

        return "redirect:/";
    }

}
