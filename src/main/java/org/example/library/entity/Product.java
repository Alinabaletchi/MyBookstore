package org.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String image;
    private String name;
    private String description;
    private double price;
    private int quantity;

    @ManyToMany
    private List<User> users;

    @ManyToMany
    private List<Cart> cartList= new ArrayList<>();

    @ManyToMany
    private List <Wishlist> wishlist=new ArrayList<>();


}
