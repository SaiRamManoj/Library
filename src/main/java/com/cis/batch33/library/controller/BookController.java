package com.cis.batch33.library.controller;

import com.cis.batch33.library.entity.Book;
import com.cis.batch33.library.model.BookDTO;
import com.cis.batch33.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book/")
public class BookController {
    @Autowired
   private  BookService bookService ;

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO){
        return bookService.createBook(bookDTO);
    }
    @GetMapping
    public BookDTO getBook(Integer bookId){
        return bookService.getBook(bookId);
    }



    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable int bookId, @RequestBody Book updatedBook){
        // Set the memberId for the member object to be updated
        return bookService.updateBook(bookId, updatedBook);
    }

    @DeleteMapping
    public void deleteBook(Integer bookId){
        bookService.deleteBook(bookId);
    }
}
