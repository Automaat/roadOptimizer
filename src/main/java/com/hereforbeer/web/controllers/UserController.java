package com.hereforbeer.web.controllers;

import com.hereforbeer.services.UserService;
import com.hereforbeer.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseEntity<Object>(CREATED);
    }

    @RequestMapping(value = "/login", method = POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody UserDTO credentials) {
        userService.login(credentials.getNick(), credentials.getPassword());

        return new ResponseEntity(OK);
    }

    @RequestMapping(value = "/users", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), OK);
    }
}
