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
package jp.co.ntt.fw.spring.functionaltest.selenium.pages.dam3;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.SystemErrorPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

public class TodoListPage implements Page<TodoListPage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "form")
    private WebElement todoRegisterBtn;

    @CacheLookup
    @FindBy(id = "todoLink_0000000001")
    private WebElement todo1Link;

    @CacheLookup
    @FindBy(id = "todoLink_0000000002")
    private WebElement todo2Link;

    @CacheLookup
    @FindBy(id = "todoLink_0000000003")
    private WebElement todo3Link;

    @CacheLookup
    @FindBy(id = "todoLink_0000000005")
    private WebElement todo5Link;

    @CacheLookup
    @FindBy(id = "todoLink_0000000010")
    private WebElement todo10Link;

    @CacheLookup
    @FindBy(id = "todoLink_0000000011")
    private WebElement todo11Link;

    @CacheLookup
    @FindBy(id = "todoLink_0000000025")
    private WebElement todo25Link;

    @CacheLookup
    @FindBy(id = "completedTodo")
    private WebElement completedTodoEle;

    @CacheLookup
    @FindBy(id = "incompleteTodo")
    private WebElement incompleteTodoEle;

    @CacheLookup
    @FindBy(id = "todoLink_0000000031")
    private WebElement todo31Link;

    @CacheLookup
    @FindBy(id = "todoLink_0000001000")
    private WebElement todo1000Link;

    @CacheLookup
    @FindBy(id = "totalTodo")
    private WebElement totalTodoEle;

    @CacheLookup
    @FindBy(id = "addBulk")
    private WebElement bulkTodoAddBtn;

    @CacheLookup
    @FindBy(id = "addReUse")
    private WebElement addReuseBtn;

    @CacheLookup
    @FindBy(id = "todoId")
    private WebElement todoIdInput;

    @CacheLookup
    @FindBy(id = "todoCategory")
    private WebElement todoCatNameInput;

    @CacheLookup
    @FindBy(id = "todoTitle")
    private WebElement todoTitleInput;

    @CacheLookup
    @FindBy(id = "createdAt")
    private WebElement createdAtInput;

    @CacheLookup
    @FindBy(id = "autoMapSrch")
    private WebElement autoMapSrchBtn;

    @CacheLookup
    @FindBy(id = "asClauseSrch")
    private WebElement asClSrchBtn;

    @CacheLookup
    @FindBy(id = "pkSrch")
    private WebElement srchByPKBtn;

    @CacheLookup
    @FindBy(id = "pkSrchNG")
    private WebElement pkSrchNGBtn;

    @CacheLookup
    @FindBy(id = "cmpKeySrch")
    private WebElement cmpKeySrchBtn;

    @CacheLookup
    @FindBy(id = "cmpKeySrchNoParamAnnt")
    private WebElement cmpKeySrchNoParamBtn;

    @FindBy(id = "criteriaSrch")
    private WebElement criteriaSrchBtn;

    @FindBy(id = "criteriaSrchMapRes")
    private WebElement criteriaSrchMapResBtn;

    @FindBy(id = "countByStatus")
    private WebElement countByStatusBtn;

    @FindBy(id = "complete")
    private WebElement completeStatRad;

    @FindBy(id = "incomplete")
    private WebElement inCompleteStatRad;

    @FindBy(id = "statCount")
    private WebElement todoStatCount;

    @FindBy(id = "stdPageSearch")
    private WebElement stdPageSearchBtn;

    @FindBy(id = "sqlRefPageSearch")
    private WebElement sqlRefPageSearchBtn;

    @CacheLookup
    @FindBy(xpath = "/html/body/div[2]/div/div[4]/ul/li[14]/a")
    private WebElement lastPageBtn;

    @FindBy(id = "batchReg")
    private WebElement batchRegBtn;

    @FindBy(id = "regCnt")
    private WebElement batcgRegCnt;

    @CacheLookup
    @FindBy(id = "todoIds")
    private WebElement todoIdsInput;

    @FindBy(id = "bulkUpdate")
    private WebElement batchUpdateBtn;

    @FindBy(id = "bulkDelete")
    private WebElement batchDeleteBtn;

    @CacheLookup
    @FindBy(id = "deleteDate")
    private WebElement cutOffDate;

    @CacheLookup
    @FindBy(id = "ifSrch")
    private WebElement ifEleUsageBtn;

    @CacheLookup
    @FindBy(id = "bindSrch")
    private WebElement bindEleUsageBtn;

    @CacheLookup
    @FindBy(id = "escpSrch")
    private WebElement escapeSearchBtn;

    @CacheLookup
    @FindBy(id = "downloadTodo")
    private WebElement downloadTodoBtn;

    @CacheLookup
    @FindBy(id = "csvPath")
    private WebElement resHandlerCSVPath;

    @CacheLookup
    @FindBy(id = "spSrch")
    private WebElement spSrchBtn;

    @CacheLookup
    @FindBy(id = "clTypeAliasSrch")
    private WebElement clTypeAliasSrchBtn;

    @CacheLookup
    @FindBy(id = "typeAlOverWrDefName")
    private WebElement typeAlOverWrDefNameSrchBtn;

    @CacheLookup
    @FindBy(id = "batchRepoUpdt")
    private WebElement batchRepoUpdtBtn;

    @CacheLookup
    @FindBy(id = "chooseSrch")
    private WebElement chooseEleSrchBtn;

    public TodoListPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public TodoListPage reload() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public String getCompletedTodoCount() {
        return WebElementOperations.getElementTextValue(completedTodoEle);
    }

    public String getIncompletTodoCount() {
        return WebElementOperations.getElementTextValue(incompleteTodoEle);
    }

    public String getTotalTodoCount() {
        return WebElementOperations.getElementTextValue(totalTodoEle);
    }

    public TodoDetailsPage displayTodoDetail(String todoEle) {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        switch (todoEle) {
        case "0000000001":
            todo1Link.click();
            break;

        case "0000000002":
            todo2Link.click();
            break;

        case "0000000010":
            todo10Link.click();
            break;

        case "0000000025":
            todo25Link.click();
            break;

        case "0000001000":
            todo1000Link.click();
            break;
        }

        return todoDetailsPage;
    }

    public TodoUpdatePage updateTodo(String todoEle) {
        TodoUpdatePage todoUpdatePage = new TodoUpdatePage(driver);
        switch (todoEle) {
        case "0000000001":
            todo1Link.click();
            break;

        case "0000000002":
            todo2Link.click();
            break;

        case "0000000003":
            todo3Link.click();
            break;

        case "0000000025":
            todo25Link.click();
            break;
        }

        return todoUpdatePage;
    }

    public boolean isTodoDisplayed(String todoId) {
        boolean todoExist = false;
        WebElement todoLink = null;
        switch (todoId) {
        case "0000000001":
            todoLink = todo1Link;
            break;
        case "0000000002":
            todoLink = todo2Link;
            break;
        case "0000000003":
            todoLink = todo3Link;
            break;
        case "0000000005":
            todoLink = todo5Link;
            break;
        case "0000000010":
            todoLink = todo10Link;
            break;
        case "0000000011":
            todoLink = todo11Link;
            break;
        case "0000000031":
            todoLink = todo31Link;
            break;
        case "0000000025":
            todoLink = todo25Link;
            break;
        case "0000001000":
            todoLink = todo1000Link;
            break;
        }

        try {
            todoLink.isDisplayed();
            todoExist = true;
        } catch (NoSuchElementException nse) {
            todoExist = false;
        }

        return todoExist;
    }

    public void setTodoForSearch(String todoId) {
        WebElementOperations.setValue(todoIdInput, todoId);
    }

    public TodoRegisterPage registerTodo() {
        TodoRegisterPage registerTodoPage = new TodoRegisterPage(driver);
        todoRegisterBtn.click();
        return registerTodoPage;
    }

    public TodoListPage registerBulkTodo() {
        TodoListPage todoListPage = new TodoListPage(driver);
        bulkTodoAddBtn.click();
        return todoListPage;
    }

    public TodoListPage registerBulkTodoByReUseMode() {
        TodoListPage todoListPage = new TodoListPage(driver);
        addReuseBtn.click();
        return todoListPage;
    }

    public TodoDetailsPage autoMappingResultSearch() {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        autoMapSrchBtn.click();
        return todoDetailsPage;
    }

    public TodoDetailsPage asClauseRstSearch() {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        asClSrchBtn.click();
        return todoDetailsPage;
    }

    public TodoDetailsPage searchByPK() {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        srchByPKBtn.click();
        return todoDetailsPage;
    }

    public SystemErrorPage searchByPKNA() {
        SystemErrorPage systemErrorPage = new SystemErrorPage(driver);
        pkSrchNGBtn.click();
        return systemErrorPage;
    }

    public TodoDetailsPage searchByCmpKey() {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        cmpKeySrchBtn.click();
        return todoDetailsPage;
    }

    public void setTodoCatForSearch(String catName) {
        WebElementOperations.setValue(todoCatNameInput, catName);
    }

    public TodoDetailsPage searchByCmpKeyNoParamAnnot() {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        cmpKeySrchNoParamBtn.click();
        return todoDetailsPage;
    }

    public void setTodoTitleContent(String titleContent) {
        WebElementOperations.setValue(todoTitleInput, titleContent);
    }

    public void setTodoCreationDate(String todoCreationDate) {
        WebElementOperations.setValue(createdAtInput, todoCreationDate);
    }

    public TodoListPage searchByCriteriaBean() {
        TodoListPage todoListPage = new TodoListPage(driver);
        criteriaSrchBtn.click();
        return todoListPage;
    }

    public TodoListPage searchByCriteriaBeanRetMap() {
        TodoListPage todoListPage = new TodoListPage(driver);
        criteriaSrchMapResBtn.click();
        return todoListPage;
    }

    public TodoListPage clickCountByStatusBtn() {
        TodoListPage todoListPage = new TodoListPage(driver);
        countByStatusBtn.click();
        return todoListPage;
    }

    public void selectCompleteStatRadio() {
        completeStatRad.click();
    }

    public void selectInCompleteStatRadio() {
        inCompleteStatRad.click();
    }

    public String getTodoCountwithStatus() {
        return WebElementOperations.getElementTextValue(todoStatCount);
    }

    public TodoListPage fetchUsingStdPaging() {
        TodoListPage todoListPage = new TodoListPage(driver);
        stdPageSearchBtn.click();
        return todoListPage;
    }

    public TodoListPage displayLastPage() {

        TodoListPage todoListPage = new TodoListPage(driver);
        lastPageBtn.click();
        return todoListPage;
    }

    public TodoListPage fetchUsingSQLRefinePaging() {
        TodoListPage todoListPage = new TodoListPage(driver);
        sqlRefPageSearchBtn.click();
        return todoListPage;
    }

    public TodoListPage batchRegister() {
        TodoListPage todoListPage = new TodoListPage(driver);
        batchRegBtn.click();
        return todoListPage;
    }

    public String getCountOfBatchRegisteredTodos() {
        return WebElementOperations.getElementTextValue(batcgRegCnt);
    }

    public void setTodoIDList(String todoIds) {
        WebElementOperations.setValue(todoIdsInput, todoIds);
    }

    public TodoListPage batchUpdate() {
        TodoListPage todoListPage = new TodoListPage(driver);
        batchUpdateBtn.click();
        return todoListPage;
    }

    public TodoListPage batchDelete() {
        TodoListPage todoListPage = new TodoListPage(driver);
        batchDeleteBtn.click();
        return todoListPage;
    }

    public void setCutOffDate(String date) {
        WebElementOperations.setValue(cutOffDate, date);
    }

    public TodoListPage ifElementUsageSearch() {
        TodoListPage todoListPage = new TodoListPage(driver);
        ifEleUsageBtn.click();
        return todoListPage;
    }

    public TodoListPage bindElementUsageSearch() {
        TodoListPage todoListPage = new TodoListPage(driver);
        bindEleUsageBtn.click();
        return todoListPage;
    }

    public TodoListPage chooseElementUsageSearch() {
        TodoListPage todoListPage = new TodoListPage(driver);
        chooseEleSrchBtn.click();
        return todoListPage;
    }

    public TodoListPage escapeSearch() {
        TodoListPage todoListPage = new TodoListPage(driver);
        escapeSearchBtn.click();
        return todoListPage;
    }

    public TodoListPage downloadCSVUsingResHndlr() {
        TodoListPage todoListPage = new TodoListPage(driver);
        downloadTodoBtn.click();
        return todoListPage;
    }

    public String getResultHandlerCSVPath() {
        return WebElementOperations.getElementTextValue(resHandlerCSVPath);
    }

    public TodoDetailsPage searchUsingStoredProc() {
        TodoDetailsPage todoDetailsPage = new TodoDetailsPage(driver);
        spSrchBtn.click();
        return todoDetailsPage;
    }

    public TodoListPage searchUsingClassTypeAlias() {
        TodoListPage todoListPage = new TodoListPage(driver);
        clTypeAliasSrchBtn.click();
        return todoListPage;
    }

    public TodoListPage searchUsingOverwrittenTypeAliasName() {
        TodoListPage todoListPage = new TodoListPage(driver);
        typeAlOverWrDefNameSrchBtn.click();
        return todoListPage;
    }

    public TodoListPage updateUsingBatchRepo() {
        TodoListPage todoListPage = new TodoListPage(driver);
        batchRepoUpdtBtn.click();
        return todoListPage;
    }

}
