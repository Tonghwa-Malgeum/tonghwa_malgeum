package com.unstage.core.instructor.component;

import com.unstage.core.exception.EntityNotFoundException;
import com.unstage.core.instructor.entity.Portfolio;
import com.unstage.core.instructor.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PortfolioReader {

    private final PortfolioRepository portfolioRepository;

    @Transactional(readOnly = true)
    public Portfolio read(final Long portfolioId) {
        log.debug("포트폴리오 조회 실행 - id={}", portfolioId);

        final Portfolio portfolio = portfolioRepository.findWithInstructorBy(portfolioId)
                .orElseThrow(() -> new EntityNotFoundException("Portfolio", portfolioId));

        log.debug("포트폴리오 조회 완료 - id={}, instructorId={}",
                portfolio.getId(), portfolio.getInstructor().getId());

        return portfolio;
    }

    @Transactional(readOnly = true)
    public List<Portfolio> readBy(final Long instructorId) {
        log.debug("강사별 포트폴리오 목록 조회 실행 - instructorId={}", instructorId);

        final List<Portfolio> portfolios = portfolioRepository.findByInstructor_Id(instructorId);

        log.debug("강사별 포트폴리오 목록 조회 완료 - instructorId={}, count={}", instructorId, portfolios.size());

        return portfolios;
    }

    public Portfolio save(final Portfolio portfolio) {
        log.debug("포트폴리오 저장 실행 - title={}, instructorId={}",
                portfolio.getTitle(), portfolio.getInstructor().getId());

        Portfolio savedPortfolio = portfolioRepository.save(portfolio);

        log.debug("포트폴리오 저장 완료 - id={}, title={}", savedPortfolio.getId(), savedPortfolio.getTitle());

        return savedPortfolio;
    }
}