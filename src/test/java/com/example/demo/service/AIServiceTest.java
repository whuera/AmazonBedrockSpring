package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.bedrock.cohere.BedrockCohereChatClient;
import org.springframework.ai.bedrock.titan.BedrockTitanEmbeddingClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.embedding.EmbeddingResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AIServiceTest {

    @Mock
    private BedrockCohereChatClient chatClient;

    @Mock
    private BedrockTitanEmbeddingClient embeddingClient;

    @InjectMocks
    private AIService aiService;

    @Test
    void generateText_ShouldReturnResponse() {
        String expectedResponse = "Test response";
        when(chatClient.call(any(Prompt.class)))
                .thenReturn(new ChatResponse(expectedResponse));

        String result = aiService.generateText("Test prompt");
        
        assertEquals(expectedResponse, result);
    }

    @Test
    void generateEmbedding_ShouldReturnEmbedding() {
        List<Double> expectedEmbedding = List.of(0.1, 0.2, 0.3);
        when(embeddingClient.embedForResponse(any(List.class)))
                .thenReturn(new EmbeddingResponse(expectedEmbedding));

        List<Double> result = aiService.generateEmbedding("Test text");
        
        assertEquals(expectedEmbedding, result);
    }
}