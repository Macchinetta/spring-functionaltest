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
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.SystemException;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.AutoMapTodoMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.CategoryMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoCriteria2;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.TodoSearchCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dam3.TodoMB3Service;

@RequestMapping("jsp/todo")
@Controller
public class DAM3_JSP_TodoController {

    @Inject
    TodoMB3Service todoMB3Service;

    @Inject
    DAM3BeanMapper beanMapper;

    private static final Logger logger = LoggerFactory.getLogger(
            DAM3_JSP_TodoController.class);

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("uuuu/MM/dd").withLocale(Locale.JAPANESE)
            .withResolverStyle(ResolverStyle.STRICT);

    @ModelAttribute
    public TodoForm setUpForm() {
        return new TodoForm();
    }

    @GetMapping(value = "{id}/update")
    public String updateForm(@PathVariable("id") String todoId,
            @Validated TodoForm form, BindingResult result, Model model) {
        String returnView = "";

        TodoMB3 todo = todoMB3Service.findOneByTodoId(todoId);

        TodoForm todoForm = beanMapper.map(todo);
        todoForm.setDescription1(todo.getNormDesc1());
        todoForm.setDescription2(todo.getNormDesc2());
        if (null == todo.getCompleteAt()) {
            todoForm.setCompleteAt(null);
        }
        todoForm.setTodoCategory(todo.getCategory().getName());
        model.addAttribute("todo", todoForm);

        returnView = "jsp/dam3/todoUpdateForm";

        return returnView;
    }

    @PostMapping(value = "{id}/update")
    public String update(@PathVariable("id") String id,
            @Validated TodoForm form, BindingResult result,
            RedirectAttributes redirectAttrs, Model model) {
        TodoMB3 todoMB3 = beanMapper.map(form);
        CategoryMB3 cat = new CategoryMB3();
        cat.setName(form.getTodoCategory());
        todoMB3.setCategory(cat);
        todoMB3Service.update(todoMB3);
        redirectAttrs.addAttribute("complete", "");
        redirectAttrs.addAttribute("id", id);

        return "redirect:/jsp/todo/{id}/update";
    }

    @PostMapping(value = "update", params = "bulkUpdate")
    public String updateForGivenID(@Validated TodoForm form,
            BindingResult result, RedirectAttributes redirectAttributes,
            Model model) {
        String todoIdStr = form.getTodoIds();
        List<String> todoIds = null;
        if (todoIdStr.contains(",")) {
            String todoIDAr[] = todoIdStr.split(",");
            todoIds = Arrays.asList(todoIDAr);
        } else {
            String todoIDAr[] = { todoIdStr };
            todoIds = Arrays.asList(todoIDAr);
        }

        int totalUpdated = todoMB3Service.updateFinishedByTodIds(true, todoIds);

        redirectAttributes.addFlashAttribute("batchUp", true);
        redirectAttributes.addFlashAttribute("regCount", totalUpdated);

        return "redirect:/jsp/todo/list";
    }

    @PostMapping(value = "update", params = "batchRepoUpdt")
    public String batchRepoUpdate(@Validated TodoForm form,
            BindingResult result, RedirectAttributes redirectAttributes,
            Model model) {
        String todoIdStr = form.getTodoIds();
        List<String> todoIds = null;
        if (todoIdStr.contains(",")) {
            String todoIDAr[] = todoIdStr.split(",");
            todoIds = Arrays.asList(todoIDAr);
        } else {
            String todoIDAr[] = { todoIdStr };
            todoIds = Arrays.asList(todoIDAr);
        }

        int totalUpdated = todoMB3Service.updateUsingBatchFinishedByTodIds(true,
                todoIds);

        redirectAttributes.addFlashAttribute("batchRepoUp", true);
        redirectAttributes.addFlashAttribute("regCount", totalUpdated);

        return "redirect:/jsp/todo/list";
    }

    @PostMapping(value = "{id}/update", params = "updateOptimistic")
    public String updateOpt(@PathVariable("id") String id,
            @Validated TodoForm form, BindingResult result,
            RedirectAttributes redirectAttrs, Model model) {
        TodoMB3 todoMB3 = beanMapper.map(form);
        CategoryMB3 cat = new CategoryMB3();
        cat.setName(form.getTodoCategory());
        todoMB3.setCategory(cat);
        todoMB3Service.updateTodo(todoMB3);
        redirectAttrs.addAttribute("complete", "");
        redirectAttrs.addAttribute("id", id);

        return "redirect:/jsp/todo/{id}/update";
    }

