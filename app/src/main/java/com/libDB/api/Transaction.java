package com.libDB.api;

import java.sql.Date;

public class Transaction {
    private String bookId;
    private String memberId;
    private Date timeOut;
    private Date timeIn;
    private String branchOut;
    private String branchIn;

    public Transaction() {}

    public Transaction(String bookId, String memberId, Date timeOut, Date timeIn, String branchOut, String branchIn) {
        this.setBookId(bookId);
        this.setMemberId(memberId);
        this.setTimeOut(timeOut);
        this.setTimeIn(timeIn);
        this.setBranchOut(branchOut);
        this.setBranchIn(branchIn);
    }

    /**
     * @return the branchIn
     */
    public String getBranchIn() {
        return branchIn;
    }

    /**
     * @param branchIn the branchIn to set
     */
    public void setBranchIn(String branchIn) {
        this.branchIn = branchIn;
    }

    /**
     * @return the branchOut
     */
    public String getBranchOut() {
        return branchOut;
    }

    /**
     * @param branchOut the branchOut to set
     */
    public void setBranchOut(String branchOut) {
        this.branchOut = branchOut;
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
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the bookId
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}