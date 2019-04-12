package com.libDB.api.entity;

import java.sql.Date;

public class Transaction {
    private String bookTitle;
    private String bookAuthor;
    private Date timeOut;
    private Date timeIn;
    private String branchAddressOut;
    private String branchAddressIn;

    public Transaction() {}

    public Transaction(String bookTitle, String bookAuthor, Date timeOut, Date timeIn, String branchAddressOut, String branchAddressIn) {
        this.setBookTitle(bookTitle);
        this.setBookAuthor(bookAuthor);
        this.setTimeOut(timeOut);
        this.setTimeIn(timeIn);
        this.setBranchAddressOut(branchAddressOut);
        this.setBranchAddressIn(branchAddressIn);
    }

    /**
     * @return the branchAddressIn
     */
    public String getBranchAddressIn() {
        return branchAddressIn;
    }

    /**
     * @param branchAddressIn the branchAddressIn to set
     */
    public void setBranchAddressIn(String branchAddressIn) {
        this.branchAddressIn = branchAddressIn;
    }

    /**
     * @return the branchAddressOut
     */
    public String getBranchAddressOut() {
        return branchAddressOut;
    }

    /**
     * @param branchAddressOut the branchAddressOut to set
     */
    public void setBranchAddressOut(String branchAddressOut) {
        this.branchAddressOut = branchAddressOut;
    }

    /**
     * @return the timeIn
     */
    public Date getTimeIn() {
        return timeIn;
    }

    /**
     * @param timeIn the timeIn to set
     */
    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }

    /**
     * @return the timeOut
     */
    public Date getTimeOut() {
        return timeOut;
    }

    /**
     * @param timeOut the timeOut to set
     */
    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * @return the bookTitle
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     * @param bookId the bookTitle to set
     */
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /**
     * @return the bookAuthor
     */
    public String getBookAuthor() {
        return bookAuthor;
    }

    /**
     * @param bookAuthor the bookAuthor to set
     */
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
}