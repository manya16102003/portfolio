package com.example.portfolio.repository;

import com.example.portfolio.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    // Custom query methods can be added here if needed
}