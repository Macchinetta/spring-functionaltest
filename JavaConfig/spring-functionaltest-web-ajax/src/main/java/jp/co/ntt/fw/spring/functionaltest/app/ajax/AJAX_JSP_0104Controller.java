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

import java.util.Arrays;
import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.MessageBoard;
import jp.co.ntt.fw.spring.functionaltest.domain.service.ajax.MessageBoardService;

@RequestMapping("jsp")
@Controller
public class AJAX_JSP_0104Controller {

    @Inject
    MessageBoardService messageBoardService;

    @Inject
    MessageBoardHelper messageBoardHelper;

    @PostMapping(value = "0104/write")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public MessageBoardResult editForXml(
            @Validated @RequestBody MessageBoardForm form) {

        MessageBoard messageBoard = new MessageBoard();
        String comment = form.getComment();
        messageBoard.setComment(comment);
        messageBoardService.register(messageBoard);

        MessageBoardResult messageBoardResult = new MessageBoardResult();
        messageBoardResult.setResultMessages(Arrays.asList(comment));

        return messageBoardResult;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResults handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e, Locale locale) {

        return messageBoardHelper.setErrorResults(e.getBindingResult()
                .getFieldErrors(), locale);
    }

}
