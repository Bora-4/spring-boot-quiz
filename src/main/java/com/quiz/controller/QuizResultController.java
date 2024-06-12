package com.quiz.controller;

import com.quiz.dto.QuizResultDTO;
import com.quiz.exceptions.notFound.EntityNotFoundException;
import com.quiz.service.QuizResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz_results")
public class QuizResultController {
    private final QuizResultService quizResultService;

    public QuizResultController(QuizResultService quizResultService) {
        this.quizResultService = quizResultService;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody QuizResultDTO quizResultDTO){

        quizResultService.save(quizResultDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Quiz result saved successfully");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody QuizResultDTO quizResultDTO){

        quizResultService.update(quizResultDTO);
        return ResponseEntity.ok("Quiz result updated successfully");

    }

    @GetMapping
    public ResponseEntity<List<QuizResultDTO>> findAll(){
        return ResponseEntity.ok(quizResultService.findAll());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<QuizResultDTO> findById(@PathVariable("id") Long id){

        QuizResultDTO quizResultDTO = quizResultService.findById(id);
        return ResponseEntity.ok(quizResultDTO);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){

        quizResultService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
