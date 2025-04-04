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
package jp.co.ntt.fw.spring.functionaltest.app.thym;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("0301")
public class THYM0301Controller {

    @GetMapping("001")
    public String handle001(Model model) {
        model.addAttribute(new SearchForm());
        return "thym/commentHtml";
    }

    @GetMapping("002")
    public String handle002(Model model) {
        model.addAttribute("items", createItems());
        return "thym/commentParserLevel";
    }

    @GetMapping("003")
    public String handle003(Model model) {
        model.addAttribute("items", createItems());
        return "thym/commentPrototypeOnly";
    }

    private List<Item> createItems() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Peach", 1000));
        items.add(new Item("Grape", 2000));
        items.add(new Item("Melon", 3000));
        items.add(new Item("PineApple", 4000));
        items.add(new Item("Orange", 5000));
        return items;
    }

}
