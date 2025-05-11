package com.unstage.core.instructor.dto;

import com.unstage.core.instructor.entity.Portfolio;

import java.time.LocalDateTime;

public record PortfolioResponse(
        Long id,
        String title,
        String content,
        Long instructorId,
        String instructorName,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {
    public static PortfolioResponse from(Portfolio portfolio) {
        return new PortfolioResponse(
                portfolio.getId(),
                portfolio.getTitle(),
                portfolio.getContent(),
                portfolio.getInstructor().getId(),
                portfolio.getInstructor().getName(),
                portfolio.getCreatedDate(),
                portfolio.getLastModifiedDate()
        );
    }
}