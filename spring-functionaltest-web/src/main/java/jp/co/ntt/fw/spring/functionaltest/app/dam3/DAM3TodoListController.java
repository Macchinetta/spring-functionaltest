/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dam3.TodoMB3Service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("dam3/todo")
@Controller
public class DAM3TodoListController {

    @Inject
    TodoMB3Service todoMB3Service;

    @ModelAttribute
    public TodoForm setUpForm() {
        return new TodoForm();
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {

        String returnView = "";

        List<TodoMB3> todolist = todoMB3Service.findAllTodos();

        model.addAttribute("todos", todolist);
        model.addAttribute("isPaginated", false);
        returnView = "dam3/todoList";
        return returnView;
    }

}