    @GetMapping(value = "update", params = "complete")
    public String updateComplete(@RequestParam("id") String todoId,
            Model model) {

        TodoMB3 todo = todoMB3Service.findOneByTodoId(todoId);
        if (null == todo) {
            todo = new TodoMB3();
        }
        model.addAttribute("todo", todo);

        return "jsp/dam3/todoRegisterComplete";
    }

    @PostMapping(value = "{id}/update", params = "cancel")
    public String updateCancel(@PathVariable("id") String id,
            RedirectAttributes redirectAttrs) {
        return "redirect:/jsp/todo/list";
    }

    @PostMapping(value = "{id}/update", params = "delete")
    public String deleteTodoVyId(@PathVariable("id") String id,
            @Validated TodoForm form, BindingResult result,
            RedirectAttributes redirectAttrs, Model model) {
        todoMB3Service.deleteByTodoId(form.getTodoId());
        return "redirect:/jsp/todo/list";
    }

    @PostMapping(value = "{id}/update", params = "deleteByTodo")
    public String deleteByTodo(@PathVariable("id") String id,
            @Validated TodoForm form, BindingResult result,
            RedirectAttributes redirectAttrs, Model model) {
        TodoMB3 todoMB3 = beanMapper.map(form);
        todoMB3Service.delete(todoMB3);
        return "redirect:/jsp/todo/list";
    }

    @PostMapping(value = "update", params = "bulkDelete")
    public String deleteOldTodo(@Validated TodoForm form, BindingResult result,
            RedirectAttributes redirectAttributes, Model model) {

        int totalUpdated = todoMB3Service.deleteOlderFinishedTodo(form
                .getDeleteDate());

        redirectAttributes.addFlashAttribute("batchDL", true);
        redirectAttributes.addFlashAttribute("regCount", totalUpdated);

        return "redirect:/jsp/todo/list";
    }

    @PostMapping(value = "register", params = "form")
    public String registerForm(TodoForm form, Model model) {
        return "jsp/dam3/todoRegisterForm";
    }

    @PostMapping(value = "bulkReg")
    public String registerBulkTodo(TodoForm form, Model model) {

        todoMB3Service.addBulkTodo();
        return "redirect:/jsp/todo/list";
    }

    @PostMapping(value = "bulkReg", params = "batchReg")
    public String registerinBatch(TodoForm form,
            RedirectAttributes redirectAttributes) {

        int totalRegistered = todoMB3Service.createInBatch();
        redirectAttributes.addFlashAttribute("batchReg", true);
        redirectAttributes.addFlashAttribute("regCount", totalRegistered);
        return "redirect:/jsp/todo/list";
    }

    @PostMapping(value = "addByReUse")
    public String registerBulkTodoUsingReUse(TodoForm form, Model model) {

        todoMB3Service.addTodoReUseMode();
        return "redirect:/jsp/todo/list";
    }

    @PostMapping(value = "register", params = "register")
    public String register(@Validated TodoForm form, BindingResult result,
            RedirectAttributes redirectAttrs, Model model) {
        if (result.hasErrors()) {
            return registerRedo(form, model);
        }

        TodoMB3 todoMB3 = beanMapper.map(form);
        CategoryMB3 cat = new CategoryMB3();
        cat.setName(form.getTodoCategory());
        todoMB3.setCategory(cat);
        todoMB3 = todoMB3Service.addTodo(todoMB3);

        redirectAttrs.addAttribute("complete", "");
        redirectAttrs.addAttribute("id", form.getTodoId());

        return "redirect:/jsp/todo/register";
    }

    @PostMapping(value = "register", params = "registerAndRetBool")
    public String registerAndRetBoolean(@Validated TodoForm form,
            BindingResult result, RedirectAttributes redirectAttrs,
            Model model) {
        if (result.hasErrors()) {
            return registerRedo(form, model);
        }

        TodoMB3 todoMB3 = beanMapper.map(form);
        CategoryMB3 cat = new CategoryMB3();
        cat.setName(form.getTodoCategory());
        todoMB3.setCategory(cat);
        boolean regResult = todoMB3Service.createAndReturnBoolean(todoMB3);

        redirectAttrs.addAttribute("id", form.getTodoId());

        redirectAttrs.addAttribute("verifyBooleanPrimitiveRet", true);
        redirectAttrs.addAttribute("regResult", regResult);

        return "redirect:/jsp/todo/register";
    }

