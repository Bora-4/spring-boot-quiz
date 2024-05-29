package com.quiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")

public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // eshte best practice per te perdorur tip te dhene Long ne vend te Integer per primary keys

    @Column(nullable = false, unique = true)
    private String name;
}
