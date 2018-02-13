/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.pages.dam3;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TodoUpdatePage implements Page<TodoUpdatePage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "todoId")
    private WebElement todoId;

    @CacheLookup
    @FindBy(id = "todoCategory")
    private WebElement todoCategoryName;

    @CacheLookup
    @FindBy(id = "todoTitle")
    private WebElement todoTitle;

    @CacheLookup
    @FindBy(id = "finished")
    private WebElement todoStatus;

    @CacheLookup
    @FindBy(id = "createdAt")
    private WebElement creationDate;

    @CacheLookup
    @FindBy(id = "version")
    private WebElement version;

    @CacheLookup
    @FindBy(id = "update")
    private WebElement updateBtn;

    @CacheLookup
    @FindBy(id = "delete")
    private WebElement deleteBtn;

    @CacheLookup
    @FindBy(id = "cancel")
    private WebElement cancelBtn;

    @CacheLookup
    @FindBy(id = "updateOptimistic")
    private WebElement updateOptimisticBtn;

    @CacheLookup
    @FindBy(id = "deleteByTodo")
    private WebElement deleteTDByObjBtn;

    @CacheLookup
    @FindBy(id = "description1")
    private WebElement blobDesc;

    @CacheLookup
    @FindBy(id = "description2")
    private WebElement clobDesc;

    public TodoUpdatePage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public TodoUpdatePage reload() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public void setTodoCategory(String todoCatVal) {
        WebElementOperations.setValue(todoCategoryName, todoCatVal);
    }

    public void setTodoTitle(String todoTitleVal) {
        WebElementOperations.setValue(todoTitle, todoTitleVal);
    }

    public void setTodoStatus(String todoTitleVal) {
        todoStatus.clear();
        WebElementOperations.setValue(todoStatus, todoTitleVal);
    }

    public TodoDetailsPage updateTodo() {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        updateBtn.click();
        return todoDetailsPage;
    }

    public TodoListPage deleteTodo() {
        TodoListPage todoListPage = new TodoListPage(driver);
        deleteBtn.click();
        return todoListPage;
    }

    public TodoListPage backToListPage() {
        TodoListPage todoListPage = new TodoListPage(driver);
        cancelBtn.click();
        return todoListPage;
    }

    public String getTodoTitle() {

        return WebElementOperations.getValue(todoTitle);
    }

    public String getTodoID() {

        return WebElementOperations.getValue(todoId);
    }

    public String getTodoStatus() {

        return WebElementOperations.getValue(todoStatus);
    }

    public String getTodoCreatedDate() {

        return WebElementOperations.getValue(creationDate);
    }

    public String getTodoCategory() {

        return WebElementOperations.getValue(todoCategoryName);
    }

    public String getTodoVersion() {

        return WebElementOperations.getValue(version);
    }

    public TodoDetailsPage updateTodoOpt() {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        updateOptimisticBtn.click();
        return todoDetailsPage;
    }

    public TodoListPage deleteByUsingTodoObj() {
        TodoListPage todoListPage = new TodoListPage(driver);
        deleteTDByObjBtn.click();
        return todoListPage;
    }

    public String getBlobDesc() {
        return WebElementOperations.getValue(blobDesc);
    }

    public String getClobbDesc() {
        return WebElementOperations.getValue(clobDesc);
    }
}
