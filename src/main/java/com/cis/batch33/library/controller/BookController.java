package com.cis.batch33.library.controller;

import com.cis.batch33.library.entity.LibraryBook;
import com.cis.batch33.library.entity.LibraryMember;
import com.cis.batch33.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book/")
public class BookController {
    @Autowired
   private  BookService bookService ;

    @PostMapping
    public LibraryBook createBook(@RequestBody LibraryBook book){
        return bookService.createBook(book);
    }

    @GetMapping
    public LibraryBook getBook(Integer bookId){
        return bookService.getBook(bookId);
    }



    @PutMapping
    public LibraryBook updateBook(@RequestBody LibraryBook book){
        return bookService.updateBook(book);
    }

    @DeleteMapping
    public void deleteBook(Integer bookId){
        bookService.deleteBook(bookId);
    }
}
