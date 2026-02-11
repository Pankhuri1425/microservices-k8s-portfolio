package com.pankhuri.portfolio.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pankhuri.portfolio.dto.StockPriceResponse;

// 1. @FeignClient: Tells Spring, "This interface is an HTTP client."
// name = "stock-service": just a logical name for logging/tracing.
// url = "http://localhost:8080": THE CRITICAL PART. This is the exact address of the running Stock Service container.
@FeignClient(name = "stock-service", url = "http://localhost:8080")
public interface StockServiceClient {

    // 2. Define the method call.
    // This looks exactly like a Controller method definition, but it's an interface!
    // It tells Feign: "When this method is called, make a GET request to /stocks/{symbol}"
    // It expects the response JSON to match the StockPriceResponse DTO structure.
    @GetMapping("/stocks/{symbol}")
    StockPriceResponse getStockPrice(@PathVariable("symbol") String symbol);
}
