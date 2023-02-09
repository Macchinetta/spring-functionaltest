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
package jp.co.ntt.fw.spring.functionaltest.selenium.dam3;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.id;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;
import jp.co.ntt.fw.spring.functionaltest.selenium.SystemErrorPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.pages.dam3.DAM3IndexPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.pages.dam3.OrderMB3ListPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.pages.dam3.OrderMB3PageListPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.pages.dam3.TodoDetailsPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.pages.dam3.TodoListPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.pages.dam3.TodoRegisterPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.pages.dam3.TodoUpdatePage;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class DataAccessMyBatis3Test extends FunctionTestSupport {

    private static WebDriver driver;

    public DataAccessMyBatis3Test() {
        disableDefaultWebDriver();
    }

    @Before
    public void setUp() {

        {
            if (driver == null) {
                driver = webDriverCreator.createLocaleSpecifiedDriver("ja");
            }
            setCurrentWebDriver(driver);
        }
        // トップ画面での操作
        {
            webDriverOperations.setDefaultTimeoutForImplicitlyWait(60);
            webDriverOperations.getWebDriver().manage().window().maximize();
        }
    }

    private void clearTestDataForBook() {
        restOperations.delete(getPackageRootUrl() + "/testdata/todos");
    }

    private void clearAndCreateTestDataForBook() {
        restOperations.postForObject(getPackageRootUrl() + "/testdata/todos",
                null, Void.class);
    }

    /**
     * <ul>
     * <li>DAM30101001 : Test the DataSource settings using the configuration done for MyBatis</li>
     * </ul>
     */
    @Test
    public void testDAM30101001() {

        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        // Connect to DB and fetch the Todo list
        TodoListPage todoListPage = dam3IndexPage.dam30101001Click();

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        // fetch the details of one of the records.
        TodoDetailsPage todoDetailsPage = todoListPage.displayTodoDetail(
                "0000000001");

        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 1"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000001"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("false"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));

        todoListPage = todoDetailsPage.showTodoListPage();

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
    }

    /**
     * <ul>
     * <li>DAM30102001 : Test the Transaction manager setting - the commit.</li>
     * </ul>
     */
    @Test
    public void testDAM30102001() {

        // データ初期化
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        // Connect to DB and fetch the Todo list
        TodoListPage todoListPage = dam3IndexPage.dam30102001Click();

        // Open todo registration page
        TodoRegisterPage registerTodoPage = todoListPage.registerTodo();

        // input todo details
        registerTodoPage.setTodoId("0000000030");
        registerTodoPage.setTodoCategory("CA2");
        registerTodoPage.setTodoTitle("Todo 30");

        TodoDetailsPage todoDetailsPage = registerTodoPage.addTodo();

        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 30"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000030"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA2"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("In-Complete"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/28"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("0"));

    }

    /**
     * <ul>
     * <li>DAM30102002 : Test the Transaction manager setting - the rollback.</li>
     * </ul>
     */
    @Test
    public void testDAM30102002() {

        // データ初期化
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        // Connect to DB and fetch the Todo list
        TodoListPage todoListPage = dam3IndexPage.dam30102002Click();

        // Open todo registration page
        TodoRegisterPage registerTodoPage = todoListPage.registerTodo();

        // input todo details
        registerTodoPage.setTodoId("0000000031");
        registerTodoPage.setTodoCategory("CA2");
        registerTodoPage.setTodoTitle("Todo 31");

        // Register the todo
        SystemErrorPage sysErrorPage = registerTodoPage.registForRollback();

        // Assertion for record earlier registered in DB.
        assertThat(sysErrorPage.getErrorMessage(), is("Intentional RollBack"));

        webDriverOperations.displayPage(getPackageRootUrl());

        dam3IndexPage = new DAM3IndexPage(driver);

        todoListPage = dam3IndexPage.dam30102002Click();

        boolean insertTodoDetailLink = todoListPage.isTodoDisplayed(
                "0000000031");

        // Due to RollBack during registration Process, No link will be
        // available for the tdo.
        assertThat(insertTodoDetailLink, is(false));
    }

    /**
     * <ul>
     * <li>DAM30103001 : Confirm the settings done to use MyBatis with spring.</li>
     * </ul>
     */
    @Test
    public void testDAM30103001() {
        // settings as done for testDAM30102001 are identical
        testDAM30102001();
    }

    /**
     * <ul>
     * <li>DAM30201001 : Confirmation for fetch size setting</li>
     * </ul>
     */
    @Test
    public void testDAM30201001() {

        // データ初期化
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);
        // Connect to DB and fetch the Todo list
        TodoListPage todoListPage = dam3IndexPage.dam30201001Click();

        todoListPage = todoListPage.registerBulkTodo();

        // データ反映までの待ち時間
        webDriverOperations.waitForDisplayed(ExpectedConditions
                .textToBePresentInElementLocated(By.id("completedTodo"),
                        "993"));

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("993"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("1000"));

    }

    /**
     * <ul>
     * <li>DAM30202001 : Confirmation for SQL Mode execution settings : REUSE Mode</li>
     * </ul>
     */
    @Test
    public void testDAM30202001() {
        // データ初期化
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);
        // Connect to DB and fetch the Todo list
        TodoListPage todoListPage = dam3IndexPage.dam30202001Click();

        todoListPage = todoListPage.registerBulkTodoByReUseMode();

        // データ反映までの待ち時間
        webDriverOperations.waitForDisplayed(ExpectedConditions
                .textToBePresentInElementLocated(By.id("completedTodo"),
                        "993"));

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("993"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("1000"));
    }

    /**
     * <ul>
     * <li>DAM30203001 : Confirmation for Type Alias Setting.</li>
     * </ul>
     */
    @Test
    public void testDAM30203001() {
        testDAM30102001();
    }

    /**
     * <ul>
     * <li>DAM30204001 : Confirmation for Mapping settings of NULL value and JDBC type.</li>
     * </ul>
     */
    @Test
    public void testDAM30204001() {
        {
            clearAndCreateTestDataForBook();
        }

        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);
        // Connect to DB and fetch the Todo list
        TodoListPage todoListPage = dam3IndexPage.dam30204001Click();

        TodoRegisterPage registerTodoPage = todoListPage.registerTodo();

        registerTodoPage.setTodoId("0000000030");
        registerTodoPage.setTodoCategory("CA2");

        TodoDetailsPage todoDetailsPage = registerTodoPage
                .addTodoWithNullTitle();
        // confirm that the ToDo registered with null title is saved in DB
        assertThat(todoDetailsPage.getTodoTitle(), equalTo(""));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000030"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA2"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("In-Complete"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/28"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("0"));
    }

    /**
     * <ul>
     * <li>DAM30205001 : Confirmation of TypeHandler settings.</li>
     * </ul>
     */
    @Test
    public void testDAM30205001() {
        /**
         * The type handler settings is done for JODA DateTime to TimeStamp conversion and vice versa
         */

        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        // Connect to DB and fetch the Todo list
        TodoListPage todoListPage = dam3IndexPage.dam30205001Click();

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        // fetch the details of one of the records.
        TodoDetailsPage todoDetailsPage = todoListPage.displayTodoDetail(
                "0000000001");

        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 1"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000001"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("false"));

        // confirmed that the date is saved and retrieved by using type handler settings without any error.
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));

    }

    /**
     * <ul>
     * <li>DAM30301001 : Confirmation of Data Access process using MyBatis.</li>
     * </ul>
     */
    @Test
    public void testDAM30301001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30301001Click();

        // Register a ToDo : Insert action
        TodoRegisterPage registerTodoPage = todoListPage.registerTodo();

        registerTodoPage.setTodoCategory("CA2");
        registerTodoPage.setTodoId("0000000025");
        registerTodoPage.setTodoTitle("Todo Insert Test");

        // Call the insert operation
        TodoDetailsPage todoDetailsPage = registerTodoPage.addTodo();

        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo Insert Test"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000025"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA2"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("In-Complete"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/28"));
        // initial version is 0
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("0"));

        // Now update the title of the above todo
        webDriverOperations.displayPage(getPackageRootUrl());
        dam3IndexPage = new DAM3IndexPage(driver);
        todoListPage = dam3IndexPage.dam30301001Click();

        // Select Action
        TodoUpdatePage todoUpdatePage = todoListPage.updateTodo("0000000025");

        assertThat(todoUpdatePage.getTodoTitle(), equalTo("Todo Insert Test"));
        assertThat(todoUpdatePage.getTodoID(), equalTo("0000000025"));
        assertThat(todoUpdatePage.getTodoCategory(), equalTo("CA2"));
        assertThat(todoUpdatePage.getTodoStatus(), equalTo("false"));
        assertThat(todoUpdatePage.getTodoCreatedDate(), equalTo("2016/12/28"));

        todoUpdatePage.setTodoTitle(":Update");
        // Update action
        todoDetailsPage = todoUpdatePage.updateTodo();
        assertThat(todoDetailsPage.getTodoTitle(), equalTo(
                "Todo Insert Test:Update"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000025"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA2"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("false"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/28"));
        // initial version is 0
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));

        // Delete Action
        webDriverOperations.displayPage(getPackageRootUrl());
        dam3IndexPage = new DAM3IndexPage(driver);
        todoListPage = dam3IndexPage.dam30301001Click();

        todoUpdatePage = todoListPage.updateTodo("0000000025");

        // delete action
        todoListPage = todoUpdatePage.deleteTodo();

        boolean isTodoPresent = todoListPage.isTodoDisplayed("0000000025");

        assertThat(isTodoPresent, is(false));

    }

    /**
     * <ul>
     * <li>DAM30401001 : Confirmation of Automatic mapping for search results.</li>
     * </ul>
     */
    @Test
    public void testDAM30401001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30401001Click();

        todoListPage.setTodoForSearch("0000000005");

        TodoDetailsPage todoDetailsPage = todoListPage
                .autoMappingResultSearch();

        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 5"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000005"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA5"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("In-Complete"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/26"));
        // initial version is 0
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));

    }

    /**
     * <ul>
     * <li>DAM30401002 : Confirmation of Automatic mapping for search results. Use of 'AS' Clause</li>
     * </ul>
     */
    @Test
    public void testDAM30401002() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30401002Click();

        todoListPage.setTodoForSearch("0000000006");

        TodoDetailsPage todoDetailsPage = todoListPage.asClauseRstSearch();

        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 6"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000006"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("Complete"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/26"));
        // initial version is 0
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));
    }

    /**
     * <ul>
     * <li>DAM30402001 : Confirmation of manual mapping for search results.</li>
     * </ul>
     */
    @Test
    public void testDAM30402001() {
        testDAM30101001();
    }

    /**
     * <ul>
     * <li>DAM30501001 : Confirmation of Fetching a single key Entity.Entity for given ID id is present</li>
     * </ul>
     */
    @Test
    public void testDAM30501001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30501001Click();

        todoListPage.setTodoForSearch("0000000006");

        TodoDetailsPage todoDetailsPage = todoListPage.searchByPK();

        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 6"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000006"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("Complete"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/26"));
        // initial version is 0
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));
    }

    /**
     * <ul>
     * <li>DAM30501001 : Confirmation of Fetching a single key Entity.Entity for given ID id is not present</li>
     * </ul>
     */
    @Test
    public void testDAM30501002() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30501002Click();

        todoListPage.setTodoForSearch("10000");

        SystemErrorPage sysErrorPage = todoListPage.searchByPKNA();

        String actualErorMsg = sysErrorPage.getResNFErrorMessage();

        assertThat(actualErorMsg, is("[e.sf.cmmn.5001] Resource not found."));
    }

    /**
     * <ul>
     * <li>DAM30501001 : Confirmation of Fetching Entity of composite key</li>
     * </ul>
     */
    @Test
    public void testDAM30502001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30502001Click();

        // this two input forms composite key
        todoListPage.setTodoForSearch("0000000006");
        todoListPage.setTodoCatForSearch("CA1");

        TodoDetailsPage todoDetailsPage = todoListPage.searchByCmpKey();

        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 6"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000006"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("Complete"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/26"));
        // initial version is 0
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));

        webDriverOperations.displayPage(getPackageRootUrl());
        dam3IndexPage = new DAM3IndexPage(driver);
        todoListPage = dam3IndexPage.dam30502001Click();

        // check for combination not present in db
        todoListPage.setTodoForSearch("0000000006");
        todoListPage.setTodoCatForSearch("CA11");

        todoListPage.searchByCmpKey();

        SystemErrorPage sysErrorPage = new SystemErrorPage(driver);

        String actualErorMsg = sysErrorPage.getResNFErrorMessage();

        assertThat(actualErorMsg, is("[e.sf.cmmn.5001] Resource not found."));
    }

    /**
     * <ul>
     * <li>DAM30501002 : Confirmation of Fetching Entity of composite key and not using @Param annotation</li>
     * </ul>
     */
    @Test
    public void testDAM30502002() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30502002Click();

        // this two input forms composite key
        todoListPage.setTodoForSearch("0000000006");
        todoListPage.setTodoCatForSearch("CA1");

        TodoDetailsPage todoDetailsPage = todoListPage
                .searchByCmpKeyNoParamAnnot();

        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 6"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000006"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("Complete"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/26"));
        // initial version is 0
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));

        webDriverOperations.displayPage(getPackageRootUrl());
        dam3IndexPage = new DAM3IndexPage(driver);
        todoListPage = dam3IndexPage.dam30502002Click();

        // check for combination not present in db
        todoListPage.setTodoForSearch("10000");
        todoListPage.setTodoCatForSearch("CA1");

        todoListPage.searchByCmpKeyNoParamAnnot();

        SystemErrorPage sysErrorPage = new SystemErrorPage(driver);

        String actualErorMsg = sysErrorPage.getResNFErrorMessage();

        assertThat(actualErorMsg, is("[e.sf.cmmn.5001] Resource not found."));
    }

    /**
     * <ul>
     * <li>DAM30503001 : Confirmation of Entity search</li>
     * <li>The return value of method is specified as java.util.List</li>
     * </ul>
     */
    @Test
    public void testDAM30503001() {

        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }
        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30503001Click();

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        boolean isTodoPresent = todoListPage.isTodoDisplayed("0000000002");

        assertThat(isTodoPresent, is(true));

        todoListPage.setTodoTitleContent("Todo 1");
        todoListPage.setTodoCreationDate("2016/12/30");

        todoListPage = todoListPage.searchByCriteriaBean();

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("1"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("1"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("2"));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000001");
        assertThat(isTodoPresent, is(true));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000010");
        assertThat(isTodoPresent, is(true));

        // this todo does not meets the criteria.Hence not displayed.
        isTodoPresent = todoListPage.isTodoDisplayed("0000000002");
        assertThat(isTodoPresent, is(false));

    }

    /**
     * <ul>
     * <li>DAM30503001 : Confirmation of Entity search</li>
     * <li>The return value of method is specified as java.util.Map.</li>
     * </ul>
     */
    @Test
    public void testDAM30503002() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);
        TodoListPage todoListPage = dam3IndexPage.dam30503002Click();

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        boolean isTodoPresent = todoListPage.isTodoDisplayed("0000000002");

        assertThat(isTodoPresent, is(true));

        todoListPage.setTodoTitleContent("Todo 1");
        todoListPage.setTodoCreationDate("2016/12/30");

        todoListPage = todoListPage.searchByCriteriaBeanRetMap();

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("1"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("1"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("2"));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000001");
        assertThat(isTodoPresent, is(true));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000010");
        assertThat(isTodoPresent, is(true));

        // this todo does not meets the criteria.Hence not displayed.
        isTodoPresent = todoListPage.isTodoDisplayed("0000000002");
        assertThat(isTodoPresent, is(false));
    }

    /**
     * <ul>
     * <li>DAM30504001 : Confirmation of Fetching Entity records</li>
     * <li>Fetching the result in primitive types</li>
     * </ul>
     */
    @Test
    public void testDAM30504001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);
        TodoListPage todoListPage = dam3IndexPage.dam30504001Click();

        // Assert the todo record count from DB table
        // These count are computed from the list returned and not fetched using query.
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        // by default the radio button for incomplete todos is selected for retrievingits count.
        todoListPage = todoListPage.clickCountByStatusBtn();

        String todoCnt = todoListPage.getTodoCountwithStatus();

        // This count is fetched from db using count query : status in-complete.
        assertThat(todoCnt, equalTo("InComplete : 7"));

        // Status selected as complete now
        todoListPage.selectCompleteStatRadio();

        todoListPage = todoListPage.clickCountByStatusBtn();
        todoCnt = todoListPage.getTodoCountwithStatus();

        // This count is fetched from db using count query : status complete.
        assertThat(todoCnt, equalTo("Completed : 3"));

    }

    /**
     * <ul>
     * <li>DAM30505001 : Confirmation of Pagination search of Entity</li>
     * <li>MyBatis3 standard method</li>
     * </ul>
     */
    @Test
    public void testDAM30505001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30505001Click();

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        todoListPage = todoListPage.registerBulkTodoByReUseMode();

        // データ反映までの待ち時間
        webDriverOperations.waitForDisplayed(ExpectedConditions
                .textToBePresentInElementLocated(By.id("completedTodo"),
                        "993"));

        todoListPage.setTodoTitleContent("TT");
        todoListPage.setTodoCreationDate("2016/12/30");

        todoListPage = todoListPage.fetchUsingStdPaging();

        boolean isTodoPresent = todoListPage.isTodoDisplayed("0000000001");
        assertThat(isTodoPresent, is(false));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000011");
        assertThat(isTodoPresent, is(true));

        // move to last page of pagination
        todoListPage = todoListPage.displayLastPage();

        // confirm the last ToDo on the page
        isTodoPresent = todoListPage.isTodoDisplayed("0000001000");
        assertThat(isTodoPresent, is(true));
    }

    /**
     * <ul>
     * <li>DAM30506001 : Confirmation of Pagination search of Entity</li>
     * <li>SQL refinement method</li>
     * </ul>
     */
    @Test
    public void testDAM30506001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30506001Click();

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        todoListPage = todoListPage.registerBulkTodoByReUseMode();

        // データ反映までの待ち時間
        webDriverOperations.waitForDisplayed(ExpectedConditions
                .textToBePresentInElementLocated(By.id("completedTodo"),
                        "993"));

        todoListPage.setTodoTitleContent("TT");
        todoListPage.setTodoCreationDate("2016/12/30");

        todoListPage = todoListPage.fetchUsingSQLRefinePaging();

        boolean isTodoPresent = todoListPage.isTodoDisplayed("0000000001");
        assertThat(isTodoPresent, is(false));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000011");
        assertThat(isTodoPresent, is(true));

        // move to last page of pagination
        todoListPage = todoListPage.displayLastPage();

        // confirm the last ToDo on the page
        isTodoPresent = todoListPage.isTodoDisplayed("0000001000");
        assertThat(isTodoPresent, is(true));
    }

    /**
     * <ul>
     * <li>DAM30507001 : Confirmation of Pagination search of Entity</li>
     * <li>Sort search data</li>
     * </ul>
     */
    @Ignore("test in testPGNT0102002, testPGNT0101001, testPGNT0101002")
    public void testDAM30507001() {
    }

    /**
     * <ul>
     * <li>DAM30601001 : Confirmation of Registering a single Entity record</li>
     * <li>Usage of insert element in SQL mapping file to add a record to the database.</li>
     * </ul>
     */
    @Test
    public void testDAM30601001() {
        testDAM30102001();
    }

    /**
     * <ul>
     * <li>DAM30601002 : Confirmation of Registering a single Entity record</li>
     * <li>Confirmation of boolean return value</li>
     * </ul>
     */
    @Test
    public void testDAM30601002() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30601002Click();

        TodoRegisterPage registerTodoPage = todoListPage.registerTodo();

        // input todo details
        registerTodoPage.setTodoId("0000000030");
        registerTodoPage.setTodoCategory("CA2");
        registerTodoPage.setTodoTitle("Todo 30");

        TodoDetailsPage todoDetailsPage = registerTodoPage
                .addTodoWithAndReturnBool();

        String booleanStr = todoDetailsPage.getRegistrationResult();

        assertThat(booleanStr, equalTo("true"));

        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 30"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000030"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("In-Complete"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/28"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("0"));

        // Now check for false return value
        webDriverOperations.displayPage(getPackageRootUrl());
        dam3IndexPage = new DAM3IndexPage(driver);

        todoListPage = dam3IndexPage.dam30601002Click();

        registerTodoPage = todoListPage.registerTodo();

        registerTodoPage.setTodoId("0000000030");
        registerTodoPage.setTodoCategory("CA2");
        registerTodoPage.setTodoTitle("Todo 30");

        todoDetailsPage = registerTodoPage.addTodoWithAndReturnBool();

        booleanStr = todoDetailsPage.getRegistrationResult();

        assertThat(booleanStr, equalTo("false"));

    }

    /**
     * <ul>
     * <li>DAM30601003 : Confirmation of Registering a single Entity record</li>
     * <li>Confirmation of int return value</li>
     * </ul>
     */
    @Test
    public void testDAM30601003() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30601003Click();

        TodoRegisterPage registerTodoPage = todoListPage.registerTodo();

        // input todo details
        registerTodoPage.setTodoId("0000000030");
        registerTodoPage.setTodoCategory("CA2");
        registerTodoPage.setTodoTitle("Todo 30");

        TodoDetailsPage todoDetailsPage = registerTodoPage.addTodoAndRetInt();

        String booleanStr = todoDetailsPage.getRegistrationResult();

        assertThat(booleanStr, equalTo("1"));

        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 30"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000030"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("In-Complete"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/28"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("0"));

        // Now check for false return value
        webDriverOperations.displayPage(getPackageRootUrl());
        dam3IndexPage = new DAM3IndexPage(driver);

        todoListPage = dam3IndexPage.dam30601003Click();

        registerTodoPage = todoListPage.registerTodo();

        registerTodoPage.setTodoId("0000000030");
        registerTodoPage.setTodoCategory("CA2");
        registerTodoPage.setTodoTitle("Todo 30");

        todoDetailsPage = registerTodoPage.addTodoAndRetInt();

        booleanStr = todoDetailsPage.getRegistrationResult();

        assertThat(booleanStr, equalTo("0"));
    }

    /**
     * <ul>
     * <li>DAM30602001 : Confirmation of Generating key</li>
     * </ul>
     */
    @Test
    public void testDAM30602001() {
        // Data preparation
        {
            clearTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30602001Click();

        // open the todo registration page
        TodoRegisterPage registerTodoPage = todoListPage.registerTodo();

        // input todo details
        registerTodoPage.setTodoCategory("CA3");
        registerTodoPage.setTodoTitle("Todo 1");

        // ID is not set. Its going to be auto generated.
        TodoDetailsPage todoDetailsPage = registerTodoPage
                .addTodoUsingAutoIdGeneration();

        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 1"));
        // This id is auto generated.
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000001"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("In-Complete"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA3"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/28"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("0"));

        // Register one more todo
        webDriverOperations.displayPage(getPackageRootUrl());
        dam3IndexPage = new DAM3IndexPage(driver);

        todoListPage = dam3IndexPage.dam30602001Click();

        registerTodoPage = todoListPage.registerTodo();

        // input todo details
        registerTodoPage.setTodoCategory("CA4");
        registerTodoPage.setTodoTitle("Todo 2");

        // ID is not set. Its going to be auto generated.
        todoDetailsPage = registerTodoPage.addTodoUsingAutoIdGeneration();

        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 2"));
        // This id is auto generated.
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000002"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("In-Complete"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA4"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/28"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("0"));

    }

    /**
     * <ul>
     * <li>DAM30603001 : Confirmation of Batch registration of Entity</li>
     * <li>Mutiple records can be inserted by firing a single query.</li>
     * </ul>
     */
    @Test
    public void testDAM30603001() {
        // Data preparation
        {
            clearTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30603001Click();

        todoListPage = todoListPage.batchRegister();

        String cntOfRegisteredTodo = todoListPage
                .getCountOfBatchRegisteredTodos();

        assertThat(cntOfRegisteredTodo, equalTo(
                "Total Todos Registered/Updated in Batch : 10"));

        assertThat(todoListPage.getCompletedTodoCount(), equalTo("5"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("5"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        // confirm for some todos as present and some as not present.
        boolean isTodoPresent = todoListPage.isTodoDisplayed("0000000001");
        assertThat(isTodoPresent, is(true));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000005");
        assertThat(isTodoPresent, is(true));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000010");
        assertThat(isTodoPresent, is(true));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000011");
        assertThat(isTodoPresent, is(false));
    }

    /**
     * <ul>
     * <li>DAM30701001 : Confirmation for Updating a single entity</li>
     * <li>Usage of update element in sql mapping file to modify a record from the database..</li>
     * </ul>
     */
    @Test
    public void testDAM30701001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30701001Click();

        TodoUpdatePage todoUpdatePage = todoListPage.updateTodo("0000000001");

        // Confirm the values before update.
        assertThat(todoUpdatePage.getTodoTitle(), equalTo("Todo 1"));
        assertThat(todoUpdatePage.getTodoID(), equalTo("0000000001"));
        assertThat(todoUpdatePage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoUpdatePage.getTodoStatus(), equalTo("false"));
        assertThat(todoUpdatePage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoUpdatePage.getTodoVersion(), equalTo("1"));

        // modify ToDo properties
        todoUpdatePage.setTodoTitle("TitleUpdate1");
        todoUpdatePage.setTodoStatus("true");

        // Call Update process
        TodoDetailsPage todoDetailsPage = todoUpdatePage.updateTodoOpt();

        // confirmation of update values : value 1
        assertThat(todoDetailsPage.getTodoTitle(), equalTo(
                "Todo 1TitleUpdate1"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000001"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));

        // confirmation of update values : value 2
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("true"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));

        // confirmation of update values : value 3
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("2"));
    }

    /**
     * <ul>
     * <li>DAM30702001 : Confirmation for Batch update of Entity</li>
     * <li>Multiple records can be updated by firing a single query..</li>
     * </ul>
     */
    @Test
    public void testDAM30702001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30702001Click();

        // Confirmation of database state before batch update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        // Assert todo id 00001 for sample. this will be updated in batch update
        TodoDetailsPage todoDetailsPage = todoListPage.displayTodoDetail(
                "0000000001");
        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 1"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000001"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("false"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));

        // Back to list page:
        todoListPage = todoDetailsPage.showTodoListPage();

        // Assert todo id 00002 for sample. this will be updated in batch update
        todoDetailsPage = todoListPage.displayTodoDetail("0000000002");
        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 2"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000002"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA2"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("false"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));

        // Back to list page:
        todoListPage = todoDetailsPage.showTodoListPage();

        // set the ids of the ToDo to be updated in batch
        todoListPage.setTodoIDList(
                "0000000001,0000000002,0000000003,0000000004");

        todoListPage = todoListPage.batchUpdate();

        String cntOfUpdatedTodo = todoListPage.getCountOfBatchRegisteredTodos();

        assertThat(cntOfUpdatedTodo, equalTo(
                "Total Todos Registered/Updated in Batch : 4"));

        todoDetailsPage = todoListPage.displayTodoDetail("0000000001");
        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 1"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000001"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("true"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("2"));

        // Back to list page:
        todoListPage = todoDetailsPage.showTodoListPage();

        // Assert todo id 00002 for sample. this will be updated in batch update
        todoDetailsPage = todoListPage.displayTodoDetail("0000000002");
        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 2"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000002"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA2"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("true"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("2"));
    }

    /**
     * <ul>
     * <li>DAM30801001 :Confirmation for Deleting a single Entity</li>
     * <li>Usage of delete element in sql mapping file to remove a record from the database.</li>
     * </ul>
     */
    @Test
    public void testDAM30801001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30801001Click();

        // Confirmation of database state before batch update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        TodoUpdatePage todoUpdatePage = todoListPage.updateTodo("0000000003");

        todoListPage = todoUpdatePage.deleteByUsingTodoObj();

        // Confirmation of total cou nt after a record is deleted.
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("6"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("9"));

        // confirm that the todo is deleted.
        boolean isTodoPresent = todoListPage.isTodoDisplayed("0000000003");
        assertThat(isTodoPresent, is(false));

    }

    /**
     * <ul>
     * <li>DAM30802001 : Confirmation for Batch deletion of Entity</li>
     * <li>Mutiple records can be deleted by firing a single query.</li>
     * </ul>
     */
    @Test
    public void testDAM30802001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30802001Click();

        // Confirmation of database state before batch update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        // set date : the completed todos having creation date before this date would be deleted in batch
        todoListPage.setCutOffDate("2016/12/30");

        todoListPage = todoListPage.batchDelete();

        String cntOfUpdatedTodo = todoListPage.getCountOfBatchRegisteredTodos();

        assertThat(cntOfUpdatedTodo, equalTo(
                "Total Todos Registered/Updated in Batch : 3"));

        // Confirmation of database state before batch update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("0"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("7"));
    }

    /**
     * <ul>
     * <li>DAM30901001 : Implementation of if element</li>
     * </ul>
     */
    @Test
    public void testDAM30901001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30901001Click();

        // Confirmation of database state before batch update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        todoListPage.selectCompleteStatRadio();

        todoListPage = todoListPage.ifElementUsageSearch();

        // Confirmation of database state before batch update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));

        // 1 and 3 todos are incomplete. hence not displayed
        boolean isTodoPresent = todoListPage.isTodoDisplayed("0000000003");
        assertThat(isTodoPresent, is(false));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000001");
        assertThat(isTodoPresent, is(false));
        // 10 todo is complete. hence displayed.
        isTodoPresent = todoListPage.isTodoDisplayed("0000000010");
        assertThat(isTodoPresent, is(true));

        // default value of finished is now false.hence todos like 1,3 will be displayed
        // where as todo like 10 will not be displayed.
        todoListPage.selectInCompleteStatRadio();
        todoListPage = todoListPage.ifElementUsageSearch();

        isTodoPresent = todoListPage.isTodoDisplayed("0000000003");
        assertThat(isTodoPresent, is(true));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000001");
        assertThat(isTodoPresent, is(true));
        // 10 todo is complete. hence displayed.
        isTodoPresent = todoListPage.isTodoDisplayed("0000000010");
        assertThat(isTodoPresent, is(false));

        // scenario when finished property of criteria is null.

    }

    /**
     * <ul>
     * <li>DAM30902001 : Implementation of choose element</li>
     * </ul>
     */
    @Test
    public void testDAM30902001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30902001Click();

        // Confirmation of database state before batch update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        // add one sample todo which has some unique title from those of the
        // above
        // this is used to have some unique combination satisfying the choose
        // element other branch
        // Open todo registration page
        TodoRegisterPage registerTodoPage = todoListPage.registerTodo();

        // input todo details
        registerTodoPage.setTodoId("0000000025");
        registerTodoPage.setTodoCategory("CA2");
        registerTodoPage.setTodoTitle("sample todo");

        registerTodoPage.addTodo();

        webDriverOperations.displayPage(getPackageRootUrl());

        dam3IndexPage = new DAM3IndexPage(driver);

        todoListPage = dam3IndexPage.dam30102002Click();

        todoListPage.setTodoTitleContent("Todo 1");

        todoListPage.setTodoCreationDate("2016/12/30");

        todoListPage = todoListPage.chooseElementUsageSearch();

        // Confirm the todos when title is specified from the choose element

        // Confirmation of database state before batch update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("1"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("1"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("2"));

        webDriverOperations.displayPage(getPackageRootUrl());

        dam3IndexPage = new DAM3IndexPage(driver);

        todoListPage = dam3IndexPage.dam30102002Click();

        todoListPage.setTodoCreationDate("2016/12/30");

        todoListPage = todoListPage.chooseElementUsageSearch();

        // Confirm the todos when title is not specified from the choose
        // element.
        // here it takes default value specified in otherwise tag of choose
        // (sample)

        // Confirmation of database state before batch update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("0"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("1"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("1"));
    }

    /**
     * <ul>
     * <li>DAM30903001 : Implementation of where element</li>
     * </ul>
     */
    @Test
    public void testDAM30903001() {
        testDAM30802001();
    }

    /**
     * <ul>
     * <li>DAM30904001 : Implementation of SET element</li>
     * </ul>
     */
    @Test
    public void testDAM30904001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30904001Click();

        // Select Action
        TodoUpdatePage todoUpdatePage = todoListPage.updateTodo("0000000001");

        assertThat(todoUpdatePage.getTodoTitle(), equalTo("Todo 1"));
        assertThat(todoUpdatePage.getTodoID(), equalTo("0000000001"));
        assertThat(todoUpdatePage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoUpdatePage.getTodoStatus(), equalTo("false"));
        assertThat(todoUpdatePage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoUpdatePage.getTodoVersion(), equalTo("1"));

        todoUpdatePage.setTodoTitle(":Update");
        // Update action, SET tag is used for set clause
        TodoDetailsPage todoDetailsPage = todoUpdatePage.updateTodo();
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 1:Update"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000001"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("false"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("2"));
    }

    /**
     * <ul>
     * <li>DAM30905001 : Implementation of FOREACH element</li>
     * </ul>
     */
    @Test
    public void testDAM30905001() {
        // this case uses forEach element for updating ToDos.
        testDAM30702001();
    }

    /**
     * <ul>
     * <li>DAM30906001 : Implementation of BIND element</li>
     * </ul>
     */
    @Test
    public void testDAM30906001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30906001Click();

        TodoRegisterPage todoRegisterPage = todoListPage.registerTodo();

        todoRegisterPage.setTodoCategory("CA5");
        todoRegisterPage.setTodoId("0000000025");
        todoRegisterPage.setTodoTitle("Bind Test");
        todoRegisterPage.addTodo();

        webDriverOperations.displayPage(getPackageRootUrl());
        dam3IndexPage = new DAM3IndexPage(driver);

        todoListPage = dam3IndexPage.dam30906001Click();

        todoListPage.setTodoTitleContent("Bi");

        todoListPage = todoListPage.bindElementUsageSearch();

        boolean isTodoPresent = todoListPage.isTodoDisplayed("0000000001");
        assertThat(isTodoPresent, is(false));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000025");
        assertThat(isTodoPresent, is(true));

        TodoDetailsPage todoDetailsPage = todoListPage.displayTodoDetail(
                "0000000025");

        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Bind Test"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000025"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA5"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("false"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/28"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("0"));
    }

    /**
     * <ul>
     * <li>DAM31001001 : Demonstration for escape during like search</li>
     * </ul>
     */
    @Test
    public void testDAM31001001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam31001001Click();

        // Confirmation of database state before any update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        TodoRegisterPage todoRegisterPage = todoListPage.registerTodo();

        todoRegisterPage.setTodoCategory("CA3");
        todoRegisterPage.setTodoId("0000001000");
        todoRegisterPage.setTodoTitle("ESC Test1");
        // add couple of todos for escape search operation.
        todoRegisterPage.addTodo();

        webDriverOperations.displayPage(getPackageRootUrl());
        dam3IndexPage = new DAM3IndexPage(driver);

        todoListPage = dam3IndexPage.dam31001001Click();

        // Confirmation of database state before batch update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("8"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("11"));

        todoRegisterPage = todoListPage.registerTodo();

        todoRegisterPage.setTodoCategory("CA4");
        todoRegisterPage.setTodoId("0000000031");
        todoRegisterPage.setTodoTitle("2 ESC Test");
        todoRegisterPage.addTodo();

        webDriverOperations.displayPage(getPackageRootUrl());
        dam3IndexPage = new DAM3IndexPage(driver);

        todoListPage = dam3IndexPage.dam31001001Click();

        // Confirmation of database state after adding 1 todo
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("9"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("12"));

        todoListPage.setTodoTitleContent("ESC");

        // perform escape search
        todoListPage = todoListPage.escapeSearch();

        // Confirmation of todos retrieved satisfying the escape search criteria
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("0"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("2"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("2"));

        // on sampling basis check the details of todo retrieved as a result of escape
        // search.
        TodoDetailsPage todoDetailsPage = todoListPage.displayTodoDetail(
                "0000001000");
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("ESC Test1"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000001000"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA3"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("false"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/28"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("0"));

    }

    /**
     * <ul>
     * <li>DAM31101001 : Confirmation of embeding using a bind variable</li>
     * <li>Avoid sql injection by using bind variable.</li>
     * </ul>
     */
    @Test
    public void testDAM31101001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam31101001Click();

        // Confirmation of database state before any update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        TodoRegisterPage todoRegisterPage = todoListPage.registerTodo();

        todoRegisterPage.setTodoCategory("CA3");
        todoRegisterPage.setTodoId("0000001000");

        // checking whether the delete statement really entered as a title value really delete the records.
        todoRegisterPage.setTodoTitle("<![CDATA[delete * from t_todo;]]>");

        // add a todo to confirm sql injection does not occur.
        todoRegisterPage.addTodo();
        webDriverOperations.displayPage(getPackageRootUrl());
        dam3IndexPage = new DAM3IndexPage(driver);

        todoListPage = dam3IndexPage.dam31101001Click();

        // Confirmation of database state whether the title value has been executed and deleted the ToDos?
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("8"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("11"));

        TodoDetailsPage todoDetailsPage = todoListPage.displayTodoDetail(
                "0000001000");

        // title value is inserted as it is.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo(
                "&lt;![CDATA[delete * from t_todo;]]&gt;"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000001000"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA3"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("false"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/28"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("0"));
    }

    /**
     * <ul>
     * <li>DAM31102001 : Confirmation of How to embed using a substitution variable</li>
     * <li>When substitution method is used, the value is substituted as a string while building SQL</li>
     * </ul>
     */
    @Test
    public void testDAM31102001() {
        testDAM31001001();
    }

    /**
     * <ul>
     * <li>DAM31201001 : Confirmation of How to share the sql statements</li>
     * <li>Sharing SQL statement in multiple SQLs</li>
     * </ul>
     */
    @Test
    public void testDAM31201001() {
        // In this test case the where clause is specified in a common sql shared in multiple queries.
        testDAM30505001();
    }

    /**
     * <ul>
     * <li>DAM31301001 : Implementation of TypeHandler for BLOB</li>
     */
    @Test
    public void testDAM31301001() {

        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30301001Click();

        TodoUpdatePage todoUpdatePage = todoListPage.updateTodo("0000000001");

        assertThat(todoUpdatePage.getTodoTitle(), equalTo("Todo 1"));
        assertThat(todoUpdatePage.getTodoID(), equalTo("0000000001"));
        assertThat(todoUpdatePage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoUpdatePage.getTodoStatus(), equalTo("false"));
        assertThat(todoUpdatePage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoUpdatePage.getTodoVersion(), equalTo("1"));
        assertThat(todoUpdatePage.getBlobDesc(), equalTo("desc11"));
    }

    /**
     * <ul>
     * <li>DAM31301001 : Implementation of TypeHandler for CLOB</li>
     * </ul>
     */
    @Test
    public void testDAM31302001() {

        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30301001Click();

        TodoUpdatePage todoUpdatePage = todoListPage.updateTodo("0000000001");

        assertThat(todoUpdatePage.getTodoTitle(), equalTo("Todo 1"));
        assertThat(todoUpdatePage.getTodoID(), equalTo("0000000001"));
        assertThat(todoUpdatePage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoUpdatePage.getTodoStatus(), equalTo("false"));
        assertThat(todoUpdatePage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoUpdatePage.getTodoVersion(), equalTo("1"));
        assertThat(todoUpdatePage.getClobbDesc(), equalTo("desc21"));
    }

    /**
     * <ul>
     * <li>DAM31301001 : Implementation of TypeHandler for joda-time</li>
     * <li>Confirmation for Implementation of TypeHandler for joda-time</li>
     * </ul>
     */
    @Test
    public void testDAM31303001() {

        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam31301001Click();

        TodoDetailsPage todoDetailsPage = todoListPage.displayTodoDetail(
                "0000000010");

        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 10"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000010"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA5"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("true"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/29"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));

        // completion date is of timeStamp in DB whereas in entity its of type or.joda.time.DateTime
        // type handler converts appropriately timeStamp to DateTime
        assertThat(todoDetailsPage.getTodoCompleteDate(), equalTo(
                "2016/12/30"));
    }

    /**
     * <ul>
     * <li>DAM31304001 : Confirmation for Mybatis3 setting of TypeHandler for JSR310 Date and Time API</li>
     * </ul>
     */
    @Test
    public void testDAM31304001() {

        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        {
            webDriverOperations.click(id("dam31304001"));
        }

        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "2016-12-29"));
            assertThat(webDriverOperations.getText(id("getDateClassResult")),
                    is("java.time.LocalDate"));
            assertThat(webDriverOperations.getText(id(
                    "getObjectCertification")), is("true"));
        }
    }

    /**
     * <ul>
     * <li>DAM31301001 : Implementation of ResultHandler</li>
     * <li>Confirmation for ResultHandler as Anonymous class</li>
     * </ul>
     */
    @Test
    public void testDAM31401001() {

        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }
        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam31401001Click();

        // Confirmation of database state before any update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        todoListPage.setTodoTitleContent("T");
        todoListPage.setCutOffDate("2016/12/30");

        todoListPage = todoListPage.downloadCSVUsingResHndlr();
        // path of downloaded csv.
        String csvPath = todoListPage.getResultHandlerCSVPath();

        assertThat(csvPath.isEmpty(), is(false));

        // The data displayed as list is extracted form the CSV file and
        // sent back to the client side.
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        TodoDetailsPage todoDetailsPage = todoListPage.displayTodoDetail(
                "0000000010");

        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 10"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000010"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA5"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("true"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/29"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));

        // Back to list page:
        todoListPage = todoDetailsPage.showTodoListPage();
        todoListPage.setTodoTitleContent("T");
        todoListPage.setCutOffDate("2016/12/30");

        todoListPage = todoListPage.downloadCSVUsingResHndlr();

        todoDetailsPage = todoListPage.displayTodoDetail("0000000001");
        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 1"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000001"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("false"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));

    }

    /**
     * <ul>
     * <li>DAM31501001 :Confirmation of Using PreparedStatement reuse mode</li>
     * </ul>
     */
    @Test
    public void testDAM31501001() {
        //
        testDAM30201001();
    }

    /**
     * <ul>
     * <li>DAM31502001 :Confirmation of Using PreparedStatement Batch mode</li>
     * </ul>
     */
    @Test
    public void testDAM31502001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }
        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam30702001Click();

        // Confirmation of database state before batch update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        // Assert todo id 00001 for sample. this will be updated in batch update
        TodoDetailsPage todoDetailsPage = todoListPage.displayTodoDetail(
                "0000000001");
        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 1"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000001"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("false"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));

        // Back to list page:
        todoListPage = todoDetailsPage.showTodoListPage();

        // Assert todo id 00002 for sample. this will be updated in batch update
        todoDetailsPage = todoListPage.displayTodoDetail("0000000002");
        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 2"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000002"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA2"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("false"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));

        // Back to list page:
        todoListPage = todoDetailsPage.showTodoListPage();

        // set the ids of the ToDo to be updated in batch
        todoListPage.setTodoIDList(
                "0000000001,0000000002,0000000003,0000000004");

        todoListPage = todoListPage.updateUsingBatchRepo();

        String cntOfUpdatedTodo = todoListPage.getCountOfBatchRegisteredTodos();

        // In case of Batch mode fixed value -2147482646 is returned if the update is successful.
        // the integer vallue is returned if the repository method return type is int
        assertThat(cntOfUpdatedTodo, equalTo(
                "Total Todos Registered/Updated in Batch : -2147482646"));

        todoDetailsPage = todoListPage.displayTodoDetail("0000000001");
        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 1"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000001"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("true"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("2"));

        // Back to list page:
        todoListPage = todoDetailsPage.showTodoListPage();

        // Assert todo id 00002 for sample. this will be updated in batch update
        todoDetailsPage = todoListPage.displayTodoDetail("0000000002");
        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 2"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000002"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA2"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("true"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("2"));
    }

    /**
     * <ul>
     * <li>DAM31601001 :Implementation of a stored procedure</li>
     * </ul>
     */
    @Test
    public void testDAM31601001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }
        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam31601001Click();

        // Confirmation of database state before any update
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        todoListPage.setTodoForSearch("0000000001");

        TodoDetailsPage todoDetailsPage = todoListPage.searchUsingStoredProc();

        webDriverOperations.waitForDisplayed(id("finished"));

        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 1"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000001"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("In-Complete"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));
    }

    /**
     * <ul>
     * <li>DAM31701001 :Mapper Interface mechanism</li>
     * <li>Implementation of a mapper interface</li>
     * </ul>
     */
    @Test
    public void testDAM31701001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        // Connect to DB and fetch the Todo list
        // list is populated using automatic mapping using a mapper interface configuration
        TodoListPage todoListPage = dam3IndexPage.dam30101001Click();

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("3"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("7"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("10"));

        // fetch the details of one of the records.
        TodoDetailsPage todoDetailsPage = todoListPage.displayTodoDetail(
                "0000000001");

        // assert all the properties of fetched record.
        assertThat(todoDetailsPage.getTodoTitle(), equalTo("Todo 1"));
        assertThat(todoDetailsPage.getTodoID(), equalTo("0000000001"));
        assertThat(todoDetailsPage.getTodoCategory(), equalTo("CA1"));
        assertThat(todoDetailsPage.getTodoStatus(), equalTo("false"));
        assertThat(todoDetailsPage.getTodoCreatedDate(), equalTo("2016/12/24"));
        assertThat(todoDetailsPage.getTodoVersion(), equalTo("1"));
    }

    /**
     * <ul>
     * <li>DAM31801001 :Configuring TypeAlias per class</li>
     * </ul>
     */
    @Test
    public void testDAM31801001() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam31801001Click();

        todoListPage.setTodoTitleContent("Todo 1");

        todoListPage.setTodoCreationDate("2016/12/30");

        todoListPage = todoListPage.searchUsingClassTypeAlias();

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("1"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("1"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("2"));

        boolean isTodoPresent = todoListPage.isTodoDisplayed("0000000001");
        assertThat(isTodoPresent, is(true));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000010");
        assertThat(isTodoPresent, is(true));

        // this todo does not meets the criteria.Hence not displayed.
        isTodoPresent = todoListPage.isTodoDisplayed("0000000002");
        assertThat(isTodoPresent, is(false));
    }

    /**
     * <ul>
     * <li>DAM31801001 :Overwriting the default alias name</li>
     * </ul>
     */
    @Test
    public void testDAM31801002() {
        // Data preparation
        {
            clearAndCreateTestDataForBook();
        }

        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        TodoListPage todoListPage = dam3IndexPage.dam31801002Click();

        todoListPage.setTodoTitleContent("Todo 1");

        todoListPage.setTodoCreationDate("2016/12/30");

        todoListPage = todoListPage.searchUsingOverwrittenTypeAliasName();

        // Assert the todo record count from DB table
        assertThat(todoListPage.getCompletedTodoCount(), equalTo("1"));
        assertThat(todoListPage.getIncompletTodoCount(), equalTo("1"));
        assertThat(todoListPage.getTotalTodoCount(), equalTo("2"));

        boolean isTodoPresent = todoListPage.isTodoDisplayed("0000000001");
        assertThat(isTodoPresent, is(true));

        isTodoPresent = todoListPage.isTodoDisplayed("0000000010");
        assertThat(isTodoPresent, is(true));

        // this todo does not meets the criteria.Hence not displayed.
        isTodoPresent = todoListPage.isTodoDisplayed("0000000002");
        assertThat(isTodoPresent, is(false));
    }

    /**
     * <ul>
     * <li>DAM31901001 :SQL switching based on database</li>
     * <li>Using databaseId attribute of selectKey element.</li>
     * </ul>
     */
    @Test
    public void testDAM31901001() {
        // in this case, there are different querirs used to generate the ids.
        // the database id is specified in the selectKey element
        testDAM30602001();
    }

    /**
     * <ul>
     * <li>DAM31901002 :SQL switching based on database</li>
     * <li>Using databaseId attribute OGNL base expression (Expression language)</li>
     * </ul>
     */
    @Test
    public void testDAM31901002() {
        // in this case, the boolean type is not supported by oracle db.
        // there is separate sql created to be executed for oracle database
        // however, the sql id that for oracle and other db are same.
        testDAM30802001();
    }

    /**
     * <ul>
     * <li>DAM32001001 : How to fetch a related Entity by a single SQL</li>
     * </ul>
     */
    @Test
    public void testDAM32001001() {
        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        // This call brings te order details from various tables into single entity using join
        OrderMB3ListPage orderMB3ListPage = dam3IndexPage.dam32001001Click();

        // Expected order details
        // the string is of the format
        // ORDER STATUS NAME, ITEM CODE, ITEM NAME, CATEGORY CODE, CATEGORY NAME, MEMO
        String expectedOrdeDetails = "Order accepted, ITM0000001, Orange juice, CTG0000001, Drink, dummy1";

        // get the details of the specified order in a single string
        String actualOrderDetails = orderMB3ListPage.getOrderDetails(1);

        // confirm the details of the order fetched from various tables
        assertThat(actualOrderDetails, is(expectedOrdeDetails));

        // confirmation for some more order details....

        expectedOrdeDetails = "Stock checking, ITM0000002, NotePC, CTG0000002, PC, ";
        actualOrderDetails = orderMB3ListPage.getOrderDetails(2);
        assertThat(actualOrderDetails, is(expectedOrdeDetails));

        expectedOrdeDetails = "Item Shipped, ITM0000003, Car, CTG0000003, Hot selling, dummy3";
        actualOrderDetails = orderMB3ListPage.getOrderDetails(3);
        assertThat(actualOrderDetails, is(expectedOrdeDetails));

        expectedOrdeDetails = "Order accepted, ITM0000001, Orange juice, CTG0000001, Drink, dummy4";
        actualOrderDetails = orderMB3ListPage.getOrderDetails(4);
        assertThat(actualOrderDetails, is(expectedOrdeDetails));
    }

    /**
     * <ul>
     * <li>DAM32001002 : Pagination search for related Entity by a single SQL</li>
     * </ul>
     */
    @Test
    public void testDAM32001002() {
        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        OrderMB3PageListPage orderMB3PageListPage = dam3IndexPage
                .dam32001002Click();

        orderMB3PageListPage.checkITM0000001();

        orderMB3PageListPage = orderMB3PageListPage.clickItemCodeSearch();

        String expectedOrdeDetails = "Order accepted, ITM0000001\nITM0000002, Orange juice\nNotePC, CTG0000001\nCTG0000002, Drink\nPC, dummy7";

        // get the details of the specified order in a single string
        String actualOrderDetails = orderMB3PageListPage.getOrderDetails(7);

        assertThat(actualOrderDetails, is(expectedOrdeDetails));

        expectedOrdeDetails = "Order accepted, ITM0000001, Orange juice, CTG0000001, Drink, dummy4";
        actualOrderDetails = orderMB3PageListPage.getOrderDetails(4);
        assertThat(actualOrderDetails, is(expectedOrdeDetails));
    }

    /**
     * <ul>
     * <li>DAM32101001 : Confirmation for fetching a related Entity using a nested SQL</li>
     * </ul>
     */
    @Test
    public void testDAM32101001() {
        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        OrderMB3ListPage orderMB3ListPage = dam3IndexPage.dam32101001Click();

        String expectedOrdeDetails = "Order accepted, ITM0000001, Orange juice, CTG0000001, Drink, dummy1";

        // get the details of the specified order in a single string
        String actualOrderDetails = orderMB3ListPage.getOrderDetails(1);

        // confirm the details of the order fetched from various tables
        assertThat(actualOrderDetails, is(expectedOrdeDetails));

        orderMB3ListPage.setItemCode("ITM0000001");

        orderMB3ListPage = orderMB3ListPage.fetchRelatedEntity();

        // Expected related entity category details
        String expRelatedEntityDet = "CTG0000001, Drink";

        String actRelatedEntityDet = orderMB3ListPage
                .getRelatedEntityCategoryDeatils(1);

        // confirmed realted entity details
        assertThat(actRelatedEntityDet, is(expRelatedEntityDet));

    }

    /**
     * <ul>
     * <li>DAM32102001 : Confirmation for fetching a related Entity using a nested SQL</li>
     * <li>lazyLoadingEnabled setting</li>
     * </ul>
     */
    @Test
    public void testDAM32102001() {
        // By default the loading related entity is eager.
        testDAM32101001();
    }

    /**
     * <ul>
     * <li>DAM32102001 : Confirmation for fetching a related Entity using a nested SQL</li>
     * <li>lazyLoading disabled</li>
     * </ul>
     */
    @Test
    public void testDAM32102002() {
        // Index page
        DAM3IndexPage dam3IndexPage = new DAM3IndexPage(driver);

        OrderMB3ListPage orderMB3ListPage = dam3IndexPage.dam32102002Click();

        String expectedOrdeDetails = "Order accepted, ITM0000001, Orange juice, CTG0000001, Drink, dummy1";

        // get the details of the specified order in a single string
        String actualOrderDetails = orderMB3ListPage.getOrderDetails(1);

        // confirm the details of the order fetched from various tables
        assertThat(actualOrderDetails, is(expectedOrdeDetails));

        orderMB3ListPage.setItemCode("ITM0000001");

        orderMB3ListPage = orderMB3ListPage.fetchRelatedEntityLazy();

        // Expected related entity category details
        String expRelatedEntityDet = "CTG0000001, Drink";

        String actRelatedEntityDet = orderMB3ListPage
                .getRelatedEntityCategoryDeatils(1);

        // confirmed realted entity details
        assertThat(actRelatedEntityDet, is(expRelatedEntityDet));
    }

}
