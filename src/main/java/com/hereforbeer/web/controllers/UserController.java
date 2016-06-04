package com.hereforbeer.web.controllers;

import com.hereforbeer.services.UserService;
import com.hereforbeer.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = POST)
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
        return new ResponseEntity<Object>(CREATED);
    }

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<?> login(@RequestBody UserDTO credentials){
        userService.login(credentials.getNick(), credentials.getPassword());

        return new ResponseEntity(OK);
    }
}
