/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import java.util.Arrays;
import java.util.Locale;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.app.ajax.ErrorResults;
import jp.co.ntt.fw.spring.functionaltest.domain.model.MessageBoard;
import jp.co.ntt.fw.spring.functionaltest.domain.service.ajax.MessageBoardService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("ajax")
@Controller
public class AJAX0104Controller {

    @Inject
    MessageBoardService messageBoardService;

    @Inject
    MessageBoardHelper messageBoardHelper;

    @RequestMapping(value = "0104/write", method = RequestMethod.POST)
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
