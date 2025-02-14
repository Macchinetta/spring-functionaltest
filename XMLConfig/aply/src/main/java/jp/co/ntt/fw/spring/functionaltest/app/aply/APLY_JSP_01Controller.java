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
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("jsp")
@Controller
public class APLY_JSP_01Controller {

    @Inject
    MessageSource messageSource;

    @ModelAttribute
    public HandleRequestForm setUpForm() {
        return new HandleRequestForm();
    }

    @GetMapping(value = "0101")
    public String handle(Model model) {
        return "jsp/aply/handleRequestForm";
    }

    @GetMapping(value = "0101/001")
    public String handle01001(Model model) {
        return "jsp/aply/handleRequestHttpMethodForm";
    }

    @GetMapping(value = "0101/001_1")
    public String handle01001_1(Model model) {
        String[] messageArgs = {"@GetMapping(value = \"0101/001_1\")"};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0001", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @PostMapping(value = "0101/001_2")
    public String handle01001_2(Model model) {
        String[] messageArgs = {"@PostMapping(value = \"0101/001_2\")"};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0001", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @RequestMapping(value = "0101/001_3", method = {RequestMethod.GET, RequestMethod.POST})
    public String handle01001_3(Model model) {
        String[] messageArgs = {
                "@RequestMapping(value = \"0101/001_3\", method = {RequestMethod.GET, RequestMethod.POST})"};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0001", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0101/002")
    public String handle01002(Model model) {
        return "jsp/aply/handleRequestPathForm";
    }

    @GetMapping(value = "0101/002/hello")
    public String handle01002_1(Model model) {
        String[] messageArgs = {"@GetMapping(value = \"0101/002/hello\")"};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0001", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = {"0101/002/nihao", "0101/002/bonjour"})
    public String handle01002_2(Model model) {
        String[] messageArgs = {"@GetMapping(value = {\"0101/002/nihao\", \"0101/002/bonjour\"})"};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0001", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0101/003")
    public String handle01003(Model model) {
        return "jsp/aply/handleRequestParameterForm";
    }

    @GetMapping(value = "0101/003", params = "form1")
    public String handle01003_1(Model model) {
        String[] messageArgs = {"@GetMapping(value = \"0101/003\", params = \"form\")"};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0001", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0101/003", params = {"form2", "formType=foo"})
    public String handle01003_2(Model model) {
        String[] messageArgs =
                {"@GetMapping(value = \"0101/003\", params = {\"form\", \"formType=foo\"})"};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0001", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0101/004")
    public String handle01004(Model model) {
        return "jsp/aply/handleRequestParameterNegativeForm";
    }

    @GetMapping(value = "0101/004/001", params = "!form")
    public String handle01004_1(Model model) {
        String[] messageArgs = {"@GetMapping(value = \"0101/004\", params = \"!form\")"};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0001", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0101/004/001", params = {"form", "formType!=foo"})
    public String handle01004_2(Model model) {
        String[] messageArgs =
                {"@GetMapping(value = \"0101/004\", params = {\"form\", \"formType!=foo\"})"};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0001", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0102/001/{id}/{version}")
    public String handle02001(@PathVariable("id") String id,
            @PathVariable("version") Integer version, Model model) {
        Object[] messageArgs = {"@GetMapping(value = \"0102/001/{id}/{version}\")", id, version};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0002", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0102/002/{id}/{version}")
    public String handle02002(@PathVariable String id, @PathVariable Integer version, Model model) {
        Object[] messageArgs = {"@GetMapping(value = \"0102/002/{id}/{version}\")", id, version};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0002", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0102/003")
    public String handle02003(@RequestParam("id") String id, @RequestParam String name,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "genderCode", required = false,
                    defaultValue = "unknown") String genderCode,
            Model model) {
        Object[] messageArgs = {"@GetMapping(value = \"0102/003\")", id, name, age, genderCode};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0003", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0102/004")
    public String handle02004(@RequestParam("id") String id, @RequestParam String name,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "genderCode", required = false,
                    defaultValue = "unknown") String genderCode,
            Model model) {
        Object[] messageArgs = {"@GetMapping(value = \"0102/004\")", id, name, age, genderCode};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0003", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0102/005")
    public String handle02005(RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("hello", "Hello World!");

        return "redirect:/jsp/0102/005?complete";
    }

    @GetMapping(value = "0102/005", params = "complete")
    public String handle02005complete(Model model) {
        String[] messageArgs = {"@GetMapping(value = \"0102/005complete\")"};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0001", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0102/006")
    public String handle02006(RedirectAttributes redirectAttrs) {
        String id = "redirect_id";
        redirectAttrs.addAttribute("id", id);
        return "redirect:/jsp/0102/006?complete";
    }

    @GetMapping(value = "0102/006", params = "complete")
    public String handle02006complete(@RequestParam("id") String id, Model model) {
        String[] messageArgs = {"@GetMapping(value = \"0102/006complete\")", id};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0004", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0102/007")
    public String handle02007(@CookieValue("JSESSIONID") String sessionId, Model model) {
        String[] messageArgs = {"@GetMapping(value = \"0102/007\")", "JSESSIONID", sessionId};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0005", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0102/008")
    public String handle02008(Model model) {
        return "jsp/aply/handleRequestSetCookieFromHttpServletResponseForm";
    }

    @GetMapping(value = "0102/008/setCookie")
    public String handle02008SetCookie(Model model, HttpServletResponse response) {
        String[] messageArgs = {"@GetMapping(value = \"0102/008\")"};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0001", messageArgs, Locale.JAPANESE));
        ResponseCookie cookie = ResponseCookie.from("HELLO", "helloworld!").httpOnly(true).build();
        response.addHeader("Set-Cookie", cookie.toString());
        return "jsp/aply/handleRequestSetCookieFromHttpServletResponseForm";
    }

    @GetMapping(value = "0102/008/confirm")
    public String handle02008confiem(@CookieValue("HELLO") String hello, Model model) {
        String[] messageArgs = {"@GetMapping(value = \"0102/008\")", "hello", hello};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0005", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }

    @GetMapping(value = "0102/009")
    public String handle02009(CommonParameters commonParams, Model model) {
        String[] messageArgs = {"@GetMapping(value = \"0102/009\")", commonParams.getCommonParam1(),
                commonParams.getCommonParam2()};
        model.addAttribute("resultMessage",
                messageSource.getMessage("i.sf.al.0006", messageArgs, Locale.JAPANESE));
        return "jsp/aply/showHandleRequestForm";
    }
}
