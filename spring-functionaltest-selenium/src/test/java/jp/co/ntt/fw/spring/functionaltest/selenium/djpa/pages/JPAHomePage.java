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
package jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

public class JPAHomePage implements Page<JPAHomePage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "djpaLink")
    private WebElement djpaLink;

    @CacheLookup
    @FindBy(id = "bookLink_1")
    private WebElement book1Details;

    @CacheLookup
    @FindBy(id = "bookLink_2")
    private WebElement book2Details;

    @CacheLookup
    @FindBy(id = "bookLink_3")
    private WebElement book3Details;

    @CacheLookup
    @FindBy(id = "bookLink_4")
    private WebElement book4Details;

    @CacheLookup
    @FindBy(id = "bookLink_20")
    private WebElement book20Details;

    @CacheLookup
    @FindBy(id = "bookLink_21")
    private WebElement book21Details;

    @CacheLookup
    @FindBy(id = "form")
    private WebElement addBookBtn;

    @CacheLookup
    @FindBy(id = "lazyLoadIntercept")
    private WebElement lazyFetchInterceptBtn;

    @CacheLookup
    @FindBy(id = "lazyLoadFilter")
    private WebElement lazyFetchFilterBtn;

    @CacheLookup
    @FindBy(id = "noLazy")
    private WebElement noLazyFetchSettingBtn;

    @CacheLookup
    @FindBy(id = "registerSession")
    private WebElement registerSessionBtn;

    @CacheLookup
    @FindBy(id = "registerFlashAttribute")
    private WebElement registerFlashAttributeBtn;

    @CacheLookup
    @FindBy(id = "acquiringNotForeignKey")
    private WebElement acquiringNotForeignKeyBtn;

    @CacheLookup
    @FindBy(id = "acquiringForeignKey")
    private WebElement acquiringForeignKeyBtn;

    @CacheLookup
    @FindBy(id = "searchInQueryBookIdIntercept")
    private WebElement bookIdForIntercept;

    @CacheLookup
    @FindBy(id = "searchInQueryBookIdFilter")
    private WebElement bookIdForFilter;

    @CacheLookup
    @FindBy(id = "searchInQueryBookIdNoLazy")
    private WebElement bookIdForNoLazySetting;

    @CacheLookup
    @FindBy(id = "searchInQueryBookIdAndRegisterSession")
    private WebElement bookIdAndRegisterSession;

    @CacheLookup
    @FindBy(id = "searchInQueryBookIdAndRegisterFlashAttribute")
    private WebElement bookIdAndRegisterFlashAttribute;

    @CacheLookup
    @FindBy(id = "searchInQueryBookIdAcquiringNotForeignKey")
    private WebElement bookIdAcquiringNotForeignKey;

    @CacheLookup
    @FindBy(id = "searchInQueryBookIdAcquiringForeignKey")
    private WebElement bookIdAcquiringForeignKey;

    @CacheLookup
    @FindBy(xpath = "/html/body/div[2]/div/div[3]/ul/li[5]/a")
    private WebElement secondPageBookList;

    @CacheLookup
    @FindBy(id = "deletAllInBatch")
    private WebElement deletAll;

    @CacheLookup
    @FindBy(id = "deletInBatch")
    private WebElement deleteInBatch;

    @CacheLookup
    @FindBy(id = "deleteAllById")
    private WebElement deleteAllById;

    @CacheLookup
    @FindBy(id = "deleteAllByIdInBatch")
    private WebElement deleteAllByIdInBatch;

    @CacheLookup
    @FindBy(id = "deletOne")
    private WebElement deletOne;

    @CacheLookup
    @FindBy(id = "deletIterable")
    private WebElement deleteIterableBtn;

    @CacheLookup
    @FindBy(id = "check")
    private WebElement defJPAExists;

    @CacheLookup
    @FindBy(id = "existResult")
    private WebElement existenceMsg;

    @CacheLookup
    @FindBy(id = "bookIdSrch")
    private WebElement bookIdForExistenceCheckInput;

    @CacheLookup
    @FindBy(id = "bookIdDelOpnInput")
    private WebElement bookIdDelOpnInput;

    @CacheLookup
    @FindBy(id = "multiSave")
    private WebElement saveEntityListBtn;

    @CacheLookup
    @FindBy(id = "multiSaveFlush")
    private WebElement saveAllAndFlushEntityListBtn;

    @CacheLookup
    @FindBy(id = "count")
    private WebElement bookCountBtn;

    @CacheLookup
    @FindBy(id = "bookQty")
    private WebElement bookCountRst;

    @CacheLookup
    @FindBy(id = "sort")
    private WebElement fetchSortedBooks;

    @CacheLookup
    @FindBy(id = "searchOrderBy")
    private WebElement sortCriteria;

    @CacheLookup
    @FindBy(xpath = "/html/body/div[2]/div/div[3]")
    private WebElement pageObjectListCnt;

    @CacheLookup
    @FindBy(id = "deleteEntity")
    private WebElement deleteByEntityBtn;

    @CacheLookup
    @FindBy(id = "customRepoSearch")
    private WebElement customRepoSearchBtn;

    @CacheLookup
    @FindBy(id = "noIntfInheriRepoSrch")
    private WebElement noRepoSearchBtn;

    @CacheLookup
    @FindBy(id = "bookId")
    private WebElement bookId;

    @CacheLookup
    @FindBy(id = "find")
    private WebElement findByIdBtn;

    @CacheLookup
    @FindBy(id = "bookTitle")
    private WebElement bookTitle;

    @CacheLookup
    @FindBy(id = "regDelOrder")
    private WebElement addEntityLink;

    @CacheLookup
    @FindBy(id = "sleepTime")
    private WebElement sleepTime;

    @CacheLookup
    @FindBy(id = "noPrimaryKeySearch")
    private WebElement noPrimaryKeySearchBtn;

    @CacheLookup
    @FindBy(id = "lckTmeOutQHint")
    private WebElement lckTmeOutQHintBtn;

    @CacheLookup
    @FindBy(id = "lckTmeOutQHintNoExcp")
    private WebElement lckTmeOutQHintNoExcpBtn;

    public JPAHomePage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public JPAHomePage reload() {

        PageFactory.initElements(driver, this);
        return this;
    }

    public JPAIndexPage toJPAIndexPage() {
        djpaLink.click();
        JPAIndexPage jpaIndexPage = new JPAIndexPage(driver);
        return jpaIndexPage;
    }

    public BookDetailsPage displayBookDetails(Integer bookNo) {

        switch (bookNo) {
        case 1:
            book1Details.click();
            break;
        case 2:
            book2Details.click();
            break;
        case 3:
            book3Details.click();
            break;
        case 4:
            book4Details.click();
            break;
        case 20:
            book20Details.click();
            break;
        case 21:
            book21Details.click();
            break;
        default:
            break;
        }

        BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
        return bookDetailsPage;
    }

    public RegisterBookPage registerBookForm() {
        addBookBtn.click();
        RegisterBookPage registerForm = new RegisterBookPage(driver);
        return registerForm;
    }

    public BookDetailsPage lazyFetchIntercept() {
        lazyFetchInterceptBtn.click();
        BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
        return bookDetailsPage;
    }

    public BookDetailsPage lazyFetchFilter() {
        lazyFetchFilterBtn.click();
        BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
        return bookDetailsPage;
    }

    public SystemErrorPage nolazyFetchSetting() {
        noLazyFetchSettingBtn.click();
        SystemErrorPage sysErrorPage = new SystemErrorPage(driver);
        return sysErrorPage;
    }

    public RegisterConfirmPage registerSession() {
        registerSessionBtn.click();
        RegisterConfirmPage registerConfirmPage = new RegisterConfirmPage(driver);
        return registerConfirmPage;
    }

    public SystemErrorPage registerFlashAttribute() {
        SystemErrorPage systemErrorPage = new SystemErrorPage(driver);
        registerFlashAttributeBtn.click();
        return systemErrorPage;
    }

    public BookDetailsPage acquiringNotForeignKey() {
        acquiringNotForeignKeyBtn.click();
        BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
        return bookDetailsPage;
    }

    public SystemErrorPage acquiringForeignKey() {
        acquiringForeignKeyBtn.click();
        SystemErrorPage systemErrorPage = new SystemErrorPage(driver);
        return systemErrorPage;
    }

    public JPAHomePage gotoSecondPageBookList() {
        secondPageBookList.click();
        return this;
    }

    public JPAHomePage jpaRepoDefDelAll() {
        deletAll.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public JPAHomePage jpaRepoDefDelOne() {
        deletOne.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public JPAHomePage jpaRepoDefDelIterable() {
        deleteIterableBtn.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public JPAHomePage jpaRepoDefDelInBatch() {
        deleteInBatch.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public JPAHomePage jpaRepoDefDelAllById() {
        deleteAllById.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public JPAHomePage jpaRepoDefDelAllByIdInBatch() {
        deleteAllByIdInBatch.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public JPAHomePage jpaRepoDefExists() {
        defJPAExists.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public JPAHomePage jpaRepoDefSaveEntityList() {
        saveEntityListBtn.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public JPAHomePage jpaRepoDefSaveAllAndFlushEntityList() {
        saveAllAndFlushEntityListBtn.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public JPAHomePage jpaRepoDefCount() {
        bookCountBtn.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public JPAHomePage jpaRepoDefSort() {
        fetchSortedBooks.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public JPAHomePage jpaRepoDefDeleteEntity() {
        deleteByEntityBtn.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public RegisterBookPage searchUsingCustomRepository() {
        customRepoSearchBtn.click();
        RegisterBookPage registerForm = new RegisterBookPage(driver);
        return registerForm;
    }

    public RegisterBookPage searchUsingNoIntfInheritedRepository() {
        noRepoSearchBtn.click();
        RegisterBookPage bookDetailsPage = new RegisterBookPage(driver);
        return bookDetailsPage;
    }

    public BookDetailsPage searchUsingNoPrimaryColumnValue() {
        noPrimaryKeySearchBtn.click();
        BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
        return bookDetailsPage;
    }

    public BookDetailsPage selectBookForUpdateByPessimisticLock() {
        lckTmeOutQHintBtn.click();
        BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
        return bookDetailsPage;
    }

    public BookDetailsPage selectBookForUpdateByPessimisticLockNoExcp() {
        lckTmeOutQHintNoExcpBtn.click();
        BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
        return bookDetailsPage;
    }

    public BookDetailsPage findOneById() {
        findByIdBtn.click();
        BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
        return bookDetailsPage;
    }

    public DeliveryOrderDetailsPage addEntityBySave() {
        addEntityLink.click();
        DeliveryOrderDetailsPage orderDetailsPage = new DeliveryOrderDetailsPage(driver);
        return orderDetailsPage;
    }

    public JPAHomePage setSortCriteria(String sortCriteriaVal) {
        WebElementOperations.setSort(sortCriteria, sortCriteriaVal);
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public void setBookTitle(String val) {
        WebElementOperations.setValue(bookTitle, val);
    }

    public void setInteceptBookId(String val) {
        WebElementOperations.setValue(bookIdForIntercept, val);
    }

    public void setSleepTime(String val) {
        WebElementOperations.setValue(sleepTime, val);
    }

    public void setBookIdForDefJPASearchOpn(String val) {
        WebElementOperations.setValue(bookIdForExistenceCheckInput, val);
    }

    public void setBookIdForDefJPADeleteOpn(String val) {
        WebElementOperations.setValue(bookIdDelOpnInput, val);
    }

    public void setFilterBookId(String val) {
        WebElementOperations.setValue(bookIdForFilter, val);
    }

    public void setBookId(String val) {
        WebElementOperations.setValue(bookId, val);
    }

    public void setNoLazyBookId(String val) {
        WebElementOperations.setValue(bookIdForNoLazySetting, val);
    }

    public void setBookIdAndRegisterSession(String val) {
        WebElementOperations.setValue(bookIdAndRegisterSession, val);
    }

    public void setBookIdAndRegisterFlashAttribute(String val) {
        WebElementOperations.setValue(bookIdAndRegisterFlashAttribute, val);
    }

    public void setBookIdAcquiringNotForeignKey(String val) {
        WebElementOperations.setValue(bookIdAcquiringNotForeignKey, val);
    }

    public void setBookIdAcquiringForeignKey(String val) {
        WebElementOperations.setValue(bookIdAcquiringForeignKey, val);
    }

    public boolean isBookDetailLinkPresent(Integer bookNo) {
        boolean visible = false;
        WebElement bookUpdateLink = null;

        switch (bookNo) {
        case 1:
            bookUpdateLink = book1Details;
            break;
        case 2:
            bookUpdateLink = book2Details;
            break;
        case 3:
            bookUpdateLink = book3Details;
            break;
        case 4:
            bookUpdateLink = book4Details;
            break;
        case 20:
            bookUpdateLink = book20Details;
            break;
        case 21:
            bookUpdateLink = book21Details;
            break;
        default:
            break;
        }

        try {
            bookUpdateLink.isDisplayed();
            visible = true;
        } catch (NoSuchElementException nse) {
            visible = false;
        }
        return visible;
    }

    public String getBookAvailabityMessage() {
        return WebElementOperations.getElementTextValue(existenceMsg);
    }

    public String getPageObjectItemCount() {
        return WebElementOperations.getElementTextValue(pageObjectListCnt);
    }

    public String getBookCount() {
        return WebElementOperations.getElementTextValue(bookCountRst);
    }

}
