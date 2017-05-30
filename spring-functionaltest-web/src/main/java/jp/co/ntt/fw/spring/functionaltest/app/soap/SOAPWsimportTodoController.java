/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.soap;

import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.soap.WsimportTodoProxyService;
import jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport.Todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("todo/wsimport")
@Controller
public class SOAPWsimportTodoController {

    @Inject
    WsimportTodoProxyService todoProxyService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getTodos(Model model) {

        List<Todo> todos = todoProxyService.getTodos();
        model.addAttribute("todos", todos);
        return "soap/list";
    }

}
