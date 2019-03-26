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
package jp.co.ntt.fw.spring.functionaltest.selenium.djpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.BookDetailsPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.DeliveryOrderDetailsPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.ItemPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.JPAHomePage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.JPAIndexPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.OrderDeliveryListPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.OrderDetailsPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.OrderEntryPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.OrdersPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.RegisterBookPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.RegisterConfirmPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages.SystemErrorPage;

@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class DataAccessJPATest extends FunctionTestSupport {

    private static WebDriver driver;

    public DataAccessJPATest() {
        disableDefaultWebDriver();
    }

    @Before
    public void setUp() {
        // 日本語ロケールのブラウザを起動
        {
            if (driver == null) {
                driver = webDriverCreator.createLocaleSpecifiedDriver("ja");
            }
            setCurrentWebDriver(driver);
        }

        // トップ画面での操作
        {
            webDriverOperations.getWebDriver().manage().window().maximize();
        }
    }

    private void clearTestDataForBook() {
        restOperations.delete(getPackageRootUrl() + "/testdata/books");
    }

    private void clearAndCreateTestDataForBook() {
        restOperations.postForObject(getPackageRootUrl() + "/testdata/books",
                null, Void.class);
    }

    /**
     * This method creates the data items in DeliverOrder table with id from 6 to 15)
     */
    private void clearAndCreateTestDataForDeliverOrder() {
        restOperations.postForObject(getPackageRootUrl() + "/testdata/order",
                null, Void.class);
    }

    /*
     * private void clearTestDataForForDeliverOrder() { restOperations.delete(getPackageRootUrl() + "/testdata/order"); }
     */

    /**
     * Test the EntityManager settings.
     */
    @Test
    public void testDJPA0101001() {

        clearAndCreateTestDataForBook();

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0101001Click();

        BookDetailsPage bookDetailsPage = jpaHomePage.displayBookDetails(1);

        assertThat(bookDetailsPage.getBookIdVal(), is("0000000001"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getTitle(), is("TitleA011"));
        assertThat(bookDetailsPage.getClobCode(), is("CodeA011"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("10000"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2013/12/24"));

    }

    /**
     * Test the Transaction manager setting - the commit.
     */
    @Test
    public void testDJPA0102001() {
        // Clearing DB.
        {
            clearTestDataForBook();
        }

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0102001Click();

        // open registration form
        RegisterBookPage bookEntryForm = jpaHomePage.registerBookForm();

        // Set input fields
        bookEntryForm.setBookId("1000000001");
        bookEntryForm.setCategoryName("A01");
        bookEntryForm.setClobCode("CL01");
        bookEntryForm.setPrice("5000");
        bookEntryForm.setReleaseDate("2015/11/04");
        bookEntryForm.setTitle("Title22");

        // Register the book
        RegisterBookPage bookDetailsPage = bookEntryForm.registerBook();

        // Assertion for record earlier registered in DB.
        assertThat(bookDetailsPage.getBookIdVal(), is("1000000001"));
        assertThat(bookDetailsPage.getTitle(), is("Title22"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getClobCode(), is("CL01"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("5000"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2015/11/04"));
    }

    /**
     * Test the Transaction manager setting- the rollback
     */
    @Test
    public void testDJPA0102002() {
        // Clearing DB.
        {
            clearTestDataForBook();
        }
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0102001Click();

        // open registration form
        RegisterBookPage bookEntryForm = jpaHomePage.registerBookForm();

        // Set input fields
        bookEntryForm.setBookId("100000001");
        bookEntryForm.setCategoryName("A01");
        bookEntryForm.setClobCode("CL01");
        bookEntryForm.setPrice("5000");
        bookEntryForm.setReleaseDate("2015/11/04");
        bookEntryForm.setTitle("Title22");

        // Register the book
        SystemErrorPage sysErrorPage = bookEntryForm.registerBookForRollback();

        // Assertion for record earlier registered in DB.
        assertThat(sysErrorPage.getErrorMessage(), is("Intentional RollBack"));

        webDriverOperations.displayPage(getPackageRootUrl());

        jpaIndexPage = new JPAIndexPage(driver);

        jpaHomePage = jpaIndexPage.djpa0102001Click();

        boolean insertBookDetailLink = jpaHomePage.isBookDetailLinkPresent(1);

        // Due to RollBack during registration Process, No link will be
        // available for the book.
        assertThat(insertBookDetailLink, is(false));
    }

    /**
     * Test the lazy loading using OpenEntityManagerInViewInterceptor settings
     */
    @Test
    public void testDJPA0103001() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        JPAHomePage jpaHomePage = jpaIndexPage.djpa0103001Click();
        jpaHomePage.setInteceptBookId("0001");
        BookDetailsPage lazyBookPage = jpaHomePage.lazyFetchIntercept();

        // Assertion for record earlier registered in DB.
        assertThat(lazyBookPage.getBookIdVal(), is("1"));
        assertThat(lazyBookPage.getTitle(), is("title1"));
        assertThat(lazyBookPage.getCategoryName(), is("A01"));
        assertThat(lazyBookPage.getClobCode(), is("54455354"));
        assertThat(lazyBookPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((lazyBookPage.getClobCode().getBytes()))));
        assertThat(lazyBookPage.getPrice(), is("40"));
        assertThat(lazyBookPage.getReleaseDate(), is("2013/01/01"));
    }

    /**
     * Test the lazy loading using OpenEntityManagerInViewFilter settings
     */
    @Test
    public void testDJPA0103002() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        JPAHomePage jpaHomePage = jpaIndexPage.djpa0103002Click();
        jpaHomePage.setFilterBookId("0001");
        BookDetailsPage lazyBookPage = jpaHomePage.lazyFetchFilter();

        // Assertion for record earlier registered in DB.
        assertThat(lazyBookPage.getBookIdVal(), is("1"));
        assertThat(lazyBookPage.getTitle(), is("title1"));
        assertThat(lazyBookPage.getCategoryName(), is("A01"));
        assertThat(lazyBookPage.getClobCode(), is("54455354"));
        assertThat(lazyBookPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((lazyBookPage.getClobCode().getBytes()))));
        assertThat(lazyBookPage.getPrice(), is("40"));
        assertThat(lazyBookPage.getReleaseDate(), is("2013/01/01"));
    }

    /**
     * Error scenario for lazy loading
     */
    @Test
    public void testDJPA0103003() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        JPAHomePage jpaHomePage = jpaIndexPage.djpa0103003Click();
        jpaHomePage.setNoLazyBookId("0001");
        SystemErrorPage sysErrorPage = jpaHomePage.nolazyFetchSetting();

        // Assertion for system error occurred due to lazy initialization.
        assertTrue(Pattern.compile(
                "^could\\snot\\sinitialize\\sproxy\\s\\[([a-z]|[A-Z]|[0-9]|#|\\.){1,}\\]\\s-\\sno\\sSession$")
                .matcher(sysErrorPage.getErrorMessage()).matches());
    }

    /**
     * Error scenario for lazy loading(acquire Session outside of OpenEntityManagerInViewInterceptor)
     */
    @Test
    public void testDJPA0103004() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        JPAHomePage jpaHomePage = jpaIndexPage.djpa0103004Click();
        jpaHomePage.setBookIdAndRegisterSession("0001");
        RegisterConfirmPage registerConfirmPage = jpaHomePage.registerSession();
        SystemErrorPage sysErrorPage = registerConfirmPage
                .accessOutOfLazyFetchScope();

        // Assertion for system error occurred due to lazy initialization.
        assertTrue(Pattern.compile(
                "^could\\snot\\sinitialize\\sproxy\\s\\[([a-z]|[A-Z]|[0-9]|#|\\.){1,}\\]\\s-\\sno\\sSession$")
                .matcher(sysErrorPage.getErrorMessage()).matches());
    }

    /**
     * Error scenario for lazy loading(acquire FlashAttribute outside of OpenEntityManagerInViewInterceptor)
     */
    @Test
    public void testDJPA0103005() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        JPAHomePage jpaHomePage = jpaIndexPage.djpa0103005Click();
        jpaHomePage.setBookIdAndRegisterFlashAttribute("0001");
        SystemErrorPage sysErrorPage = jpaHomePage.registerFlashAttribute();

        // Assertion for system error occurred due to lazy initialization.
        assertTrue(Pattern.compile(
                "^could\\snot\\sinitialize\\sproxy\\s\\[([a-z]|[A-Z]|[0-9]|#|\\.){1,}\\]\\s-\\sno\\sSession$")
                .matcher(sysErrorPage.getErrorMessage()).matches());
    }

    /**
     * Way of Inheriting the interface of spring Data Confirmation for Repository interface for generic CRUD operations.
     */
    @Test
    public void testDJPA0201001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201001Click();
        // open registration form
        RegisterBookPage bookEntryForm = jpaHomePage.registerBookForm();

        bookEntryForm.setCategoryName("A01");
        bookEntryForm.setClobCode("someclobCode");
        bookEntryForm.setPrice("1234");
        bookEntryForm.setReleaseDate("2015/11/19");
        bookEntryForm.setTitle("Title19");

        // Register the book
        RegisterBookPage bookDetailsPage = bookEntryForm
                .registerBookUsingCrudReository();

        // Assertion for record earlier registered in DB.
        assertThat(bookDetailsPage.getBookIdVal(), is("3"));
        assertThat(bookDetailsPage.getTitle(), is("Title19"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getClobCode(), is("someclobCode"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("1234"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2015/11/19"));
    }

    /**
     * Way of Inheriting the interface of spring Data. Confirmation for Repository interface wherein Pagination function and
     * Sort function are added to findAll method of CrudRepository.
     */
    @Test
    public void testDJPA0201002() {
        clearAndCreateTestDataForBook();
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201002Click();

        // jpaHomePage = jpaHomePage.gotoSecondPageBookList();

        BookDetailsPage bookDetailsPage = jpaHomePage.displayBookDetails(1);

        // assertThat(bookDetailsPage.getBookIdVal(), is("1"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getTitle(), is("Manual Title 1"));
        assertThat(bookDetailsPage.getClobCode(), is("54455354"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("40"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2013/01/01"));
    }

    /**
     * JPARepository Default Method Confirmation. Method Under Test : deleteAllInBatch()
     */
    @Test
    public void testDJPA0201003() {
        clearAndCreateTestDataForBook();
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201003Click();

        boolean firstBookDetailLink = jpaHomePage.isBookDetailLinkPresent(1);

        assertThat(firstBookDetailLink, is(true));

        JPAHomePage jpaHomePageAfterDelete = jpaHomePage.jpaRepoDefDelAll();

        boolean bookDetailLink = jpaHomePageAfterDelete.isBookDetailLinkPresent(
                1);

        assertThat(bookDetailLink, is(false));
    }

    /**
     * 
     */
    @Test
    public void testDJPA0201004() {
        // Creating data set
        clearAndCreateTestDataForBook();
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201004Click();

        // setting the book id to find whether it exists in DB
        jpaHomePage.setBookIdForDefJPASearchOpn("0000000001");

        // Invoking the existence check method
        JPAHomePage jpaHomePageAfterSearch = jpaHomePage.jpaRepoDefExists();

        // Collecting the existence check message
        String message = jpaHomePageAfterSearch.getBookAvailabityMessage();

        // Confirming the book exists
        assertThat(message, is("Book Available!!"));

        // setting the book id to find whether it exists in DB.The book with this id is not present in db.
        jpaHomePageAfterSearch.setBookIdForDefJPASearchOpn("12345");

        // Invoking the existence check method
        jpaHomePageAfterSearch = jpaHomePageAfterSearch.jpaRepoDefExists();

        // Collecting the existence check message
        message = jpaHomePageAfterSearch.getBookAvailabityMessage();

        // Confirming the book does not exists
        assertThat(message, is("Book Not Available!!"));

    }

    @Test
    public void testDJPA0201005() {

        clearAndCreateTestDataForBook();
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201005Click();

        jpaHomePage.setBookIdForDefJPADeleteOpn("0000000001");
        // Deleting the above set book
        jpaHomePage = jpaHomePage.jpaRepoDefDelOne();

        jpaHomePage.setBookIdForDefJPASearchOpn("0000000001");

        // searching if the deleted book still exists
        JPAHomePage jpaHomePageAfterSearch = jpaHomePage.jpaRepoDefExists();

        String message = jpaHomePageAfterSearch.getBookAvailabityMessage();

        // confirming that the book is really deleted.
        assertThat(message, is("Book Not Available!!"));

    }

    @Test
    public void testDJPA0201006() {
        clearAndCreateTestDataForBook();
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201006Click();

        jpaHomePage.setBookIdForDefJPADeleteOpn(
                "0000000001,0000000002,0000000005,0000000008");

        // Deleting the above set books
        jpaHomePage = jpaHomePage.jpaRepoDefDelInBatch();

        /**
         * One by one check whether the books with above ids have really been deleted successfully?
         */
        // confirmation for bookId : 0000000001
        jpaHomePage.setBookIdForDefJPASearchOpn("0000000001");
        jpaHomePage = jpaHomePage.jpaRepoDefExists();
        String message = jpaHomePage.getBookAvailabityMessage();
        assertThat(message, is("Book Not Available!!"));

        // confirmation for bookId : 0000000002
        jpaHomePage.setBookIdForDefJPASearchOpn("0000000002");
        jpaHomePage = jpaHomePage.jpaRepoDefExists();
        message = jpaHomePage.getBookAvailabityMessage();
        assertThat(message, is("Book Not Available!!"));

        // confirmation for bookId : 0000000005
        jpaHomePage.setBookIdForDefJPASearchOpn("0000000005");
        jpaHomePage = jpaHomePage.jpaRepoDefExists();
        message = jpaHomePage.getBookAvailabityMessage();
        assertThat(message, is("Book Not Available!!"));

        // confirmation for bookId : 0000000008
        jpaHomePage.setBookIdForDefJPASearchOpn("0000000008");
        jpaHomePage = jpaHomePage.jpaRepoDefExists();
        message = jpaHomePage.getBookAvailabityMessage();
        assertThat(message, is("Book Not Available!!"));

    }

    @Test
    public void testDJPA0201007() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201007Click();

        jpaHomePage.jpaRepoDefSaveEntityList();

        String bookCount = jpaHomePage.getPageObjectItemCount();

        // confirm that 16 books are added succesfully.The 15 books are created in the service layer.
        assertThat(bookCount, is("18 Books Available!!"));

        BookDetailsPage bookDetailsPage = jpaHomePage.displayBookDetails(4);

        assertThat(bookDetailsPage.getBookIdVal(), is("4"));
        assertThat(bookDetailsPage.getCategoryName(), is("B01"));
        assertThat(bookDetailsPage.getTitle(), is("Manual Title 4"));
        assertThat(bookDetailsPage.getClobCode(), is("BX5D4"));
        assertThat(bookDetailsPage.getBlobCode(), is("4258354434"));
        assertThat(bookDetailsPage.getPrice(), is("404"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2015/11/23"));

    }

    /**
     * JPARepository Default Method Confirmation. Method Under Test : long count()
     */
    @Test
    public void testDJPA0201008() {

        clearAndCreateTestDataForBook();

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201008Click();

        jpaHomePage = jpaHomePage.jpaRepoDefCount();

        String bookCnt = jpaHomePage.getBookCount();

        assertThat(bookCnt, is("21"));
    }

    @Test
    public void testDJPA0201009_1() {
        clearAndCreateTestDataForBook();

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201009Click();
        // Book ID Ascending
        String sortCriteria = "Book Id. ASC";
        jpaHomePage = jpaHomePage.setSortCriteria(sortCriteria);

        // fetching books in ascending order of book id
        jpaHomePage = jpaHomePage.jpaRepoDefSort();
        BookDetailsPage bookDetailsPage = jpaHomePage.displayBookDetails(1);

        // first book
        assertThat(bookDetailsPage.getBookIdVal(), is("0000000001"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getTitle(), is("TitleA011"));
        assertThat(bookDetailsPage.getClobCode(), is("CodeA011"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("10000"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2013/12/24"));

        jpaHomePage = bookDetailsPage.goBackToBookListPage();

        jpaHomePage = jpaHomePage.setSortCriteria(sortCriteria);
        jpaHomePage = jpaHomePage.jpaRepoDefSort();

        bookDetailsPage = jpaHomePage.displayBookDetails(2);

        // second book
        assertThat(bookDetailsPage.getBookIdVal(), is("0000000002"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getTitle(), is("TitleA012"));
        assertThat(bookDetailsPage.getClobCode(), is("CodeA012"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("10000"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2013/12/24"));

        jpaHomePage = bookDetailsPage.goBackToBookListPage();

        // Book ID Descending
        sortCriteria = "Book Id. DESC";
        jpaHomePage = jpaHomePage.setSortCriteria(sortCriteria);
        // fetching books in descending order of book id
        jpaHomePage = jpaHomePage.jpaRepoDefSort();

        bookDetailsPage = jpaHomePage.displayBookDetails(1);

        // book with highest book id is the first
        assertThat(bookDetailsPage.getBookIdVal(), is("0000000021"));
        assertThat(bookDetailsPage.getCategoryName(), is("Z"));
        assertThat(bookDetailsPage.getTitle(), is("TitleZ3"));
        assertThat(bookDetailsPage.getClobCode(), is("CodeZ3"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("10000"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2013/12/24"));

        jpaHomePage = bookDetailsPage.goBackToBookListPage();

        jpaHomePage = jpaHomePage.setSortCriteria(sortCriteria);
        jpaHomePage = jpaHomePage.jpaRepoDefSort();

        bookDetailsPage = jpaHomePage.displayBookDetails(2);

        assertThat(bookDetailsPage.getBookIdVal(), is("0000000020"));
        assertThat(bookDetailsPage.getCategoryName(), is("Z"));
        assertThat(bookDetailsPage.getTitle(), is("TitleZ2"));
        assertThat(bookDetailsPage.getClobCode(), is("CodeZ2"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("10000"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2013/12/24"));

        jpaHomePage = bookDetailsPage.goBackToBookListPage();

    }

    @Test
    public void testDJPA0201009_2() {
        clearAndCreateTestDataForBook();

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201009Click();

        // Book Title Ascending
        String sortCriteria = "Title. ASC";
        jpaHomePage = jpaHomePage.setSortCriteria(sortCriteria);

        // fetching books in ascending order of book title
        jpaHomePage = jpaHomePage.jpaRepoDefSort();

        BookDetailsPage bookDetailsPage = jpaHomePage.displayBookDetails(1);

        assertThat(bookDetailsPage.getBookIdVal(), is("0000000001"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getTitle(), is("TitleA011"));
        assertThat(bookDetailsPage.getClobCode(), is("CodeA011"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("10000"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2013/12/24"));

        jpaHomePage = bookDetailsPage.goBackToBookListPage();

        jpaHomePage = jpaHomePage.setSortCriteria(sortCriteria);
        jpaHomePage = jpaHomePage.jpaRepoDefSort();

        bookDetailsPage = jpaHomePage.displayBookDetails(3);

        assertThat(bookDetailsPage.getBookIdVal(), is("0000000003"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getTitle(), is("TitleA013"));
        assertThat(bookDetailsPage.getClobCode(), is("CodeA013"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("10000"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2013/12/24"));

        jpaHomePage = bookDetailsPage.goBackToBookListPage();

        jpaHomePage = jpaHomePage.setSortCriteria(sortCriteria);
        jpaHomePage = jpaHomePage.jpaRepoDefSort();

        bookDetailsPage = jpaHomePage.displayBookDetails(4);

        assertThat(bookDetailsPage.getBookIdVal(), is("0000000010"));
        assertThat(bookDetailsPage.getCategoryName(), is("A02"));
        assertThat(bookDetailsPage.getTitle(), is("TitleA021"));
        assertThat(bookDetailsPage.getClobCode(), is("CodeA021"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("10000"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2013/12/24"));

        jpaHomePage = bookDetailsPage.goBackToBookListPage();

        // Book Title Ascending
        sortCriteria = "Title. DESC";
        jpaHomePage = jpaHomePage.setSortCriteria(sortCriteria);
        jpaHomePage = jpaHomePage.jpaRepoDefSort();

        bookDetailsPage = jpaHomePage.displayBookDetails(21);

        assertThat(bookDetailsPage.getBookIdVal(), is("0000000001"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getTitle(), is("TitleA011"));
        assertThat(bookDetailsPage.getClobCode(), is("CodeA011"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("10000"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2013/12/24"));

        jpaHomePage = bookDetailsPage.goBackToBookListPage();

        jpaHomePage = jpaHomePage.setSortCriteria(sortCriteria);
        jpaHomePage = jpaHomePage.jpaRepoDefSort();

        bookDetailsPage = jpaHomePage.displayBookDetails(20);

        assertThat(bookDetailsPage.getBookIdVal(), is("0000000002"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getTitle(), is("TitleA012"));
        assertThat(bookDetailsPage.getClobCode(), is("CodeA012"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("10000"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2013/12/24"));
    }

    @Test
    public void testDJPA0201010() {
        clearAndCreateTestDataForBook();

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201010Click();

        // deleting all the books using delete with iterable as parameter
        jpaHomePage = jpaHomePage.jpaRepoDefDelIterable();

        // getting the count of book from DB
        jpaHomePage = jpaHomePage.jpaRepoDefCount();

        // getting count value
        String bookCnt = jpaHomePage.getBookCount();

        // confirming zero records.
        assertThat(bookCnt, is("0"));
    }

    @Test
    public void testDJPA0201011() {
        clearAndCreateTestDataForBook();

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201011Click();

        // open registration form
        RegisterBookPage bookEntryForm = jpaHomePage.registerBookForm();

        bookEntryForm.setCategoryName("A01");
        bookEntryForm.setClobCode("someclobCode");
        bookEntryForm.setPrice("1234");
        bookEntryForm.setReleaseDate("2015/11/25");
        bookEntryForm.setTitle("Title19");

        BookDetailsPage bookDetailsPage = bookEntryForm
                .registerUsingSaveAndFlush();

        // Assertion for record earlier registered in DB.
        assertThat(bookDetailsPage.getBookIdVal(), is("19"));
        assertThat(bookDetailsPage.getTitle(), is("Title19"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getClobCode(), is("someclobCode"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("1234"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2015/11/25"));
    }

    @Test
    public void testDJPA0201012() {
        clearAndCreateTestDataForBook();

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201012Click();

        jpaHomePage.setBookIdForDefJPADeleteOpn("0000000001");

        jpaHomePage = jpaHomePage.jpaRepoDefDeleteEntity();

        // confirmation for entity with bookId : 0000000001 is deleted from db
        // the exists method is used to check whether the given entity is
        // present in db.
        jpaHomePage.setBookIdForDefJPASearchOpn("0000000001");
        jpaHomePage = jpaHomePage.jpaRepoDefExists();
        String message = jpaHomePage.getBookAvailabityMessage();

        // confirmation message
        assertThat(message, is("Book Not Available!!"));
    }

    /**
     * 
     */
    @Test
    public void testDJPA0201013() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0201013Click();
        // open registration form
        RegisterBookPage bookEntryForm = jpaHomePage.registerBookForm();

        bookEntryForm.setCategoryName("A01");
        bookEntryForm.setClobCode("someclobCode");
        bookEntryForm.setPrice("1234");
        bookEntryForm.setReleaseDate("2015/11/25");
        bookEntryForm.setTitle("Title19");

        BookDetailsPage bookDetailsPage = bookEntryForm.registerUsingFlush();

        // Assertion for record earlier registered in DB.
        assertThat(bookDetailsPage.getTitle(), is("Title19"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getClobCode(), is("someclobCode"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("1234"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2015/11/25"));

        /**
         * Confirm that the flush method performed the insert operation by checking 1. its timeStamp of operation and 2.the
         * timeStamp of the test debug statement just next to it. The insert statement timeStamp should be smaller than the test
         * debug statement.
         */
        List<String> list1 = dbLogAssertOperations.getLogByRegexMessageTime(
                null, null, "\\\\*Debug:: after flush*");

        List<String> list2 = dbLogAssertOperations.getLogByRegexMessageTime(
                null, null,
                "\\\\*insert into t_book_eg \\(blob_code, category_id, clob_code, created_by, created_date, last_modified_by,"
                        + " last_modified_date, price, release_date, title, version, book_id\\)*");

        Timestamp afterInserTm = new Timestamp(Long.valueOf(list1.get(0)));
        Timestamp inserTm = new Timestamp(Long.valueOf(list2.get(0)));

        assertThat(afterInserTm.after(inserTm), is(true));

    }

    @Test
    public void testDJPA0202001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0202001Click();

        // open registration form
        RegisterBookPage bookEntryForm = jpaHomePage.registerBookForm();

        bookEntryForm.setCategoryName("A01");
        bookEntryForm.setClobCode("someclobCode");
        bookEntryForm.setPrice("1234");
        bookEntryForm.setReleaseDate("2015/11/25");
        bookEntryForm.setTitle("Title19");

        RegisterBookPage bookDetailsPage = bookEntryForm.saveUsingCustomRepo();

        // Assertion for record earlier registered in DB.
        String bookId = bookDetailsPage.getBookIdVal();
        assertThat(bookDetailsPage.getTitle(), is("Title19"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getClobCode(), is("someclobCode"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("1234"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2015/11/25"));

        jpaHomePage = bookDetailsPage.goBackToPaginatedList();

        jpaHomePage.setBookIdForDefJPASearchOpn(bookId);

        bookDetailsPage = jpaHomePage.searchUsingCustomRepository();

        // confirmation of find method of custom repository
        assertThat(bookDetailsPage.getBookIdVal(), is(bookId));
        assertThat(bookDetailsPage.getTitle(), is("Title19"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getClobCode(), is("someclobCode"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("1234"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2015/11/25"));
    }

    @Test
    public void testDJPA0202002() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0202002Click();

        // open registration form
        RegisterBookPage bookEntryForm = jpaHomePage.registerBookForm();

        bookEntryForm.setCategoryName("A01");
        bookEntryForm.setClobCode("someclobCode");
        bookEntryForm.setPrice("1234");
        bookEntryForm.setReleaseDate("2015/11/25");
        bookEntryForm.setTitle("Title19");

        RegisterBookPage bookDetailsPage = bookEntryForm
                .saveUsingNoInheritedIntfRepo();

        // Assertion for record earlier registered in DB.
        String bookId = bookDetailsPage.getBookIdVal();
        assertThat(bookDetailsPage.getTitle(), is("Title19"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getClobCode(), is("someclobCode"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("1234"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2015/11/25"));

        jpaHomePage = bookDetailsPage.goBackToPaginatedList();

        jpaHomePage.setBookIdForDefJPASearchOpn(bookId);

        bookDetailsPage = jpaHomePage.searchUsingNoIntfInheritedRepository();

        // confirmation of find method of custom repository
        assertThat(bookDetailsPage.getBookIdVal(), is(bookId));
        assertThat(bookDetailsPage.getTitle(), is("Title19"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getClobCode(), is("someclobCode"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("1234"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2015/11/25"));
    }

    @Test
    public void testDJPA0301001() {
        clearAndCreateTestDataForDeliverOrder();

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        OrderDeliveryListPage orderListPage = jpaIndexPage.djpa0301001Click();
        // set the search value : status
        orderListPage.setSearchValue("受付");

        // search using @Query
        orderListPage = orderListPage.searchByGivenStatus();

        // confirm the details of the first record in the list
        DeliveryOrderDetailsPage orderDetailsPage = orderListPage
                .displayOrderDetails(1);

        // here the records are displayed in descending order
        // as in the query DESC is used
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("送り先名5"));
        assertThat(orderDetailsPage.getReceiverAddress(), is("送り先住所5"));
        assertThat(orderDetailsPage.getSenderName(), is("送り主名5"));
        assertThat(orderDetailsPage.getSenderAddress(), is("送り主住所5"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("ドライバー5"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("受付"));

        orderListPage = orderDetailsPage.showOrderListPage();

        orderListPage.setSearchValue("受付");

        orderListPage = orderListPage.searchByGivenStatus();

        orderDetailsPage = orderListPage.displayOrderDetails(2);

        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("送り先名4"));
        assertThat(orderDetailsPage.getReceiverAddress(), is("送り先住所4"));
        assertThat(orderDetailsPage.getSenderName(), is("送り主名4"));
        assertThat(orderDetailsPage.getSenderAddress(), is("送り主住所4"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("ドライバー4"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("受付"));

    }

    @Test
    public void testDJPA0301002() {
        clearAndCreateTestDataForDeliverOrder();

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        OrderDeliveryListPage orderListPage = jpaIndexPage.djpa0301002Click();
        // set the search value : sender address sample value
        orderListPage.setSearchValue("send");

        orderListPage = orderListPage.setSearchCriteria(
                "Forward Match Sender Address");

        orderListPage = orderListPage.atQueryLikeSearch();
        DeliveryOrderDetailsPage orderDetailsPage = orderListPage
                .displayOrderDetails(1);
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("receiverName6"));
        assertThat(orderDetailsPage.getReceiverAddress(), is(
                "receiverAddress6"));
        assertThat(orderDetailsPage.getSenderName(), is("senderName6"));
        assertThat(orderDetailsPage.getSenderAddress(), is("senderAddress6"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("deliveryDriver6"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("配達中"));

        orderListPage = orderDetailsPage.showOrderListPage();

        // set the search value : receiver address sample value
        orderListPage.setSearchValue("1");

        orderListPage = orderListPage.setSearchCriteria(
                "Backward Match Receiver Address");

        orderListPage = orderListPage.atQueryLikeSearch();

        orderDetailsPage = orderListPage.displayOrderDetails(1);
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("送り先名1"));
        assertThat(orderDetailsPage.getReceiverAddress(), is("送り先住所1"));
        assertThat(orderDetailsPage.getSenderName(), is("送り主名1"));
        assertThat(orderDetailsPage.getSenderAddress(), is("送り主住所1"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("ドライバー1"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("受付"));

        orderListPage = orderDetailsPage.showOrderListPage();

        // set the search value : receiver address sample value
        orderListPage.setSearchValue("nde");

        orderListPage = orderListPage.setSearchCriteria(
                "Partial Match Sender Name");
        orderListPage = orderListPage.atQueryLikeSearch();
        orderDetailsPage = orderListPage.displayOrderDetails(1);
        assertThat(orderDetailsPage.getReceiverName(), is("receiverName6"));
        assertThat(orderDetailsPage.getReceiverAddress(), is(
                "receiverAddress6"));
        assertThat(orderDetailsPage.getSenderName(), is("senderName6"));
        assertThat(orderDetailsPage.getSenderAddress(), is("senderAddress6"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("deliveryDriver6"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("配達中"));
    }

    @Test
    public void testDJPA0302001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        OrderDeliveryListPage orderListPage = jpaIndexPage.djpa0302001Click();

        orderListPage.setSearchValue("受付");

        orderListPage = orderListPage.searchByMethodNameConvention();

        DeliveryOrderDetailsPage orderDetailsPage = orderListPage
                .displayOrderDetails(1);

        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("送り先名1"));
        assertThat(orderDetailsPage.getReceiverAddress(), is("送り先住所1"));
        assertThat(orderDetailsPage.getSenderName(), is("送り主名1"));
        assertThat(orderDetailsPage.getSenderAddress(), is("送り主住所1"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("ドライバー1"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("受付"));

        orderListPage = orderDetailsPage.showOrderListPage();

        orderListPage.setSearchValue("受付");

        orderListPage = orderListPage.searchByMethodNameConvention();

        orderListPage = orderListPage.gotoNextPage();

        orderDetailsPage = orderListPage.displayOrderDetails(2);

        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("送り先名4"));
        assertThat(orderDetailsPage.getReceiverAddress(), is("送り先住所4"));
        assertThat(orderDetailsPage.getSenderName(), is("送り主名4"));
        assertThat(orderDetailsPage.getSenderAddress(), is("送り主住所4"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("ドライバー4"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("受付"));
    }

    @Test
    public void testDJPA0303001() {
        clearAndCreateTestDataForDeliverOrder();

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        OrderDeliveryListPage orderListPage = jpaIndexPage.djpa0303001Click();
        // search for status 受付
        orderListPage.setSearchValue("受付");

        orderListPage = orderListPage.searchUsingNativeQuery();

        DeliveryOrderDetailsPage orderDetailsPage = orderListPage
                .displayOrderDetails(1);

        // confirmation of each property
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("送り先名1"));
        assertThat(orderDetailsPage.getReceiverAddress(), is("送り先住所1"));
        assertThat(orderDetailsPage.getSenderName(), is("送り主名1"));
        assertThat(orderDetailsPage.getSenderAddress(), is("送り主住所1"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("ドライバー1"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("受付"));

        orderListPage = orderDetailsPage.showOrderListPage();

        // search for status 配達中
        orderListPage.setSearchValue("配達中");

        orderListPage = orderListPage.searchUsingNativeQuery();

        // confirm result of native query for status 配達中 : 1st item
        orderDetailsPage = orderListPage.displayOrderDetails(1);

        // confirmation of each property
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("receiverName6"));
        assertThat(orderDetailsPage.getReceiverAddress(), is(
                "receiverAddress6"));
        assertThat(orderDetailsPage.getSenderName(), is("senderName6"));
        assertThat(orderDetailsPage.getSenderAddress(), is("senderAddress6"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("deliveryDriver6"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("配達中"));

        orderListPage = orderDetailsPage.showOrderListPage();

        orderListPage.setSearchValue("配達中");

        orderListPage = orderListPage.searchUsingNativeQuery();

        // confirm result of native query for status 配達中 : 2nd item
        orderDetailsPage = orderListPage.displayOrderDetails(2);

        // confirmation of each property
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("receiverName8"));
        assertThat(orderDetailsPage.getReceiverAddress(), is(
                "receiverAddress8"));
        assertThat(orderDetailsPage.getSenderName(), is("senderName8"));
        assertThat(orderDetailsPage.getSenderAddress(), is("senderAddress8"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("deliveryDriver8"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("配達中"));

        // clearTestDataForDeliverOrder();
    }

    /**
     * Parameter passing to the query. Confirmation of parameters passing to the @Query annotation
     */
    @Test
    public void testDJPA0401001() {
        testDJPA0301001();
    }

    /**
     * Searching page of entities matching the conditions. Confirmation of page of entities matching the conditions
     */
    @Test
    public void testDJPA0402001() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        OrderDeliveryListPage orderListPage = jpaIndexPage.djpa0402001Click();

        orderListPage.setSearchValue("受付");
        orderListPage = orderListPage.searchEnityPageMatchingConditions();

        DeliveryOrderDetailsPage orderDetailsPage = orderListPage
                .displayOrderDetails(1);
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("送り先名5"));
        assertThat(orderDetailsPage.getReceiverAddress(), is("送り先住所5"));
        assertThat(orderDetailsPage.getSenderName(), is("送り主名5"));
        assertThat(orderDetailsPage.getSenderAddress(), is("送り主住所5"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("ドライバー5"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("受付"));

        orderDetailsPage.showOrderListPage();

        orderListPage.setSearchValue("受付");
        orderListPage = orderListPage.searchEnityPageMatchingConditions();
        orderListPage = orderListPage.gotoNextPage();

        orderDetailsPage = orderListPage.displayOrderDetails(1);
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("送り先名3"));
        assertThat(orderDetailsPage.getReceiverAddress(), is("送り先住所3"));
        assertThat(orderDetailsPage.getSenderName(), is("送り主名3"));
        assertThat(orderDetailsPage.getSenderAddress(), is("送り主住所3"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("ドライバー3"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("受付"));
    }

    /**
     * Searching all entities matching the dynamic conditions
     */
    @Test
    public void testDJPA0501001() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        OrderDeliveryListPage orderListPage = jpaIndexPage.djpa0501001Click();
        // specify only two values so that the total conditions passed to query
        // becomes 2 out of 3
        orderListPage.setSearchValue("受付,配達中");
        orderListPage.setDeliveryTypeValue("通常");

        orderListPage = orderListPage.searchPageUsingDynaConditions();

        DeliveryOrderDetailsPage orderDetailsPage = orderListPage
                .displayOrderDetails(1);

        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("送り先名1"));
        assertThat(orderDetailsPage.getReceiverAddress(), is("送り先住所1"));
        assertThat(orderDetailsPage.getSenderName(), is("送り主名1"));
        assertThat(orderDetailsPage.getSenderAddress(), is("送り主住所1"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("ドライバー1"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("受付"));

        orderDetailsPage.showOrderListPage();
        // Once again search with above criteria and assert for any other record
        // specify only two values so that the total conditions passed to query
        // becomes 2 out of 3
        orderListPage.setSearchValue("受付,配達中");
        orderListPage.setDeliveryTypeValue("通常");

        orderListPage = orderListPage.searchPageUsingDynaConditions();

        orderDetailsPage = orderListPage.displayOrderDetails(7);

        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("receiverName8"));
        assertThat(orderDetailsPage.getReceiverAddress(), is(
                "receiverAddress8"));
        assertThat(orderDetailsPage.getSenderName(), is("senderName8"));
        assertThat(orderDetailsPage.getSenderAddress(), is("senderAddress8"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("deliveryDriver8"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("配達中"));

        orderDetailsPage.showOrderListPage();

        // Once again search by changing criteria and assert for record
        // specify only one values so that the total conditions passed to query
        // becomes 1 out of 3
        orderListPage.setDeliveryTypeValue("速配");
        orderListPage = orderListPage.searchPageUsingDynaConditions();

        orderDetailsPage = orderListPage.displayOrderDetails(1);

        assertThat(orderDetailsPage.getDeliveryType(), is("2"));
        assertThat(orderDetailsPage.getReceiverName(), is("receiverName7"));
        assertThat(orderDetailsPage.getReceiverAddress(), is(
                "receiverAddress7"));
        assertThat(orderDetailsPage.getSenderName(), is("senderName7"));
        assertThat(orderDetailsPage.getSenderAddress(), is("senderAddress7"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("deliveryDriver7"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("完了"));

        orderDetailsPage.showOrderListPage();
        // do not specify any search condition
        // no records would be displayed or returned by query execution.
        orderListPage = orderListPage.searchPageUsingDynaConditions();
        // confirm no records are returned.
        boolean bookDetailLink = orderListPage.isBookDetailLinkPresent(1);
        assertThat(bookDetailLink, is(false));
    }

    /**
     * Fetching record based on the primary id
     */
    @Test
    public void testDJPA0601001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0601001Click();

        jpaHomePage.setBookId("20");

        BookDetailsPage bookDetailsPage = jpaHomePage.findOneById();

        assertThat(bookDetailsPage.getBookIdVal(), is("20"));
        assertThat(bookDetailsPage.getTitle(), is("Title19"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getClobCode(), is("someclobCode"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("1234"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2015/11/25"));

    }

    /**
     * Load timing of the related-entity : Eager Fetch
     */
    @Test
    public void testDJPA0601002() {
        testDJPA0601001();
        List<String> list = dbLogAssertOperations.getLogByRegexMessage(null,
                null,
                "\\\\*select jpacategor0_.category_id as category1_2_0_, jpacategor0_.name as name2_2_0_ from m_category_eg jpacategor0_ where jpacategor0_.category_id=1*");
        Integer expVal = 0;
        // confirmation of no query is for dependent entity(i.e.JPACategoryEG )
        assertThat(list.size(), is(expVal));

    }

    /**
     * Load timing of the related-entity : Lazy Fetch
     */
    @Test
    public void testDJPA0601003() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        JPAHomePage jpaHomePage = jpaIndexPage.djpa0601003Click();
        jpaHomePage.setBookIdAcquiringNotForeignKey("0001");
        BookDetailsPage lazyBookPage = jpaHomePage.acquiringNotForeignKey();

        // Assertion for record earlier registered in DB.
        assertThat(lazyBookPage.getBookIdVal(), is("1"));
        assertThat(lazyBookPage.getTitle(), is("title1"));
        assertThat(lazyBookPage.getCategoryName(), is("A01"));
        assertThat(lazyBookPage.getClobCode(), is("54455354"));
        assertThat(lazyBookPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((lazyBookPage.getClobCode().getBytes()))));
        assertThat(lazyBookPage.getPrice(), is("40"));
        assertThat(lazyBookPage.getReleaseDate(), is("2013/01/01"));

        List<String> list = dbLogAssertOperations.getLogByRegexMessage(null,
                null,
                "\\\\*[select jpacategor0_.category_id as category]1_3_0_, jpacategor0_.name as name2_3_0_ from m_category_lz jpacategor0_ where jpacategor0_.category_id=1*");
        Integer expVal = 1;
        // confirmation of query is for dependent entity(i.e.JPACategoryLz )
        assertThat(list.size(), is(expVal));
    }

    /**
     * Load timing of the related-entity : Lazy Fetch (cant't fetch related-entity when acquire foreign key)
     * For changes Hibernate 5.2.12(HHH-11838)
     */
    @Test
    public void testDJPA0601004() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        JPAHomePage jpaHomePage = jpaIndexPage.djpa0601004Click();
        jpaHomePage.setBookIdAcquiringForeignKey("0001");
        SystemErrorPage sysErrorPage = jpaHomePage.acquiringForeignKey();

        // Assertion for system error occurred due to lazy initialization.
        assertTrue(Pattern.compile(
                "^could\\snot\\sinitialize\\sproxy\\s\\[([a-z]|[A-Z]|[0-9]|#|\\.){1,}\\]\\s-\\sno\\sSession$")
                .matcher(sysErrorPage.getErrorMessage()).matches());
    }

    /**
     * Search entities by conditions other than ID
     */
    @Test
    public void testDJPA0602001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0602001Click();

        // search using book title
        jpaHomePage.setBookTitle("Manual Title 1");

        // there is only one book satisfying above criteria
        BookDetailsPage bookDetailsPage = jpaHomePage
                .searchUsingNoPrimaryColumnValue();

        // assertThat(bookDetailsPage.getBookIdVal(), is("4"));
        assertThat(bookDetailsPage.getTitle(), is("Manual Title 1"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getClobCode(), is("54455354"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("40"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2013/01/01"));

        jpaHomePage = bookDetailsPage.goBackToPaginatedList();

        // once again search using book title
        jpaHomePage.setBookTitle("Title19");

        // for above entered book title there is more than one book
        // as a result of multiple records being returned, there is
        // IncorrectResultSizeDataAccessException thrown
        bookDetailsPage = jpaHomePage.searchUsingNoPrimaryColumnValue();

        // confirm the IncorrectResultSizeDataAccessException
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*.IncorrectResultSizeDataAccessException.*");
    }

    /**
     * Adding an entity to database using the save method
     */
    @Test
    public void testDJPA0701001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        JPAHomePage jpaHomePage = jpaIndexPage.djpa0701001Click();
        DeliveryOrderDetailsPage orderDetailForm = jpaHomePage
                .addEntityBySave();

        orderDetailForm.setAcceptDateTime("2015/12/13");
        orderDetailForm.setCompletionDateTime("2015/12/14");
        orderDetailForm.setDeliveryDriver("TestDriver");
        orderDetailForm.setDeliveryStatus("完了");
        orderDetailForm.setDeliveryType("1");
        orderDetailForm.setReceiverAddress("Test Receiver Address");
        orderDetailForm.setReceiverName("Test Receiver Name");
        orderDetailForm.setSenderAddress("Test Sender Address");
        orderDetailForm.setSenderName("Test Sender Name");

        DeliveryOrderDetailsPage orderDetailsPage = orderDetailForm
                .addEntityBySave();

        assertThat(orderDetailsPage.getAcceptDateTime(), is(
                "Sun Dec 13 00:00:00 JST 2015"));
        assertThat(orderDetailsPage.getCompletionDateTime(), is(
                "Mon Dec 14 00:00:00 JST 2015"));
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is(
                "Test Receiver Name"));
        assertThat(orderDetailsPage.getReceiverAddress(), is(
                "Test Receiver Address"));
        assertThat(orderDetailsPage.getSenderName(), is("Test Sender Name"));
        assertThat(orderDetailsPage.getSenderAddress(), is(
                "Test Sender Address"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("TestDriver"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("完了"));
    }

    /**
     * Confirmation of the related entity getting saved in DB by setting it in the Parent entity which is managed by
     * entity-manager
     */
    @Test
    public void testDJPA0702001() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0702001Click();
        OrderEntryPage orderEntryPage = ordersPage.createOrder();
        orderEntryPage.setQuantity(1, "1");
        orderEntryPage.setQuantity(2, "1");
        orderEntryPage.setQuantity(3, "2");
        orderEntryPage.setOrderMemo("Test Order Comment");

        ordersPage = orderEntryPage.placeOrder();

        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(8);

        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("8"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                "Order accepted"));
        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("1500"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is(
                "Test Order Comment"));
    }

    /**
     * Confirmation of linking the related entity to the existing entity fetched from DB and saving the related entity.
     */
    @Test
    public void testDJPA0703001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0703001Click();

        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(6);

        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("6"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                "Item Shipped"));
        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("13100"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is("dummy6"));

        OrderEntryPage orderEntryPage = orderDetailsPage.addItemToOrder();

        orderEntryPage.setQuantity(1, "1");

        orderEntryPage.setOrderMemo("Mod comment");

        ordersPage = orderEntryPage.addToExistingOrder();

        orderDetailsPage = ordersPage.displayOderDetail(6);

        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("6"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                "Item Shipped"));
        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("13200"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is(
                "dummy6 Mod comment"));
    }

    /**
     * Confirming of saving directly the related entity
     */
    @Test
    public void testDJPA0704001() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0704001Click();
        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(2);
        // Check for item 1 : The order contains only one item
        boolean itemPresent = orderDetailsPage.isItemPresent(1);
        assertThat(itemPresent, is(true));
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000002"));

        // Check for item 2 : The order contains only one item so it should be false.
        itemPresent = orderDetailsPage.isItemPresent(2);
        assertThat(itemPresent, is(false));

        // Come back to orderListPage
        ordersPage = orderDetailsPage.backToOrdersPage();

        // This will add an order item to the existing order with order id 2.
        OrderEntryPage orderEntryPage = ordersPage.createOrder();
        orderEntryPage.setQuantity(1, "1");

        // call method to add dependent entity directly.
        ordersPage = orderEntryPage.addDirectDependentEntity();

        // show details of orderId#2
        orderDetailsPage = ordersPage.displayOderDetail(2);

        // Now confirm that there are two items in the order.
        itemPresent = orderDetailsPage.isItemPresent(1);
        assertThat(itemPresent, is(true));
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000002"));

        // Check for item 2 : The order contains two items so it should be true.
        itemPresent = orderDetailsPage.isItemPresent(2);
        assertThat(itemPresent, is(true));
        assertThat(orderDetailsPage.getItemCodeValue(2), is("ITM0000001"));
    }

    /**
     * Confirmation of the save method of the repository to update an entity to the database.
     */
    @Test
    public void testDJPA0801001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0801001Click();

        // original order status value before update
        String orgStatus = "Order accepted";

        // confirmation of order status before updating the status of this orderId#1.
        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(1);
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                orgStatus));

        orderDetailsPage.clearStatusField();

        // new status value
        String statusToSet = "Item Shipped";

        orderDetailsPage.setStatus(statusToSet);
        // update the status
        ordersPage = orderDetailsPage.updateStatus();

        // again get the details of orderId#1 after status update
        orderDetailsPage = ordersPage.displayOderDetail(1);
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                statusToSet));

    }

    /**
     * Confirmation of the modified values of entities getting saved in db even when save method is not invoked on the entity
     */
    @Test
    public void testDJPA0802001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0802001Click();

        // Confirm the order details before performing update orderID#1
        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(1);
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("10"));
        assertThat(orderDetailsPage.getItemCodeValue(2), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(2), is("10"));
        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("11000"));

        // Update the quantity for item#2 of the item list
        ItemPage itemPage = orderDetailsPage.displayItemDetail(2);
        itemPage.clearQuantityField();
        itemPage.setQuantity(5);
        ordersPage = itemPage.updateQuantity();

        // Confirmation of updated item:: orderID#1, item number 2(itemNumber#7)
        orderDetailsPage = ordersPage.displayOderDetail(1);
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("10"));
        assertThat(orderDetailsPage.getItemCodeValue(2), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(2), is("5"));
        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("6000"));

    }

    /**
     * Update the related entity directly without the parent entity using the related entities save method
     */
    @Test
    public void testDJPA0803001() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0803001Click();

        // Confirm the order details before performing update orderID#3
        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(3);
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000003"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("30"));
        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("6000"));

        // Update the quantity for item#1 of the item list
        ItemPage itemPage = orderDetailsPage.displayItemDetail(1);
        itemPage.clearQuantityField();
        itemPage.setQuantity(20);
        ordersPage = itemPage.directUpdateRelatedEntity();

        // Confirmation of updated item:: orderID#3, item number 1(itemNumber#3)
        orderDetailsPage = ordersPage.displayOderDetail(3);
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000003"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("20"));
        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("6000"));

    }

    /**
     * Confirming the entity is getting updated by using query annotation
     */
    @Test
    public void testDJPA0804001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0804001Click();

        // Confirm the order details before performing update orderID#1
        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(1);
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("10"));
        assertThat(orderDetailsPage.isItemRevmovable(2), is("No"));

        assertThat(orderDetailsPage.getItemCodeValue(2), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(2), is("5"));
        assertThat(orderDetailsPage.isItemRevmovable(2), is("No"));

        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("6000"));
        // this method will update the flags isItemRevmovable to true for all items
        ItemPage itemPage = orderDetailsPage.updateByQuery();

        // check the item status of item#1. This would return the same status as above though the update method is invoked
        // this because the default value of clearAutomatically attribute is used durin update
        String itemRemovable = itemPage.getItemStatus();
        assertThat(itemRemovable, is("No"));

        ordersPage = itemPage.backToOrdersPage();

        // Confirmation of flags for the items are updated to Yes(true)
        orderDetailsPage = ordersPage.displayOderDetail(1);
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("10"));
        assertThat(orderDetailsPage.isItemRevmovable(2), is("Yes"));

        assertThat(orderDetailsPage.getItemCodeValue(2), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(2), is("5"));
        assertThat(orderDetailsPage.isItemRevmovable(2), is("Yes"));

        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("6000"));
    }

    /**
     * Confirming that the exception is thrown if no @ Modifying annotaion is used above the method have update query
     */
    @Test
    public void testDJPA0804002() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0804002Click();
        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(1);
        orderDetailsPage.updateByQueryErrorScenario();
        // Assertion for system error occurred due to no @Modifying annotation
        // is used on update query method.
        // confirm the IncorrectResultSizeDataAccessException

        List<String> list = dbLogAssertOperations.getLogByRegexMessage(null,
                null,
                "\\\\*org.hibernate.hql.internal.QueryExecutionRequestException: Not supported for DML operations*");
        System.out.println("**=>" + list.size());
        Integer expVal = 2;
        // confirmation of error occurrence
        assertThat(list.size(), is(expVal));
    }

    /**
     * Usage of clearAutomatically attribute along with @Modifying annotation Confirmation of the clearAutomatically attribute
     */
    @Test
    public void testDJPA0804003() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0804003Click();

        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(3);

        String itemRemovable = orderDetailsPage.isItemRevmovable(1);
        // By default the item is not removable . Confirming the same before update
        assertThat(itemRemovable, is("No"));

        // this method makes the item removable by updating flag to true.
        ItemPage itemPage = orderDetailsPage.updateByQueryWithClearON();

        // As clearAutomatically attribute is set to true, the above update is
        // automatically reflected in DB and the updated item is displayed.
        itemRemovable = itemPage.getItemStatus();
        assertThat(itemRemovable, is("Yes"));
    }

    /**
     * Deleting parent-entity and related-entity Confirmation of deletion of both parent and related entities upon invoking the
     * delete method on the parent repository.
     */
    @Test
    public void testDJPA0901001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0901001Click();
        // confirm before deletion the details of the order
        String orderIdVal = "5";
        ordersPage.setSearchOrderId(orderIdVal);
        OrderDetailsPage orderDetailsPage = ordersPage.displayOrderDetail();

        // confirm that the order is really present
        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("5"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is("dummy5"));
        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("50000"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                "Stock checking"));
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("50"));
        assertThat(orderDetailsPage.isItemRevmovable(1), is("No"));

        // Back to orders page to delete the order
        ordersPage = orderDetailsPage.backToOrdersPage();

        // set the order to delete
        ordersPage.setDeleteOrderId(orderIdVal);

        // delete the order.
        ordersPage = ordersPage.deleteOrder();

        // now confirm the order is really deleted by doing the search for the deleted order
        // resource not found exception should be displayed.
        ordersPage.setSearchOrderId(orderIdVal);
        ordersPage.displayOrderDetail();

        // confirm the IncorrectResultSizeDataAccessException
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*.ResourceNotFoundException.*");
    }

    /**
     * Deleting the related-entity
     */
    @Test
    public void testDJPA0902001() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0902001Click();
        // confirm before deletion the details of the order
        Integer orderIdVal = 4;
        Integer item = 1;
        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(
                orderIdVal);

        // confirm that the order is really present
        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("4"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is("dummy4"));
        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("4000"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                "Order accepted"));
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("40"));
        assertThat(orderDetailsPage.isItemRevmovable(1), is("No"));

        // Back to orders page to delete the order
        ordersPage = orderDetailsPage.backToOrdersPage();

        orderDetailsPage = ordersPage.displayOderDetail(orderIdVal);

        ItemPage itemPage = orderDetailsPage.displayItemDetail(item);

        ordersPage = itemPage.deleteRelatedEntity();

        orderDetailsPage = ordersPage.displayOderDetail(orderIdVal);

        boolean itemPresent = orderDetailsPage.isItemPresent(item);

        // confirmation for the item is deleted
        assertThat(itemPresent, is(false));

    }

    /**
     * Deleting the related-entity directly. Confirmation of deleting the related entity without using the parent entity
     */
    @Test
    public void testDJPA0903001() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0903001Click();
        // confirm before deletion the details of the order
        Integer orderIdVal = 5;

        // candid item for deletion.
        Integer itemSrNum = 4;

        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(
                orderIdVal);

        // confirm that the order is really present with 4 items
        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("6"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is(
                "dummy6 Mod comment"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                "Item Shipped"));

        // Item 1
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000003"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("60"));
        assertThat(orderDetailsPage.isItemRevmovable(1), is("No"));

        // Item 2
        assertThat(orderDetailsPage.getItemCodeValue(2), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(2), is("1"));
        assertThat(orderDetailsPage.isItemRevmovable(2), is("No"));

        // Item 3
        assertThat(orderDetailsPage.getItemCodeValue(3), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(3), is("1"));
        assertThat(orderDetailsPage.isItemRevmovable(3), is("No"));

        // Item 4
        assertThat(orderDetailsPage.getItemCodeValue(4), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(4), is("1"));

        // The item number 4 would be deleted. hence traversing to its details
        ItemPage itemPage = orderDetailsPage.displayItemDetail(itemSrNum);
        ordersPage = itemPage.directDeleteRelatedEntity();

        // Now confirm the items of order # 6

        orderDetailsPage = ordersPage.displayOderDetail(orderIdVal);
        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("6"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is(
                "dummy6 Mod comment"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                "Item Shipped"));

        // confirmation for the item 3 is deleted
        assertThat(orderDetailsPage.isItemPresent(itemSrNum), is(false));

        // confirmation for the item 1 and 2 are present
        assertThat(orderDetailsPage.isItemPresent(1), is(true));
        assertThat(orderDetailsPage.isItemPresent(2), is(true));
        assertThat(orderDetailsPage.isItemPresent(3), is(true));

        // confirmation of the details of item1 and item2 and item3
        // Item 1
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000003"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("60"));
        assertThat(orderDetailsPage.isItemRevmovable(1), is("No"));

        // Item 2
        assertThat(orderDetailsPage.getItemCodeValue(2), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(2), is("1"));
        assertThat(orderDetailsPage.isItemRevmovable(2), is("No"));

        // Item 3
        assertThat(orderDetailsPage.getItemCodeValue(3), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(3), is("1"));
        assertThat(orderDetailsPage.isItemRevmovable(3), is("No"));
    }

    /**
     * Deleting the related-entity directly. Confirmation of deleting the related entity not getting deleted as the parent of
     * the entity is in managed state
     */
    @Test
    public void testDJPA0904001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0904001Click();

        // confirm before deletion the details of the order
        Integer orderIdVal = 5;

        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(
                orderIdVal);

        // confirm that the order is really present with 3 items
        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("6"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is(
                "dummy6 Mod comment"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                "Item Shipped"));

        // Item 1
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000003"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("60"));
        assertThat(orderDetailsPage.isItemRevmovable(1), is("No"));

        // Item 2
        assertThat(orderDetailsPage.getItemCodeValue(2), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(2), is("1"));
        assertThat(orderDetailsPage.isItemRevmovable(2), is("No"));

        // Item 3
        assertThat(orderDetailsPage.getItemCodeValue(3), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(3), is("1"));
        assertThat(orderDetailsPage.isItemRevmovable(3), is("No"));

        ItemPage itemPage = orderDetailsPage.displayItemDetail(1);

        // unsuccessful deletion of related entity as the parent entity is in managed state.
        ordersPage = itemPage.deleteRelatedEntityOfManagedParentEntity();

        orderDetailsPage = ordersPage.displayOderDetail(orderIdVal);

        // confirmation for no items have been deleted
        assertThat(orderDetailsPage.isItemPresent(1), is(true));
        assertThat(orderDetailsPage.isItemPresent(2), is(true));
        assertThat(orderDetailsPage.isItemPresent(3), is(true));

        // confirmation of each items once again
        // confirm that the order is really present with 3 items
        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("6"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is(
                "dummy6 Mod comment"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                "Item Shipped"));

        // Item 1
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000003"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("60"));
        assertThat(orderDetailsPage.isItemRevmovable(1), is("No"));

        // Item 2
        assertThat(orderDetailsPage.getItemCodeValue(2), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(2), is("1"));
        assertThat(orderDetailsPage.isItemRevmovable(2), is("No"));

        // Item 3
        assertThat(orderDetailsPage.getItemCodeValue(3), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(3), is("1"));
        assertThat(orderDetailsPage.isItemRevmovable(3), is("No"));
    }

    /**
     * Deleting using query method. Confirming the entity is getting deleted by using query annotation
     */
    @Test
    public void testDJPA0905001() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa0903001Click();
        // confirm before deletion the details of the order
        Integer orderIdVal = 5;

        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(
                orderIdVal);

        // confirm that the order is really present with 3 items
        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("6"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is(
                "dummy6 Mod comment"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                "Item Shipped"));

        // Item 1
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000003"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("60"));
        assertThat(orderDetailsPage.isItemRevmovable(1), is("No"));

        // Item 2
        assertThat(orderDetailsPage.getItemCodeValue(2), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(2), is("1"));
        assertThat(orderDetailsPage.isItemRevmovable(2), is("No"));

        // Item 3
        assertThat(orderDetailsPage.getItemCodeValue(3), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(3), is("1"));
        assertThat(orderDetailsPage.isItemRevmovable(3), is("No"));

        ordersPage = orderDetailsPage.deleteByQueryWithClearON();

        orderDetailsPage = ordersPage.displayOderDetail(orderIdVal);

        // confirmation for the items are deleted
        assertThat(orderDetailsPage.isItemPresent(1), is(false));
        assertThat(orderDetailsPage.isItemPresent(2), is(false));
        assertThat(orderDetailsPage.isItemPresent(3), is(false));

    }

    /**
     * Type of matching condition specified in Query. To confirm the behavior when type of matching is specified in the query
     * for the escape character "%"
     */
    @Test
    public void testDJPA1001001() {
        clearAndCreateTestDataForDeliverOrder();

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        OrderDeliveryListPage orderListPage = jpaIndexPage.djpa1001001Click();
        // set the search value : sender address sample value
        orderListPage.setEscapeSearchValue("send");

        orderListPage = orderListPage.escapeSearchModulo();

        DeliveryOrderDetailsPage orderDetailsPage = orderListPage
                .displayOrderDetails(1);
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("receiverName6"));
        assertThat(orderDetailsPage.getReceiverAddress(), is(
                "receiverAddress6"));
        assertThat(orderDetailsPage.getSenderName(), is("senderName6"));
        assertThat(orderDetailsPage.getSenderAddress(), is("senderAddress6"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("deliveryDriver6"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("配達中"));

        orderListPage = orderDetailsPage.showOrderListPage();

        orderListPage.setEscapeSearchValue("send");

        orderListPage = orderListPage.escapeSearchModulo();

        orderListPage = orderListPage.gotoNextPage();

        orderDetailsPage = orderListPage.displayOrderDetails(1);
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("receiverName12"));
        assertThat(orderDetailsPage.getReceiverAddress(), is(
                "receiverAddress12"));
        assertThat(orderDetailsPage.getSenderName(), is("senderName12"));
        assertThat(orderDetailsPage.getSenderAddress(), is("senderAddress12"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is(
                "deliveryDriver12"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("配達中"));
    }

    /**
     * Type of matching condition specified in Query. To confirm the behavior when type of matching is specified in the query
     * for the escape character "_"
     */
    @Test
    public void testDJPA1001002() {
        clearAndCreateTestDataForDeliverOrder();

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        OrderDeliveryListPage orderListPage = jpaIndexPage.djpa1001002Click();
        // set the search value : sender address sample value
        orderListPage.setEscapeSearchValue("_end%");

        orderListPage = orderListPage.escapeSearchUnderscore();

        orderListPage = orderListPage.gotoNextPage();

        DeliveryOrderDetailsPage orderDetailsPage = orderListPage
                .displayOrderDetails(1);
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("receiverName12"));
        assertThat(orderDetailsPage.getReceiverAddress(), is(
                "receiverAddress12"));
        assertThat(orderDetailsPage.getSenderName(), is("senderName12"));
        assertThat(orderDetailsPage.getSenderAddress(), is("senderAddress12"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is(
                "deliveryDriver12"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("配達中"));
    }

    /**
     * Type of matching in logic.To confirm the behavior when type of matching is specified in the Logic.
     */
    @Test
    public void testDJPA1002001() {
        clearAndCreateTestDataForDeliverOrder();

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        OrderDeliveryListPage orderListPage = jpaIndexPage.djpa1001002Click();
        // set the search value : sender address sample value
        orderListPage.setEscapeSearchValue("send");

        orderListPage = orderListPage.escapeSearchMatchInLogic();

        DeliveryOrderDetailsPage orderDetailsPage = orderListPage
                .displayOrderDetails(1);
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("receiverName6"));
        assertThat(orderDetailsPage.getReceiverAddress(), is(
                "receiverAddress6"));
        assertThat(orderDetailsPage.getSenderName(), is("senderName6"));
        assertThat(orderDetailsPage.getSenderAddress(), is("senderAddress6"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("deliveryDriver6"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("配達中"));

        orderListPage = orderDetailsPage.showOrderListPage();

        orderListPage.setEscapeSearchValue("send");

        orderListPage = orderListPage.escapeSearchMatchInLogic();

        orderListPage = orderListPage.gotoNextPage();

        orderDetailsPage = orderListPage.displayOrderDetails(1);
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("receiverName12"));
        assertThat(orderDetailsPage.getReceiverAddress(), is(
                "receiverAddress12"));
        assertThat(orderDetailsPage.getSenderName(), is("senderName12"));
        assertThat(orderDetailsPage.getSenderAddress(), is("senderAddress12"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is(
                "deliveryDriver12"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("配達中"));
    }

    /**
     * JOIN Fetch. Confirmation of the JOIN FETCH clause.
     */
    @Test
    public void testDJPA1101001() {

        clearAndCreateTestDataForDeliverOrder();
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa1101001Click();

        String searchStatus = "Stock checking";
        // set the search status value
        ordersPage.setSearchStatus(searchStatus);

        // search the orders
        ordersPage = ordersPage.searchByJoinFetch();

        // confirm the order details
        assertThat(ordersPage.getOrderAmount(1), is("20300"));
        assertThat(ordersPage.getOrderMemo(1), is("dummy2"));
        assertThat(ordersPage.getOrderStatus(1), is(searchStatus));

        OrderDetailsPage orderDetailsPage = ordersPage.displayOderDetail(1);

        // confirm that the order is really present with 3 items
        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("2"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is("dummy2"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                searchStatus));
        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("20300"));

        // Item 1
        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("20"));
        assertThat(orderDetailsPage.isItemRevmovable(1), is("No"));

    }

    /**
     * Adding the custom methods to an individual Repository interfaces.
     */
    @Test
    public void testDJPA1201001() {
        clearAndCreateTestDataForDeliverOrder();
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        OrderDeliveryListPage orderListPage = jpaIndexPage.djpa1201001Click();
        String searchStatus = "配達中";
        orderListPage.setSearchValue(searchStatus);
        orderListPage.searchByCustomMethodAddedTOIndRepo();

        DeliveryOrderDetailsPage orderDetailsPage = orderListPage
                .displayOrderDetails(1);
        assertThat(orderDetailsPage.getDeliveryType(), is("1"));
        assertThat(orderDetailsPage.getReceiverName(), is("receiverName6"));
        assertThat(orderDetailsPage.getReceiverAddress(), is(
                "receiverAddress6"));
        assertThat(orderDetailsPage.getSenderName(), is("senderName6"));
        assertThat(orderDetailsPage.getSenderAddress(), is("senderAddress6"));
        assertThat(orderDetailsPage.getDeliveryDriver(), is("deliveryDriver6"));
        assertThat(orderDetailsPage.getDeliveryStatus(), is("配達中"));
    }

    /**
     * Adding the custom methods to all Repository interfaces in batch. To confirm the added custom method s to a repository
     * interface which is inherited by all the entities
     */
    @Test
    public void testDJPA1202001() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa1202001Click();

        BookDetailsPage bookDetailPage = jpaHomePage.displayBookDetails(1);
        assertThat(bookDetailPage.getTitle(), is("Manual Title 1"));
        // before update the creator and modified by are same as By InitDB
        assertThat(bookDetailPage.getCreatedBy(), is("By InitDB"));
        assertThat(bookDetailPage.getModifiedBy(), is("By InitDB"));
        bookDetailPage.clearTitleField();
        bookDetailPage.setTitle("New_title1");
        bookDetailPage = bookDetailPage.clickUpdateBtn();
        assertThat(bookDetailPage.getTitle(), is("New_title1"));

        // after update the creator and modified by are different
        assertThat(bookDetailPage.getCreatedBy(), is("By InitDB"));
        assertThat(bookDetailPage.getModifiedBy(), is("UnknownUser"));
        assertThat(bookDetailPage.getModificationTime(), is("2013/12/09"));
        jpaHomePage = bookDetailPage.goBackToPaginatedList();
    }

    /**
     * Storing query fetch results in objects other than entity. To confirm that the data from the query result can be stored in
     * any other custom object other than the entity
     */
    @Test
    public void testDJPA1301001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa1301001Click();

        ordersPage.setDeleteOrderId("4");

        ordersPage = ordersPage.deleteOrder();

        ordersPage.setDeleteOrderId("6");

        ordersPage = ordersPage.deleteOrder();

        ordersPage = ordersPage.fetchResultInCustomObject();

        assertThat(ordersPage.getOrderAmount(1), is("1500"));
        assertThat(ordersPage.getOrderMemo(1), is("Test Order Comment"));
        assertThat(ordersPage.getOrderStatus(1), is("Order accepted"));

        assertThat(ordersPage.getOrderAmount(2), is("2700"));
        assertThat(ordersPage.getOrderMemo(2), is("dummy7"));
        assertThat(ordersPage.getOrderStatus(2), is("Item Shipped"));
    }

    /**
     * Audit Properties Behavior
     */
    @Test
    public void testDJPA1402001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa1402001Click();

        // open registration form
        RegisterBookPage bookEntryForm = jpaHomePage.registerBookForm();

        bookEntryForm.setCategoryName("A01");
        bookEntryForm.setClobCode("someclobCode");
        bookEntryForm.setPrice("1234");
        bookEntryForm.setReleaseDate("2015/11/25");
        bookEntryForm.setTitle("Title19");

        BookDetailsPage bookDetailsPage = bookEntryForm
                .registerUsingSaveAndFlush();

        // Assertion for record earlier registered in DB.
        assertThat(bookDetailsPage.getTitle(), is("Title19"));
        assertThat(bookDetailsPage.getCategoryName(), is("A01"));
        assertThat(bookDetailsPage.getClobCode(), is("someclobCode"));
        assertThat(bookDetailsPage.getBlobCode(), is(DatatypeConverter
                .printHexBinary((bookDetailsPage.getClobCode().getBytes()))));
        assertThat(bookDetailsPage.getPrice(), is("1234"));
        assertThat(bookDetailsPage.getReleaseDate(), is("2015/11/25"));

        assertThat(bookDetailsPage.getCreatedBy(), is("UnknownUser"));
        assertThat(bookDetailsPage.getCreationTime(), is("2013/12/09"));
        assertThat(bookDetailsPage.getModifiedBy(), is("UnknownUser"));
        assertThat(bookDetailsPage.getModificationTime(), is("2013/12/09"));
    }

    /**
     * Test Common conditions added for JPQL. Confirm the behavior of the common conditions added in JPQL.
     */
    @Test
    public void testDJPA1501001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa1501001Click();
        // for this order id the is_logical_delete flag is true.
        String orderId = "1";

        ordersPage.setSearchOrderId(orderId);
        // do search using normal search entity method. The normal fetch method
        // would retrieve the details.
        OrderDetailsPage orderDetailsPage = ordersPage.displayOrderDetail();

        // confirm that the order is really present with logical_delete flag as
        // true(display value as Yes)
        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("1"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is("dummy1"));
        assertThat(orderDetailsPage.getOrderSummaryOrderDelFlag(), is("Yes"));

        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("10"));
        assertThat(orderDetailsPage.isItemRevmovable(1), is("Yes"));

        assertThat(orderDetailsPage.getItemCodeValue(2), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(2), is("5"));
        assertThat(orderDetailsPage.isItemRevmovable(2), is("Yes"));

        ordersPage = orderDetailsPage.backToOrdersPage();

        // for this order id the is_logical_delete flag is false.
        orderId = "2";
        ordersPage.setSearchOrderId(orderId);

        // do search using entity having common condition. the condition is such
        // that it retrieves the orders having logical_delete flag as false.
        // the order ID#2 is having flag as false and hence it would be
        // retrieved
        orderDetailsPage = ordersPage
                .displayOrderDetailUsingCmnConditionSpecifiedOnEntity();
        // confirm the details of order#2
        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("2"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is("dummy2"));
        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("20300"));
        assertThat(orderDetailsPage.getOrderSummaryOrderDelFlag(), is("No"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                "Stock checking"));

        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("20"));
        assertThat(orderDetailsPage.isItemRevmovable(1), is("No"));

        ordersPage = orderDetailsPage.backToOrdersPage();

        // for this order id the is_logical_delete flag is true.
        orderId = "1";

        ordersPage.setSearchOrderId(orderId);

        ordersPage.setSearchOrderId(orderId);
        // do search using entity having common condition. the condition is such
        // that it retrieves the orders having logical_delete flag as false.
        // the order ID#1 is having flag as true and hence it wont be retrieved
        // giving ResourceNotFound exception.
        ordersPage.displayOrderDetailUsingCmnConditionSpecifiedOnEntity();

        // confirm that the order id#1 is having the flag as true and hence not retrieved here
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*.ResourceNotFoundException.*");
    }

    /**
     * Adding common conditions to JPQL to fetch the related-entities. Confirm the behavior of the common conditions added in
     * JPQL to fetch the related entities
     */
    @Test
    public void testDJPA1502001() {
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        OrdersPage ordersPage = jpaIndexPage.djpa1502001Click();
        String orderIdVal = "7";
        ordersPage.setSearchOrderId(orderIdVal);
        OrderDetailsPage orderDetailsPage = ordersPage.displayOrderDetail();

        // confirm that the order is really present with logical_delete flag as
        // false (display value as NO)
        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("7"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is("dummy7"));
        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("2700"));
        assertThat(orderDetailsPage.getOrderSummaryOrderDelFlag(), is("No"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                "Item Shipped"));

        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("1"));
        assertThat(orderDetailsPage.isItemRevmovable(1), is("No"));

        assertThat(orderDetailsPage.getItemCodeValue(2), is("ITM0000002"));
        assertThat(orderDetailsPage.getItemQuantity(2), is("2"));
        assertThat(orderDetailsPage.isItemRevmovable(2), is("Yes"));

        assertThat(orderDetailsPage.getItemCodeValue(3), is("ITM0000003"));
        assertThat(orderDetailsPage.getItemQuantity(3), is("3"));
        assertThat(orderDetailsPage.isItemRevmovable(3), is("No"));

        ordersPage = orderDetailsPage.backToOrdersPage();

        ordersPage.setSearchOrderId(orderIdVal);
        orderDetailsPage = ordersPage
                .displayOrderDetailUsingCmnConditionSpecifiedOnEntity();

        // confirm that the order is really present with logical_delete flag as
        // false (display value as NO)
        assertThat(orderDetailsPage.getOrderSummaryOrderId(), is("7"));
        assertThat(orderDetailsPage.getOrderSummaryOrderMemo(), is("dummy7"));
        assertThat(orderDetailsPage.getOrderSummaryOrderAmount(), is("2700"));
        assertThat(orderDetailsPage.getOrderSummaryOrderDelFlag(), is("No"));
        assertThat(orderDetailsPage.getOrderSummaryOrderStatus(), is(
                "Item Shipped"));

        assertThat(orderDetailsPage.getItemCodeValue(1), is("ITM0000001"));
        assertThat(orderDetailsPage.getItemQuantity(1), is("1"));
        assertThat(orderDetailsPage.isItemRevmovable(1), is("No"));

        assertThat(orderDetailsPage.getItemCodeValue(2), is("ITM0000003"));
        assertThat(orderDetailsPage.getItemQuantity(2), is("3"));
        assertThat(orderDetailsPage.isItemRevmovable(2), is("No"));

    }

    /**
     * DataIntegrityViolationException. To confirm the behavior when unique key constraint is violated.
     */
    @Test
    public void testDJPA1601001() {

        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);

        JPAHomePage jpaHomePage = jpaIndexPage.djpa0102001Click();

        // open registration form
        RegisterBookPage bookEntryForm = jpaHomePage.registerBookForm();

        // Set input fields
        bookEntryForm.setBookId("3");
        bookEntryForm.setCategoryName("A01");
        bookEntryForm.setClobCode("CL01");
        bookEntryForm.setPrice("5000");
        bookEntryForm.setReleaseDate("2015/11/04");
        bookEntryForm.setTitle("title1");

        // Register the book
        bookEntryForm.registerBookForDataIntegrityViolation();

        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.dao.DataIntegrityViolationException.*");
    }

    /*
     * @Test public void testDJPA3001001(){ clearTestDataForForDeliverOrder(); }
     */

}
