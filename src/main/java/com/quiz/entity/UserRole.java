package com.quiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "id=" + id +
                ", user=" + user +
                ", role=" + role +
                '}';
    }
}
