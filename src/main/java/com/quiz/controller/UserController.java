package com.quiz.controller;

import com.quiz.dto.UserDTO;
import com.quiz.exceptions.notFound.EntityNotFoundException;
import com.quiz.exceptions.requirements.PasswordRequirementsException;
import com.quiz.exceptions.requirements.UsernameRequirementsException;
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
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO, @RequestParam Long roleId) {

        userService.save(userDTO, roleId);
        // e kam bere te kthej nje string per ta bere me te qarte ne prezantim qe metodat jane kryer me sukses
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");

    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody UserDTO userDTO){
        userService.update(userDTO);
        return ResponseEntity.ok("User updated successfully");
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("id/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id){
        UserDTO userDTO = userService.findById(id);
        return ResponseEntity.ok(userDTO);

    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable("username") String username) {

        UserDTO userDTO = userService.findByUsername(username);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){

        userService.delete(id);
        return ResponseEntity.ok("User deleted successfully");
    }

//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody UserDTO userDTO, @RequestParam Long roleId) {
//        try {
//            userService.save(userDTO, roleId);
//            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
//        } catch (UsernameRequirementsException | PasswordRequirementsException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register user: " + e.getMessage());
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login() {
//        return ResponseEntity.ok("Logged in successfully");
//    }

}
