package org.example.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.example.library.repository.BookRepository;
import org.example.library.service.BookServiceImpl;
import org.example.library.serviceInterfaces.BookService;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


    @ManyToMany
    private List<Product> products;

    @ManyToMany
    private List<Book> books= new ArrayList<>();

    @ManyToMany
    private List<Game> games=new ArrayList<>();


}
