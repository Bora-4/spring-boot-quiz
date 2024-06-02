package com.quiz.controller;

import com.quiz.dto.QuizDTO;
import com.quiz.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public void save(@RequestBody QuizDTO quizDTO){
        quizService.save(quizDTO);
    }

    @PutMapping
    public void update(@RequestBody QuizDTO quizDTO){
        quizService.update(quizDTO);
    }

    @GetMapping
    public ResponseEntity<List<QuizDTO>> findAll(){
        return ResponseEntity.ok(quizService.findAll());
    }

    @GetMapping("id/{id}")
    public QuizDTO findById(@PathVariable("id") Long id){
        return quizService.findById(id);
    }

    @DeleteMapping("id/{id}")
    public void delete(@PathVariable("id") Long id){
        quizService.delete(id);
    }
}
