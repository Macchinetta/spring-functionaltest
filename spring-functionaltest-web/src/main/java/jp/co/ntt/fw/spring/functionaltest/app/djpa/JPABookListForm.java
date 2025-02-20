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
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import java.io.Serializable;

public class JPABookListForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String searchInQueryBookIdFilter;

    private String searchInQueryBookIdIntercept;

    private String searchInQueryBookIdNoLazy;

    private String searchInQueryBookIdAndRegisterSession;

    private String searchInQueryBookIdAndRegisterFlashAttribute;

    private String searchInQueryBookIdAcquiringNotForeignKey;

    private String searchInQueryBookIdAcquiringForeignKey;

    private String bookId;

    private String bookIdSrch;

    private String bookIdDelOpnInput;

    private String searchOrderBy;

    private String bookTitle;

    private Integer sleepTime;

    public Integer getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Integer sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getSearchOrderBy() {
        return searchOrderBy;
    }

    public void setSearchOrderBy(String searchOrderBy) {
        this.searchOrderBy = searchOrderBy;
    }

    public String getBookIdDelOpnInput() {
        return bookIdDelOpnInput;
    }

    public void setBookIdDelOpnInput(String bookIdDelOpnInput) {
        this.bookIdDelOpnInput = bookIdDelOpnInput;
    }

    public String getBookIdSrch() {
        return bookIdSrch;
    }

    public void setBookIdSrch(String bookIdSrch) {
        this.bookIdSrch = bookIdSrch;
    }

    public String getSearchInQueryBookIdFilter() {
        return searchInQueryBookIdFilter;
    }

    public void setSearchInQueryBookIdFilter(String searchInQueryBookIdFilter) {
        this.searchInQueryBookIdFilter = searchInQueryBookIdFilter;
    }

    public String getSearchInQueryBookIdIntercept() {
        return searchInQueryBookIdIntercept;
    }

    public void setSearchInQueryBookIdIntercept(String searchInQueryBookIdIntercept) {
        this.searchInQueryBookIdIntercept = searchInQueryBookIdIntercept;
    }

    public String getSearchInQueryBookIdNoLazy() {
        return searchInQueryBookIdNoLazy;
    }

    public void setSearchInQueryBookIdNoLazy(String searchInQueryBookIdNoLazy) {
        this.searchInQueryBookIdNoLazy = searchInQueryBookIdNoLazy;
    }

    public String getSearchInQueryBookIdAndRegisterSession() {
        return searchInQueryBookIdAndRegisterSession;
    }

    public void setSearchInQueryBookIdAndRegisterSession(
            String searchInQueryBookIdAndRegisterSession) {
        this.searchInQueryBookIdAndRegisterSession = searchInQueryBookIdAndRegisterSession;
    }

    public String getSearchInQueryBookIdAndRegisterFlashAttribute() {
        return searchInQueryBookIdAndRegisterFlashAttribute;
    }

    public void setSearchInQueryBookIdAndRegisterFlashAttribute(
            String searchInQueryBookIdAndRegisterFlashAttribute) {
        this.searchInQueryBookIdAndRegisterFlashAttribute =
                searchInQueryBookIdAndRegisterFlashAttribute;
    }

    public String getSearchInQueryBookIdAcquiringNotForeignKey() {
        return searchInQueryBookIdAcquiringNotForeignKey;
    }

    public void setSearchInQueryBookIdAcquiringNotForeignKey(
            String searchInQueryBookIdAcquiringNotForeignKey) {
        this.searchInQueryBookIdAcquiringNotForeignKey = searchInQueryBookIdAcquiringNotForeignKey;
    }

    public String getSearchInQueryBookIdAcquiringForeignKey() {
        return searchInQueryBookIdAcquiringForeignKey;
    }

    public void setSearchInQueryBookIdAcquiringForeignKey(
            String searchInQueryBookIdAcquiringForeignKey) {
        this.searchInQueryBookIdAcquiringForeignKey = searchInQueryBookIdAcquiringForeignKey;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

}
