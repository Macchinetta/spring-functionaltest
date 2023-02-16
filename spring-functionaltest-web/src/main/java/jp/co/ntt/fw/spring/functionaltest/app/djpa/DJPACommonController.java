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

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.DataBaseInfo;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABook;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookEG;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookLZ;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategory;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategoryEG;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategoryLZ;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPABookEGService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPABookLZService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPABookService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPACategoryEGService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPACategoryLzService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPACategoryService;

@Controller
@RequestMapping("djpa/book")
public class DJPACommonController {

    private static final Logger logger = LoggerFactory.getLogger(
            DJPACommonController.class);

    @Inject
    JPABookService jpaBookService;

    @Inject
    JPABookEGService jpaBookEGService;

    @Inject
    JPACategoryService jpaCategoryService;

    @Inject
    JPACategoryLzService jpaCategoryLzService;

    @Inject
    JPACategoryEGService jpaCategoryEGService;

    @Inject
    JPABookLZService jpaBookLZService;

    @Inject
    JpaVendorAdapter jpaVendorAdapter;

    @Inject
    DJPABeanMapper beaMapper;

    @ModelAttribute(value = "jpaBookListForm")
    public JPABookListForm setUpForm() {
        return new JPABookListForm();
    }

    @GetMapping(value = "list")
    public String handle0101001(Model model) {

        List<JPABook> bookList = jpaBookService.findAll();
        return displayJPAListPage(model, bookList);

    }

    @PostMapping(value = "register")
    public String registerBook(Model model, BookForm bookForm,
            RedirectAttributes redirectAttrs) {
        JPABook jpaBook = beaMapper.map(bookForm);

        JPACategory category = jpaCategoryService.getCategoryDetails(bookForm
                .getCategoryName());
        jpaBook.setCategory(category);
        jpaBook = jpaBookService.addBook(jpaBook);

        BookForm form = beaMapper.map(jpaBook);
        form.setCategoryName(category.getCategoryName());

        redirectAttrs.addAttribute("complete", "");
        redirectAttrs.addAttribute("id", form.getBookId());
        return "redirect:register";

    }

    @PostMapping(value = "register", params = "registerDIV")
    public String registerBookInJPABookLz(Model model, BookForm bookForm,
            RedirectAttributes redirectAttrs) {
        JPABookLZ jpaBook = beaMapper.mapLZ(bookForm);

        JPACategoryLZ category = jpaCategoryLzService.getCategoryDetails(
                bookForm.getCategoryName());
        jpaBook.setCategory(category);
        jpaBook = jpaBookLZService.addBook(jpaBook);

        BookForm form = beaMapper.mapLZ(jpaBook);
        form.setCategoryName(category.getCategoryName());

        redirectAttrs.addAttribute("complete", "");
        redirectAttrs.addAttribute("id", form.getBookId());
        return "redirect:register";

    }

    @PostMapping(value = "register", params = "saveAndFlush")
    public String registerBookUsingSaveAndFlush(BookForm bookForm,
            RedirectAttributes redirectAttrs) {
        JPABookEG jpaBook = beaMapper.mapEG(bookForm);

        JPACategoryEG category = jpaCategoryEGService.getCategoryDetails(
                bookForm.getCategoryName());
        jpaBook.setCategory(category);

        jpaBook.setBlobCode(jpaBook.getClobCode().getBytes());
        jpaBook = jpaBookEGService.saveAndFlush(jpaBook);

        BookForm form = beaMapper.mapEG(jpaBook);
        form.setCategoryName(category.getCategoryName());

        redirectAttrs.addAttribute("egComplete", "");
        redirectAttrs.addAttribute("bookId", form.getBookId());
        return "redirect:register";

    }

    @PostMapping(value = "register", params = "flush")
    public String registerBookUsingFlush(BookForm bookForm,
            RedirectAttributes redirectAttrs) throws InterruptedException {
        JPABookEG jpaBook = beaMapper.mapEG(bookForm);

        JPACategoryEG category = jpaCategoryEGService.getCategoryDetails(
                bookForm.getCategoryName());
        jpaBook.setCategory(category);

        jpaBook.setBlobCode(jpaBook.getClobCode().getBytes());
        jpaBook = jpaBookEGService.flush(jpaBook);

        BookForm form = beaMapper.mapEG(jpaBook);
        form.setCategoryName(category.getCategoryName());

        redirectAttrs.addAttribute("egComplete", "");
        redirectAttrs.addAttribute("bookId", form.getBookId());
        return "redirect:register";

    }

