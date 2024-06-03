package com.quiz.controller;

import com.quiz.dto.UserDTO;
import com.quiz.service.UserService;
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
    public void save(@RequestBody UserDTO userDTO){
        userService.save(userDTO);
    }

    @PutMapping
    public void update(@RequestBody UserDTO userDTO){
        userService.update(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("username/{username}")
    public UserDTO findByUsername(@PathVariable("username") String username){
        return userService.findByUsername(username);
    }

    @DeleteMapping("id/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }
}
