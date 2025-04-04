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

import java.net.URI;
import java.util.Collection;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
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
@RequestMapping("jsp/auth/todo/01")
public class OTH201Controller {

    private static final String MAJOR_ITEM_ID = "01";

    @Inject
    @Named("todoServiceImpl")
    TodoService todoService;

    @Inject
    @Named("todoMethodAnnotationServiceImpl")
    TodoService todoMethodAnnotationService;

    @Inject
    TodoFormMapper beanMapper;

    @ModelAttribute
    public TodoForm setUpForm() {
        return new TodoForm();
    }

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("id", MAJOR_ITEM_ID);
        return "jsp/todo/authorizationCodeIndex";
    }

    @GetMapping("list")
    public String list(Model model, @RequestParam(name = "registrationId") String registrationId,
            @RequestParam(name = "resourceProtect") String resourceProtect) {

        @SuppressWarnings("unchecked")
        Collection<Todo> todos = switch (resourceProtect) {
            case "intercepturl" -> this.todoService.findAll(registrationId);
            case "annotation" -> this.todoMethodAnnotationService.findAll(registrationId);
            default -> CollectionUtils.EMPTY_COLLECTION;
        };

        model.addAttribute("todos", todos);
        model.addAttribute("registrationId", registrationId);
        model.addAttribute("resourceProtect", resourceProtect);
        model.addAttribute("id", MAJOR_ITEM_ID);
        return "jsp/todo/list";
    }

    @PostMapping("create")
    public String create(@Validated({Default.class, TodoCreate.class}) TodoForm todoForm,
            BindingResult bindingResult, Model model, RedirectAttributes attributes,
            @RequestParam(name = "registrationId") String registrationId,
            @RequestParam(name = "resourceProtect") String resourceProtect) {

        if (bindingResult.hasErrors()) {
            return list(model, registrationId, resourceProtect);
        }

        Todo todo = this.beanMapper.map(todoForm);

        try {
            switch (resourceProtect) {
                case "intercepturl" -> this.todoService.create(todo, registrationId);
                case "annotation" -> this.todoMethodAnnotationService.create(todo, registrationId);
                default -> throw new IllegalStateException();
            }
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model, registrationId, resourceProtect);
        }

        attributes.addFlashAttribute(
                ResultMessages.success().add(ResultMessage.fromText("Created successfully!")));

        URI location = createRedirectUri(registrationId, resourceProtect).toUri();
        return "redirect:" + location.toString();
    }

    @PostMapping("finish")
    public String finish(@Validated({Default.class, TodoFinish.class}) TodoForm form,
            BindingResult bindingResult, Model model, RedirectAttributes attributes,
            @RequestParam(name = "registrationId") String registrationId,
            @RequestParam(name = "resourceProtect") String resourceProtect) {

        if (bindingResult.hasErrors()) {
            return list(model, registrationId, resourceProtect);
        }

        try {
            switch (resourceProtect) {
                case "intercepturl" -> this.todoService.finish(form.getTodoId(), registrationId);
                case "annotation" -> this.todoMethodAnnotationService.finish(form.getTodoId(),
                        registrationId);
                default -> throw new IllegalStateException();
            }
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model, registrationId, resourceProtect);
        }

        attributes.addFlashAttribute(
                ResultMessages.success().add(ResultMessage.fromText("Finished successfully!")));

        URI location = createRedirectUri(registrationId, resourceProtect).toUri();
        return "redirect:" + location.toString();
    }

    @PostMapping("delete")
    public String delete(@Validated({Default.class, TodoDelete.class}) TodoForm form,
            BindingResult bindingResult, Model model, RedirectAttributes attributes,
            @RequestParam(name = "registrationId") String registrationId,
            @RequestParam(name = "resourceProtect") String resourceProtect) {

        if (bindingResult.hasErrors()) {
            return list(model, registrationId, resourceProtect);
        }

        try {
            switch (resourceProtect) {
                case "intercepturl" -> this.todoService.delete(form.getTodoId(), registrationId);
                case "annotation" -> this.todoMethodAnnotationService.delete(form.getTodoId(),
                        registrationId);
                default -> throw new IllegalStateException();
            }
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model, registrationId, resourceProtect);
        }

        attributes.addFlashAttribute(
                ResultMessages.success().add(ResultMessage.fromText("Deleted successfully!")));

        URI location = createRedirectUri(registrationId, resourceProtect).toUri();
        return "redirect:" + location.toString();
    }

    private UriComponents createRedirectUri(String registrationId, String resourceProtect) {
        return MvcUriComponentsBuilder.fromMethodName(OTH201Controller.class, "list",
                new Object() {}, registrationId, resourceProtect).build();
    }
}
