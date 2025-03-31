package com.example.demo.service;

import org.springframework.ai.bedrock.cohere.BedrockCohereChatClient;
import org.springframework.ai.bedrock.titan.BedrockTitanEmbeddingClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIService {

    private final BedrockCohereChatClient chatClient;
    private final BedrockTitanEmbeddingClient embeddingClient;

    @Autowired
    public AIService(BedrockCohereChatClient chatClient, 
                    BedrockTitanEmbeddingClient embeddingClient) {
        this.chatClient = chatClient;
        this.embeddingClient = embeddingClient;
    }

    public String generateText(String prompt) {
        ChatResponse response = chatClient.call(new Prompt(prompt));
        return response.getResult().getOutput().getContent();
    }

    public List<Double> generateEmbedding(String text) {
        EmbeddingResponse response = embeddingClient.embedForResponse(List.of(text));
        return response.getResults().get(0).getOutput();
    }

    public List<List<Double>> generateEmbeddings(List<String> texts) {
        EmbeddingResponse response = embeddingClient.embedForResponse(texts);
        return response.getResults().stream()
                .map(result -> result.getOutput())
                .toList();
    }
}