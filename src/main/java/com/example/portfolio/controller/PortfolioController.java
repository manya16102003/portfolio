package com.example.portfolio.controller;

import com.example.portfolio.service.PortfolioService;
import com.example.portfolio.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    // Endpoint to retrieve the portfolio of a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserPortfolio(@PathVariable Long userId) {
        try {
            Portfolio portfolio = portfolioService.getPortfolioByUserId(userId);
            return ResponseEntity.ok(portfolio);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving the portfolio.");
        }
    }
}