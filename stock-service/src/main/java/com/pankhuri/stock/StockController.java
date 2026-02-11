package com.pankhuri.stock;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController // Request handler
public class StockController {

    // This method handles GET requests to "/stocks/{symbol}"
    // Example: localhost:8080/stocks/AAPL
    @GetMapping("/stocks/{symbol}")
    public Map<String, Object> getStockPrice(@PathVariable String symbol) {
        // In a real app, we would query a database or external API here.
        // For now, we return hardcoded data to test the connectivity.

        Map<String, Object> response = new HashMap<>();
        response.put("symbol", symbol.toUpperCase());
        // Simulate a price: generate a somewhat random number based on the symbol length
        response.put("price", 100.0 + symbol.length() * 10.5);
        response.put("currency", "USD");

        // Spring automatically converts this Map into JSON format.
        return response;
    }
}
