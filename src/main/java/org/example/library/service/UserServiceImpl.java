package org.example.library.service;

import org.example.library.entity.Book;
import org.example.library.entity.User;
import org.example.library.repository.UserRepository;
import org.example.library.serviceInterfaces.UserService;
import org.example.library.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;

    @Override
    public User createUser(User user) throws UserValidator.ValidationException {
    BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
    userValidator.validate(user, bindingResult);

    if (bindingResult.hasErrors()) {
        throw new UserValidator.ValidationException(bindingResult);
    }

    return userRepository.save(user);
}


    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();

    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            User updateUser = foundUser.get();
            updateUser.setFirstName(user.getFirstName());
            updateUser.setEmail(user.getEmail());
            updateUser.setPassword(user.getPassword());
            updateUser.setRole(user.getRole());
            return userRepository.save(updateUser);
        }
        else {

            return null;
        }
    }

    @Override
    public User updatePartialUser(Long id, User user) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            User updateUser = foundUser.get();
            if (null != user.getFirstName()) {
                updateUser.setFirstName(user.getFirstName());
            }
            if (null != user.getEmail()) {
                updateUser.setEmail(user.getEmail());
            }
            if (null != user.getPassword()) {
                updateUser.setPassword(user.getPassword());
            }
            if (null != user.getRole()) {
                updateUser.setRole(user.getRole());
            }
            return userRepository.save(updateUser);
        }
        else {
            return null;
        }
    }



}
