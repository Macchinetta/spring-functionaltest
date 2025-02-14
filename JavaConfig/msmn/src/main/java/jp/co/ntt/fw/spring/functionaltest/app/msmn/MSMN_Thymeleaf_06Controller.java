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
import com.example.common.message.MessageIds;

@Controller
@RequestMapping("thymeleaf/06")
public class MSMN_Thymeleaf_06Controller {

    @GetMapping(value = "01/002")
    public String msmn0601002(Model model) {
        // @formatter:off
        ResultMessages messages = ResultMessages.error()
                .add(MessageIds.E_EX_AN_9001)
                .add(MessageIds.E_EX_AN_8001, 1024);
        // @formatter:on
        model.addAttribute(messages);
        return "thymeleaf/msmn/resultMessage";
    }

}