    @PostMapping(value = "register", params = "registerAndRetInt")
    public String registerAndRetInt(@Validated TodoForm form,
            BindingResult result, RedirectAttributes redirectAttrs,
            Model model) {
        if (result.hasErrors()) {
            return registerRedo(form, model);
        }

        TodoMB3 todoMB3 = beanMapper.map(form);
        CategoryMB3 cat = new CategoryMB3();
        cat.setName(form.getTodoCategory());
        todoMB3.setCategory(cat);
        int regResult = todoMB3Service.createAndReturnInt(todoMB3);

        redirectAttrs.addAttribute("id", form.getTodoId());

        redirectAttrs.addAttribute("verifyIntegerPrimitiveRet", true);
        redirectAttrs.addAttribute("regResult", regResult);

        return "redirect:/jsp/todo/register";
    }

    @PostMapping(value = "register", params = "registerForNull")
    public String registerForNullCheck(@Validated TodoForm form,
            BindingResult result, RedirectAttributes redirectAttrs,
            Model model) {
        if (result.hasErrors()) {
            return registerRedo(form, model);
        }

        TodoMB3 todoMB3 = beanMapper.map(form);
        CategoryMB3 cat = new CategoryMB3();
        cat.setName(form.getTodoCategory());
        todoMB3.setCategory(cat);
        todoMB3 = todoMB3Service.addTodoWithNullTitle(todoMB3);

        redirectAttrs.addAttribute("complete", "");
        redirectAttrs.addAttribute("id", form.getTodoId());

        return "redirect:/jsp/todo/register";
    }

    @PostMapping(value = "register", params = "registerRollback")
    public String registerRollback(@Validated TodoForm form,
            BindingResult result, RedirectAttributes redirectAttrs,
            Model model) {
        if (result.hasErrors()) {
            return registerRedo(form, model);
        }

        TodoMB3 todoMB3 = beanMapper.map(form);
        CategoryMB3 cat = new CategoryMB3();
        cat.setName(form.getTodoCategory());
        todoMB3.setCategory(cat);
        todoMB3 = todoMB3Service.addTodoForRollback(todoMB3);

        redirectAttrs.addAttribute("complete", "");
        redirectAttrs.addAttribute("id", form.getTodoId());

        return "redirect:/jsp/todo/register";
    }

    @GetMapping(value = "register", params = "complete")
    public String registerComplete(@RequestParam("id") String todoId,
            Model model) {

        TodoMB3 todo = todoMB3Service.findOneByTodoId(todoId);
        if (null == todo) {
            todo = new TodoMB3();
        }

        TodoForm todoForm = beanMapper.map(todo);
        if (null != todo.getCategory()) {
            todoForm.setTodoCategory(todo.getCategory().getName());
        }
        model.addAttribute("todo", todoForm);

        return "jsp/dam3/todoRegisterComplete";
    }

    @GetMapping(value = "register", params = "verifyBooleanPrimitiveRet")
    public String registerCompleteBoolean(@RequestParam("id") String todoId,
            @RequestParam("verifyBooleanPrimitiveRet") boolean verifyBooleanPrimitiveRet,
            @RequestParam("regResult") String regResult, Model model) {

        TodoMB3 todo = todoMB3Service.findOneByTodoId(todoId);
        if (null == todo) {
            todo = new TodoMB3();
        }

        TodoForm todoForm = beanMapper.map(todo);
        if (null != todo.getCategory()) {
            todoForm.setTodoCategory(todo.getCategory().getName());
        }
        model.addAttribute("todo", todoForm);
        model.addAttribute("verifyBooleanPrimitiveRet",
                verifyBooleanPrimitiveRet);
        model.addAttribute("regResult", regResult);

        return "jsp/dam3/todoRegisterComplete";
    }

