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
package jp.co.ntt.fw.spring.functionaltest.app.thym;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("thym/0802")
public class THYM0802Controller {

    @RequestMapping("003")
    public String handle003(Model model) {

        Item item = new Item();
        item.setName("Orange");
        model.addAttribute(item);
        return "thym/jsNaturalTemplate";
    }

    @RequestMapping("004")
    public String handle004(Model model) {

        // Strings
        model.addAttribute("strAttr", "Orange");

        // Numbers
        model.addAttribute("numberAttr", 123.456);

        // Booleans
        model.addAttribute("booleanAttr", true);

        // Arrays
        model.addAttribute("arrayAttr", new String[] { "abc", "def", "ghi" });

        // Collections
        Set<String> set = new LinkedHashSet<>();
        set.addAll(Arrays.asList("jkl", "mno", "pqr"));
        model.addAttribute("collectionAttr", set);

        // Maps
        Map<String, String> map = new HashMap<>();
        map.put("a", "abc");
        map.put("d", "def");
        map.put("g", "ghi");
        model.addAttribute("mapAttr", map);

        // Beans
        Item item = new Item();
        item.setName("Apple");
        item.setPrice(100);
        model.addAttribute("beanAttr", item);

        return "thym/jsObjectSerialize";
    }

    @RequestMapping("006")
    public String handle006(Model model) {

        List<Item> items = new ArrayList<>();
        items.add(new Item("Orange", 200));
        items.add(new Item("Orange Juice", 80));
        items.add(new Item("Orange Jam", 400));
        model.addAttribute("items", items);
        return "thym/jsTemplateExample";
    }

}
