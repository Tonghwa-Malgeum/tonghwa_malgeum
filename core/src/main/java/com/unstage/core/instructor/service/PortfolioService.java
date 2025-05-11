package com.unstage.core.instructor.service;

import com.unstage.core.PortfolioWriter;
import com.unstage.core.instructor.component.PortfolioReader;
import com.unstage.core.instructor.dto.CreatePortfolioRequest;
import com.unstage.core.instructor.dto.GetPortfolioResponse;
import com.unstage.core.instructor.dto.GetPortfoliosResponse;
import com.unstage.core.instructor.entity.Portfolio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioWriter portfolioWriter;
    private final PortfolioReader portfolioReader;

    public GetPortfolioResponse getPortfolio(final Long portfolioId) {
        final Portfolio portfolio = portfolioReader.read(portfolioId);
        return GetPortfolioResponse.from(portfolio);
    }

    @Transactional(readOnly = true)
    public GetPortfoliosResponse getPortfoliosByInstructorId(final Long instructorId) {
        final List<Portfolio> portfolios = portfolioReader.readBy(instructorId);
        return GetPortfoliosResponse.from(portfolios);
    }

    public Long createPortfolio(final Long instructorId, final CreatePortfolioRequest request) {
        return portfolioWriter.save(instructorId, request.title(), request.content());
    }

//    @Transactional
//    public Portfolio updatePortfolio(final Long id, final String title, final String content) {
//        final Portfolio portfolio = getPortfolio(id);
//        portfolio.update(title, content);
//        return portfolio;
//    }
//
//    @Transactional
//    public void deletePortfolio(final Long id) {
//        final Portfolio portfolio = getPortfolio(id);
//        portfolio.softDelete(java.time.LocalDateTime.now());
//    }
}

