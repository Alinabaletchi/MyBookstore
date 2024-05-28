package org.example.library.service;

import org.example.library.entity.Cart;
import org.example.library.entity.User;
import org.example.library.entity.Wishlist;
import org.example.library.repository.CartRepository;
import org.example.library.repository.UserRepository;
import org.example.library.repository.WishlistRepository;
import org.example.library.serviceInterfaces.RegisterService;
import org.example.library.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public User registerUser(User user) throws UserValidator.ValidationException, UserValidator.EmailAlreadyExistsException {
        BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new UserValidator.ValidationException(bindingResult);
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserValidator.EmailAlreadyExistsException("Email already exists: " + user.getEmail());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(savedUser);
        cartRepository.save(cart);

        Wishlist wishlist= new Wishlist();
        wishlist.setUser(savedUser);
        wishlistRepository.save(wishlist);

        return savedUser;
    }

}
