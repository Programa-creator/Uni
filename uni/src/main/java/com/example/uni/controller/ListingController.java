package com.example.uni.controller;

import com.example.uni.model.Listing;
import com.example.uni.service.ListingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ListingController {
    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("/listings")
    public String getAllListings(Model model) {
        List<Listing> listings = listingService.getAllListings();
        model.addAttribute("listings", listings);
        return "listings"; // Назва HTML-файлу (listings.html)
    }
}
