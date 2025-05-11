package com.unstage.core.instructor.dto;

import com.unstage.core.instructor.entity.Portfolio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPortfolioResponse {
    private Long portfolioId;
    private String title;
    private String content;
    private Long instructorId;
    private String instructorName;

    public static GetPortfolioResponse from(Portfolio portfolio) {
        return new GetPortfolioResponse(
                portfolio.getId(),
                portfolio.getTitle(),
                portfolio.getContent(),
                portfolio.getInstructor().getId(),
                portfolio.getInstructor().getName()
        );
    }
}
