package org.example.library.serviceInterfaces;

import org.example.library.entity.User;
import org.example.library.validator.UserValidator;

public interface RegisterService {
    User registerUser(User user) throws UserValidator.ValidationException, UserValidator.EmailAlreadyExistsException, UserValidator.EmailAlreadyExistsException;

}
