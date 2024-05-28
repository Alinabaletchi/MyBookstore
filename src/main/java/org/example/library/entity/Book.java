package org.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.library.enumes.BookType;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String image;
    private String name;

    @Enumerated(EnumType.STRING)
    private BookType bookType;

    private String description;

    private double price;

    private int quantity;

    @ManyToMany
    private List<User> users;

    @ManyToMany
    private List<Cart> cartList=new ArrayList<>();

    @ManyToMany
    private List<Wishlist> wishlists=new ArrayList<>();


}
