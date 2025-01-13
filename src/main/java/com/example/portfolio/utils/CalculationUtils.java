package com.example.portfolio.utils;

public class CalculationUtils {

    /**
     * Calculates the gain/loss for a stock.
     *
     * @param currentPrice The current market price of the stock.
     * @param buyPrice     The price at which the stock was bought.
     * @param quantity     The number of shares held.
     * @return The total gain/loss for the stock.
     */
    public static double calculateGainLoss(double currentPrice, double buyPrice, int quantity) {
        return (currentPrice - buyPrice) * quantity;
    }

    /**
     * Calculates the total percentage profit/loss for the portfolio.
     *
     * @param totalCurrentValue The total current value of the portfolio.
     * @param totalBuyValue     The total buy value of the portfolio.
     * @return The profit/loss percentage.
     */
    public static double calculateProfitLossPercentage(double totalCurrentValue, double totalBuyValue) {
        if (totalBuyValue == 0) {
            return 0.0;
        }
        return ((totalCurrentValue - totalBuyValue) / totalBuyValue) * 100;
    }
}