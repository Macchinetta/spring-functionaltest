/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dtac;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dtac.UserListRoutingService;
import jp.co.ntt.fw.spring.functionaltest.infra.datasource.dtac.FixedJodaTimeDateFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("dtac/user")
public class DTACUserListController {

    @Inject
    UserListRoutingService userListRoutingService;

    @Inject
    FixedJodaTimeDateFactory dateFactory;

    @ModelAttribute
    public UserForm setUpForm() {
        return new UserForm();
    }

    @RequestMapping(value = "list")
    public String handleList(
            @PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {

        Page<User> users = userListRoutingService.getUsers(pageable);
        model.addAttribute("page", users);

        return "dtac/list";
    }

    @RequestMapping(value = "listOpen")
    public String handleListOpen(
            @PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {
        return handleListBoth(10, pageable, model);
    }

    @RequestMapping(value = "listClose")
    public String handleListClose(
            @PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {
        return handleListBoth(1, pageable, model);
    }

    private String handleListBoth(int hourOfDay, Pageable pageable, Model model) {
        Page<User> users = null;
        try {
            dateFactory.setHourOfDay(hourOfDay);
            users = userListRoutingService.getUsers(pageable);
        } finally {
            dateFactory.removeHourOfDayThreadLocal();
        }

        model.addAttribute("page", users);

        return "dtac/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.POST, params = "back")
    public String handleListBack(Model model) {
        return "redirect:/dtac/login";
    }

}
