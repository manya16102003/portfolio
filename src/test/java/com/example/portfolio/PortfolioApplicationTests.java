package com.example.portfolio;

import com.example.portfolio.model.Stock;
import com.example.portfolio.model.Trade;
import com.example.portfolio.repository.StockRepository;
import com.example.portfolio.repository.TradeRepository;
import com.example.portfolio.service.PortfolioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PortfolioApplicationTests {

	@Autowired
	private PortfolioService portfolioService;

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private StockRepository stockRepository;

	@BeforeEach
	void setUp() {
		tradeRepository.deleteAll();
		stockRepository.deleteAll();

		// Add dummy stock data
		Stock stock1 = new Stock("Apple", 150.0, 155.0, 157.0, 149.0, 155.0);
		Stock stock2 = new Stock("Google", 2800.0, 2900.0, 2950.0, 2750.0, 2900.0);
		stock1.setId(1L); // Explicit ID setting for test purposes
		stock2.setId(2L);
		stockRepository.saveAll(List.of(stock1, stock2));

		// Add dummy trade data
		Trade trade1 = new Trade();
		trade1.setUserId(1L);
		trade1.setStockId(1L);
		trade1.setType("Buy");
		trade1.setQuantity(10);
		trade1.setPrice(150.0);
		Trade trade2 = new Trade();
		trade2.setUserId(1L);
		trade2.setStockId(2L);
		trade2.setType("Buy");
		trade2.setQuantity(5);
		trade2.setPrice(2800.0);
		Trade trade3 = new Trade();
		trade3.setUserId(1L);
		trade3.setStockId(1L);
		trade3.setType("Sell");
		trade3.setQuantity(3);
		trade3.setPrice(155.0);
		tradeRepository.saveAll(List.of(trade1, trade2, trade3));
	}

	@Test
	void testPortfolioGeneration() {
		// Generate portfolio for user with ID 1
		var portfolio = portfolioService.getPortfolioByUserId(1L);

		// Assert portfolio details
		assertNotNull(portfolio);
		assertEquals(2, portfolio.getHoldings().size()); // 2 different stocks in the portfolio

		// Validate Apple stock holding
		var appleHolding = portfolio.getHoldings().stream()
				.filter(h -> h.getStockName().equals("Apple"))
				.findFirst()
				.orElse(null);
		assertNotNull(appleHolding);
		assertEquals(7, appleHolding.getQuantity()); // 10 bought - 3 sold
		assertEquals(150.0, appleHolding.getBuyPrice()); // Average buy price
		assertEquals(155.0, appleHolding.getCurrentPrice()); // Current price
		assertEquals((155.0 - 150.0) * 7, appleHolding.getGainLoss()); // Gain/loss calculation

		// Validate Google stock holding
		var googleHolding = portfolio.getHoldings().stream()
				.filter(h -> h.getStockName().equals("Google"))
				.findFirst()
				.orElse(null);
		assertNotNull(googleHolding);
		assertEquals(5, googleHolding.getQuantity()); // All bought retained
		assertEquals(2800.0, googleHolding.getBuyPrice());
		assertEquals(2900.0, googleHolding.getCurrentPrice());
		assertEquals((2900.0 - 2800.0) * 5, googleHolding.getGainLoss());
	}
}