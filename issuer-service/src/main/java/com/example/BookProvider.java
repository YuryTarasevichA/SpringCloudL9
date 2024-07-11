package com.example;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
public class BookProvider {

    private final WebClient webClient;

    public BookProvider() {
        webClient = WebClient.builder().build();
    }

    public UUID getRandomBookID() {

        BookResponse randomBook = webClient.get()
                .uri("http://localhost:8180/api/book/random")
                .retrieve()
                .bodyToMono(BookResponse.class)
                .block();

        return randomBook.getId();
    }


    @Data
    private static class BookResponse {
        private UUID id;
    }
}
