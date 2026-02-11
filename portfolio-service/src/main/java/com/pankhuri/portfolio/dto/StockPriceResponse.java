package com.pankhuri.portfolio.dto;

import java.math.BigDecimal;

import lombok.Data;

// This is Data Transfer Object (DTO) is an object that carries data between processes.
// This class is just a container for data coming from an external API.
// We don't need @Entity here because we aren't saving this to our own DB table directly.
@Data
public class StockPriceResponse {
    private String symbol;
    private BigDecimal price; // Matches the "price" field in JSON
    private String currency;
}