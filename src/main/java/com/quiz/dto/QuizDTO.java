package com.quiz.dto;

import com.quiz.entity.QuestionEntity;
import com.quiz.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class QuizDTO {

    private Long id;

    private String title;

    private UserDTO user;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
