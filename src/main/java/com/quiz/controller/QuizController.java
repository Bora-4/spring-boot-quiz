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
        try {
            quizService.save(quizDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Quiz created successfully.");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: "+ e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody QuizDTO quizDTO){
        try {
            quizService.update(quizDTO);
            return ResponseEntity.ok("Quiz updated successfully.");
        } catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<QuizDTO>> findAll(){
        return ResponseEntity.ok(quizService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> findById(@PathVariable("id") Long id){
        try {
            QuizDTO quizDTO = quizService.findById(id);
            return ResponseEntity.ok(quizDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        try {
            quizService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
