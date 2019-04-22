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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Todo;
import jp.co.ntt.fw.spring.functionaltest.domain.service.soap.TodoProxyService;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.exception.SystemException;

@RequestMapping("todo/{proxy}")
@Controller
public class SOAPTodoController {

    @Inject
    Mapper beanMapper;

    @Inject
    TodoProxyService todoProxyService;

    @Value("${app.upload.temporaryDirectory}")
    File temporaryDirectory;

    @ModelAttribute("proxy")
    public String setUpProxy(@PathVariable("proxy") String proxy) {
        return proxy;
    }

    @ModelAttribute
    public TodoForm setUpTodoForm() {
        return new TodoForm();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // bind empty strings as null
        binder.registerCustomEditor(String.class,
                new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @PathVariable("proxy") String proxy) {

        List<Todo> todos = todoProxyService.getTodos(proxy);
        model.addAttribute("todos", todos);
        return "soap/list";
    }

    @RequestMapping(value = "deleteAll", method = RequestMethod.GET)
    public String deleteAll(Model model, @PathVariable("proxy") String proxy) {

        todoProxyService.deleteTodos(proxy);
        return "soap/list";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET, params = {
            "form" })
    public String createForm() {
        return "soap/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(Model model, TodoForm form,
            @PathVariable("proxy") String proxy) {

        Todo todo = todoProxyService.createTodo(proxy, beanMapper.map(form,
                Todo.class));
        model.addAttribute("todo", todo);
        return "soap/todo";
    }

    @RequestMapping(value = "get", method = RequestMethod.GET, params = {
            "form" })
    public String getForm() {
        return "soap/get";
    }

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public String get(Model model, TodoForm form,
            @PathVariable("proxy") String proxy) {

        Todo todo = todoProxyService.getTodo(proxy, form.getTodoId());
        model.addAttribute("todo", todo);
        return "soap/todo";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET, params = {
            "form" })
    public String updateForm() {
        return "soap/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Model model, TodoForm form,
            @PathVariable("proxy") String proxy) {

        Todo todo = todoProxyService.updateTodo(proxy, beanMapper.map(form,
                Todo.class));
        model.addAttribute("todo", todo);
        return "soap/todo";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET, params = {
            "form" })
    public String deleteForm() {
        return "soap/delete";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(Model model, TodoForm form,
            @PathVariable("proxy") String proxy) {

        todoProxyService.deleteTodo(proxy, form.getTodoId());
        return "soap/todo";
    }

    @RequestMapping(value = "confirmHandler", method = RequestMethod.GET)
    public String confirmHandler(Model model,
            @PathVariable("proxy") String proxy) {

        todoProxyService.confirmHandler(proxy);
        return "soap/index";
    }

    @RequestMapping(value = "requestTimeout", method = RequestMethod.GET)
    public String requestTimeout(Model model,
            @PathVariable("proxy") String proxy) throws InterruptedException {

        todoProxyService.requestTimeout(proxy);
        return "soap/index";
    }

    @RequestMapping(value = "upload", method = RequestMethod.GET, params = {
            "form" })
    public String uploadForm(UploadFileForm form) {
        return "soap/upload";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload(Model model, @Validated UploadFileForm form,
            BindingResult result, @PathVariable("proxy") String proxy) {

        if (result.hasErrors()) {
            return uploadForm(form);
        }

        temporaryDirectory.mkdirs();
        File temporaryFile = new File(temporaryDirectory, UUID.randomUUID()
                .toString());

        try {
            Files.copy(form.getMultipartFile().getInputStream(), temporaryFile
                    .toPath());
            todoProxyService.uploadFile(proxy, temporaryFile);
        } catch (IOException e) {
            throw new SystemException("e.sf.cmmn.9001", e);
        } finally {
            // deleteメソッドによる削除の成功失敗によってその後のアクションをとることは無いため、SonarQube指摘は未対応としています。
            temporaryFile.delete();
        }

        return "soap/index";
    }

}