    @GetMapping(value = "register", params = "verifyIntegerPrimitiveRet")
    public String registerComplete(@RequestParam("id") String todoId,
            @RequestParam("verifyIntegerPrimitiveRet") boolean verifyIntegerPrimitiveRet,
            @RequestParam("regResult") String regResult, Model model) {

        TodoMB3 todo = todoMB3Service.findOneByTodoId(todoId);
        if (null == todo) {
            todo = new TodoMB3();
        }

        TodoForm todoForm = beanMapper.map(todo);
        if (null != todo.getCategory()) {
            todoForm.setTodoCategory(todo.getCategory().getName());
        }
        model.addAttribute("todo", todoForm);
        model.addAttribute("verifyIntegerPrimitiveRet",
                verifyIntegerPrimitiveRet);
        model.addAttribute("regResult", regResult);

        return "jsp/dam3/todoRegisterComplete";
    }

    @PostMapping(value = "register", params = "autoIdGenRegister")
    public String registerWithAutoIDGen(@Validated TodoForm form,
            BindingResult result, RedirectAttributes redirectAttrs,
            Model model) {
        if (result.hasErrors()) {
            return registerRedo(form, model);
        }

        TodoMB3 todoMB3 = beanMapper.map(form);
        CategoryMB3 cat = new CategoryMB3();
        cat.setName(form.getTodoCategory());
        todoMB3.setCategory(cat);
        todoMB3 = todoMB3Service.createUsingAutoIncreament(todoMB3);

        redirectAttrs.addAttribute("complete", "");
        redirectAttrs.addAttribute("id", todoMB3.getTodoId());

        return "redirect:/jsp/todo/register";
    }

    @PostMapping(value = "register", params = "redo")
    public String registerRedo(TodoForm form, Model model) {

        model.addAttribute("todo", form);

        return "jsp/dam3/todoRegisterForm";
    }

    @GetMapping(value = "/search", params = "autoMapSrch")
    public String search(@Validated TodoForm form, BindingResult result,
            Model model) {
        String returnView = "";

        TodoMB3 todo = todoMB3Service.selectByAutoMap(form.getTodoId());

        TodoForm todoForm = beanMapper.map(todo);
        todoForm.setTodoCategory(todo.getCategory().getName());
        model.addAttribute("todo", todoForm);

        returnView = "jsp/dam3/todoRegisterComplete";

        return returnView;
    }

    @GetMapping(value = "/search", params = "asClauseSrch")
    public String searchUsingAs(@Validated TodoForm form, BindingResult result,
            Model model) {
        String returnView = "";

        TodoMB3 todo = todoMB3Service.selectByAsClause(form.getTodoId());

        TodoForm todoForm = beanMapper.map(todo);
        todoForm.setTodoCategory(todo.getCategory().getName());
        model.addAttribute("todo", todoForm);

        returnView = "jsp/dam3/todoRegisterComplete";

        return returnView;
    }

    @GetMapping(value = "/search", params = "pkSrch")
    public String searchUsingPK(@Validated TodoForm form, BindingResult result,
            Model model) {
        String returnView = "";

        returnView = findByPk(form, model);

        return returnView;
    }

    @GetMapping(value = "/search", params = "pkSrchNG")
    public String searchUsingPKNA(@Validated TodoForm form,
            BindingResult result, Model model) {
        String returnView = "";

        returnView = findByPk(form, model);

        return returnView;
    }

    @GetMapping(value = "/search", params = "cmpKeySrch")
    public String searchUsingCmpKey(@Validated TodoForm form,
            BindingResult result, Model model) {
        String returnView = "";

        TodoMB3 todo = todoMB3Service.findOneUsingCompositeKey(form.getTodoId(),
                form.getTodoCategory());

        TodoForm todoForm = beanMapper.map(todo);
        todoForm.setTodoCategory(todo.getCategory().getName());
        model.addAttribute("todo", todoForm);

        returnView = "jsp/dam3/todoRegisterComplete";

        return returnView;
    }

    @GetMapping(value = "/search", params = "cmpKeySrchNoParamAnnt")
    public String searchUsingCmpKeyNoParamAnnot(@Validated TodoForm form,
            BindingResult result, Model model) {
        String returnView = "";

        TodoMB3 todo = todoMB3Service.findOneUsingCompositeKeyNoParamAnnot(form
                .getTodoId(), form.getTodoCategory());

        TodoForm todoForm = beanMapper.map(todo);
        todoForm.setTodoCategory(todo.getCategory().getName());
        model.addAttribute("todo", todoForm);

        returnView = "jsp/dam3/todoRegisterComplete";

        return returnView;
    }

