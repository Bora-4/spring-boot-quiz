package com.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class QuizResultDTO {
    private Long id;

    private UserDTO user;

    private QuizDTO quiz;

    private Integer score;

    private LocalDateTime completedAt;
}
