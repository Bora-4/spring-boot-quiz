package com.quiz.controller;

import com.quiz.dto.QuizDTO;
import com.quiz.exceptions.notFound.EntityNotFoundException;
import com.quiz.service.QuizService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> save(@RequestBody QuizDTO quizDTO){
            quizService.save(quizDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Quiz created successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody QuizDTO quizDTO){

        quizService.update(quizDTO);
        return ResponseEntity.ok("Quiz updated successfully.");
    }

    @GetMapping
    public ResponseEntity<List<QuizDTO>> findAll(){
        return ResponseEntity.ok(quizService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> findById(@PathVariable("id") Long id){

        QuizDTO quizDTO = quizService.findById(id);
        return ResponseEntity.ok(quizDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){

        quizService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
