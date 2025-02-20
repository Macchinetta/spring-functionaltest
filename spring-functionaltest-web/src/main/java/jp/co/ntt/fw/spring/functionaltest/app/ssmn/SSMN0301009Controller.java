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
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.github.dozermapper.core.Mapper;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.InvalidRequestException;
import jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm.Address;
import jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm.Other;
import jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm.Personal;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Member;
import jp.co.ntt.fw.spring.functionaltest.domain.service.ssmn.MemberService;

@Controller
@SessionAttributes(types = MemberForm.class)
@RequestMapping(value = "ssmn/0301/009")
public class SSMN0301009Controller {
    @Inject
    Mapper beanMapper;

    @Inject
    MemberService memberService;

    @ModelAttribute
    public MemberForm setUpForm() {
        return new MemberForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle01009(Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/ssmn/0301/009?personalForm";
    }

    @RequestMapping(method = RequestMethod.GET, params = "personalForm")
    public String createMemberPersonalForm(Model model) {
        return "ssmn/createMemberPersonal";
    }

    @RequestMapping(method = RequestMethod.POST, params = "addressForm")
    public String createMemberAddressForm(Model model, @Validated(Personal.class) MemberForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return createRedoMemberPersonal(model);
        }
        return "ssmn/createMemberAddress";
    }

    @RequestMapping(method = RequestMethod.POST, params = "otherForm")
    public String createMemberOtherForm(Model model, @Validated(Address.class) MemberForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return createRedoMemberAddress(model);
        }
        return "ssmn/createMemberOther";
    }

    @RequestMapping(method = RequestMethod.POST, params = "confirm")
    public String createMemberConfirm(Model model, @Validated(Other.class) MemberForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return createRedoMemberOther(model);
        }
        return "ssmn/createMemberConfirmWithAddRequestParameter";
    }

    @RequestMapping(method = RequestMethod.POST, params = "binding=true")
    public String createMemberWithBindingTrue(
            @ModelAttribute(binding = true)
            @Validated({Personal.class, Address.class, Other.class}) MemberForm form,
            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            throw new InvalidRequestException(ResultMessages.error().add("e.sf.fw.8002"));
        }

        Member member = beanMapper.map(form, Member.class);
        member = memberService.createMember(member);
        redirectAttributes.addFlashAttribute(member);

        ResultMessages messages = ResultMessages.success().add("i.sf.sm.0001");
        redirectAttributes.addFlashAttribute(messages);

        return "redirect:/ssmn/0301/009?complete";
    }

    @RequestMapping(method = RequestMethod.POST, params = "binding=false")
    public String createMemberWithBindingFalse(
            @ModelAttribute(binding = false)
            @Validated({Personal.class, Address.class, Other.class}) MemberForm form,
            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            throw new InvalidRequestException(ResultMessages.error().add("e.sf.fw.8002"));
        }

        Member member = beanMapper.map(form, Member.class);
        member = memberService.createMember(member);
        redirectAttributes.addFlashAttribute(member);

        ResultMessages messages = ResultMessages.success().add("i.sf.sm.0001");
        redirectAttributes.addFlashAttribute(messages);

        return "redirect:/ssmn/0301/009?complete";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createMember(
            @ModelAttribute
            @Validated({Personal.class, Address.class, Other.class}) MemberForm form,
            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            throw new InvalidRequestException(ResultMessages.error().add("e.sf.fw.8002"));
        }

        Member member = beanMapper.map(form, Member.class);
        member = memberService.createMember(member);
        redirectAttributes.addFlashAttribute(member);

        ResultMessages messages = ResultMessages.success().add("i.sf.sm.0001");
        redirectAttributes.addFlashAttribute(messages);

        return "redirect:/ssmn/0301/009?complete";
    }

    @RequestMapping(method = RequestMethod.GET, params = "complete")
    public String createMemberComplete(Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "ssmn/createMemberComplete";
    }

    @RequestMapping(method = RequestMethod.POST, params = "redoAddress")
    public String createRedoMemberAddress(Model model) {
        return "ssmn/createMemberAddress";
    }

    @RequestMapping(method = RequestMethod.POST, params = "redoPersonal")
    public String createRedoMemberPersonal(Model model) {
        return "ssmn/createMemberPersonal";
    }

    @RequestMapping(method = RequestMethod.POST, params = "redo")
    public String createRedoMemberOther(Model model) {
        return "ssmn/createMemberOther";
    }
}
