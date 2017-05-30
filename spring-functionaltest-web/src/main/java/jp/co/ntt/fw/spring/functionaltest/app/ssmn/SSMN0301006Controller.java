/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.InvalidRequestException;
import jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm.Address;
import jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm.Other;
import jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm.Personal;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Member;
import jp.co.ntt.fw.spring.functionaltest.domain.service.ssmn.MemberService;

import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;

@Controller
@SessionAttributes(value = { "memberForm" })
@RequestMapping(value = "ssmn/0301/006")
public class SSMN0301006Controller {

    @Inject
    Mapper beanMapper;

    @Inject
    MemberService memberService;

    @RequestMapping(method = RequestMethod.GET)
    public String handle01006(Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/ssmn/0301/006?personalForm";
    }

    @RequestMapping(method = RequestMethod.GET, params = "personalForm")
    public String createMemberPersonalForm(Model model, MemberForm form) {
        model.addAttribute(form);
        return "ssmn/createMemberPersonal";
    }

    @RequestMapping(method = RequestMethod.POST, params = "addressForm")
    public String createMemberAddressForm(Model model,
            @Validated(Personal.class) MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return createRedoMemberPersonal(model, form);
        }
        model.addAttribute(form);
        return "ssmn/createMemberAddress";
    }

    @RequestMapping(method = RequestMethod.POST, params = "otherForm")
    public String createMemberOtherForm(Model model,
            @Validated(Address.class) MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return createRedoMemberAddress(model, form);
        }
        model.addAttribute(form);
        return "ssmn/createMemberOther";
    }

    @RequestMapping(method = RequestMethod.POST, params = "confirm")
    public String createMemberConfirm(Model model,
            @Validated(Other.class) MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return createRedoMemberOther(model, form);
        }
        model.addAttribute(form);
        return "ssmn/createMemberConfirm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createMember(@Validated({ Personal.class, Address.class,
            Other.class }) MemberForm form, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            throw new InvalidRequestException(ResultMessages.error().add(
                    "e.sf.cmmn.8002"));
        }

        Member member = beanMapper.map(form, Member.class);
        member = memberService.createMember(member);
        redirectAttributes.addFlashAttribute(member);

        ResultMessages messages = ResultMessages.success()
                .add("i.sf.ssmn.0001");
        redirectAttributes.addFlashAttribute(messages);

        return "redirect:/ssmn/0301/006?complete";
    }

    @RequestMapping(method = RequestMethod.GET, params = "complete")
    public String createMemberComplete(Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "ssmn/createMemberComplete";
    }

    @RequestMapping(method = RequestMethod.POST, params = "redoAddress")
    public String createRedoMemberAddress(Model model, MemberForm form) {
        model.addAttribute(form);
        return "ssmn/createMemberAddress";
    }

    @RequestMapping(method = RequestMethod.POST, params = "redoPersonal")
    public String createRedoMemberPersonal(Model model, MemberForm form) {
        model.addAttribute(form);
        return "ssmn/createMemberPersonal";
    }

    @RequestMapping(method = RequestMethod.POST, params = "redo")
    public String createRedoMemberOther(Model model, MemberForm form) {
        model.addAttribute(form);
        return "ssmn/createMemberOther";
    }

}