    @GetMapping(value = "/search", params = "criteriaSrch")
    public String searchUsingCriteria(@Validated TodoForm form,
            BindingResult result, Model model) {
        String returnView = "";

        TodoCriteria todoCriteria = new TodoCriteria();

        todoCriteria.setCreatedAt(form.getCreatedAt());
        todoCriteria.setTitle(form.getTodoTitle());

        List<TodoMB3> todolist = todoMB3Service.findAllByCriteria(todoCriteria);

        model.addAttribute("todos", todolist);
        model.addAttribute("isPaginated", false);
        returnView = "jsp/dam3/todoList";

        return returnView;
    }

    @GetMapping(value = "/search", params = "criteriaSrchMapRes")
    public String searchUsingCriteriaResMap(@Validated TodoForm form,
            BindingResult result, Model model) {
        String returnView = "";

        TodoCriteria todoCriteria = new TodoCriteria();

        todoCriteria.setCreatedAt(form.getCreatedAt());
        todoCriteria.setTitle(form.getTodoTitle());

        Map<String, AutoMapTodoMB3> todoMap = todoMB3Service
                .findAllByCriteriaRetMap(todoCriteria);

        model.addAttribute("todoMapRes", todoMap);
        model.addAttribute("isPaginated", false);
        model.addAttribute("mapRes", true);
        returnView = "jsp/dam3/todoList";

        return returnView;
    }

    @GetMapping(value = "/search", params = "countByStatus")
    public String getCountForGivenStatus(@Validated TodoForm form,
            BindingResult result, Model model) {
        String returnView = "";

        boolean statSelected = form.isFinished();

        long count = todoMB3Service.getTodoCountForGivenStatus(statSelected);

        if (statSelected) {
            model.addAttribute("status", "Completed");

        } else {
            model.addAttribute("status", "InComplete");

        }
        model.addAttribute("count", count);
        model.addAttribute("todoForm", new TodoForm());
        returnView = "jsp/dam3/todoList";

        return returnView;
    }

    @GetMapping(value = "/search", params = "stdPageSearch")
    public String paginationSearchSTD(TodoForm form, BindingResult result,
            Model model, Pageable pageable) {
        String returnView = "";

        TodoCriteria todoCriteria = new TodoCriteria();

        todoCriteria.setCreatedAt(form.getCreatedAt());
        todoCriteria.setTitle(form.getTodoTitle());

        Page<TodoMB3> todoPages = todoMB3Service.searchTodos(todoCriteria,
                pageable);

        model.addAttribute("page", todoPages);
        model.addAttribute("isPaginated", true);
        model.addAttribute("mapRes", false);
        model.addAttribute("todoForm", form);
        returnView = "jsp/dam3/todoList";

        return returnView;
    }

    @GetMapping(value = "/search", params = "sqlRefPageSearch")
    public String paginationSearchSQLRef(TodoForm form, BindingResult result,
            Model model, Pageable pageable) {
        String returnView = "";

        TodoCriteria todoCriteria = new TodoCriteria();

        todoCriteria.setCreatedAt(form.getCreatedAt());
        todoCriteria.setTitle(form.getTodoTitle());

        Page<TodoMB3> todoPages = todoMB3Service.findPageByCriteriaSqLRef(
                todoCriteria, pageable);

        model.addAttribute("page", todoPages);
        model.addAttribute("isPaginated", true);
        model.addAttribute("mapRes", false);
        model.addAttribute("isSQLRfinePageSrch", true);
        model.addAttribute("todoForm", form);
        returnView = "jsp/dam3/todoList";

        return returnView;
    }

    @GetMapping(value = "/search", params = "ifSrch")
    public String ifElementUsage(TodoForm form, BindingResult result,
            Model model) {
        String returnView = "";

        TodoCriteria todoCriteria = new TodoCriteria();

        todoCriteria.setFinished(form.isFinished());
        todoCriteria.setTitle(form.getTodoTitle());
        List<TodoMB3> todolist = todoMB3Service.findAllByCriteriaIFEle(
                todoCriteria);

        model.addAttribute("isPaginated", false);
        model.addAttribute("mapRes", false);
        model.addAttribute("isSQLRfinePageSrch", false);
        model.addAttribute("todoForm", form);
        model.addAttribute("todos", todolist);
        returnView = "jsp/dam3/todoList";

        return returnView;
    }

