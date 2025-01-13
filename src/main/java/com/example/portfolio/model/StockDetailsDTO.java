package com.example.portfolio.model;

public class StockDetailsDTO {
    private Long stockId;
    private String stockName;
    private double openPrice;
    private double closePrice;
    private double highPrice;
    private double lowPrice;
    private double settlementPrice;

    // Default constructor
    public StockDetailsDTO() {
    }

    // Parameterized constructor
    public StockDetailsDTO(Long stockId, String stockName, double openPrice, double closePrice, double highPrice, double lowPrice, double settlementPrice) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.settlementPrice = settlementPrice;
    }

    // Getters and Setters
    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(double settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    // toString method for debugging/logging
    @Override
    public String toString() {
        return "StockDetailsDTO{" +
                "stockId=" + stockId +
                ", stockName='" + stockName + '\'' +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", settlementPrice=" + settlementPrice +
                '}';
    }
}