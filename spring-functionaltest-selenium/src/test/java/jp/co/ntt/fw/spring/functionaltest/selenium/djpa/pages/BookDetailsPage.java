/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookDetailsPage implements Page<BookDetailsPage> {

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
    @FindBy(id = "toPageList")
    private WebElement toPageListBtn;

    @CacheLookup
    @FindBy(id = "back")
    private WebElement toList;

    @CacheLookup
    @FindBy(id = "cancel")
    private WebElement cancelBtn;

    @CacheLookup
    @FindBy(id = "update")
    private WebElement updateBtn;

    @CacheLookup
    @FindBy(id = "createdBy")
    private WebElement createdBy;

    @CacheLookup
    @FindBy(id = "createdDate")
    private WebElement createdDate;

    @CacheLookup
    @FindBy(id = "lastModifiedBy")
    private WebElement lastModifiedBy;

    @CacheLookup
    @FindBy(id = "lastModifiedDate")
    private WebElement lastModifiedDate;

    @CacheLookup
    @FindBy(id = "databaseId")
    private WebElement databaseId;

    public BookDetailsPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public BookDetailsPage reload() {

        PageFactory.initElements(driver, this);
        return this;
    }

    public String getDataBaseId() {
        return WebElementOperations.getValue(databaseId);
    }

    public String getBookIdVal() {
        return WebElementOperations.getValue(bookId);
    }

    public void clearTitleField() {
        title.clear();
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

    public String getCreatedBy() {
        return WebElementOperations.getValue(createdBy);
    }

    public String getCreationTime() {
        return WebElementOperations.getValue(createdDate);
    }

    public String getModifiedBy() {
        return WebElementOperations.getValue(lastModifiedBy);
    }

    public String getModificationTime() {
        return WebElementOperations.getValue(lastModifiedDate);
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

    public BookDetailsPage clickUpdateBtn() {
        updateBtn.click();
        BookDetailsPage bookDetailsPage = new BookDetailsPage(driver);
        return bookDetailsPage;
    }

}
