package com.example.portfolio.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "open_price", nullable = false)
    private double openPrice;

    @Column(name = "close_price", nullable = false)
    private double closePrice;

    @Column(name = "high_price", nullable = false)
    private double highPrice;

    @Column(name = "low_price", nullable = false)
    private double lowPrice;

    @Column(name = "settlement_price", nullable = false)
    private double settlementPrice;

    // Constructors
    public Stock() {
    }

    public Stock(String name, double openPrice, double closePrice, double highPrice, double lowPrice, double settlementPrice) {
        this.name = name;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.settlementPrice = settlementPrice;
    }

    // Get the current price of the stock (assumed to be close price for simplicity)
    public double getCurrentPrice() {
        return this.closePrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public void setSettlementPrice(double settlementPrice) {
        this.settlementPrice = settlementPrice;
    }
}