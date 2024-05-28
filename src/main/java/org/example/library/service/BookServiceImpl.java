package org.example.library.service;

import org.example.library.entity.Book;
import org.example.library.enumes.BookType;
import org.example.library.repository.BookRepository;
import org.example.library.serviceInterfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Book> getBooksByBookType(BookType bookType) {
        return bookRepository.findAllByBookType(bookType);
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);

    }

    @Override
    public Book updateBook(Long id, Book book) {
        Optional<Book> foundBook = bookRepository.findById(id);
        if (foundBook.isPresent()) {
            Book updateBook = foundBook.get();
            updateBook.setName(book.getName());
            updateBook.setDescription(book.getDescription());
            updateBook.setPrice(book.getPrice());
            updateBook.setQuantity(book.getQuantity());
            updateBook.setImage(book.getImage());
            return bookRepository.save(updateBook);
        } else {

            return null;
        }
    }

    @Override
    public Book updatePartialBook(Long id, Book book) {
        Optional<Book> foundBook = bookRepository.findById(id);
        if (foundBook.isPresent()) {
            Book updateBook = foundBook.get();
            if (null != book.getName()) {
                updateBook.setName(book.getName());
            }
            if (null != book.getDescription()) {
                updateBook.setDescription(book.getDescription());
            }
            if (0 != book.getPrice()) {
                updateBook.setPrice(book.getPrice());
            }
            if (0 != book.getQuantity()) {
                updateBook.setQuantity(book.getQuantity());
            }
            if (null != book.getImage()) {
                updateBook.setImage(book.getImage());
            }
            return bookRepository.save(updateBook);
        } else {
            return null;
        }
    }
}
