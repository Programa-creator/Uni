package com.example.uni.controller;

import com.example.uni.service.ListingParserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parse")
public class ParseController {
    private final ListingParserService listingParserService;

    public ParseController(ListingParserService listingParserService) {
        this.listingParserService = listingParserService;
    }

    @GetMapping
    public String triggerParsing() {
        listingParserService.parseListings(); // Викликаємо метод без параметрів
        return "Парсинг запущено!";
    }
}
