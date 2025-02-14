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
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import java.io.Serializable;

public class BookListForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String searchInQueryBookId;

    private String searchLikeQueryTitle;

    private String searchLikeQueryCategoryName;

    private String searchOrderBy;

    private String searchLikeLogicTitle;

    private String searchLikeLogicCategoryName;

    private String searchLikeLogicCondition;

    private String updateWhereTitle;

    private String updateWherePrice;

    private String deleteWhereTitle;

    public String getSearchInQueryBookId() {
        return searchInQueryBookId;
    }

    public void setSearchInQueryBookId(String searchInQueryBookId) {
        this.searchInQueryBookId = searchInQueryBookId;
    }

    public String getSearchLikeQueryTitle() {
        return searchLikeQueryTitle;
    }

    public void setSearchLikeQueryTitle(String searchLikeQueryTitle) {
        this.searchLikeQueryTitle = searchLikeQueryTitle;
    }

    public String getSearchLikeQueryCategoryName() {
        return searchLikeQueryCategoryName;
    }

    public void setSearchLikeQueryCategoryName(String searchLikeQueryCategoryName) {
        this.searchLikeQueryCategoryName = searchLikeQueryCategoryName;
    }

    public String getSearchOrderBy() {
        return searchOrderBy;
    }

    public void setSearchOrderBy(String searchOrderBy) {
        this.searchOrderBy = searchOrderBy;
    }

    public String getSearchLikeLogicTitle() {
        return searchLikeLogicTitle;
    }

    public void setSearchLikeLogicTitle(String searchLikeLogicTitle) {
        this.searchLikeLogicTitle = searchLikeLogicTitle;
    }

    public String getSearchLikeLogicCategoryName() {
        return searchLikeLogicCategoryName;
    }

    public void setSearchLikeLogicCategoryName(String searchLikeLogicCategoryName) {
        this.searchLikeLogicCategoryName = searchLikeLogicCategoryName;
    }

    public String getSearchLikeLogicCondition() {
        return searchLikeLogicCondition;
    }

    public void setSearchLikeLogicCondition(String searchLikeLogicCondition) {
        this.searchLikeLogicCondition = searchLikeLogicCondition;
    }

    public String getUpdateWhereTitle() {
        return updateWhereTitle;
    }

    public void setUpdateWhereTitle(String updateWhereTitle) {
        this.updateWhereTitle = updateWhereTitle;
    }

    public String getUpdateWherePrice() {
        return updateWherePrice;
    }

    public void setUpdateWherePrice(String updateWherePrice) {
        this.updateWherePrice = updateWherePrice;
    }

    public String getDeleteWhereTitle() {
        return deleteWhereTitle;
    }

    public void setDeleteWhereTitle(String deleteWhereTitle) {
        this.deleteWhereTitle = deleteWhereTitle;
    }

}
