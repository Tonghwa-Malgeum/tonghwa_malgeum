package com.unstage.api.controller;

import com.unstage.core.instructor.dto.GetPortfolioResponse;
import com.unstage.core.instructor.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;

    // 코드 리뷰 완료
    @GetMapping("/{portfolioId}")
    public ResponseEntity<GetPortfolioResponse> getPortfolio(@PathVariable final Long portfolioId) {
        final GetPortfolioResponse response = portfolioService.getPortfolio(portfolioId);
        return ResponseEntity.ok(response);
    }

//    @PutMapping("/portfolios/{id}")
//    public ResponseEntity<PortfolioResponse> updatePortfolio(
//            @PathVariable final Long id,
//            @RequestBody final CreatePortfolioRequest request) {
//        final Portfolio portfolio = portfolioService.updatePortfolio(
//                id,
//                request.title(),
//                request.content()
//        );
//        return ResponseEntity.ok(PortfolioResponse.from(portfolio));
//    }
//
//    @DeleteMapping("/portfolios/{id}")
//    public ResponseEntity<Void> deletePortfolio(@PathVariable final Long id) {
//        portfolioService.deletePortfolio(id);
//        return ResponseEntity.noContent().build();
//    }
}
