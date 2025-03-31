package com.example.demo.controller;

import com.example.demo.service.AIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AIController.class)
class AIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AIService aiService;

    @Test
    void generateText_ShouldReturnGeneratedText() throws Exception {
        String expectedResponse = "Generated text";
        when(aiService.generateText(any(String.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/ai/generate")
                .contentType(MediaType.TEXT_PLAIN)
                .content("Test prompt"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void generateEmbedding_ShouldReturnEmbedding() throws Exception {
        List<Double> expectedEmbedding = List.of(0.1, 0.2, 0.3);
        when(aiService.generateEmbedding(any(String.class))).thenReturn(expectedEmbedding);

        mockMvc.perform(post("/api/ai/embed")
                .contentType(MediaType.TEXT_PLAIN)
                .content("Test text"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(0.1))
                .andExpect(jsonPath("$[1]").value(0.2))
                .andExpect(jsonPath("$[2]").value(0.3));
    }
}