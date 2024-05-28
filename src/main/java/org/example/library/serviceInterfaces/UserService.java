package org.example.library.serviceInterfaces;

import org.example.library.entity.User;
import org.example.library.validator.UserValidator;

import java.util.List;

public interface UserService {
    User createUser(User user) throws UserValidator.ValidationException, UserValidator.ValidationException;

    User getUserById(Long id);

    User findByEmail(String email);

    List<User> getAllUsers();

    void deleteAllUsers();

    void deleteById(Long id);

    User updateUser(Long id, User user);

    User updatePartialUser(Long id, User user);

}
