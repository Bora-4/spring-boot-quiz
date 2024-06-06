package com.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.dto.QuizDTO;
import com.quiz.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class QuizControllerTest {

    @Mock
    private QuizService quizService;

    @InjectMocks
    private QuizController quizController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();
    }

    @Test
    void testFindAllQuizzes() throws Exception {
        when(quizService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/quizzes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testFindQuizById() throws Exception {
        long quizId = 1L;
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setId(quizId);
        quizDTO.setTitle("Sample Quiz");

        when(quizService.findById(quizId)).thenReturn(quizDTO);

        mockMvc.perform(get("/quizzes/{id}", quizId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(quizId))
                .andExpect(jsonPath("$.title").value("Sample Quiz"));
    }

    @Test
    void testSaveQuiz() throws Exception {
        String quizJson = objectMapper.writeValueAsString(new QuizDTO()); // Provide appropriate QuizDTO object
        mockMvc.perform(post("/quizzes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(quizJson))
                .andExpect(status().isCreated()); // Change status to isCreated() for 201
    }


    @Test
    void testUpdateQuiz() throws Exception {
        long quizId = 1L;
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setId(quizId);
        quizDTO.setTitle("Updated Quiz");

        mockMvc.perform(put("/quizzes/{id}", quizId)
                        .content(objectMapper.writeValueAsString(quizDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(quizService, times(1)).update(any(QuizDTO.class));
    }

    @Test
    void testDeleteQuiz() throws Exception {
        mockMvc.perform(delete("/quizzes/{id}", 1L)) // Assuming you're deleting quiz with ID 1
                .andExpect(status().isNoContent()); // Change status to isNoContent() for 204
    }
}
