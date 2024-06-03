//package com.quiz.controller;
//import com.quiz.entity.UserEntity;
//import com.quiz.service.UserService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class AuthController {
//
//    private UserService userService;
//    private PasswordEncoder passwordEncoder;
//
//    @GetMapping("/register")
//    public String showRegistrationForm(Model model){
//        model.addAttribute("user", new UserEntity());
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute("user") UserEntity user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setEnabled(true);
//        userService.save(user);
//        return "redirect:/login";
//    }
//
//    @GetMapping("/login")
//    public String showLoginForm(){
//        return "login";
//    }
//}
