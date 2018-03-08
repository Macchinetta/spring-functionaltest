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
package jp.co.ntt.fw.spring.functionaltest.selenium.pages.dam3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.SystemErrorPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

public class TodoRegisterPage implements Page<TodoRegisterPage> {

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
    @FindBy(id = "register")
    private WebElement registTodoBtn;

    @CacheLookup
    @FindBy(id = "registerRollback")
    private WebElement registRollBackBtn;

    @CacheLookup
    @FindBy(id = "registerForNull")
    private WebElement registerWithNullBtn;

    @CacheLookup
    @FindBy(id = "registerAndRetInt")
    private WebElement registerAndRetIntBtn;

    @CacheLookup
    @FindBy(id = "registerAndRetBool")
    private WebElement registerAndRetBoolBtn;

    @CacheLookup
    @FindBy(id = "autoIdGenRegister")
    private WebElement registerWithAutoIdGenlBtn;

    public TodoRegisterPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public TodoRegisterPage reload() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public void setTodoId(String todoIdVal) {
        WebElementOperations.setValue(todoId, todoIdVal);
    }

    public void setTodoCategory(String todoCatVal) {
        WebElementOperations.setValue(todoCategoryName, todoCatVal);
    }

    public void setTodoTitle(String todoTitleVal) {
        WebElementOperations.setValue(todoTitle, todoTitleVal);
    }

    public TodoDetailsPage addTodo() {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        registTodoBtn.click();
        return todoDetailsPage;
    }

    public TodoDetailsPage addTodoWithNullTitle() {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        registerWithNullBtn.click();
        return todoDetailsPage;
    }

    public SystemErrorPage registForRollback() {
        SystemErrorPage systemErrorPage = new SystemErrorPage(driver);
        registRollBackBtn.click();
        return systemErrorPage;
    }

    public TodoDetailsPage addTodoWithAndReturnBool() {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        registerAndRetBoolBtn.click();
        return todoDetailsPage;
    }

    public TodoDetailsPage addTodoAndRetInt() {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        registerAndRetIntBtn.click();
        return todoDetailsPage;
    }

    public TodoDetailsPage addTodoUsingAutoIdGeneration() {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        registerWithAutoIdGenlBtn.click();
        return todoDetailsPage;
    }

}