    @PostMapping(value = "register", params = "customRepoSave")
    public String registerBookUsingCustomRepo(BookForm bookForm,
            RedirectAttributes redirectAttrs) {
        JPABookEG jpaBook = beaMapper.mapEG(bookForm);

        JPACategoryEG category = jpaCategoryEGService.getCategoryDetails(
                bookForm.getCategoryName());
        jpaBook.setCategory(category);

        jpaBook.setBlobCode(jpaBook.getClobCode().getBytes());
        jpaBook = jpaBookEGService.saveUsingCustomRepo(jpaBook);

        BookForm form = beaMapper.mapEG(jpaBook);
        form.setCategoryName(category.getCategoryName());

        redirectAttrs.addAttribute("egComplete", "");
        redirectAttrs.addAttribute("bookId", form.getBookId());
        return "redirect:register";
    }

    @PostMapping(value = "register", params = "noIntfInheriRepoSave")
    public String registerBookUsingNoIntfInheriRepo(BookForm bookForm,
            RedirectAttributes redirectAttrs) {
        JPABookEG jpaBook = beaMapper.mapEG(bookForm);

        JPACategoryEG category = jpaCategoryEGService.getCategoryDetails(
                bookForm.getCategoryName());
        jpaBook.setCategory(category);

        jpaBook.setBlobCode(jpaBook.getClobCode().getBytes());
        jpaBook = jpaBookEGService.saveUsingNoIntfInheriRepo(jpaBook);

        BookForm form = beaMapper.mapEG(jpaBook);
        form.setCategoryName(category.getCategoryName());

        redirectAttrs.addAttribute("egComplete", "");
        redirectAttrs.addAttribute("bookId", form.getBookId());
        return "redirect:register";
    }

    @GetMapping(value = "existence", params = "customRepoSearch")
    public String searchBookUsingCustomRepo(JPABookListForm jpaBookListForm,
            Model model) {

        Integer bookId = Integer.valueOf(jpaBookListForm.getBookIdSrch());

        JPABookEG jpaBook = jpaBookEGService.findUsingCustomRepo(bookId);

        jpaBook.setBlobCodeHex(new String(Hex.encode(jpaBook.getBlobCode()))
                .toUpperCase());

        model.addAttribute("bookForm", new BookForm());
        model.addAttribute("book", jpaBook);
        model.addAttribute("pageListFlag", true);

        return "djpa/registerComplete";

    }

    @GetMapping(value = "existence", params = "noPrimaryKeySearch")
    public String searchBookUsingNonPrimarKey(JPABookListForm jpaBookListForm,
            Model model) {

        String bookTitle = jpaBookListForm.getBookTitle();

        JPABookEG jpaBook = jpaBookEGService.findByTitle(bookTitle);

        jpaBook.setBlobCodeHex(new String(Hex.encode(jpaBook.getBlobCode()))
                .toUpperCase());

        model.addAttribute("bookForm", new BookForm());
        model.addAttribute("book", jpaBook);
        model.addAttribute("pageListFlag", true);

        return "djpa/registerComplete";

    }

    @GetMapping(value = "existence", params = "noIntfInheriRepoSrch")
    public String searchBookUsingNoInterfaceRepo(
            JPABookListForm jpaBookListForm, Model model) {

        Integer bookId = Integer.valueOf(jpaBookListForm.getBookIdSrch());

        JPABookEG jpaBook = jpaBookEGService.findUsingNoIntfInheriRepo(bookId);

        jpaBook.setBlobCodeHex(new String(Hex.encode(jpaBook.getBlobCode()))
                .toUpperCase());

        model.addAttribute("bookForm", new BookForm());
        model.addAttribute("book", jpaBook);
        model.addAttribute("pageListFlag", true);

        return "djpa/registerComplete";

    }

