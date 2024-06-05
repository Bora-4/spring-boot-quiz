package com.quiz.controller.rest;

import com.quiz.dto.OptionDTO;
import com.quiz.service.OptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/options")
public class OptionController {
    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @PostMapping
    public void save(@RequestBody OptionDTO optionDTO){
        optionService.save(optionDTO);
    }

    @PutMapping
    public void update(@RequestBody OptionDTO optionDTO){
        optionService.update(optionDTO);
    }

    @GetMapping
    public ResponseEntity<List<OptionDTO>> findAll(){
        return ResponseEntity.ok(optionService.findAll());
    }

    @GetMapping("id/{id}")
    public OptionDTO findById(@PathVariable("id") Long id){
        return optionService.findById(id);
    }

    @DeleteMapping("id/{id}")
    public void delete(@PathVariable("id") Long id){
        optionService.delete(id);
    }


}
