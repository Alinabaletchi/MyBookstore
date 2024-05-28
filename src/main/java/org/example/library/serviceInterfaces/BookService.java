package org.example.library.serviceInterfaces;

import org.example.library.entity.Book;
import org.example.library.enumes.BookType;

import java.util.List;

public interface BookService {
    Book createBook(Book book);

    Book getBookById(Long id);

    List<Book> getBooksByBookType(BookType bookType);

    List<Book> getAllBook();

    void deleteById(Long id);

    Book updateBook(Long id, Book book);

    Book updatePartialBook(Long id, Book book);
}
