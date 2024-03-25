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
package jp.co.ntt.fw.spring.functionaltest.app.todo;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.groups.Default;
import jp.co.ntt.fw.spring.functionaltest.app.todo.TodoForm.TodoCreate;
import jp.co.ntt.fw.spring.functionaltest.app.todo.TodoForm.TodoDelete;
import jp.co.ntt.fw.spring.functionaltest.app.todo.TodoForm.TodoFinish;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Todo;
import jp.co.ntt.fw.spring.functionaltest.domain.service.todo.TodoService;

@Controller
@RequestMapping("noauth/todo")
public class TodoNoAuthController {

    @Inject
    @Named("todoNoAuthServiceImpl")
    TodoService todoService;

    @Inject
    TodoFormMapper beanMapper;

    @ModelAttribute
    public TodoForm setUpForm() {
        TodoForm form = new TodoForm();
        return form;
    }

    @GetMapping("list")
    public String list(Model model) {
        Collection<Todo> todos = this.todoService.findAll(null);
        model.addAttribute("todos", todos);
        return "todo/listForTesting";
    }

    @PostMapping("create")
    public String create(@Validated({ Default.class,
            TodoCreate.class }) TodoForm todoForm, BindingResult bindingResult,
            Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return list(model);
        }

        Todo todo = this.beanMapper.map(todoForm);

        try {
            this.todoService.create(todo, null);
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage
                .fromText("Created successfully!")));
        return "redirect:/noauth/todo/list";
    }

    @PostMapping("finish")
    public String finish(@Validated({ Default.class,
            TodoFinish.class }) TodoForm form, BindingResult bindingResult,
            Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return list(model);
        }

        try {
            this.todoService.finish(form.getTodoId(), null);
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage
                .fromText("Finished successfully!")));
        return "redirect:/noauth/todo/list";
    }

    @PostMapping("delete")
    public String delete(@Validated({ Default.class,
            TodoDelete.class }) TodoForm form, BindingResult bindingResult,
            Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return list(model);
        }

        try {
            this.todoService.delete(form.getTodoId(), null);
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage
                .fromText("Deleted successfully!")));
        return "redirect:/noauth/todo/list";
    }

    @GetMapping("deleteall")
    public String deleteAll(Model model) {
        try {
            this.todoService.deleteAll();
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        return "redirect:/noauth/todo/list";
    }

}
