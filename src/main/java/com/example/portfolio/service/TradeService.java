package com.example.portfolio.service;

import com.example.portfolio.model.Trade;
import com.example.portfolio.model.Stock;
import com.example.portfolio.repository.TradeRepository;
import com.example.portfolio.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private StockRepository stockRepository;

    public void recordTrade(Trade trade) {
        // Validate trade type
        if (!"Buy".equalsIgnoreCase(trade.getTradeType()) &&
                !"Sell".equalsIgnoreCase(trade.getTradeType())) {
            throw new IllegalArgumentException("Trade type must be 'Buy' or 'Sell'.");
        }

        // Validate quantity
        if (trade.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        // Check if the stock exists
        Optional<Stock> stockOptional = stockRepository.findById(trade.getStock().getId());
        if (stockOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid stock ID: " + trade.getStock().getId());
        }

        // If stock exists, associate it with the trade
        trade.setStock(stockOptional.get());

        // Save the trade in the database
        tradeRepository.save(trade);
    }
}