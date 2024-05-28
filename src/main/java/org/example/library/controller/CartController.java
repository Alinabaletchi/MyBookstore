package org.example.library.controller;


import org.example.library.entity.Cart;
import org.example.library.service.UserServiceImpl;
import org.example.library.serviceInterfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin("http://localhost:4200")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserServiceImpl userService;
      @PostMapping
  public Cart createCart() {
    return cartService.createCart();
  }

    @GetMapping
    public ResponseEntity<List<Cart>> getCartList() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        if (null == cart) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/mycart")
    public ResponseEntity<Cart> getMyCart(Authentication authentication) {
        String email = authentication.getName();
        Cart cart = cartService.getCartByUserEmail(email);
        return ResponseEntity.ok(cart);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        if (null == cart) {
            return ResponseEntity.notFound().build();
        }
        cartService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/addProductToCart")
    public ResponseEntity<Cart> addProductToCart(@RequestParam Long cartId, @RequestParam Long productId) {
        try {
            Cart cart = cartService.addProductToCart(cartId, productId);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



    @PatchMapping("/removeProduct")
    public ResponseEntity<Cart> removeProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) {
        Cart cart = cartService.removeProductFromCart(cartId, productId);
        if (null == cart) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }



    @PostMapping("/addBookToCart")
    public ResponseEntity<Cart> addBookToCart(@RequestParam Long cartId, @RequestParam Long bookId) {
        try {
            Cart cart = cartService.addBookToCart(cartId, bookId);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



    @PatchMapping("/removeBook")
    public ResponseEntity<Cart> removeBookFromCart(@RequestParam Long cartId, @RequestParam Long bookId) {
        Cart cart = cartService.removeBookFromCart(cartId, bookId);
        if (null == cart) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }


    @PostMapping("/addGameToCart")
    public ResponseEntity<Cart> addGameToCart(@RequestParam Long cartId, @RequestParam Long gameId) {
        try {
            Cart cart = cartService.addGameToCart(cartId, gameId);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PatchMapping("/removeGame")
    public ResponseEntity<Cart> removeGameFromCart(@RequestParam Long cartId, @RequestParam Long gameId) {
        Cart cart = cartService.removeGameFromCart(cartId, gameId);
        if (null == cart) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }



}