    @GetMapping(value = "/search", params = "chooseSrch")
    public String chooseElementUsage(TodoForm form, BindingResult result,
            Model model) {
        String returnView = "";

        TodoCriteria todoCriteria = new TodoCriteria();

        todoCriteria.setCreatedAt(form.getCreatedAt());
        String title = form.getTodoTitle();
        if ("".equals(title)) {
            todoCriteria.setTitle(null);
        } else {
            todoCriteria.setTitle(title);
        }

        List<TodoMB3> todolist = todoMB3Service.findUsingChooseEle(
                todoCriteria);

        model.addAttribute("isPaginated", false);
        model.addAttribute("mapRes", false);
        model.addAttribute("isSQLRfinePageSrch", false);
        model.addAttribute("todoForm", form);
        model.addAttribute("todos", todolist);
        returnView = "jsp/dam3/todoList";

        return returnView;
    }

    @GetMapping(value = "/search", params = "bindSrch")
    public String bindElementUsage(TodoForm form, BindingResult result,
            Model model) {
        String returnView = "";

        TodoCriteria todoCriteria = new TodoCriteria();

        todoCriteria.setTitle(form.getTodoTitle());
        List<TodoMB3> todolist = todoMB3Service.findAllByCriteriaOSGNL(
                todoCriteria);

        model.addAttribute("isPaginated", false);
        model.addAttribute("mapRes", false);
        model.addAttribute("isSQLRfinePageSrch", false);
        model.addAttribute("todoForm", form);
        model.addAttribute("todos", todolist);
        returnView = "jsp/dam3/todoList";

        return returnView;
    }

    @GetMapping(value = "/search", params = "downloadTodo")
    public String downloadTodo(TodoForm form, BindingResult result,
            Model model) {
        String returnView = "";
        List<TodoMB3> todolist = null;

        TodoCriteria todoCriteria = new TodoCriteria();

        todoCriteria.setTitle(form.getTodoTitle());
        todoCriteria.setCreatedAt(form.getCreatedAt());
        File tempFile = null;
        try {
            tempFile = File.createTempFile("TodoList", ".csv");

            try (BufferedWriter downloadWriter = new BufferedWriter(new FileWriter(tempFile))) {

                todoMB3Service.downloadTodos(todoCriteria, downloadWriter);
            } catch (IOException e) {
                throw new SystemException("e.sf.dam3.8001", "Create csv file error!", e);
            }

            todolist = getTodoListFromCSVFile(tempFile);

        } catch (IOException e1) {
            throw new SystemException("e.sf.dam3.8001", "Create csv file error!", e1);
        } finally {
            if (tempFile != null) {
                // deleteメソッドによる削除の成功失敗によってその後のアクションをとることは無いため、SonarQube指摘は未対応としています。
                tempFile.delete();
            }
        }

        model.addAttribute("isPaginated", false);
        model.addAttribute("mapRes", false);
        model.addAttribute("isSQLRfinePageSrch", false);
        model.addAttribute("todoForm", new TodoForm());
        model.addAttribute("showCSVPath", true);
        if (tempFile != null) {
            model.addAttribute("csvFilePath", tempFile.getAbsoluteFile());
        }
        model.addAttribute("todos", todolist);
        returnView = "jsp/dam3/todoList";

        return returnView;
    }

    @GetMapping(value = "/search", params = "escpSrch")
    public String esacpeSrch(TodoForm form, BindingResult result, Model model) {
        String returnView = "";

        TodoCriteria todoCriteria = new TodoCriteria();

        todoCriteria.setTitle(form.getTodoTitle());
        List<TodoMB3> todolist = todoMB3Service.findAllByCriteriaEscapeSrch(
                todoCriteria);

        model.addAttribute("isPaginated", false);
        model.addAttribute("mapRes", false);
        model.addAttribute("isSQLRfinePageSrch", false);
        model.addAttribute("todoForm", form);
        model.addAttribute("todos", todolist);
        returnView = "jsp/dam3/todoList";

        return returnView;
    }

