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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.ntt.fw.spring.functionaltest.domain.service.soap.WsimportTodoProxyService;
import jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport.Todo;

/**
 * Web service test controller. (本コントローラで処理される内容は、全てSOAPサーバー側に通信を行い処理されます。
 * wsimportを使用したソースをもとに、SOAPサービスを呼び出す。)
 */
@RequestMapping("todo/wsimport")
@Controller
public class SOAPWsimportTodoController {

    @Inject
    WsimportTodoProxyService wsimportTodoService;

    /**
     * Get all todo list. (JaxWsPortProxyFactoryBeanを使用してアクセス)
     * @param model Model
     * @return list page
     */
    @GetMapping(value = "list/jaxWsPortProxy")
    public String getTodosJaxWsPortProxy(Model model) {

        List<Todo> todos = wsimportTodoService.getTodosJaxWsPortProxy();
        model.addAttribute("todos", todos);
        return "soap/list";
    }

    /**
     * Get all todo list. (WSDLファイルから生成したクライアントよりアクセス)
     * @param model Model
     * @return list page
     */
    @GetMapping(value = "list/wsimport")
    public String getTodosWsimport(Model model) {

        List<Todo> todos = wsimportTodoService.getTodosWsimport();
        model.addAttribute("todos", todos);
        return "soap/list";
    }
}
