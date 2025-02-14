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
package jp.co.ntt.fw.spring.functionaltest.app.athr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.SystemConfig;
import jp.co.ntt.fw.spring.functionaltest.domain.service.athr.SystemConfigService;

@Controller
public class ATHR_Thymeleaf_09Controller {
    @Inject
    SystemConfigService systemConfigService;

    @Inject
    ATHRBeanMapper beanMapper;

    @GetMapping("0901/001")
    public String handle0901001loginForStaff() {
        return "thymeleaf/athr/loginForRoleHierarchy";
    }

    @GetMapping(value = "0901/001/afterLogin")
    public String handle0901001afterLogin() {
        return "thymeleaf/athr/showForRoleHierarchyDsp";
    }

    @GetMapping("0901/002")
    public String handle0901002loginForStaff() {
        return "thymeleaf/athr/loginForThymeleafRoleHierarchy";
    }

    @GetMapping(value = "0901/002/afterLogin")
    public String handle0901002afterLogin() {
        return "thymeleaf/athr/showForThymeleafRoleHierarchyDsp";
    }

    @GetMapping("0901/003")
    public String handle0901003loginForStaff() {
        return "thymeleaf/athr/loginForMethodRoleHierarchy";
    }

    @GetMapping(value = "0901/003/afterLogin")
    public String handle09010031afterLogin() {
        return "thymeleaf/athr/methodHierarchyChoosePage";
    }

    @GetMapping(value = "0901/003", params = "select")
    public String handle09010031select(SystemConfigForm systemConfigForm, Model model) {
        model.addAttribute("systemConfig", systemConfigService.findDeviceForStaff(
                systemConfigForm.getDevice(), systemConfigForm.getExecuteUser()));
        return "thymeleaf/athr/showMethodHierarchyAccessAllowedPage";
    }

    @PostMapping(value = "0901/003", params = "insert")
    public String handle09010031insert(SystemConfigForm systemConfigForm) {
        return "thymeleaf/athr/methodHierarchyAccessAllowedInsertPage";
    }

    @PostMapping(value = "0901/003", params = "register")
    public String handle09010031register(SystemConfigForm systemConfigForm, Model model,
            RedirectAttributes redirectAttributes) {
        SystemConfig systemConfig = beanMapper.map(systemConfigForm);
        systemConfigService.insertForStaff(systemConfig);
        redirectAttributes.addFlashAttribute("systemConfigForm", systemConfigForm);
        return "redirect:/thymeleaf/0901/003?select";
    }

    @RequestMapping(value = "thymeleaf/methodHierarchyAccessDeniedPage",
            method = {RequestMethod.GET, RequestMethod.POST})
    public String handle09010031registerError() {
        return "thymeleaf/athr/methodHierarchyAccessDeniedPage";
    }

}
