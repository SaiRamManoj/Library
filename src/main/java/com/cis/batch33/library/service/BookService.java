package com.cis.batch33.library.service;

import com.cis.batch33.library.entity.BookIsbn;
import com.cis.batch33.library.entity.LibraryBook;
import com.cis.batch33.library.model.Book;
import com.cis.batch33.library.model.BookIsbnDTO;
import com.cis.batch33.library.model.CheckoutDTO;
import com.cis.batch33.library.repository.LibraryBookRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BookService {
    //private Map<Long, Book> bookMap = new HashMap<>();
    @Autowired
    private LibraryBookRepository bookRepository;
    public BookService(LibraryBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Book createBook(Book book){

        // call the database
        //Integer bookId = new Random().nextInt();
        //book.setBookId(bookId);
        //bookMap.put(bookId, book);
        /*LibraryBook libraryBook = new LibraryBook();
        book.setBookId(libraryBook.getBookId());
        book.setTitle(libraryBook.getTitle());
        book.setAuthorName(libraryBook.getAuthorName());
        book.setYearPublished(libraryBook.getYearPublished());
        book.setQuantity(libraryBook.getQuantity());*/

//        return  bookRepository.save(book);
        // Create a new LibraryBook object
        LibraryBook libraryBook = new LibraryBook();

        // Manually map properties from Book to LibraryBook
        BeanUtils.copyProperties(book, libraryBook);

        // Map BookIsbn objects from Book to LibraryBook
        List<BookIsbn> bookIsbns = book.getBookIsbns().stream().map(bi -> {
            BookIsbn bookIsbn = new BookIsbn();
            BeanUtils.copyProperties(bi, bookIsbn);
            bookIsbn.setLibraryBook(libraryBook); // Set LibraryBook reference for each BookIsbn
            return bookIsbn;
        }).collect(Collectors.toList());

        // Set the BookIsbn list to the LibraryBook
        libraryBook.setBookIsbns(bookIsbns);

        // Save the LibraryBook to the database
        LibraryBook savedLibraryBook = bookRepository.save(libraryBook);

        // Create a new Book object to return
        Book savedBook = new Book();

        // Manually map properties from saved LibraryBook to Book
        BeanUtils.copyProperties(savedLibraryBook, savedBook);

        // Manually map BookIsbn objects from saved LibraryBook to Book
        List<BookIsbnDTO> savedBookIsbns = savedLibraryBook.getBookIsbns().stream().map(savedBi -> {
            BookIsbnDTO savedBiDto = new BookIsbnDTO();
            BeanUtils.copyProperties(savedBi, savedBiDto);
            return savedBiDto;
        }).collect(Collectors.toList());

        // Set the BookIsbnDTO list to the Book
        savedBook.setBookIsbns(savedBookIsbns);

        return savedBook;
    }

    public Book getBook(Integer bookId) {
        Optional<LibraryBook> bookOptional =
                bookRepository.findById(bookId);
        LibraryBook libraryBook =
                bookOptional.orElse(new LibraryBook());

        Book book = new Book();
        book.setBookId(libraryBook.getBookId());
        book.setTitle(libraryBook.getTitle());
        book.setAuthorName(libraryBook.getAuthorName());
        book.setYearPublished(libraryBook.getYearPublished());
        book.setQuantity(libraryBook.getQuantity());

        List<BookIsbnDTO> bookIsbnDTOS =
                libraryBook.getBookIsbns().stream().map(b -> {
                    BookIsbnDTO bdo = new BookIsbnDTO();
                    bdo.setIsbn(b.getIsbn());
                    bdo.setBookId(b.getBookId());
                    return bdo;
                }).collect(Collectors.toList());

        /*BookIsbnDTO bookIsbnDTO = new BookIsbnDTO();
        bookIsbnDTO.setBookIsbn(libraryBook.getBookIsbn().getIsbn());
        bookIsbnDTO.setBookId(libraryBook.getBookIsbn().getBookId());
*/
        book.setBookIsbns(bookIsbnDTOS);

        return book;
    }

    public Book updateBook(Book book) {
        //Integer bookId = LibraryBook.getBookId();
        //bookMap.put(bookId, book);
        //return bookRepository.save(LibraryBook);

        // Create a new LibraryBook object
        LibraryBook libraryBook = new LibraryBook();

        // Manually map properties from Book to LibraryBook
        BeanUtils.copyProperties(book, libraryBook);

        // Map BookIsbn objects from Book to LibraryBook
        List<BookIsbn> bookIsbns = book.getBookIsbns().stream().map(bi -> {
            BookIsbn bookIsbn = new BookIsbn();
            BeanUtils.copyProperties(bi, bookIsbn);
            bookIsbn.setLibraryBook(libraryBook); // Set LibraryBook reference for each BookIsbn
            return bookIsbn;
        }).collect(Collectors.toList());

        // Set the BookIsbn list to the LibraryBook
        libraryBook.setBookIsbns(bookIsbns);

        // Save the LibraryBook to the database
        LibraryBook savedLibraryBook = bookRepository.save(libraryBook);

        // Create a new Book object to return
        Book savedBook = new Book();

        // Manually map properties from saved LibraryBook to Book
        BeanUtils.copyProperties(savedLibraryBook, savedBook);

        // Manually map BookIsbn objects from saved LibraryBook to Book
        List<BookIsbnDTO> savedBookIsbns = savedLibraryBook.getBookIsbns().stream().map(savedBi -> {
            BookIsbnDTO savedBiDto = new BookIsbnDTO();
            BeanUtils.copyProperties(savedBi, savedBiDto);
            return savedBiDto;
        }).collect(Collectors.toList());

        // Set the BookIsbnDTO list to the Book
        savedBook.setBookIsbns(savedBookIsbns);

        return savedBook;
    }

    public void deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);
    }
}
