package org.example.library.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.library.enumes.GameType;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String image;
    @Enumerated(EnumType.STRING)
    private GameType gameType;

    private double price;

    private String description;

    private int quantity;

    @ManyToMany
    private List<User> users;

    @ManyToMany
    private List<Cart> cartList= new ArrayList<>();

    @ManyToMany
    private List <Wishlist> wishlist=new ArrayList<>();

}
