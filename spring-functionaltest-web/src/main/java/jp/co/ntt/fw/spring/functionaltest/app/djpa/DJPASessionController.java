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
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.app.dam3.JPABookListForm;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookLZ;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPABookLZService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("djpa/book")
@SessionAttributes(types = JPABookLZ.class)
public class DJPASessionController {

    private static final Logger logger = LoggerFactory.getLogger(
            DJPASessionController.class);

    @Inject
    JPABookLZService jpaBookLZService;

    @ModelAttribute(value = "jpaBookListForm")
    public JPABookListForm setUpForm() {
        return new JPABookListForm();
    }

    /**
     * This method demonstrates when acquire Session outside of
     * OpenEntityManagerInViewInterceptor, LazyInitializationException occur.
     * @param model
     * @param bookListForm
     * @return
     */
    @RequestMapping(value = "interceptSrch", method = RequestMethod.POST, params = "registerSession")
    public String registerSession(Model model, JPABookListForm bookListForm) {
        Integer bookId = Integer.valueOf(bookListForm
                .getSearchInQueryBookIdAndRegisterSession());
        JPABookLZ book = jpaBookLZService.findById(bookId);
        book.setBlobCodeHex(new String(Hex.encode(book.getBlobCode()))
                .toUpperCase());

        // register Session
        model.addAttribute("book", book);
        return "djpa/registerConfirm";

    }

    @RequestMapping(value = "getRegisterSession", method = RequestMethod.POST)
    public String getRegisterSession(@SessionAttribute("book") JPABookLZ book,
            Model model) {

        // when acquire CategoryName, LazyInitializationException will thrown
        model.addAttribute("bookCategory", book.getCategory()
                .getCategoryName());

        model.addAttribute("bookForm", new BookForm());
        model.addAttribute("book", book);
        return "djpa/registerComplete";

    }

}
