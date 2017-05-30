/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.ajax.MessageBoardService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "ajax/testdata")
@Controller
public class AJAXTestDataController {

    @Inject
    MessageBoardService messageBoardService;

    @RequestMapping(value = "messageBoards", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> delete() {
        messageBoardService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
