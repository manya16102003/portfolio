package com.example.portfolio.repository;

import com.example.portfolio.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    // Custom query to find all trades for a specific user
    @Query("SELECT t FROM Trade t WHERE t.userAccountiId = :userId")
    List<Trade> findByUserId(@Param("userId") Long userId);
}