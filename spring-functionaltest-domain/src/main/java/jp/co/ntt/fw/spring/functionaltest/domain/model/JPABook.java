/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
@Table(name = "t_book")
public class JPABook {

    @Id
    @Column(name = "book_id", nullable = false, length = 10)
    private String bookId;

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

    public JPABook() {

    }

    @ManyToOne(targetEntity = JPACategory.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    JPACategory category;

    /*
     * @ManyToOne( targetEntity=JPACategory.class , fetch=FetchType.LAZY)
     * @JoinColumn( name="category_id" )
     */

    public String getBookId() {
        return bookId;
    }

    public void setCategory(JPACategory category) {
        this.category = category;
    }

    public JPACategory getCategory() {
        return category;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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
