package com.cis.batch33.library.model;

import lombok.Data;

import java.util.List;

@Data
public class BookDTO {

    private int bookId;
    private String title;
    private String authorName;
    private int yearPublished;
    private int quantity;

    private List<BookIsbnDTO> bookIsbns;


}
