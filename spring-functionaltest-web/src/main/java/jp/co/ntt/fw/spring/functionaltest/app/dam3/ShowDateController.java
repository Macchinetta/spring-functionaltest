/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DateMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dam3.TodoMB3ForJSR310Service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("dam3/show")
@Controller
public class ShowDateController {

    @Inject
    TodoMB3ForJSR310Service todoMB3ForJSR310Service;

    @RequestMapping(value = "date", method = RequestMethod.GET)
    public String getDate(Model model) {

        DateMB3 dateMB3 = todoMB3ForJSR310Service
                .findCreatedAtOne("0000000010");

        model.addAttribute("resultDate", dateMB3.getLocalDate());

        return "dam3/showDate";
    }

}
