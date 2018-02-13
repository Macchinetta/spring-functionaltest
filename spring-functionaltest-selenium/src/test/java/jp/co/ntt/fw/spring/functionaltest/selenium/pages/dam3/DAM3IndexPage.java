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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DAM3IndexPage implements Page<DAM3IndexPage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "dam30101001")
    private WebElement dam30101001;

    @CacheLookup
    @FindBy(id = "dam30102001")
    private WebElement dam30102001;

    @CacheLookup
    @FindBy(id = "dam30102002")
    private WebElement dam30102002;

    @CacheLookup
    @FindBy(id = "dam30201001")
    private WebElement dam30201001;

    @CacheLookup
    @FindBy(id = "dam30202001")
    private WebElement dam30202001;

    @CacheLookup
    @FindBy(id = "dam30204001")
    private WebElement dam30204001;

    @CacheLookup
    @FindBy(id = "dam30205001")
    private WebElement dam30205001;

    @CacheLookup
    @FindBy(id = "dam30301001")
    private WebElement dam30301001;

    @CacheLookup
    @FindBy(id = "dam30401001")
    private WebElement dam30401001;

    @CacheLookup
    @FindBy(id = "dam30401002")
    private WebElement dam30401002;

    @CacheLookup
    @FindBy(id = "dam30501001")
    private WebElement dam30501001;

    @CacheLookup
    @FindBy(id = "dam30501002")
    private WebElement dam30501002;

    @CacheLookup
    @FindBy(id = "dam30502001")
    private WebElement dam30502001;

    @CacheLookup
    @FindBy(id = "dam30502002")
    private WebElement dam30502002;

    @CacheLookup
    @FindBy(id = "dam30503001")
    private WebElement dam30503001;

    @CacheLookup
    @FindBy(id = "dam30503002")
    private WebElement dam30503002;

    @CacheLookup
    @FindBy(id = "dam30504001")
    private WebElement dam30504001;

    @CacheLookup
    @FindBy(id = "dam30505001")
    private WebElement dam30505001;

    @CacheLookup
    @FindBy(id = "dam30506001")
    private WebElement dam30506001;

    @CacheLookup
    @FindBy(id = "dam30601002")
    private WebElement dam30601002;

    @CacheLookup
    @FindBy(id = "dam30601003")
    private WebElement dam30601003;

    @CacheLookup
    @FindBy(id = "dam30602001")
    private WebElement dam30602001;

    @CacheLookup
    @FindBy(id = "dam30603001")
    private WebElement dam30603001;

    @CacheLookup
    @FindBy(id = "dam30701001")
    private WebElement dam30701001;

    @CacheLookup
    @FindBy(id = "dam30702001")
    private WebElement dam30702001;

    @CacheLookup
    @FindBy(id = "dam30801001")
    private WebElement dam30801001;

    @CacheLookup
    @FindBy(id = "dam30802001")
    private WebElement dam30802001;

    @CacheLookup
    @FindBy(id = "dam30901001")
    private WebElement dam30901001;

    @CacheLookup
    @FindBy(id = "dam30902001")
    private WebElement dam30902001;

    @CacheLookup
    @FindBy(id = "dam30904001")
    private WebElement dam30904001;

    @CacheLookup
    @FindBy(id = "dam30906001")
    private WebElement dam30906001;

    @CacheLookup
    @FindBy(id = "dam31001001")
    private WebElement dam31001001;

    @CacheLookup
    @FindBy(id = "dam31101001")
    private WebElement dam31101001;

    @CacheLookup
    @FindBy(id = "dam31301001")
    private WebElement dam31301001;

    @CacheLookup
    @FindBy(id = "dam31401001")
    private WebElement dam31401001;

    @CacheLookup
    @FindBy(id = "dam31601001")
    private WebElement dam31601001;

    @CacheLookup
    @FindBy(id = "dam32001001")
    private WebElement dam32001001;

    @CacheLookup
    @FindBy(id = "dam32001002")
    private WebElement dam32001002;

    @CacheLookup
    @FindBy(id = "dam32101001")
    private WebElement dam32101001;

    @CacheLookup
    @FindBy(id = "dam31801001")
    private WebElement dam31801001;

    @CacheLookup
    @FindBy(id = "dam31801002")
    private WebElement dam31801002;

    @CacheLookup
    @FindBy(id = "dam32102002")
    private WebElement dam32102002;

    @Override
    public DAM3IndexPage reload() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public DAM3IndexPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    private TodoListPage showTodoListPage() {
        TodoListPage todoListPage = new TodoListPage(driver);
        return todoListPage;
    }

    public TodoListPage dam30101001Click() {
        dam30101001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30102001Click() {
        dam30102001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30102002Click() {
        dam30102002.click();
        return showTodoListPage();
    }

    public TodoListPage dam30201001Click() {
        dam30201001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30202001Click() {
        dam30202001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30204001Click() {
        dam30204001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30205001Click() {
        dam30205001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30301001Click() {
        dam30301001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30401001Click() {
        dam30401001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30401002Click() {
        dam30401002.click();
        return showTodoListPage();
    }

    public TodoListPage dam30501001Click() {
        dam30501001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30501002Click() {
        dam30501002.click();
        return showTodoListPage();
    }

    public TodoListPage dam30502001Click() {
        dam30502001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30502002Click() {
        dam30502002.click();
        return showTodoListPage();
    }

    public TodoListPage dam30503001Click() {
        dam30503001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30503002Click() {
        dam30503002.click();
        return showTodoListPage();
    }

    public TodoListPage dam30504001Click() {
        dam30504001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30505001Click() {
        dam30505001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30506001Click() {
        dam30506001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30601002Click() {
        dam30601002.click();
        return showTodoListPage();
    }

    public TodoListPage dam30601003Click() {
        dam30601003.click();
        return showTodoListPage();
    }

    public TodoListPage dam30602001Click() {
        dam30602001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30603001Click() {
        dam30603001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30701001Click() {
        dam30701001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30702001Click() {
        dam30702001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30801001Click() {
        dam30801001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30802001Click() {
        dam30801001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30901001Click() {
        dam30901001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30902001Click() {
        dam30902001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30904001Click() {
        dam30904001.click();
        return showTodoListPage();
    }

    public TodoListPage dam30906001Click() {
        dam30906001.click();
        return showTodoListPage();
    }

    public TodoListPage dam31001001Click() {
        dam31001001.click();
        return showTodoListPage();
    }

    public TodoListPage dam31101001Click() {
        dam31101001.click();
        return showTodoListPage();
    }

    public TodoListPage dam31301001Click() {
        dam31301001.click();
        return showTodoListPage();
    }

    public TodoListPage dam31401001Click() {
        dam31401001.click();
        return showTodoListPage();
    }

    public TodoListPage dam31601001Click() {
        dam31601001.click();
        return showTodoListPage();
    }

    public OrderMB3ListPage dam32001001Click() {
        dam32001001.click();
        OrderMB3ListPage orderMB3ListPage = new OrderMB3ListPage(driver);
        return orderMB3ListPage;
    }

    public OrderMB3PageListPage dam32001002Click() {
        dam32001002.click();
        OrderMB3PageListPage orderMB3PageListPage = new OrderMB3PageListPage(driver);
        return orderMB3PageListPage;
    }

    public OrderMB3ListPage dam32101001Click() {
        dam32101001.click();
        OrderMB3ListPage orderMB3ListPage = new OrderMB3ListPage(driver);
        return orderMB3ListPage;
    }

    public TodoListPage dam31801001Click() {
        dam31801001.click();
        return showTodoListPage();
    }

    public TodoListPage dam31801002Click() {
        dam31801002.click();
        return showTodoListPage();
    }

    public OrderMB3ListPage dam32102002Click() {
        dam32102002.click();
        OrderMB3ListPage orderMB3ListPage = new OrderMB3ListPage(driver);
        return orderMB3ListPage;
    }

}
