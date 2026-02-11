package com.pankhuri.portfolio.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 1. Tells Hibernate: "Make a database table out of this class"
@Table(name = "portfolio_items") // 2. Name the table explicitly (optional, but good practice)
@Data // 3. Lombok: Generates getters, setters, toString, etc.
@AllArgsConstructor // 4. Lombok: Generates a constructor with all arguments
@NoArgsConstructor  // 5. Lombok: Generates an empty constructor (required by JPA)
public class PortfolioItem {

    @Id // Marks this field as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment the ID (like MySQL AUTO_INCREMENT)
    private Long id;

    // In a real app, this would link to a User entity, but we'll keep it simple for now.
    private String userId;

    private String stockSymbol;

    private Integer quantity;

    // Use BigDecimal for money, never Double! 
    private BigDecimal purchasePrice;
}