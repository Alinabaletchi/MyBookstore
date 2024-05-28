package org.example.library.serviceInterfaces;

import org.example.library.entity.Cart;
import org.example.library.entity.User;
import org.example.library.entity.Wishlist;

import java.util.List;

public interface CartService {
    Cart createCart();

    Cart getCartByUserEmail(String email);

    Cart getCartById(Long id);

    List<Cart> getAllCarts();
     Cart findByUser(User user);

    void deleteById(Long id);

    Cart addProductToCart(Long id, Long productId);

    Cart removeProductFromCart(Long id, Long productId);

    Cart addBookToCart(Long id, Long bookId);

    Cart removeBookFromCart(Long id, Long bookId);

    Cart addGameToCart(Long id, Long gameId);

    Cart removeGameFromCart(Long id, Long gameId);

}
