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
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;

@Entity
@Table(name = "t_book_lz")
public class JPABookLZ {

    @Id
    @Column(name = "book_id", nullable = false, length = 10)
    private int bookId;

    @Column(name = "title", nullable = true, length = 1024)
    private String title;

    @Column(name = "price")
    private Integer price;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "clob_code")
    private String clobCode;

    @Column(name = "blob_code")
    private byte[] blobCode;

    @Transient
    private String blobCodeHex;

    /**
     * Following properties are made transient because these are not columns of this entity. However, the jsp used to show the
     * details is in common with the entity that has these properties as columns in DB table. START::
     */
    @Transient
    private long version;

    @Transient
    private String createdBy;

    @Transient
    private DateTime createdDate;

    @Transient
    private String lastModifiedBy;

    @Transient
    private DateTime lastModifiedDate;

    /**
     * Following properties are made transient because these are not columns of this entity. However, the jsp used to show the
     * details is in common with the entity that has these properties as columns in DB table. END::
     */

    @ManyToOne(targetEntity = JPACategoryLZ.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    JPACategoryLZ category;

    public void setCategory(JPACategoryLZ category) {
        this.category = category;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public JPACategoryLZ getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getClobCode() {
        return clobCode;
    }

    public void setClobCode(String clobCode) {
        this.clobCode = clobCode;
    }

    public byte[] getBlobCode() {
        return blobCode;
    }

    public void setBlobCode(byte[] blobCode) {
        this.blobCode = blobCode;
    }

    public String getBlobCodeHex() {
        return blobCodeHex;
    }

    public void setBlobCodeHex(String blobCodeHex) {
        this.blobCodeHex = blobCodeHex;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
