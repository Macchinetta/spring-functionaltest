/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bookId;

    private Category category;

    private String title;

    private Integer price;

    private Date releaseDate;

    private String clobCode;

    private byte[] blobCode;

    private String blobCodeHex;

    public Book() {
        category = new Category();
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        if (blobCode == null) {
            this.blobCode = null;
        } else {
            this.blobCode = Arrays.copyOf(blobCode, blobCode.length);
        }
    }

    public String getBlobCodeHex() {
        return blobCodeHex;
    }

    public void setBlobCodeHex(String blobCodeHex) {
        this.blobCodeHex = blobCodeHex;
    }

}
