package com.pankhuri.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pankhuri.portfolio.entity.PortfolioItem;

@Repository // Marks this as a Spring Bean component responsible for data access
public interface PortfolioRepository extends JpaRepository<PortfolioItem, Long> {

    // MAGIC HAPPENS HERE!
    // By extending JpaRepository<Entity, ID Type>, we automatically get methods like:
    // .save(), .findAll(), .findById(), .deleteById(), etc.

    // We can also define custom finders just by naming the method correctly.
    // Spring interprets "findByUserId" and automatically generates the SQL:
    // SELECT * FROM portfolio_items WHERE user_id = ?
    List<PortfolioItem> findByUserId(String userId);
}