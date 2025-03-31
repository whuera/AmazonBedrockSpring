package com.example.demo.controller;

import com.example.demo.service.AIService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/generate")
    public String generateText(@RequestBody String prompt) {
        return aiService.generateText(prompt);
    }

    @PostMapping("/embed")
    public List<Double> generateEmbedding(@RequestBody String text) {
        return aiService.generateEmbedding(text);
    }

    @PostMapping("/embed/batch")
    public List<List<Double>> generateEmbeddings(@RequestBody List<String> texts) {
        return aiService.generateEmbeddings(texts);
    }
}