    @GetMapping(value = "/search", params = "spSrch")
    public String storedProcedureSearch(TodoForm form, BindingResult result,
            Model model) {
        String returnView = "";

        AutoMapTodoMB3 todo = todoMB3Service.findOneBySP(form.getTodoId());
        CategoryMB3 categoryMB3 = todoMB3Service.getCategory(todo
                .getCategoryId());

        TodoForm todoForm = beanMapper.mapToTodoForm(todo);
        todoForm.setTodoCategory(categoryMB3.getName());
        model.addAttribute("todo", todoForm);

        returnView = "jsp/dam3/todoRegisterComplete";

        return returnView;
    }

    @GetMapping(value = "/search", params = "clTypeAliasSrch")
    public String classTypeAliasSearch(TodoForm form, BindingResult result,
            Model model) {
        String returnView = "";

        TodoSearchCriteria todoSearchCriteria = new TodoSearchCriteria();

        todoSearchCriteria.setTitle(form.getTodoTitle());
        todoSearchCriteria.setCreatedAt(form.getCreatedAt());

        List<AutoMapTodoMB3> todos = todoMB3Service.findByUsingClassTypeAlias(
                todoSearchCriteria);
        List<TodoMB3> todolist = new ArrayList<TodoMB3>();
        for (AutoMapTodoMB3 autoMapTodoMB3 : todos) {
            TodoMB3 todoMB3 = beanMapper.mapToTodoMB3(autoMapTodoMB3);
            CategoryMB3 categoryMB3 = todoMB3Service.getCategory(autoMapTodoMB3
                    .getCategoryId());
            todoMB3.setCategory(categoryMB3);
            todolist.add(todoMB3);
        }

        model.addAttribute("todos", todolist);
        model.addAttribute("isPaginated", false);
        returnView = "jsp/dam3/todoList";

        return returnView;
    }

    @GetMapping(value = "/search", params = "typeAlOverWrDefName")
    public String classTypeAliasSearchOverwriteDefName(TodoForm form,
            BindingResult result, Model model) {
        String returnView = "";

        TodoCriteria2 todoSearchCriteria = new TodoCriteria2();

        todoSearchCriteria.setDailyTodoTitle(form.getTodoTitle());
        todoSearchCriteria.setCreatedAt(form.getCreatedAt());

        List<AutoMapTodoMB3> todos = todoMB3Service
                .findByUsingOverwrittenDefltTypeAliasName(todoSearchCriteria);
        List<TodoMB3> todolist = new ArrayList<TodoMB3>();
        for (AutoMapTodoMB3 autoMapTodoMB3 : todos) {
            TodoMB3 todoMB3 = beanMapper.mapToTodoMB3(autoMapTodoMB3);
            CategoryMB3 categoryMB3 = todoMB3Service.getCategory(autoMapTodoMB3
                    .getCategoryId());
            todoMB3.setCategory(categoryMB3);
            todolist.add(todoMB3);
        }

        model.addAttribute("todos", todolist);
        model.addAttribute("isPaginated", false);
        returnView = "jsp/dam3/todoList";

        return returnView;
    }

    private String findByPk(TodoForm form, Model model) {
        String returnView = "";
        TodoMB3 todo = todoMB3Service.findOne(form.getTodoId());

        TodoForm todoForm = beanMapper.map(todo);
        todoForm.setTodoCategory(todo.getCategory().getName());
        model.addAttribute("todo", todoForm);

        returnView = "jsp/dam3/todoRegisterComplete";
        return returnView;
    }

    private List<TodoMB3> getTodoListFromCSVFile(File tempFile) {
        List<TodoMB3> todolist = new ArrayList<TodoMB3>();
        File file = new File(tempFile.getAbsoluteFile().getAbsolutePath());
        try (FileReader fileRdr = new FileReader(file);
                BufferedReader bufRdr = new BufferedReader(fileRdr)) {
            String line = null;

            while ((line = bufRdr.readLine()) != null) {
                String todoArr[] = line.split(",");
                TodoMB3 todoMB3 = new TodoMB3();
                todoMB3.setTodoId(todoArr[0]);
                todoMB3.setTodoTitle(todoArr[1]);
                todoMB3.setFinished(Boolean.valueOf(todoArr[2]));
                todoMB3.setCreatedAt(LocalDate.parse(todoArr[3],
                        DATE_TIME_FORMATTER));
                todoMB3.setVersion(Long.valueOf(todoArr[4]));
                todolist.add(todoMB3);
            }
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        return todolist;
    }

}
