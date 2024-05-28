package org.example.library.validator;

import org.example.library.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
@Component
public class UserValidator {
    public void validate(User user, BindingResult bindingResult){
        if(user.getEmail().isEmpty()){
            FieldError fieldError= new FieldError("user", "name","Name should not be empty! ");
            bindingResult.addError(fieldError);

        }else if (!user.getFirstName().matches("[A-Za-z ]+" +
                "")){
            FieldError fieldError = new FieldError("use", "name", "Name should only contain space and letters");

        }


        if (user.getPassword().length()<5){
            FieldError fieldError= new FieldError("user", "password", "Password most be at least 5 characters long!");
            bindingResult.addError(fieldError);


        }



    }

    public static class EmailAlreadyExistsException extends Throwable {
        public EmailAlreadyExistsException(String email) {
        }
    }

    public static class ValidationException extends Throwable {
        public ValidationException(BindingResult bindingResult) {
        }
    }

}
