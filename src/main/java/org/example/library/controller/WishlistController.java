package org.example.library.controller;

import org.example.library.entity.Cart;
import org.example.library.entity.Game;
import org.example.library.entity.Wishlist;
import org.example.library.serviceInterfaces.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin("http://localhost:4200")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @PostMapping
    public Wishlist createWishlist() {
        return wishlistService.createWishlist();
    }

    @GetMapping
    public ResponseEntity<List<Wishlist>> getWishlistList() {

        return ResponseEntity.ok(wishlistService.getAllWishlist());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable Long id) {
        Wishlist wishlist = wishlistService.getWishlistById(id);
        if (null == wishlist) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wishlist);
    }

    @GetMapping("/mywishlist")
    public ResponseEntity<Wishlist> getMyWishlist(Authentication authentication) {
        String email = authentication.getName();
        Wishlist wishlist = wishlistService.getWishlistByUserEmail(email);
        return ResponseEntity.ok(wishlist);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        Wishlist wishlist = wishlistService.getWishlistById(id);
        if (null == wishlist) {
            return ResponseEntity.notFound().build();
        }
        wishlistService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/addProductToWishlist")
    public ResponseEntity<Wishlist> addProductToWishlist(@RequestParam Long wishlistId, @RequestParam Long productId) {
        try {
            Wishlist wishlist= wishlistService.addProductToWishlist(wishlistId, productId);
            return ResponseEntity.ok(wishlist);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



    @PatchMapping("/removeProduct")
    public ResponseEntity<Wishlist> removeProductFromWishlist(@RequestParam Long wishlistId, @RequestParam Long productId) {
        Wishlist wishlist = wishlistService.removeProductFromWishlist(wishlistId, productId);
        if (null == wishlist) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wishlist);
    }



    @PostMapping("/addBookToWishlist")
    public ResponseEntity<Wishlist> addBookToWishlist(@RequestParam Long wishlistId, @RequestParam Long bookId) {
        try {
            Wishlist wishlist = wishlistService.addBookToWishlist(wishlistId, bookId);
            return ResponseEntity.ok(wishlist);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



    @PatchMapping("/removeBook")
    public ResponseEntity<Wishlist> removeBookFromWishlist(@RequestParam Long wishlistId, @RequestParam Long bookId) {
        Wishlist wishlist = wishlistService.removeBookFromWishlist(wishlistId, bookId);
        if (null == wishlist) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wishlist);
    }


    @PostMapping("/addGameToWishlist")
    public ResponseEntity<Wishlist> addGameToWishlist(@RequestParam Long wishlistId, @RequestParam Long gameId) {
        try {
            Wishlist wishlist= wishlistService.addGameToWishlist(wishlistId, gameId);
            return ResponseEntity.ok(wishlist);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PatchMapping("/removeGame")
    public ResponseEntity<Wishlist> removeGameFromWishlist(@RequestParam Long wishlistId, @RequestParam Long gameId) {
        Wishlist wishlist = wishlistService.removeGameFromWishlist(wishlistId, gameId);
        if (null == wishlist) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wishlist);
    }


}