    @PostMapping(value = "register", params = "multiSave")
    public String registerBulk(RedirectAttributes redirectAttrs) {

        jpaBookEGService.saveMultiple();

        return "redirect:pgList";

    }

    @PostMapping(value = "register", params = "multiSaveFlush")
    public String registerBulkFlush(RedirectAttributes redirectAttrs) {

        jpaBookService.saveMultipleFlush();

        return "redirect:/djpa/book/list";

    }

    @PostMapping(value = "register", params = "errorReg")
    public String registerBookRollback(Model model, BookForm bookForm,
            RedirectAttributes redirectAttrs) {
        JPABook jpaBook = beaMapper.map(bookForm);

        JPACategory category = jpaCategoryService.getCategoryDetails(bookForm
                .getCategoryName());
        jpaBook.setCategory(category);

        jpaBook = jpaBookService.addBookWithRollback(jpaBook);

        redirectAttrs.addAttribute("complete", "");
        redirectAttrs.addAttribute("bookId", jpaBook.getBookId());

        return "redirect:register";

    }

    @PostMapping(value = "register", params = "crudReg")
    public String registerUsingCrudRepository(Model model, BookForm bookForm,
            RedirectAttributes redirectAttrs) {
        JPABookEG jpaBookEG = beaMapper.mapEG(bookForm);

        JPACategoryEG category = jpaCategoryEGService.getCategoryDetails(
                bookForm.getCategoryName());
        jpaBookEG.setCategory(category);
        jpaBookEG.setBlobCode(bookForm.getClobCode().getBytes());
        jpaBookEG = jpaBookEGService.addBook(jpaBookEG);

        redirectAttrs.addAttribute("egComplete", "");
        redirectAttrs.addAttribute("bookId", jpaBookEG.getBookId());

        return "redirect:register";

    }

    /**
     * This method demonstrates the Interceptor settings done for lazy fetching.
     * @param model
     * @param bookListForm
     * @param redirectAttrs
     * @return
     */
    @PostMapping(value = "interceptSrch", params = "lazyLoadIntercept")
    public String searchLazyInterceptDemo(Model model,
            JPABookListForm bookListForm, RedirectAttributes redirectAttrs) {
        Integer bookId = Integer.valueOf(bookListForm
                .getSearchInQueryBookIdIntercept());
        JPABookLZ book = jpaBookLZService.findById(bookId);
        model.addAttribute("bookForm", new BookForm());
        book.setBlobCodeHex(new String(Hex.encode(book.getBlobCode()))
                .toUpperCase());
        model.addAttribute("book", book);
        return "djpa/registerComplete";

    }

    /**
     * This method demonstrates the Filter settings done for lazy fetching.
     * @param model
     * @param bookListForm
     * @param redirectAttrs
     * @return
     */
    @PostMapping(value = "filterSrch", params = "lazyLoadFilter")
    public String searchLazyFilterDemo(Model model,
            JPABookListForm bookListForm, RedirectAttributes redirectAttrs) {
        Integer bookId = Integer.valueOf(bookListForm
                .getSearchInQueryBookIdFilter());
        JPABookLZ book = jpaBookLZService.findById(bookId);
        model.addAttribute("bookForm", new BookForm());
        book.setBlobCodeHex(new String(Hex.encode(book.getBlobCode()))
                .toUpperCase());
        model.addAttribute("book", book);
        return "djpa/registerComplete";

    }

    /**
     * This method demonstrates the lazy initialization exception if no settings are performed and dependent entities properties
     * are accessed at application layer
     * @param model
     * @param bookListForm
     * @param redirectAttrs
     * @return
     */
    @PostMapping(value = "noLazySetting", params = "noLazy")
    public String searchNoLazySetting(Model model, JPABookListForm bookListForm,
            RedirectAttributes redirectAttrs) {
        Integer bookId = Integer.valueOf(bookListForm
                .getSearchInQueryBookIdNoLazy());
        JPABookLZ book = jpaBookLZService.findById(bookId);

        // when acquire CategoryName, LazyInitializationException will thrown
        model.addAttribute("bookCategory", book.getCategory()
                .getCategoryName());

        model.addAttribute("bookForm", new BookForm());
        book.setBlobCodeHex(new String(Hex.encode(book.getBlobCode()))
                .toUpperCase());
        model.addAttribute("book", book);
        return "djpa/registerComplete";

    }

