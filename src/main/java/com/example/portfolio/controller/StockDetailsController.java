package com.example.portfolio.controller;

import com.example.portfolio.model.StockDetailsDTO;
import com.example.portfolio.model.Stock;
import com.example.portfolio.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockDetailsController {

    @Autowired
    private StockService stockService;

    // Endpoint to retrieve stock details by Stock ID
    @GetMapping("/details/{stockId}")
    public ResponseEntity<?> getStockDetails(@PathVariable Long stockId) {
        try {
            Stock stock = stockService.getStockById(stockId);

            // Map the Stock entity to StockDetailsDTO
            StockDetailsDTO stockDetails = new StockDetailsDTO(
                    stock.getId(),
                    stock.getName(),
                    stock.getOpenPrice(),
                    stock.getClosePrice(),
                    stock.getHighPrice(),
                    stock.getLowPrice(),
                    stock.getSettlementPrice()
            );

            return ResponseEntity.ok(stockDetails);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving stock details.");
        }
    }
}