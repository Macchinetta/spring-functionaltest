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
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.InvalidRequestException;
import jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm.Address;
import jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm.Other;
import jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm.Personal;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Member;
import jp.co.ntt.fw.spring.functionaltest.domain.service.ssmn.MemberService;

@Controller
@SessionAttributes(types = MemberForm.class)
@RequestMapping(value = "thymeleaf/0301/001")
public class SSMN_Thymeleaf_0301001Controller {

    private static final Logger logger =
            LoggerFactory.getLogger(SSMN_Thymeleaf_0301001Controller.class);

    @Inject
    SSMNBeanMapper beanMapper;

    @Inject
    MemberService memberService;

    @ModelAttribute("memberForm")
    public MemberForm setUpForm() {
        logger.info("ModelAttribute Method Called");
        return new MemberForm();
    }

    @GetMapping
    public String handle01001(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/thymeleaf/0301/001?personalForm";
    }

    @GetMapping(params = "personalForm")
    public String createMemberPersonalForm() {
        return "thymeleaf/ssmn/createMemberPersonal";
    }

    @PostMapping(params = "addressForm")
    public String createMemberAddressForm(@Validated(Personal.class) MemberForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return createRedoMemberPersonal();
        }
        return "thymeleaf/ssmn/createMemberAddress";
    }

    @PostMapping(params = "otherForm")
    public String createMemberOtherForm(@Validated(Address.class) MemberForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return createRedoMemberAddress();
        }
        return "thymeleaf/ssmn/createMemberOther";
    }

    @PostMapping(params = "confirm")
    public String createMemberConfirm(@Validated(Other.class) MemberForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return createRedoMemberOther();
        }
        return "thymeleaf/ssmn/createMemberConfirm";
    }

    @PostMapping
    public String createMember(
            @Validated({Personal.class, Address.class, Other.class}) MemberForm form,
            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            throw new InvalidRequestException(ResultMessages.error().add("e.sf.fw.8002"));
        }

        Member member = beanMapper.map(form);
        member = memberService.createMember(member);
        redirectAttributes.addFlashAttribute(member);

        ResultMessages messages = ResultMessages.success().add("i.sf.sm.0001");
        redirectAttributes.addFlashAttribute(messages);

        return "redirect:/thymeleaf/0301/001?complete";
    }

    @GetMapping(params = "complete")
    public String createMemberComplete(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "thymeleaf/ssmn/createMemberComplete";
    }

    @PostMapping(params = "redoAddress")
    public String createRedoMemberAddress() {
        return "thymeleaf/ssmn/createMemberAddress";
    }

    @PostMapping(params = "redoPersonal")
    public String createRedoMemberPersonal() {
        return "thymeleaf/ssmn/createMemberPersonal";
    }

    @PostMapping(params = "redo")
    public String createRedoMemberOther() {
        return "thymeleaf/ssmn/createMemberOther";
    }
}
