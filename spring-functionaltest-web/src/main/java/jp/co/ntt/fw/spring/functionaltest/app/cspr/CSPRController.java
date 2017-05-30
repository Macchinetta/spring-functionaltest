/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.cspr;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("cspr")
public class CSPRController {

    @RequestMapping
    public String handle() {
        return "cspr/index";
    }

    @RequestMapping(value = "deleteSession", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Void> handleDeleteSession(
            SessionStatus sessionStatus, HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
