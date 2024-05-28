package org.example.library.controller;

import org.example.library.entity.User;
import org.example.library.serviceInterfaces.CartService;
import org.example.library.serviceInterfaces.RegisterService;
import org.example.library.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@CrossOrigin("http://localhost:4200")

public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user){
        try {
            registerService.registerUser(user);
            return ResponseEntity.ok().build();
        } catch (UserValidator.ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserValidator.EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
