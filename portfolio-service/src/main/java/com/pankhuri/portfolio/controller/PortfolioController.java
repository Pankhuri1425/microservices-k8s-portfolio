package com.pankhuri.portfolio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankhuri.portfolio.client.StockServiceClient;
import com.pankhuri.portfolio.dto.StockPriceResponse;
import com.pankhuri.portfolio.entity.PortfolioItem;
import com.pankhuri.portfolio.repository.PortfolioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/portfolios") // Base URL path for all methods in this controller
@RequiredArgsConstructor // Lombok: Generates a constructor for final fields (best practice for injection)
public class PortfolioController {

    // We inject the repository so we can use it.
    // Because it's 'final' and we use @RequiredArgsConstructor, Spring automatically injects it.
    private final PortfolioRepository portfolioRepository;

    private final StockServiceClient stockServiceClient;

    // Endpoint to add a new item to a user's portfolio
    // POST request to http://localhost:8081/portfolios
    @PostMapping
    public PortfolioItem addPortfolioItem(@RequestBody PortfolioItem item) {
        // The @RequestBody annotation tells Spring to map the incoming JSON(from server) to the PortfolioItem object.

        // 1. Log what we are doing for debugging
        System.out.println("Fetching price for symbol: " + item.getStockSymbol());

        // 2. Use Feign Client to make the HTTP call to the Stock Service
        StockPriceResponse priceResponse = stockServiceClient.getStockPrice(item.getStockSymbol());

        // 3. Set the real-time price on the item object
        // We get the price from the DTO returned by the other service.
        item.setPurchasePrice(priceResponse.getPrice());

        // 4. Save the complete object to our own database
        System.out.println("Saving item with fetched price: " + item.getPurchasePrice());
        // repository.save() inserts the new item into the database and returns the saved entity (with its new ID).
        return portfolioRepository.save(item);
    }

    // Endpoint to get all items for a specific user
    // GET request to http://localhost:8081/portfolios/someUser
    @GetMapping("/{userId}")
    public List<PortfolioItem> getUserPortfolio(@PathVariable String userId) {
        // Uses the custom finder method we defined in the repository interface.
        return portfolioRepository.findByUserId(userId);
    }
}