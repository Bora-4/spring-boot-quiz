package com.quiz.controller;

import com.quiz.dto.QuizResultDTO;
import com.quiz.service.QuizResultService;
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
    public void save(@RequestBody QuizResultDTO quizResultDTO){
        quizResultService.save(quizResultDTO);
    }

    @PutMapping
    public void update(@RequestBody QuizResultDTO quizResultDTO){
        quizResultService.update(quizResultDTO);
    }

    @GetMapping
    public ResponseEntity<List<QuizResultDTO>> findAll(){
        return ResponseEntity.ok(quizResultService.findAll());
    }

    @GetMapping("id/{id}")
    public QuizResultDTO findById(@PathVariable("id") Long id){
        return quizResultService.findById(id);
    }

    @DeleteMapping("id/{id}")
    public void delete(@PathVariable("id") Long id){
        quizResultService.delete(id);
    }
}
