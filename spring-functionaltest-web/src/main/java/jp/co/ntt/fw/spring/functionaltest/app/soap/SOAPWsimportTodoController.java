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
