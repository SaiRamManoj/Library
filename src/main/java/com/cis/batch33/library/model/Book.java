package com.cis.batch33.library.model;

public class Book {

    private int bookId;

    private String bookTitle;

    private String authorName;

    private long yearPublished;

    private long quantity;


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public long getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(long yearPublished) {
        this.yearPublished = yearPublished;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
