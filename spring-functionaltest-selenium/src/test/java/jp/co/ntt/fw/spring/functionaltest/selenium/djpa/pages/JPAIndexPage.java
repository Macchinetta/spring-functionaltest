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

import static jp.co.ntt.fw.spring.functionaltest.selenium.PageOperations.loadNextPage;
import jp.co.ntt.fw.spring.functionaltest.selenium.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JPAIndexPage implements Page<JPAIndexPage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "djpa0101001")
    private WebElement djpa0101001;

    @CacheLookup
    @FindBy(id = "djpa0102001")
    private WebElement djpa0102001;

    @CacheLookup
    @FindBy(id = "djpa0102002")
    private WebElement djpa0102002;

    @CacheLookup
    @FindBy(id = "djpa0103001")
    private WebElement lazyFetchBtnPageIntercept;

    @CacheLookup
    @FindBy(id = "djpa0103002")
    private WebElement lazyFetchBtnPageFilter;

    @CacheLookup
    @FindBy(id = "djpa0103003")
    private WebElement noLazyFetchSettingLink;

    @CacheLookup
    @FindBy(id = "djpa0103004")
    private WebElement registerSession;

    @CacheLookup
    @FindBy(id = "djpa0103005")
    private WebElement registerFlashAttribute;

    @CacheLookup
    @FindBy(id = "djpa0201001")
    private WebElement crudeRepoCheckPage;

    @CacheLookup
    @FindBy(id = "djpa0201002")
    private WebElement paginatedRepoCheckPage;

    @CacheLookup
    @FindBy(id = "djpa0201003")
    private WebElement jpaRepoDefDelAll;

    @CacheLookup
    @FindBy(id = "djpa0201004")
    private WebElement jpaRepoDefExists;

    @CacheLookup
    @FindBy(id = "djpa0201005")
    private WebElement jpaRepoDefDeleteByID;

    @CacheLookup
    @FindBy(id = "djpa0201006")
    private WebElement jpaRepoDefDeleteInBatch;

    @CacheLookup
    @FindBy(id = "djpa0201007")
    private WebElement jpaRepoDefSaveEntityList;

    @CacheLookup
    @FindBy(id = "djpa0201008")
    private WebElement jpaRepoDefCount;

    @CacheLookup
    @FindBy(id = "djpa0201009")
    private WebElement jpaRepoDefSort;

    @CacheLookup
    @FindBy(id = "djpa0201010")
    private WebElement jpaRepoDefIterableDelLink;

    @CacheLookup
    @FindBy(id = "djpa0201011")
    private WebElement jpaRepoDefsaveAndFlushLink;

    @CacheLookup
    @FindBy(id = "djpa0201012")
    private WebElement jpaRepoDefDeleteEntityLink;

    @CacheLookup
    @FindBy(id = "djpa0201013")
    private WebElement jpaRepoDefFlushLink;

    @CacheLookup
    @FindBy(id = "djpa0202001")
    private WebElement jpaCustomRepositoryLink;

    @CacheLookup
    @FindBy(id = "djpa0202002")
    private WebElement jpaNoIntfRepositoryLink;

    @CacheLookup
    @FindBy(id = "djpa0301001")
    private WebElement atQueryAnnotLink;

    @CacheLookup
    @FindBy(id = "djpa0301002")
    private WebElement atQueryAnnotWithLikeSerachLink;

    @CacheLookup
    @FindBy(id = "djpa0302001")
    private WebElement queryUsingMethodNameLink;

    @CacheLookup
    @FindBy(id = "djpa0303001")
    private WebElement queryUsingNativeMehodLink;

    @CacheLookup
    @FindBy(id = "djpa0304002")
    private WebElement queryHintQuerTimeOutLink;

    @CacheLookup
    @FindBy(id = "djpa0402001")
    private WebElement entityPageMatchingCond;

    @CacheLookup
    @FindBy(id = "djpa0501001")
    private WebElement pageSearchDynaParamLink;

    @CacheLookup
    @FindBy(id = "djpa0601001")
    private WebElement findOneByIdLink;

    @CacheLookup
    @FindBy(id = "djpa0601003")
    private WebElement acquiringNotForeignKey;

    @CacheLookup
    @FindBy(id = "djpa0601004")
    private WebElement acquiringForeignKey;

    @CacheLookup
    @FindBy(id = "djpa0602001")
    private WebElement noPrimaryKeySerach;

    @CacheLookup
    @FindBy(id = "djpa0701001")
    private WebElement addEntityLink;

    @CacheLookup
    @FindBy(id = "djpa0702001")
    private WebElement relatedEntityLink;

    @CacheLookup
    @FindBy(id = "djpa0703001")
    private WebElement addOnlyRelatedEntityLink;

    @CacheLookup
    @FindBy(id = "djpa0704001")
    private WebElement addDirectRelatedEntityLink;

    @CacheLookup
    @FindBy(id = "djpa0801001")
    private WebElement updateEntityLink;

    @CacheLookup
    @FindBy(id = "djpa0802001")
    private WebElement updateRelatedEntityUsingParentLink;

    @CacheLookup
    @FindBy(id = "djpa0803001")
    private WebElement updateDirectRelatedEntityLink;

    @CacheLookup
    @FindBy(id = "djpa0804001")
    private WebElement updateByQueryLink;

    @CacheLookup
    @FindBy(id = "djpa0804002")
    private WebElement updateByQueryErrLink;

    @CacheLookup
    @FindBy(id = "djpa0804003")
    private WebElement updateByQueryWithClearLink;

    @CacheLookup
    @FindBy(id = "djpa0901001")
    private WebElement deleteEntityLink;

    @CacheLookup
    @FindBy(id = "djpa0902001")
    private WebElement deleteRelatedEntityLink;

    @CacheLookup
    @FindBy(id = "djpa0903001")
    private WebElement deleteDirectRelatedEntityLink;

    @CacheLookup
    @FindBy(id = "djpa0904001")
    private WebElement deleteRelatedOfManagedParentEntity;

    @CacheLookup
    @FindBy(id = "djpa0905001")
    private WebElement deleteEntityByQueryLink;

    @CacheLookup
    @FindBy(id = "djpa1001001")
    private WebElement escapeSrchModLink;

    @CacheLookup
    @FindBy(id = "djpa1001002")
    private WebElement escapeSrchUnderscoreLink;

    @CacheLookup
    @FindBy(id = "djpa1002001")
    private WebElement escapeMatchLogicLink;

    @CacheLookup
    @FindBy(id = "djpa1101001")
    private WebElement joinFetchLink;

    @CacheLookup
    @FindBy(id = "djpa1201001")
    private WebElement addCustomMethodToIndividualRepo;

    @CacheLookup
    @FindBy(id = "djpa1202001")
    private WebElement addCustomMethodInBunch;

    @CacheLookup
    @FindBy(id = "djpa1301001")
    private WebElement storResultInCustomObjLink;

    @CacheLookup
    @FindBy(id = "djpa1401001")
    private WebElement defAuditPropertyLink;

    @CacheLookup
    @FindBy(id = "djpa1402001")
    private WebElement customAuditPropertyLink;

    @CacheLookup
    @FindBy(id = "djpa1501001")
    private WebElement cmnCondEntityFetchLink;

    @CacheLookup
    @FindBy(id = "djpa1502001")
    private WebElement cmnCondRelatedEntityFetchLink;

    public JPAIndexPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public JPAIndexPage reload() {

        PageFactory.initElements(driver, this);
        return this;
    }

    public JPAHomePage djpa0101001Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        djpa0101001.click();
        return homePage;
    }

    public JPAHomePage djpa0102001Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        djpa0102001.click();
        return homePage;
    }

    public JPAHomePage djpa0102002Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        djpa0102002.click();
        return homePage;
    }

    public JPAHomePage djpa0103001Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        lazyFetchBtnPageIntercept.click();
        return homePage;
    }

    public JPAHomePage djpa0103002Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        lazyFetchBtnPageFilter.click();
        return homePage;
    }

    public JPAHomePage djpa0103003Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        noLazyFetchSettingLink.click();
        return homePage;
    }

    public JPAHomePage djpa0103004Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        registerSession.click();
        return homePage;
    }

    public JPAHomePage djpa0103005Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        registerFlashAttribute.click();
        return homePage;
    }

    public JPAHomePage djpa0201001Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        crudeRepoCheckPage.click();
        return homePage;
    }

    public JPAHomePage djpa0201002Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        paginatedRepoCheckPage.click();
        return homePage;
    }

    public JPAHomePage djpa0201003Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        jpaRepoDefDelAll.click();
        return homePage;
    }

    public JPAHomePage djpa0201004Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        jpaRepoDefExists.click();
        return homePage;
    }

    public JPAHomePage djpa0201005Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        jpaRepoDefDeleteByID.click();
        return homePage;
    }

    public JPAHomePage djpa0201006Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        jpaRepoDefDeleteInBatch.click();
        return homePage;
    }

    public JPAHomePage djpa0201007Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        jpaRepoDefSaveEntityList.click();
        return homePage;
    }

    public JPAHomePage djpa0201008Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        jpaRepoDefCount.click();
        return homePage;
    }

    public JPAHomePage djpa0201009Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        jpaRepoDefSort.click();
        return homePage;
    }

    public JPAHomePage djpa0201010Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        jpaRepoDefIterableDelLink.click();
        return homePage;
    }

    public JPAHomePage djpa0201011Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        jpaRepoDefsaveAndFlushLink.click();
        return homePage;
    }

    public JPAHomePage djpa0201012Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        jpaRepoDefDeleteEntityLink.click();
        return homePage;
    }

    public JPAHomePage djpa0201013Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        jpaRepoDefFlushLink.click();
        return homePage;
    }

    public JPAHomePage djpa0202001Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        jpaCustomRepositoryLink.click();
        return homePage;
    }

    public JPAHomePage djpa0202002Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        jpaNoIntfRepositoryLink.click();
        return homePage;
    }

    public OrderDeliveryListPage djpa0301001Click() {
        OrderDeliveryListPage orderDeliveryPage = new OrderDeliveryListPage(driver);
        atQueryAnnotLink.click();
        return orderDeliveryPage;
    }

    public OrderDeliveryListPage djpa0301002Click() {
        OrderDeliveryListPage orderDeliveryPage = new OrderDeliveryListPage(driver);
        atQueryAnnotWithLikeSerachLink.click();
        return orderDeliveryPage;
    }

    public OrderDeliveryListPage djpa0302001Click() {
        OrderDeliveryListPage orderDeliveryPage = new OrderDeliveryListPage(driver);
        queryUsingMethodNameLink.click();
        return orderDeliveryPage;
    }

    public OrderDeliveryListPage djpa0303001Click() {
        OrderDeliveryListPage orderDeliveryPage = new OrderDeliveryListPage(driver);
        queryUsingNativeMehodLink.click();
        return orderDeliveryPage;
    }

    public OrderDeliveryListPage djpa0304002Click() {
        OrderDeliveryListPage orderDeliveryPage = new OrderDeliveryListPage(driver);
        queryHintQuerTimeOutLink.click();
        return orderDeliveryPage;
    }

    public OrderDeliveryListPage djpa0402001Click() {
        OrderDeliveryListPage orderDeliveryPage = new OrderDeliveryListPage(driver);
        entityPageMatchingCond.click();
        return orderDeliveryPage;
    }

    public OrderDeliveryListPage djpa0501001Click() {
        OrderDeliveryListPage orderDeliveryPage = new OrderDeliveryListPage(driver);
        pageSearchDynaParamLink.click();
        return orderDeliveryPage;
    }

    public JPAHomePage djpa0601001Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        findOneByIdLink.click();
        return homePage;
    }

    public JPAHomePage djpa0601003Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        acquiringNotForeignKey.click();
        return homePage;
    }

    public JPAHomePage djpa0601004Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        acquiringForeignKey.click();
        return homePage;
    }

    public JPAHomePage djpa0602001Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        noPrimaryKeySerach.click();
        return homePage;
    }

    public JPAHomePage djpa0701001Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        addEntityLink.click();
        return homePage;
    }

    public OrdersPage djpa0702001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        relatedEntityLink.click();
        return ordersPage;
    }

    public OrdersPage djpa0703001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        addOnlyRelatedEntityLink.click();
        return ordersPage;
    }

    public OrdersPage djpa0704001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        addDirectRelatedEntityLink.click();
        return ordersPage;
    }

    public OrdersPage djpa0801001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        updateEntityLink.click();
        return ordersPage;
    }

    public OrdersPage djpa0802001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        updateRelatedEntityUsingParentLink.click();
        return ordersPage;
    }

    public OrdersPage djpa0803001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        updateDirectRelatedEntityLink.click();
        return ordersPage;
    }

    public OrdersPage djpa0804001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        updateByQueryLink.click();
        return ordersPage;
    }

    public OrdersPage djpa0804002Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        updateByQueryErrLink.click();
        return ordersPage;
    }

    public OrdersPage djpa0804003Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        updateByQueryWithClearLink.click();
        return ordersPage;
    }

    public OrdersPage djpa0901001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        deleteEntityLink.click();
        return ordersPage;
    }

    public OrdersPage djpa0902001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        deleteRelatedEntityLink.click();
        return ordersPage;
    }

    public OrdersPage djpa0903001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        deleteDirectRelatedEntityLink.click();
        return ordersPage;
    }

    public OrdersPage djpa0904001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        deleteDirectRelatedEntityLink.click();
        return ordersPage;
    }

    public OrdersPage djpa0905001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        deleteEntityByQueryLink.click();
        return ordersPage;
    }

    public OrderDeliveryListPage djpa1001001Click() {
        OrderDeliveryListPage orderDeliveryPage = new OrderDeliveryListPage(driver);
        escapeSrchModLink.click();
        return orderDeliveryPage;
    }

    public OrderDeliveryListPage djpa1001002Click() {
        OrderDeliveryListPage orderDeliveryPage = new OrderDeliveryListPage(driver);
        escapeSrchUnderscoreLink.click();
        return orderDeliveryPage;
    }

    public OrderDeliveryListPage djpa1002001Click() {
        OrderDeliveryListPage orderDeliveryPage = new OrderDeliveryListPage(driver);
        escapeMatchLogicLink.click();
        return orderDeliveryPage;
    }

    public OrdersPage djpa1101001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        joinFetchLink.click();
        return ordersPage;
    }

    public OrderDeliveryListPage djpa1201001Click() {
        OrderDeliveryListPage homePage = new OrderDeliveryListPage(driver);
        addCustomMethodToIndividualRepo.click();
        return homePage;
    }

    public JPAHomePage djpa1202001Click() {
        JPAHomePage homePage = new JPAHomePage(driver);
        addCustomMethodInBunch.click();
        return homePage;
    }

    public OrdersPage djpa1301001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        storResultInCustomObjLink.click();
        return ordersPage;
    }

    public JPAHomePage djpa1401001Click() {
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        defAuditPropertyLink.click();
        return jpaHomePage;
    }

    public JPAHomePage djpa1402001Click() {
        JPAHomePage jpaHomePage = new JPAHomePage(driver);
        customAuditPropertyLink.click();
        return jpaHomePage;
    }

    public OrdersPage djpa1501001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        cmnCondEntityFetchLink.click();
        return ordersPage;
    }

    public OrdersPage djpa1502001Click() {
        OrdersPage ordersPage = new OrdersPage(driver);
        cmnCondRelatedEntityFetchLink.click();
        return ordersPage;
    }

    public <P extends Page<P>> P save(Class<P> nextPage) {
        this.djpa0101001.click();
        return loadNextPage(this, nextPage, driver);
    }

}
