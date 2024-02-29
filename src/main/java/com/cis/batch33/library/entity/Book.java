package com.cis.batch33.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name="book")
@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private int bookId;

    @Column(name="title")
    private String title;

    @Column(name="author_name")
    private String authorName;

    @Column(name="year_published")
    private int yearPublished;

    @Column(name="quantity")
    private int quantity;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookIsbn> bookIsbns;

}
