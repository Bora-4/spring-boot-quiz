package com.quiz.service.impl;

//import com.quiz.entity.MyUserDetails;
//import com.quiz.entity.UserEntity;
//import com.quiz.repository.UserRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@Transactional
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private final UserRepository userRepository;
//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//        UserEntity user = userRepository.findByUsername(username);
//        if(user == null){
//            throw new UsernameNotFoundException("Could not find user");
//        }
//        return new MyUserDetails(user);
//    }
//}
