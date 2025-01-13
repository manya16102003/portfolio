package com.example.portfolio.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Portfolio {

    private List<Holding> holdings;
    private double totalBuyPrice;
    private double totalCurrentValue;
    private double totalGainLoss;
    private double totalGainLossPercentage;

    public Portfolio(List<Holding> holdings, double totalBuyPrice, double totalCurrentValue, double totalGainLoss, double totalGainLossPercentage) {
        this.holdings = holdings;
        this.totalBuyPrice = totalBuyPrice;
        this.totalCurrentValue = totalCurrentValue;
        this.totalGainLoss = totalGainLoss;
        this.totalGainLossPercentage = totalGainLossPercentage;
    }

    // Nested class for holding details
    public static class Holding {

        private String stockName;
        private Long stockId;
        private int quantity;
        private double buyPrice;
        private double currentPrice;
        private double gainLoss;

        public Holding(String stockName, Long stockId, int quantity, double buyPrice, double currentPrice, double gainLoss) {
            this.stockName = stockName;
            this.stockId = stockId;
            this.quantity = quantity;
            this.buyPrice = buyPrice;
            this.currentPrice = currentPrice;
            this.gainLoss = gainLoss;
        }

        public String getStockName() {
            return stockName;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        public Long getStockId() {
            return stockId;
        }

        public void setStockId(Long stockId) {
            this.stockId = stockId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getBuyPrice() {
            return buyPrice;
        }

        public void setBuyPrice(double buyPrice) {
            this.buyPrice = buyPrice;
        }

        public double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public double getGainLoss() {
            return gainLoss;
        }

        public void setGainLoss(double gainLoss) {
            this.gainLoss = gainLoss;
        }
    }
}