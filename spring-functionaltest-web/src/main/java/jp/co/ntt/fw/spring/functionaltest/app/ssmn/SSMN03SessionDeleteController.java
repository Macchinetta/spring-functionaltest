/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * セッション削除機能<br>
 * <p>
 * セッションを画面遷移の途中で削除したい場合、restTemplateを使用してこのControllerにアクセスする。
 * </p>
 */
@Controller
@SessionAttributes(value = { "memberForm" })
@RequestMapping(value = "ssmn/0301")
public class SSMN03SessionDeleteController {

    @RequestMapping(value = "deleteSession", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Void> handleDeleteSession(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
