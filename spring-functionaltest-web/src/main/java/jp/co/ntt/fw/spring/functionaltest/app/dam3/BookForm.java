/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import java.io.Serializable;
import java.util.Date;

public class BookForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bookId;

    private String categoryName;

    private String title;

    private Integer price;

    private Date releaseDate;

    private String clobCode;

    private String blobCode;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getBlobCode() {
        return blobCode;
    }

    public void setBlobCode(String blobCode) {
        this.blobCode = blobCode;
    }

}
