package org.example.library.controller;

import org.example.library.entity.Book;
import org.example.library.enumes.BookType;
import org.example.library.serviceInterfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("http://localhost:4200")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/createBooks")
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (null == book) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<Book>> getBooksByBookType(@PathVariable String type) {
        BookType bookType = BookType.valueOf(type);
        List<Book> books = bookService.getBooksByBookType(bookType);
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/allBooks")
    public ResponseEntity<List<Book>> getAllBooks(){

        return ResponseEntity.ok(bookService.getAllBook());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        if (book==null){
            return ResponseEntity.notFound().build();
        }
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        if (null == updatedBook) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedBook);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Book> updatePartialBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updatePartialBook(id, book);
        if (null == updatedBook) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedBook);
    }


}
