package com.example.portfolio.controller;

import com.example.portfolio.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/stocks")
public class UpdateStocksController {

    @Autowired
    private StockService stockService;

    /**
     * Endpoint to update stock details using a CSV file.
     *
     * @param file The uploaded CSV file containing stock data.
     * @return ResponseEntity with a success or error message.
     */
    @PostMapping("/update")
    public ResponseEntity<?> updateStockDetails(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty. Please upload a valid CSV file.");
            }

            stockService.updateStockDetails(file);
            return ResponseEntity.ok("Stock details updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating stock details.");
        }
    }
}