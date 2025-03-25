package com.example.uni.service;

import com.example.uni.model.Listing;
import com.example.uni.repository.ListingRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListingService {
    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

}
