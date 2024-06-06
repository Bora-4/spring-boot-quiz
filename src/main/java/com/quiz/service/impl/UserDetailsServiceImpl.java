//package com.quiz.service.impl;

//import com.quiz.entity.UserEntity;
//import com.quiz.repository.UserRepository;
//import jakarta.transaction.Transactional;
//
//import org.springframework.stereotype.Service;
//
//import java.util.stream.Collectors;
//@Service
//@Transactional
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder
//
//    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    // Load user by username
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        // Return UserDetails with encoded password
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                user.getEnabled(),
//                true, true, true,
//                user.getUserRoles().stream()
//                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().getName()))
//                        .collect(Collectors.toList())
//        );
//    }
//}