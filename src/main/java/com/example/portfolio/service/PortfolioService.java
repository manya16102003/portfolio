package com.example.portfolio.service;

import com.example.portfolio.model.Portfolio;
import com.example.portfolio.model.Trade;
import com.example.portfolio.model.Stock;
import com.example.portfolio.repository.TradeRepository;
import com.example.portfolio.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class PortfolioService {

    private final TradeRepository tradeRepository;
    private final StockRepository stockRepository;

    @Autowired
    public PortfolioService(TradeRepository tradeRepository, StockRepository stockRepository) {
        this.tradeRepository = tradeRepository;
        this.stockRepository = stockRepository;
    }

    public Portfolio getPortfolioByUserId(Long userId) {
        // Retrieve all trades for the user
        List<Trade> trades = tradeRepository.findByUserId(userId);
        if (trades.isEmpty()) {
            throw new IllegalArgumentException("No trades found for the user.");
        }

        // Aggregate trades to calculate holdings
        Map<Long, Integer> stockQuantities = new HashMap<>();
        Map<Long, Double> stockBuyPrices = new HashMap<>();

        for (Trade trade : trades) {
            Long stockId = trade.getStockId();
            int quantity = trade.getQuantity();
            double price = trade.getPrice();

            if (trade.getType().equalsIgnoreCase("Buy")) {
                stockQuantities.put(stockId, stockQuantities.getOrDefault(stockId, 0) + quantity);
                stockBuyPrices.put(stockId, stockBuyPrices.getOrDefault(stockId, 0.0) + (quantity * price));
            } else if (trade.getType().equalsIgnoreCase("Sell")) {
                stockQuantities.put(stockId, stockQuantities.getOrDefault(stockId, 0) - quantity);
            }
        }

        // Filter out stocks with zero or negative holdings
        stockQuantities = stockQuantities.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Fetch stock details and calculate portfolio holdings
        List<Portfolio.Holding> holdings = stockQuantities.entrySet().stream().map(entry -> {
            Long stockId = entry.getKey();
            Integer quantity = entry.getValue();
            Stock stock = stockRepository.findById(stockId)
                    .orElseThrow(() -> new IllegalArgumentException("Stock not found for ID: " + stockId));

            double averageBuyPrice = stockBuyPrices.get(stockId) / quantity;
            double currentPrice = stock.getSettlementPrice(); // Adjusted for the Stock model's structure
            double gainLoss = (currentPrice - averageBuyPrice) * quantity;

            return new Portfolio.Holding(
                    stock.getName(),
                    stockId,
                    quantity,
                    averageBuyPrice,
                    currentPrice,
                    gainLoss
            );
        }).collect(Collectors.toList());

        // Calculate portfolio summary
        double totalBuyPrice = holdings.stream().mapToDouble(h -> h.getBuyPrice() * h.getQuantity()).sum();
        double totalCurrentValue = holdings.stream().mapToDouble(h -> h.getCurrentPrice() * h.getQuantity()).sum();
        double totalGainLoss = totalCurrentValue - totalBuyPrice;
        double totalGainLossPercentage = (totalGainLoss / totalBuyPrice) * 100;

        return new Portfolio(holdings, totalBuyPrice, totalCurrentValue, totalGainLoss, totalGainLossPercentage);
    }
}