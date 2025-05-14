package com.unstage.core.user.repository;

import com.unstage.core.user.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    @Query("SELECT p FROM Portfolio p" +
            " JOIN FETCH p.user" +
            " WHERE p.id = :portfolioId")
    Optional<Portfolio> findWithInstructorBy(@Param("portfolioId") Long portfolioId);
    
    List<Portfolio> findByUser_Id(Long userId);
}