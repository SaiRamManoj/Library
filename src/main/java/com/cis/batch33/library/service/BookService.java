package com.cis.batch33.library.service;

import com.cis.batch33.library.entity.Book;
import com.cis.batch33.library.entity.BookIsbn;
import com.cis.batch33.library.model.BookDTO;
import com.cis.batch33.library.model.BookIsbnDTO;
import com.cis.batch33.library.repository.LibraryBookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private LibraryBookRepository bookRepository;

    public BookDTO createBook(BookDTO bookDTO){

        Book lb = new Book();
        lb.setTitle(bookDTO.getTitle());
        lb.setQuantity(bookDTO.getQuantity());
        lb.setAuthorName(bookDTO.getAuthorName());
        lb.setYearPublished(bookDTO.getYearPublished());


        // Example of generating and adding multiple ISBNs
        List<BookIsbn> bookIsbns = new ArrayList<>();
        for (int i = 0; i < bookDTO.getQuantity(); i++) {
            BookIsbn bk = new BookIsbn();
            // Optionally set the ISBN value here, if you have a method to generate or assign it
            // bk.setIsbn(generateIsbn()); // Assuming generateIsbn() is a method to generate an ISBN
            bk.setBook(lb);
            bookIsbns.add(bk);
        }

        // Set the list of ISBNs to the LibraryBook
        lb.setBookIsbns(bookIsbns);

        // Save the LibraryBook (and cascadingly, the BookIsbn(s)) to the database
        Book lbr = bookRepository.save(lb);

        // Update the original book object with the generated bookId
        bookDTO.setBookId(lbr.getBookId());

        // Assuming you want to return a Book object, make sure it reflects any changes
        // For example, if you want to include the generated ISBNs in the returned object, you should update it accordingly
        Optional<Book> bookOptional = bookRepository.findById(lbr.getBookId());
        Book book =
                bookOptional.orElse(new Book());

        BookDTO bookDTO1 = new BookDTO();
        bookDTO1.setBookId(book.getBookId());
        bookDTO1.setTitle(book.getTitle());
        bookDTO1.setAuthorName(book.getAuthorName());
        bookDTO1.setYearPublished(book.getYearPublished());
        bookDTO1.setQuantity(book.getQuantity());

        List<BookIsbnDTO> bookIsbnDTOS =
                book.getBookIsbns().stream().map(b -> {
                    BookIsbnDTO bdo = new BookIsbnDTO();
                    bdo.setIsbn(b.getIsbn());
                    bdo.setBookId(lbr.getBookId());
                    return bdo;
                }).collect(Collectors.toList());

        bookDTO1.setBookIsbns(bookIsbnDTOS);

        return bookDTO1;
    }

    public BookDTO getBook(int bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Book book =
                bookOptional.orElse(new Book());

        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(book.getBookId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthorName(book.getAuthorName());
        bookDTO.setYearPublished(book.getYearPublished());
        bookDTO.setQuantity(book.getQuantity());

        List<BookIsbnDTO> bookIsbnDTOS =
                book.getBookIsbns().stream().map(b -> {
                    BookIsbnDTO bdo = new BookIsbnDTO();
                    bdo.setIsbn(b.getIsbn());
                    bdo.setBookId(bookId);
                    return bdo;
                }).collect(Collectors.toList());

        bookDTO.setBookIsbns(bookIsbnDTOS);

        return bookDTO;
    }

    public Book updateBook(int bookId, Book updatedBook) {
        //return bookRepository.save(updatedBook);
        // Retrieve the existing LibraryBook from the database
        Book existingBook = bookRepository.findById(updatedBook.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        // Update the fields of the existing LibraryBook with the new values
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthorName(updatedBook.getAuthorName());
        existingBook.setYearPublished(updatedBook.getYearPublished());

        int newQuantity = updatedBook.getQuantity();
        int currentQuantity = existingBook.getQuantity();

        // Adjust ISBN records based on the change in quantity
        if (newQuantity > currentQuantity) {
            // Increase ISBN records
            List<BookIsbn> bookIsbns = new ArrayList<>();
            for (int i = 0; i < newQuantity - currentQuantity; i++) {
                BookIsbn newIsbn = new BookIsbn();
                newIsbn.setBook(existingBook);
                existingBook.getBookIsbns().add(newIsbn);
            }
        } else if (newQuantity < currentQuantity) {
            // Decrease ISBN records
            int recordsToRemove = currentQuantity - newQuantity;
            List<BookIsbn> isbnsToRemove = existingBook.getBookIsbns().subList(0, recordsToRemove);
            existingBook.getBookIsbns().removeAll(isbnsToRemove);
        }

        // Update the quantity
        existingBook.setQuantity(newQuantity);

        // Save the updated LibraryBook (and cascadingly, the BookIsbn(s)) to the database
        Book updatedLibraryBook = bookRepository.save(existingBook);

        // Update the original book object with the updated values
        updatedBook.setQuantity(updatedLibraryBook.getQuantity());

        return updatedBook;
    }

    public void deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);
    }
}
