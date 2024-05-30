package com.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder

public class QuestionDTO {
    private Long id;

    private QuizDTO quiz;

    private String question;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
