package com.unstage.core;

import com.unstage.core.instructor.component.InstructorReader;
import com.unstage.core.instructor.entity.Instructor;
import com.unstage.core.instructor.entity.Portfolio;
import com.unstage.core.instructor.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PortfolioWriter {
    private final InstructorReader instructorReader;
    private final PortfolioRepository portfolioRepository;

    @Transactional
    public Long save(final Long instructorId, final String title, final String content) {
        final Instructor instructor = instructorReader.read(instructorId);
        final Portfolio portfolio = Portfolio.builder()
                .title(title)
                .content(content)
                .instructor(instructor)
                .build();

        final Portfolio savedPortfolio = portfolioRepository.save(portfolio);

        log.info("포트폴리오 생성 완료 - portfolioId={}, instructorId={}",
                savedPortfolio.getId(), savedPortfolio.getInstructor().getId());

        return savedPortfolio.getId();
    }
}