    /**
     * This method demonstrates when acquire FlashAttribute outside of OpenEntityManagerInViewInterceptor,
     * LazyInitializationException occur.
     * @param bookListForm
     * @param redirectAttrs
     * @return
     */
    @PostMapping(value = "interceptSrch", params = "registerFlashAttribute")
    public String registerFlashAttribute(JPABookListForm bookListForm,
            RedirectAttributes redirectAttrs) {
        Integer bookId = Integer.valueOf(bookListForm
                .getSearchInQueryBookIdAndRegisterFlashAttribute());
        JPABookLZ book = jpaBookLZService.findById(bookId);
        book.setBlobCodeHex(new String(Hex.encode(book.getBlobCode()))
                .toUpperCase());

        // register FlashAttribute
        redirectAttrs.addFlashAttribute("book", book);
        return "redirect:/djpa/book/redirectRegisterComplete";

    }

    @GetMapping(value = "redirectRegisterComplete")
    public String redirectRegisterComplete(
            @ModelAttribute(name = "book", binding = false) JPABookLZ book,
            Model model) {

        // when acquire CategoryName, LazyInitializationException will thrown
        model.addAttribute("bookCategory", book.getCategory()
                .getCategoryName());

        model.addAttribute("bookForm", new BookForm());
        model.addAttribute("book", book);
        return "djpa/registerComplete";

    }

    /**
     * This method demonstrates when acquiring anything but foreign key, the entire Entity is acquired.
     * @param model
     * @param bookListForm
     * @return
     */
    @PostMapping(value = "noLazySetting", params = "acquiringNotForeignKey")
    public String searchNoLazySettingAcquiringNotForeignKey(Model model,
            JPABookListForm bookListForm) {
        Integer bookId = Integer.valueOf(bookListForm
                .getSearchInQueryBookIdAcquiringNotForeignKey());
        JPABookLZ book = jpaBookLZService.findByIdAcquiringNotForeignKey(
                bookId);
        model.addAttribute("bookCategory", book.getCategory()
                .getCategoryName());
        model.addAttribute("bookForm", new BookForm());
        book.setBlobCodeHex(new String(Hex.encode(book.getBlobCode()))
                .toUpperCase());
        model.addAttribute("book", book);
        return "djpa/registerComplete";

    }

    /**
     * This method demonstrates when acquiring foreign key, the entire Entity is not acquired.
     * @param model
     * @param bookListForm
     * @return
     */
    @PostMapping(value = "noLazySetting", params = "acquiringForeignKey")
    public String searchNoLazySettingAcquiringForeignKey(Model model,
            JPABookListForm bookListForm) {
        Integer bookId = Integer.valueOf(bookListForm
                .getSearchInQueryBookIdAcquiringForeignKey());
        JPABookLZ book = jpaBookLZService.findByIdAcquiringForeignKey(bookId);

        // when acquire CategoryName, LazyInitializationException will thrown
        model.addAttribute("bookCategory", book.getCategory()
                .getCategoryName());

        model.addAttribute("bookForm", new BookForm());
        book.setBlobCodeHex(new String(Hex.encode(book.getBlobCode()))
                .toUpperCase());
        model.addAttribute("book", book);
        return "djpa/registerComplete";

    }

    @GetMapping(value = "register", params = "complete")
    public String registerComplete(@RequestParam("id") String id, Model model) {

        JPABook book = jpaBookService.findById(id);
        if (null == book) {
            book = new JPABook();
        } else {
            String blob = new String(Hex.encode(book.getBlobCode()))
                    .toUpperCase();
            book.setBlobCodeHex(blob);
        }
        model.addAttribute("bookForm", new BookForm());
        model.addAttribute("book", book);

        return "djpa/registerComplete";
    }

