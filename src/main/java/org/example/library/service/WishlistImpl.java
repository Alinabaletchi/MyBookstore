package org.example.library.service;

import org.example.library.entity.*;
import org.example.library.repository.*;
import org.example.library.serviceInterfaces.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistImpl implements WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Wishlist createWishlist() {
        Wishlist wishlist = new Wishlist();
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlistByUserEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getWishlist();
    }

    @Override
    public Wishlist getWishlistById(Long id) {
        return wishlistRepository.findById(id).orElse(null);
    }

    @Override
    public Object findByUser(User user) {
        return wishlistRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public List<Wishlist> getAllWishlist() {

        return wishlistRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        wishlistRepository.deleteById(id);
    }

    @Override
    public Wishlist addProductToWishlist(Long wishlistId, Long productId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new RuntimeException("Wishlist not found"));
        Product product= productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        boolean productExists = wishlist.getProducts().stream().anyMatch(b -> b.getId().equals(productId));
        if (productExists) {
            throw new RuntimeException("Book already in wishlist");
        } else {
            wishlist.getProducts().add(product);
            return wishlistRepository.save(wishlist);
        }
    }
    @Override
    public Wishlist removeProductFromWishlist(Long id, Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            return null;
        }
        Optional<Wishlist> optionalWishlist = wishlistRepository.findById(id);
        if (optionalWishlist.isEmpty()) {
            return null;
        }
        Product product = optionalProduct.get();
        Wishlist wishlist = optionalWishlist.get();
        List<Product> products=wishlist.getProducts();
        if (!products.contains(product)) {
            return null;
        }
        wishlist.getProducts().remove(product);
        return wishlistRepository.save(wishlist);
    }


    @Override
    public Wishlist addBookToWishlist(Long wishlistId, Long bookId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new RuntimeException("Wishlist not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        boolean bookExists = wishlist.getBooks().stream().anyMatch(b -> b.getId().equals(bookId));
        if (bookExists) {
            throw new RuntimeException("Book already in wishlist");
        } else {
            wishlist.getBooks().add(book);
            return wishlistRepository.save(wishlist);
        }
    }


    @Override
    public Wishlist removeBookFromWishlist(Long id, Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            return null;
        }
        Optional<Wishlist> optionalWishlist = wishlistRepository.findById(id);
        if (optionalWishlist.isEmpty()) {
            return null;
        }
        Book book = optionalBook.get();
        Wishlist wishlist= optionalWishlist.get();
        List<Book> books = wishlist.getBooks();
        if (!books.contains(book)) {
            return null;
        }
        wishlist.getBooks().remove(book);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist addGameToWishlist(Long cartId, Long gameId) {
        Wishlist wishlist= wishlistRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Wishlist not found"));
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game ot found"));

        boolean gameExists = wishlist.getGames().stream().anyMatch(b -> b.getId().equals(gameId));
        if (gameExists) {
            throw new RuntimeException("Game already in wishlist");
        } else {
            wishlist.getGames().add(game);
            return wishlistRepository.save(wishlist);
        }
    }
    @Override
    public Wishlist removeGameFromWishlist(Long id, Long gameId) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isEmpty()) {
            return null;
        }
        Optional<Wishlist> optionalWishlist = wishlistRepository.findById(id);
        if (optionalWishlist.isEmpty()) {
            return null;
        }
        Game game= optionalGame.get();
        Wishlist wishlist= optionalWishlist.get();
        List<Game> games = wishlist.getGames();
        if (!games.contains(game)) {
            return null;
        }
        wishlist.getGames().remove(game);
        return wishlistRepository.save(wishlist);

    }

}

