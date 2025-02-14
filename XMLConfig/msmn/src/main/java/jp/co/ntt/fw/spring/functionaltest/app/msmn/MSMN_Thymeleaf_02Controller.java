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
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessages;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.UserService;

@Controller
@RequestMapping("thymeleaf/02")
public class MSMN_Thymeleaf_02Controller {

    @Inject
    private UserService userService;

    @GetMapping(value = "01/001")
    public String msmn0201001(Model model) {
        ResultMessages messages = ResultMessages.error().add("e.ex.an.9001");
        model.addAttribute(messages);
        return "thymeleaf/msmn/resultMessage";
    }

    @GetMapping(value = "02/001")
    public String msmn0202001(Model model) {
        // @formatter:off
        ResultMessages messages = ResultMessages.error()
                .add("e.ex.an.9001")
                .add("e.ex.an.8001", 1024);
        // @formatter:on
        model.addAttribute(messages);
        return "thymeleaf/msmn/resultMessage";
    }

    @GetMapping(value = "03/003")
    public String msmn0203003(Model model) {
        ResultMessages messages = ResultMessages.error().add("e.ex.an.8002", 1024);
        model.addAttribute(messages);
        return "thymeleaf/msmn/resultMessage";
    }

    @GetMapping(value = "05/001")
    public String msmn0205001(Model model) {
        // 確認の為i.ex.an.0001を使いまわしているが、メッセージタイプに応じたIDを適切に付与すること
        model.addAttribute("messages_success", ResultMessages.success().add("i.ex.an.0001"));
        model.addAttribute("messages_info", ResultMessages.info().add("i.ex.an.0001"));
        model.addAttribute("messages_warning", ResultMessages.warning().add("i.ex.an.0001"));
        model.addAttribute("messages_error", ResultMessages.error().add("i.ex.an.0001"));
        model.addAttribute("messages_danger", ResultMessages.danger().add("i.ex.an.0001"));
        model.addAttribute("messages_primary", ResultMessages.primary().add("i.ex.an.0001"));
        model.addAttribute("messages_secondary", ResultMessages.secondary().add("i.ex.an.0001"));
        model.addAttribute("messages_light", ResultMessages.light().add("i.ex.an.0001"));
        model.addAttribute("messages_dark", ResultMessages.dark().add("i.ex.an.0001"));
        return "thymeleaf/msmn/resultMessages";
    }

    @GetMapping(value = {"06"})
    public String msmn0206(Model model) {
        return "thymeleaf/msmn/exception";
    }

    @GetMapping(value = "create")
    public String create(Model model) {
        try {
            userService.create();
        } catch (BusinessException | ResourceNotFoundException e) {
            ResultMessages messages = e.getResultMessages();
            model.addAttribute(messages);

            return "thymeleaf/msmn/exception";
        }

        // Not Reachable
        return null;
    }

    @GetMapping(value = "update")
    public String update(Model model) {
        try {
            userService.update();
        } catch (ResourceNotFoundException e) {
            ResultMessages messages = e.getResultMessages();
            model.addAttribute(messages);

            return "thymeleaf/msmn/exception";
        }

        // Not Reachable
        return null;
    }

}