    @GetMapping(value = "register", params = "egComplete")
    public String registerEGComplete(@RequestParam("bookId") String id,
            Model model) {

        JPABookEG book = jpaBookEGService.findById(Integer.valueOf(id));
        if (null == book) {
            book = new JPABookEG();
        } else {
            book.setBlobCodeHex(new String(Hex.encode(book.getBlobCode()))
                    .toUpperCase());
        }
        model.addAttribute("bookForm", new BookForm());
        model.addAttribute("book", book);
        model.addAttribute("pageListFlag", true);

        return "djpa/registerComplete";
    }

    @GetMapping(value = "register", params = "find")
    public String displayBookDetail(@RequestParam("bookId") String id,
            Model model) {

        JPABookEG book = jpaBookEGService.findByIdUsingJPARepository(Integer
                .valueOf(id));
        if (null == book) {
            book = new JPABookEG();
        } else {
            book.setBlobCodeHex(new String(Hex.encode(book.getBlobCode()))
                    .toUpperCase());
        }
        model.addAttribute("bookForm", new BookForm());
        model.addAttribute("book", book);

        return "djpa/registerComplete";
    }

    @PostMapping(value = "register", params = "form")
    public String registerForm(BookForm form, Model model) {
        return "djpa/registerForm";
    }

    @GetMapping(value = "{id}/update")
    public String updateForm(@PathVariable("id") String id,
            @Validated BookForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return updateRedo(form, model);
        }

        JPABook book = jpaBookService.findById(id);
        if (null == book) {
            book = new JPABook();
        } else {
            book.setBlobCodeHex(new String(Hex.encode(book.getBlobCode()))
                    .toUpperCase());
        }
        model.addAttribute("book", book);

