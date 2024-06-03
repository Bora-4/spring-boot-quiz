//package com.quiz.configuration;

//import com.quiz.service.impl.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//public class SecurityConfiguration {
//
//    UserDetailsServiceImpl userDetailsService;
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider().setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> auth
//                .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
//                .requestMatchers("/new").hasAnyAuthority("ADMIN")
//                .requestMatchers("/register").hasAnyAuthority("USER", "ADMIN")
//                .requestMatchers("/edit/**").hasAnyAuthority("ADMIN")
//                .requestMatchers("/delete/**").hasAnyAuthority("ADMIN")
//                .anyRequest().authenticated()
//        )
//                .formLogin(login -> login.permitAll())
//                .logout(logout -> logout.permitAll())
//                .exceptionHandling(eh -> eh.accessDeniedPage("/403"))
//        ;
//
//        return http.build();
//
//    }
//
//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//}
