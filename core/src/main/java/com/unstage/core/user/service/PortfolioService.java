package com.unstage.core.user.service;

import com.unstage.core.user.component.PortfolioReader;
import com.unstage.core.user.dto.GetPortfolioResponse;
import com.unstage.core.user.entity.Portfolio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioReader portfolioReader;

    public GetPortfolioResponse getPortfolio(final Long portfolioId) {
        final Portfolio portfolio = portfolioReader.read(portfolioId);
        return GetPortfolioResponse.from(portfolio);
    }
}