        return "djpa/jpaUpdateForm";
    }

    @GetMapping(value = "{id}/pgUpdate")
    public String paginatedUpdateForm(@PathVariable("id") Integer id,
            @Validated BookForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return updateRedo(form, model);
        }

        JPABookEG book = jpaBookEGService.findById(id);
        if (null == book) {
            book = new JPABookEG();
        } else {
            book.setBlobCodeHex(new String(Hex.encode(book.getBlobCode()))
                    .toUpperCase());
        }
        model.addAttribute("book", book);

        return "djpa/jpaUpdateForm";
    }

    @PostMapping(value = "{id}/update", params = "redo")
    public String updateRedo(BookForm form, Model model) {
        model.addAttribute("book", form);

        return "djpa/jpaUpdateForm";
    }

    @PostMapping(value = "{id}/update", params = "cancel")
    public String updatecancel(BookForm form, Model model) {
        // model.addAttribute("book", form);

        return "redirect:/djpa/book/list";
    }

    @PostMapping(value = "register", params = "back")
    public String backToList(BookForm form, Model model) {
        // model.addAttribute("book", form);

        return "redirect:/djpa/book/list";
    }

    @PostMapping(value = "register", params = "toPageList")
    public String backToPageList(Model model) {
        // model.addAttribute("book", form);

        return "redirect:/djpa/book/pgList";
    }

    @GetMapping(value = "0103/001")
    public String handle0103001() {

        // List<JPABook> bookList = jpaBookService.getAllBooks();

        return "djpa/jpaIndex";

    }

    /**
     * ********************************************
     */

    @GetMapping(value = "pgList")
    public String paginationUsingPageable(
            @PageableDefault(page = 0, size = 4, sort = {
                    "bookId" }, direction = Direction.ASC) Pageable pageable,
            Model model) {

        Page<JPABookEG> bookListPage = jpaBookEGService.getPaginatedBooks(
                pageable);

        model.addAttribute("page", bookListPage);
        return "djpa/pageList";

    }

    @DeleteMapping(value = "delete", params = "deletInBatch")
    public String defaultDeleteAll(JPABookListForm bookListForm) {
        // Split using comma on the BookIdDelOpnInput to get multiple book Ids.
        String[] idArray = bookListForm.getBookIdDelOpnInput().split(",");
        Iterable<String> iterable = Arrays.asList(idArray);
        jpaBookService.deleteInBatch(iterable);
        return "redirect:list";
    }

    @DeleteMapping(value = "delete", params = "deleteAllById")
    public String defaultDeleteAllById(JPABookListForm bookListForm) {
        // Split using comma on the BookIdDelOpnInput to get multiple book Ids.
        String[] idArray = bookListForm.getBookIdDelOpnInput().split(",");
        Iterable<String> iterable = Arrays.asList(idArray);
        jpaBookService.deleteAllById(iterable);
        return "redirect:list";
    }

    @DeleteMapping(value = "delete", params = "deleteAllByIdInBatch")
    public String defaultDeleteAllByIdInBatch(JPABookListForm bookListForm) {
        // Split using comma on the BookIdDelOpnInput to get multiple book Ids.
        String[] idArray = bookListForm.getBookIdDelOpnInput().split(",");
        Iterable<String> iterable = Arrays.asList(idArray);
        jpaBookService.deleteAllByIdInBatch(iterable);
        return "redirect:list";
    }

    @DeleteMapping(value = "delete", params = "deletAllInBatch")
    public String defaultDeleteSpecifiedEntities() {

        jpaBookService.deleteAllInBatch();

        return "redirect:list";
    }

    @DeleteMapping(value = "delete", params = "deletOne")
    public String defaultDeleteOne(JPABookListForm bookListForm,
            RedirectAttributes redirectAttrs) {

        jpaBookService.delete(bookListForm.getBookIdDelOpnInput());

        return "redirect:list";
    }

    @DeleteMapping(value = "delete", params = "deletIterable")
    public String defaultDeleteIterableList(JPABookListForm bookListForm,
            RedirectAttributes redirectAttrs) {

        List<JPABook> bookList = jpaBookService.findAll();

        Iterable<JPABook> iterableBook = bookList;

        jpaBookService.delete(iterableBook);

        return "redirect:list";
    }

    @DeleteMapping(value = "delete", params = "deleteEntity")
    public String defaultDeleteByEntity(JPABookListForm bookListForm,
            RedirectAttributes redirectAttrs) {

        JPABook jpaBook = jpaBookService.findById(bookListForm
                .getBookIdDelOpnInput());

        jpaBookService.delete(jpaBook);

        return "redirect:list";
    }

    @GetMapping(value = "existence", params = "check")
    public String defaultExistenceCheck(JPABookListForm bookListForm,
            RedirectAttributes redirectAttrs) {
        boolean isBookPresent = jpaBookService.isPresent(bookListForm
                .getBookIdSrch());
        redirectAttrs.addFlashAttribute("isBookPresent", isBookPresent);
        return "redirect:list";
    }

    @GetMapping(value = "existence", params = "count")
    public String defaultCount(RedirectAttributes redirectAttrs) {
        long bookCount = jpaBookService.getBookCount();
        redirectAttrs.addFlashAttribute("bookCount", bookCount);
        return "redirect:list";
    }

    @GetMapping(value = "sort")
    public String defaultUsingSort(JPABookListForm bookListForm, Model model) {
        String sortFld[] = bookListForm.getSearchOrderBy().split(" ");
        List<JPABook> bookList = jpaBookService.getSortedBookList(sortFld[0],
                sortFld[1]);
        return displayJPAListPage(model, bookList);
    }

    /**
     * Utility method
     * @param model
     * @param bookList
     * @return
     */
    private String displayJPAListPage(Model model, List<JPABook> bookList) {
        for (JPABook jpaBook : bookList) {
            jpaBook.setBlobCodeHex(new String(Hex.encode(jpaBook.getBlobCode()))
                    .toUpperCase());
        }

        model.addAttribute("bookList", bookList);
        return "djpa/list";
    }

    /*
     * By Sunil for djpa1202001 Adding the custom methods to all Repository interfaces in batch
     */
    @PostMapping(value = "{id}/update", params = "update")
    public String updateBookUsingProjectRepo(@PathVariable("id") String id,
            @Validated BookUpdateForm bookForm, BindingResult result,
            Model model, RedirectAttributes redirectAttrs) {

        if (result.hasErrors()) {
            model.addAttribute("book", bookForm);

            return "djpa/jpaUpdateForm";
        }

        bookForm.setBlobCode(null);
        JPABookEG jpaBookEG = beaMapper.map(bookForm);

        JPACategoryEG category = jpaCategoryEGService.getCategoryDetails(
                bookForm.getCategoryName());
        jpaBookEG.setCategory(category);

        jpaBookEG.setBlobCode(jpaBookEG.getClobCode().getBytes());

        jpaBookEG = jpaBookEGService.updateUsingMyProjectRepo(jpaBookEG);

        bookForm = beaMapper.mapEGToForm(jpaBookEG);
        bookForm.setCategoryName(category.getCategoryName());

        redirectAttrs.addAttribute("egComplete", "");
        redirectAttrs.addAttribute("bookId", bookForm.getBookId());
        return "redirect:update";

    }

    @GetMapping(value = "{id}/update", params = "egComplete")
    public String updateEGComplete(@PathVariable("id") String id, Model model) {

        JPABookEG book = jpaBookEGService.findById(Integer.valueOf(id));
        if (null == book) {
            book = new JPABookEG();
        } else {
            book.setBlobCodeHex(new String(Hex.encode(book.getBlobCode()))
                    .toUpperCase());
        }
        model.addAttribute("bookForm", new BookForm());
        model.addAttribute("book", book);
        model.addAttribute("pageListFlag", true);

        return "djpa/jpaUpdateComplete";
    }

    @PostMapping(value = "update", params = "toPageList")
    public String backToPageListfromUpdate(BookForm form, Model model) {

        return "redirect:/djpa/book/pgList";
    }

    @GetMapping(value = "existence", params = "lckTmeOutQHint")
    public String lockTimeoutAndPessimisticLocking(
            JPABookListForm jpaBookListForm,
            Model model) throws InterruptedException {

        String dataBaseName = DataBaseInfo.getDataBaseID(
                (HibernateJpaVendorAdapter) jpaVendorAdapter);
        logger.debug("Current Database Under Test ::" + dataBaseName);

        Integer bookId = Integer.valueOf(jpaBookListForm.getBookIdSrch());

        JPABookEG jpaBook = new JPABookEG();
        try {
            jpaBook = jpaBookEGService
                    .findOneForUpdateLckTmeOutPessismisticLocking(bookId,
                            jpaBookListForm.getSleepTime());
        } catch (PessimisticLockingFailureException e) {
            model.addAttribute(ResultMessages.warning().add(
                    "excn.result.exclusivelockacquired"));
            return "common/error/exclusiveLockError";
        }
        jpaBook.setBlobCodeHex(new String(Hex.encode(jpaBook.getBlobCode()))
                .toUpperCase());

        model.addAttribute("databaseId", dataBaseName);
        model.addAttribute("bookForm", new BookForm());
        model.addAttribute("book", jpaBook);
        model.addAttribute("pageListFlag", true);

        return "djpa/registerComplete";

    }

    @GetMapping(value = "existence", params = "lckTmeOutQHintNoExcp")
    public String lockTimeoutAndPessimisticLockingNoExp(
            JPABookListForm jpaBookListForm,
            Model model) throws InterruptedException {

        String dataBaseName = DataBaseInfo.getDataBaseID(
                (HibernateJpaVendorAdapter) jpaVendorAdapter);
        logger.debug("Current Database Under Test ::" + dataBaseName);

        Integer bookId = Integer.valueOf(jpaBookListForm.getBookIdSrch());

        JPABookEG jpaBook = new JPABookEG();
        try {
            jpaBook = jpaBookEGService
                    .findOneForUpdateLckTmeOutPessismisticLockingNoExcp(bookId,
                            jpaBookListForm.getSleepTime());
        } catch (PessimisticLockingFailureException e) {
            model.addAttribute(ResultMessages.warning().add(
                    "excn.result.exclusivelockacquired"));
            return "common/error/exclusiveLockError";
        }
        jpaBook.setBlobCodeHex(new String(Hex.encode(jpaBook.getBlobCode()))
                .toUpperCase());

        model.addAttribute("databaseId", dataBaseName);
        model.addAttribute("bookForm", new BookForm());
        model.addAttribute("book", jpaBook);
        model.addAttribute("pageListFlag", true);

        return "djpa/registerComplete";

    }

}
