package com.example.uni.controller;

import com.example.uni.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/usd")
    public BigDecimal getUsdRate() {
        return currencyService.getUsdRate();
    }
}
