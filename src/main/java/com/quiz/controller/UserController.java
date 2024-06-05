package com.quiz.controller;

import com.quiz.dto.CreateUserRequest;
import com.quiz.dto.UserDTO;
import com.quiz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.save(createUserRequest);
        return ResponseEntity.ok("User created successfully");
    }

    @PutMapping
    public void update(@RequestBody UserDTO userDTO){
        userService.update(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("id/{id}")
    public UserDTO findByUsername(@PathVariable("id") Long id){
        return userService.findById(id);
    }

    @DeleteMapping("id/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }
}
