package com.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String token;
    private String tokenExpiryDate;
    private Set<RoleDTO> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
