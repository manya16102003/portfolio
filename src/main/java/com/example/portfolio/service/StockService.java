package com.example.portfolio.service;

import com.example.portfolio.model.Stock;
import com.example.portfolio.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    /**
     * Retrieve stock details by Stock ID.
     *
     * @param stockId the ID of the stock
     * @return the stock details
     */
    public Stock getStockById(Long stockId) {
        Optional<Stock> stock = stockRepository.findById(stockId);
        if (stock.isEmpty()) {
            throw new IllegalArgumentException("Stock with ID " + stockId + " not found.");
        }
        return stock.get();
    }

    /**
     * Update stock data from a CSV file.
     *
     * @param file the CSV file containing stock data
     */
    public void updateStockDetails(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    // Skip the header line
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",");

                // Assuming CSV structure: Stock ID, Stock Name, Open Price, Close Price, High Price, Low Price, Settlement Price
                if (data.length < 7) {
                    throw new IllegalArgumentException("Invalid CSV format. Each line must have 7 fields.");
                }

                Long stockId = Long.parseLong(data[0].trim());
                String stockName = data[1].trim();
                double openPrice = Double.parseDouble(data[2].trim());
                double closePrice = Double.parseDouble(data[3].trim());
                double highPrice = Double.parseDouble(data[4].trim());
                double lowPrice = Double.parseDouble(data[5].trim());
                double settlementPrice = Double.parseDouble(data[6].trim());

                Stock stock = stockRepository.findById(stockId).orElse(new Stock());
                stock.setId(stockId);
                stock.setName(stockName);
                stock.setOpenPrice(openPrice);
                stock.setClosePrice(closePrice);
                stock.setHighPrice(highPrice);
                stock.setLowPrice(lowPrice);
                stock.setSettlementPrice(settlementPrice);

                stockRepository.save(stock);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update stocks from CSV: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieve all stocks.
     *
     * @return a list of all stocks
     */
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
}