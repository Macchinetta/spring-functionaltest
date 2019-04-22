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

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.InvalidRequestException;
import jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm.Address;
import jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm.Other;
import jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm.Personal;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Member;
import jp.co.ntt.fw.spring.functionaltest.domain.service.ssmn.MemberService;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
@SessionAttributes(types = MemberForm.class)
@RequestMapping("synchronism/delay")
public class SSMN0601001DelayController {

    private static final Logger logger = LoggerFactory.getLogger(
            SSMN0601001DelayController.class);

    @Inject
    Mapper beanMapper;

    @Inject
    MemberService memberService;

    @Inject
    SSMN0601001Helper sSMN0601001Helper;

    @ModelAttribute("memberForm")
    public MemberForm setUpForm() {
        return new MemberForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle01001(Model model, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/ssmn/0601/001/synchronism/delay?createForm";
    }

    @RequestMapping(method = RequestMethod.GET, params = "createForm")
    public String createMemberForm() {
        return "ssmn/createMemberAll";
    }

    @RequestMapping(method = RequestMethod.POST, params = "confirm")
    public String createMemberConfirm(@Validated({ Personal.class,
            Address.class, Other.class }) MemberForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return createRedoMember();
        }

        return "ssmn/createMemberConfirm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createMember(@Validated({ Personal.class, Address.class,
            Other.class }) MemberForm form, BindingResult result,
            RedirectAttributes redirectAttributes) throws InterruptedException {
        if (result.hasErrors()) {
            throw new InvalidRequestException(ResultMessages.error().add(
                    "e.sf.cmmn.8002"));
        }

        logger.info(
                "[Session Synchronization Confirmation]SSMN0601001DelayController createMember process start");

        try {
            // delay
            // EnableSynchronizeOnSessionPostProcessorが有効となっていることを確認するために、処理を遅延させている。
            sSMN0601001Helper.delay(3000);
        } catch (InterruptedException e) {
            logger.warn("InterruptedException Occured", e);
            throw e;
        }

        Member member = beanMapper.map(form, Member.class);
        member = memberService.createMember(member);
        redirectAttributes.addFlashAttribute(member);

        ResultMessages messages = ResultMessages.success().add(
                "i.sf.ssmn.0001");
        redirectAttributes.addFlashAttribute(messages);

        logger.info(
                "[Session Synchronization Confirmation]SSMN0601001DelayController createMember process finish");
        return "redirect:/ssmn/0601/001/synchronism/delay?complete";
    }

    @RequestMapping(method = RequestMethod.GET, params = "complete")
    public String createMemberComplete(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "ssmn/createMemberComplete";
    }

    @RequestMapping(method = RequestMethod.POST, params = "redo")
    public String createRedoMember() {
        return "ssmn/createMemberAll";
    }
}
