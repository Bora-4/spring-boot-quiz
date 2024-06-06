package com.quiz.controller;

import com.quiz.dto.QuestionDTO;
import com.quiz.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public void save(@RequestBody QuestionDTO questionDTO){
        questionService.save(questionDTO);
    }

    @PutMapping
    public void update(@RequestBody QuestionDTO questionDTO){
        questionService.update(questionDTO);
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> findAll(){
        return ResponseEntity.ok(questionService.findAll());
    }

    @GetMapping("id/{id}")
    public QuestionDTO findById(@PathVariable ("id") Long id){
        return questionService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        questionService.delete(id);
    }
}
