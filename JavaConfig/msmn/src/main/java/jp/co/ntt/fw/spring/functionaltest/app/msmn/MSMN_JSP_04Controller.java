/*
 * Copyright(c) 2025 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.msmn;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.common.message.ResultMessages;

@Controller
@RequestMapping("jsp/04")
public class MSMN_JSP_04Controller {

    @GetMapping()
    public String msmn04(Model model) {
        model.addAttribute("messages_panelElement", ResultMessages.info().add("i.ex.od.0002"));
        model.addAttribute("messages_classs", ResultMessages.error().add("e.ex.an.9001"));
        model.addAttribute("messages_outer_inner_element",
                ResultMessages.error().add("e.ex.an.9001").add("e.ex.an.8001", 1024));
        return "jsp/msmn/changeTagAttributes";
    }

}
