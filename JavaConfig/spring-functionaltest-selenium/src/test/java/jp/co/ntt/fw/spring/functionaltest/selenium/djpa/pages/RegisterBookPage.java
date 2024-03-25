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
package jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

public class RegisterBookPage implements Page<RegisterBookPage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "bookId")
    private WebElement bookId;

    @CacheLookup
    @FindBy(id = "categoryName")
    private WebElement categoryName;

    @CacheLookup
    @FindBy(id = "title")
    private WebElement title;

    @CacheLookup
    @FindBy(id = "blobCode")
    private WebElement blobCode;

    @CacheLookup
    @FindBy(id = "clobCode")
    private WebElement clobCode;

    @CacheLookup
    @FindBy(id = "price")
    private WebElement price;

    @CacheLookup
    @FindBy(id = "releaseDate")
    private WebElement releaseDate;

    @CacheLookup
    @FindBy(id = "register")
    private WebElement register;

    @CacheLookup
    @FindBy(id = "registerDIV")
    private WebElement registerDataInViolation;

    @CacheLookup
    @FindBy(id = "errorReg")
    private WebElement registerRollback;

    @CacheLookup
    @FindBy(id = "crudReg")
    private WebElement crudRepoDefSave;

    @CacheLookup
    @FindBy(id = "saveAndFlush")
    private WebElement saveAndFlushBtn;

    @CacheLookup
    @FindBy(id = "customRepoSave")
    private WebElement customRepoSaveBtn;

    @CacheLookup
    @FindBy(id = "noIntfInheriRepoSave")
    private WebElement noIntfInheriRepoSaveBtn;

    @CacheLookup
    @FindBy(id = "flush")
    private WebElement flushBtn;

    /**
     * Following properties or elements are from register complete page : Start
     */

    @CacheLookup
    @FindBy(id = "toPageList")
    private WebElement toPageListBtn;

    @CacheLookup
    @FindBy(id = "back")
    private WebElement toList;

    @CacheLookup
    @FindBy(id = "cancel")
    private WebElement cancelBtn;

    /**
     * Following properties or elements are from register complete page : End
     */

    public RegisterBookPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public RegisterBookPage reload() {

        PageFactory.initElements(driver, this);
        return this;
    }

    public String getBookIdVal() {
        return WebElementOperations.getValue(bookId);
    }

    public String getTitle() {
        return WebElementOperations.getValue(title);
    }

    public String getCategoryName() {
        return WebElementOperations.getValue(categoryName);
    }

    public String getBlobCode() {
        return WebElementOperations.getValue(blobCode);
    }

    public String getClobCode() {
        return WebElementOperations.getValue(clobCode);
    }

    public String getPrice() {
        return WebElementOperations.getValue(price);
    }

    public String getReleaseDate() {
        return WebElementOperations.getValue(releaseDate);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getBookId() {
        return bookId;
    }

    public void setBookId(String val) {
        WebElementOperations.setValue(bookId, val);
    }

    public RegisterBookPage registerBook() {
        register.click();
        RegisterBookPage bookDetailsPage = new RegisterBookPage(driver);
        return bookDetailsPage;
    }

    public RegisterBookPage registerBookForDataIntegrityViolation() {
        registerDataInViolation.click();
        RegisterBookPage bookDetailsPage = new RegisterBookPage(driver);
        return bookDetailsPage;
    }

    public BookDetailsPage registerUsingSaveAndFlush() {
        saveAndFlushBtn.click();
        BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
        return bookDetailsPage;
    }

    public RegisterBookPage saveUsingCustomRepo() {
        customRepoSaveBtn.click();
        RegisterBookPage bookDetailsPage = new RegisterBookPage(driver);
        return bookDetailsPage;
    }

    public RegisterBookPage saveUsingNoInheritedIntfRepo() {
        customRepoSaveBtn.click();
        RegisterBookPage bookDetailsPage = new RegisterBookPage(driver);
        return bookDetailsPage;
    }

    public SystemErrorPage registerBookForRollback() {
        registerRollback.click();
        SystemErrorPage sysErrPage = new SystemErrorPage(driver);
        return sysErrPage;
    }

    public BookDetailsPage registerUsingFlush() {
        flushBtn.click();
        BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
        return bookDetailsPage;
    }

    public RegisterBookPage registerBookUsingCrudReository() {
        crudRepoDefSave.click();
        RegisterBookPage bookDetailsPage = new RegisterBookPage(driver);
        return bookDetailsPage;
    }

    public JPAHomePage goBackToBookListPage() {
        cancelBtn.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public JPAHomePage goBackToPaginatedList() {
        toPageListBtn.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public JPAHomePage goBackToBookListPageFromComplete() {
        toList.click();
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        return jpaHomePage;
    }

    public void setRegister(String val) {
        WebElementOperations.setValue(register, val);
    }

    public void setCategoryName(String val) {
        WebElementOperations.setValue(categoryName, val);
    }

    public void setTitle(String val) {
        WebElementOperations.setValue(title, val);
    }

    public void setBlobCode(String val) {
        WebElementOperations.setValue(blobCode, val);
    }

    public void setClobCode(String val) {
        WebElementOperations.setValue(clobCode, val);
    }

    public void setPrice(String val) {
        WebElementOperations.setValue(price, val);
    }

    public void setReleaseDate(String val) {
        WebElementOperations.setValue(releaseDate, val);
    }

}
