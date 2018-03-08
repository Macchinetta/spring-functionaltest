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

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TodoDetailsPage implements Page<TodoDetailsPage> {

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
    @FindBy(id = "cancel")
    private WebElement cancelBtn;

    @CacheLookup
    @FindBy(id = "returnRes")
    private WebElement returnResEle;

    @CacheLookup
    @FindBy(id = "completeAt")
    private WebElement completeAtEle;

    public TodoDetailsPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public TodoDetailsPage reload() {
        PageFactory.initElements(driver, this);
        return this;
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

    public String getRegistrationResult() {
        String res = "";

        res = WebElementOperations.getElementTextValue(returnResEle);

        return res;
    }

    public TodoListPage showTodoListPage() {
        cancelBtn.click();
        TodoListPage todoListPage = new TodoListPage(driver);
        return todoListPage;
    }

    public String getTodoCompleteDate() {

        return WebElementOperations.getValue(completeAtEle);
    }

}
