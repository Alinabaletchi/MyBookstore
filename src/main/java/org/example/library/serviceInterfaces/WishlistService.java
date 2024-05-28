package org.example.library.serviceInterfaces;

import org.example.library.entity.Cart;
import org.example.library.entity.Game;
import org.example.library.entity.User;
import org.example.library.entity.Wishlist;

import java.util.List;

public interface WishlistService {
    Wishlist createWishlist();
    Wishlist getWishlistByUserEmail(String email);

    Wishlist getWishlistById(Long id);

    Object findByUser(User user);
    List<Wishlist> getAllWishlist();

    void deleteById(Long id);

    Wishlist addProductToWishlist(Long id, Long productId);

    Wishlist removeProductFromWishlist(Long id, Long productId);

    Wishlist addBookToWishlist(Long id, Long bookId);

    Wishlist removeBookFromWishlist(Long id, Long bookId);

    Wishlist addGameToWishlist(Long id, Long gameId);

    Wishlist removeGameFromWishlist(Long id, Long gameId);
}
