package org.example.library.repository;

import org.example.library.entity.Book;
import org.example.library.enumes.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByBookType(BookType bookType);

    List<Book> findAllByBookType(BookType bookType);


}
