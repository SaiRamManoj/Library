package com.cis.batch33.library.service;

import com.cis.batch33.library.entity.LibraryBook;
import com.cis.batch33.library.entity.LibraryMember;
import com.cis.batch33.library.repository.LibraryBookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class BookService {

    @Autowired
    private LibraryBookRepository bookRepository;

    public LibraryBook createBook(LibraryBook book){

        // call the database
        Integer memberId = Math.abs(new Random().nextInt());
        book.setBookId(memberId);

        return bookRepository.save(book);
    }
    public LibraryBook getBook(Integer memberId) {

        Optional<LibraryBook> memberOptional =
                bookRepository.findById(memberId);
        return  memberOptional.orElse(new LibraryBook());

    }

    public LibraryBook updateBook(LibraryBook book){
        return bookRepository.save(book);
    }

    @Transactional
    public void  deleteBook(Integer bookId){
        bookRepository.deleteById(bookId);
        //return "Book deleted";
    }
}
