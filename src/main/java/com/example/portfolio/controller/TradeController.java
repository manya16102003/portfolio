package com.example.portfolio.controller;

import com.example.portfolio.service.TradeService;
import com.example.portfolio.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TradeService")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/record")
    public ResponseEntity<?> recordTrade(@RequestBody Trade trade) {
        try {
            tradeService.recordTrade(trade);
            return ResponseEntity.ok("Trade recorded successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while recording the trade.");
        }
    }
}