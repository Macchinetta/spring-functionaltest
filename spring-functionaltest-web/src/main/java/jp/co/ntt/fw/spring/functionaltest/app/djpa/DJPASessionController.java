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

import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookLZ;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPABookLZService;

@Controller
@RequestMapping("djpa/book")
@SessionAttributes(types = JPABookLZ.class)
public class DJPASessionController {

    @Inject
    JPABookLZService jpaBookLZService;

    @ModelAttribute(value = "jpaBookListForm")
    public JPABookListForm setUpForm() {
        return new JPABookListForm();
    }

    /**
     * This method demonstrates when acquire Session outside of OpenEntityManagerInViewInterceptor, LazyInitializationException
     * occur.
     * @param model
     * @param bookListForm
     * @return
     */
    @PostMapping(value = "interceptSrch", params = "registerSession")
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

    @PostMapping(value = "getRegisterSession")
    public String getRegisterSession(
            @ModelAttribute(name = "book", binding = false) JPABookLZ book,
            Model model, SessionStatus sessionStatus) {

        sessionStatus.setComplete();

        // when acquire CategoryName, LazyInitializationException will thrown
        model.addAttribute("bookCategory", book.getCategory()
                .getCategoryName());

        model.addAttribute("bookForm", new BookForm());
        model.addAttribute("book", book);
        return "djpa/registerComplete";

    }

}
