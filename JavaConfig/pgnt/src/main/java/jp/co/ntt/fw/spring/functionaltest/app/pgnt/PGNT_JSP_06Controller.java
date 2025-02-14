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
package jp.co.ntt.fw.spring.functionaltest.app.pgnt;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Person;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.CelebritySearchCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.PersonSearchCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.service.pgnt.PersonService;

@RequestMapping("jsp")
@Controller
public class PGNT_JSP_06Controller {

    @Inject
    protected PersonService personService;

    @ModelAttribute
    public CelebritySearchCriteria setUpCelebritySearchForm() {
        CelebritySearchCriteria criteria = new CelebritySearchCriteria();
        return criteria;
    }

    @ModelAttribute
    public PersonSearchCriteria setUpPersonSearchForm() {
        PersonSearchCriteria criteria = new PersonSearchCriteria();
        return criteria;
    }

    @GetMapping(value = "0602/001")
    public String handle0602001(Model model, @PageableDefault(size = 100) Pageable pageable) {
        Page<Person> page = personService.getPersons(new PersonSearchCriteria(), pageable);
        model.addAttribute("page", page);
        return "jsp/pgnt/personList";
    }

    @GetMapping(value = "0602/002")
    public String handle0602002(Model model, @PageableDefault(size = 30) Pageable pageable) {
        Page<Person> page = personService.getPersons(new PersonSearchCriteria(), pageable);
        model.addAttribute("page", page);
        return "jsp/pgnt/personList";
    }

    @GetMapping(value = "0602/003")
    public String handle0602003(Model model, @PageableDefault(size = 15) Pageable pageable) {
        Page<Person> page = personService.getPersons(new PersonSearchCriteria(), pageable);
        model.addAttribute("page", page);
        return "jsp/pgnt/personList";
    }

    @GetMapping(value = "0602/004")
    public String handle0602004(Model model, @PageableDefault Pageable pageable) {
        List<Person> nameList = new ArrayList<Person>();
        model.addAttribute("page", new PageImpl<Person>(nameList));
        return "jsp/pgnt/personList";
    }

    @GetMapping(value = "0602/005")
    public String handle0602005(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("page", null);
        return "jsp/pgnt/personList";
    }

    @GetMapping(value = "0602/006")
    public String handle0602006(Model model, @PageableDefault(size = 100) Pageable pageable) {
        Page<Person> page = personService.getPersons(new PersonSearchCriteria(), pageable);
        model.addAttribute("page", page);
        return "jsp/pgnt/personList";
    }

    @GetMapping(value = "0602/007")
    public String handle0602007(Model model, @PageableDefault(size = 100) Pageable pageable) {
        Page<Person> page = personService.getPersons(new PersonSearchCriteria(), pageable);
        model.addAttribute("page", page);
        return "jsp/pgnt/personList";
    }

    @GetMapping(value = "0602/008")
    public String handle0602008(Model model, @PageableDefault(size = 100) Pageable pageable) {
        Page<Person> page = personService.getPersons(new PersonSearchCriteria(), pageable);
        model.addAttribute("page", page);
        return "jsp/pgnt/personList";
    }

    @GetMapping(value = "0602/009")
    public String handle0602009(Model model, @PageableDefault(size = 15) Pageable pageable) {
        Page<Person> page = personService.getPersons(new PersonSearchCriteria(), pageable);
        model.addAttribute("page", page);
        return "jsp/pgnt/personList";
    }

    @GetMapping(value = "0602/010")
    public String handle0602010(Model model, @PageableDefault(size = 15) Pageable pageable) {
        Page<Person> page = personService.getPersons(new PersonSearchCriteria(), pageable);
        model.addAttribute("page", page);
        return "jsp/pgnt/personList";
    }

    @GetMapping(value = "0602/011")
    public String handle0602011(Model model, @PageableDefault(size = 15) Pageable pageable) {
        Page<Person> page = personService.getPersons(new PersonSearchCriteria(), pageable);
        model.addAttribute("page", page);
        return "jsp/pgnt/personList";
    }

    @GetMapping(value = "0603/001")
    public String handle0603001(Model model) {
        model.addAttribute("path", "celebritySearch");
        return "jsp/pgnt/celebrityList";
    }

    @GetMapping(value = "0603/002")
    public String handle0603002(Model model) {
        model.addAttribute("path", "celebritySearch");
        return "jsp/pgnt/celebrityList";
    }

}
