package com.quiz.controller.rest;

import com.quiz.dto.QuizDTO;
import com.quiz.dto.UserDTO;
import com.quiz.exceptions.notFound.EntityNotFoundException;
import com.quiz.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @PutMapping
    public ResponseEntity<String> update(@RequestBody QuizDTO quizDTO){
        try {
            quizService.update(quizDTO);
            return ResponseEntity.ok("Quiz updated succesfully.");
        } catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " +e.getMessage());
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: "
                    + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<QuizDTO>> findAll(){
        return ResponseEntity.ok(quizService.findAll());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<QuizDTO> findById(@PathVariable("id") Long id){
        try {
            QuizDTO quizDTO = quizService.findById(id);
            return ResponseEntity.ok(quizDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        try {
            quizService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
