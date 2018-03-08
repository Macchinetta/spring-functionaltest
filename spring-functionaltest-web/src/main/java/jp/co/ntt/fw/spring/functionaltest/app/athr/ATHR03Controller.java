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
package jp.co.ntt.fw.spring.functionaltest.app.athr;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.SystemConfig;
import jp.co.ntt.fw.spring.functionaltest.domain.service.athr.SystemConfigService;

import org.dozer.Mapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ATHR03Controller {

    @Inject
    SystemConfigService systemConfigService;

    @Inject
    Mapper beanMapper;

    @RequestMapping("0301/001")
    public String handle0301001loginForStaff() {
        return "athr/loginForMethod";
    }

    @RequestMapping(value = "0301/001/afterLogin")
    public String handle0301001afterLogin() {
        return "athr/methodChoosePage";
    }

    @RequestMapping(value = "0301/001", params = "select")
    public String handle0301001select(SystemConfigForm systemConfigForm,
            Model model) {

        model.addAttribute("systemConfig", systemConfigService
                .findDeviceForAdmin(systemConfigForm.getDevice(),
                        systemConfigForm.getExecuteUser()));
        return "athr/showMethodAccessAllowedPage";
    }

    @ModelAttribute
    public SystemConfigForm setUpsystemConfigForm() {
        SystemConfigForm systemConfigForm = new SystemConfigForm();
        return systemConfigForm;
    }

    @RequestMapping(value = "0301/001", params = "insert")
    public String handle0301001insert(SystemConfigForm systemConfigForm) {
        return "athr/methodAccessAllowedInsertPage";
    }

    @RequestMapping(value = "0301/001", params = "register")
    public String handle0301001register(SystemConfigForm systemConfigForm,
            Model model, RedirectAttributes redirectAttributes) {
        SystemConfig systemConfig = beanMapper.map(systemConfigForm,
                SystemConfig.class);
        systemConfigService.insertForAdmin(systemConfig);
        redirectAttributes.addFlashAttribute("systemConfigForm",
                systemConfigForm);
        return "redirect:/athr/0301/001?select";
    }

}
