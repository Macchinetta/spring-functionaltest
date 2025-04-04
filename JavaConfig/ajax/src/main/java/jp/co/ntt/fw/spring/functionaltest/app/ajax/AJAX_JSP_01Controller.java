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
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import java.util.List;
import java.util.Locale;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.bean.ErrorResults;
import jp.co.ntt.fw.spring.functionaltest.domain.model.MessageBoard;
import jp.co.ntt.fw.spring.functionaltest.domain.model.PersonalComputer;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.ajax.PersonalComputerCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.service.ajax.MessageBoardService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.ajax.PersonalComputerService;

@RequestMapping("jsp")
@Controller
public class AJAX_JSP_01Controller {

    @Inject
    PersonalComputerService personalComputerService;

    @Inject
    PersonalComputerHelper personalComputerHelper;

    @Inject
    MessageBoardService messageBoardService;

    @ModelAttribute
    public PersonalComputerCriteria setSearchCriteria() {
        return new PersonalComputerCriteria();
    }

    @ModelAttribute
    public PersonalComputerForm setUpPersonalComputerForm() {
        return new PersonalComputerForm();
    }

    @ModelAttribute
    public MessageBoardForm setUpMessageBoardForm() {
        return new MessageBoardForm();
    }

    @GetMapping(value = "0101/001")
    public String handle0101001() {
        return "jsp/ajax/personalComputerSearch";
    }

    @GetMapping(value = "0102/001")
    public String handle0102001(PersonalComputerForm form, Model model) {

        personalComputerHelper.setPersonalComputer(1, form);

        model.addAttribute("path", "0102/001/edit");
        model.addAttribute("method", "ajax.editPersonalComputer");

        return "jsp/ajax/personalComputerEdit";
    }

    @GetMapping(value = "0103/001")
    public String handle0103001(PersonalComputerForm form, Model model) {

        personalComputerHelper.setPersonalComputer(1, form);

        model.addAttribute("path", "0103/001/edit");
        model.addAttribute("method", "ajax.editPersonalComputerForJson");

        return "jsp/ajax/personalComputerEdit";
    }

    @GetMapping(value = "0104/001/001")
    public String handle0104001001(Model model) {

        List<MessageBoard> messageBoards = messageBoardService.getMessageBoards();

        model.addAttribute("messageResults", messageBoards);
        model.addAttribute("path", "0104/write");

        return "jsp/ajax/messageBoard";
    }

    @GetMapping(value = "0104/001")
    public String handle0104001(Model model) {

        List<MessageBoard> messageBoards = messageBoardService.getMessageBoards();

        model.addAttribute("messageResults", messageBoards);
        model.addAttribute("path", "0104/001/write");

        return "jsp/ajax/messageBoard";
    }

    @GetMapping(value = "search")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PersonalComputerSearchResult search(PersonalComputerCriteria criteria, Model model) {

        List<PersonalComputer> result = personalComputerService.getPersonalComputers(criteria);

        PersonalComputerSearchResult computerSearchResult = new PersonalComputerSearchResult();
        computerSearchResult.setPersonalComputerResult(result);

        return computerSearchResult;
    }

    @PostMapping(value = "0102/001/edit")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PersonalComputerResult edit(@Validated PersonalComputerForm form, Locale locale) {

        return personalComputerHelper.updateAndBindPersonalComputerResult(form, locale);
    }

    @PostMapping(value = "0103/001/edit")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PersonalComputerResult editForJson(@Validated @RequestBody PersonalComputerForm form,
            Locale locale) {

        return personalComputerHelper.updateAndBindPersonalComputerResult(form, locale);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResults handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
            Locale locale) {

        return personalComputerHelper.setErrorResults(e.getBindingResult().getFieldErrors(),
                locale);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResults handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
            Locale locale) {

        return personalComputerHelper.setHttpMessageNotReadableExceptionResults(e, locale);
    }

}
