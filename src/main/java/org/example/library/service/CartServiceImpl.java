package org.example.library.service;


import org.example.library.entity.*;
import org.example.library.repository.*;
import org.example.library.serviceInterfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByUserEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getCart();
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart findByUser(User user) {
        return cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public List<Cart> getAllCarts() {

        return cartRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart addProductToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product= productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        boolean productExists = cart.getProducts().stream().anyMatch(b -> b.getId().equals(productId));
        if (productExists) {
            throw new RuntimeException("Book already in cart");
        } else {
            cart.getProducts().add(product);
            return cartRepository.save(cart);
        }
    }
    @Override
    public Cart removeProductFromCart(Long id, Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            return null;
        }
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isEmpty()) {
            return null;
        }
        Product product = optionalProduct.get();
        Cart cart = optionalCart.get();
        List<Product> products= cart.getProducts();
        if (!products.contains(product)) {
            return null;
        }
        cart.getProducts().remove(product);
        return cartRepository.save(cart);
    }


    @Override
    public Cart addBookToCart(Long cartId, Long bookId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        boolean bookExists = cart.getBooks().stream().anyMatch(b -> b.getId().equals(bookId));
        if (bookExists) {
            throw new RuntimeException("Book already in cart");
        } else {
            cart.getBooks().add(book);
            return cartRepository.save(cart);
        }
    }


    @Override
    public Cart removeBookFromCart(Long id, Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            return null;
        }
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isEmpty()) {
            return null;
        }
        Book book = optionalBook.get();
        Cart cart = optionalCart.get();
        List<Book> books = cart.getBooks();
        if (!books.contains(book)) {
            return null;
        }
        cart.getBooks().remove(book);
        return cartRepository.save(cart);
    }

    @Override
    public Cart addGameToCart(Long cartId, Long gameId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game ot found"));

        boolean gameExists = cart.getGames().stream().anyMatch(b -> b.getId().equals(gameId));
        if (gameExists) {
            throw new RuntimeException("Game already in cart");
        } else {
            cart.getGames().add(game);
            return cartRepository.save(cart);
        }
    }
    @Override
    public Cart removeGameFromCart(Long id, Long gameId) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isEmpty()) {
            return null;
        }
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isEmpty()) {
            return null;
        }
        Game game= optionalGame.get();
        Cart cart = optionalCart.get();
        List<Game> games = cart.getGames();
        if (!games.contains(game)) {
            return null;
        }
        cart.getGames().remove(game);
        return cartRepository.save(cart);

    }

}
