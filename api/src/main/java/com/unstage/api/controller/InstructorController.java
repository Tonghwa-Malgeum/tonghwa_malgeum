package com.unstage.api.controller;

import com.unstage.core.instructor.dto.CreatePortfolioRequest;
import com.unstage.core.instructor.dto.GetPortfoliosResponse;
import com.unstage.core.instructor.dto.InstructorRequest;
import com.unstage.core.instructor.dto.InstructorResponse;
import com.unstage.core.instructor.entity.Instructor;
import com.unstage.core.instructor.service.InstructorService;
import com.unstage.core.instructor.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;
    private final PortfolioService portfolioService;

    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponse> getInstructor(@PathVariable final Long id) {
        final Instructor instructor = instructorService.getInstructor(id);
        return ResponseEntity.ok(InstructorResponse.from(instructor));
    }

    // 코드 리뷰 완료
    @GetMapping("/{instructorId}/portfolios")
    public ResponseEntity<GetPortfoliosResponse> getPortfoliosByInstructor(@PathVariable final Long instructorId) {
        //TODO 세션 검증 추가
        final GetPortfoliosResponse response = portfolioService.getPortfoliosByInstructorId(instructorId);
        return ResponseEntity.ok(response);
    }

    // 코드 리뷰 완료
    @PostMapping("/{instructorId}/portfolios")
    //TODO 세션이 구현되면 {instructorId} 제거된 URI를 가진 컨트롤러 필요
    public ResponseEntity<Void> createPortfolio(
            @PathVariable final Long instructorId, @RequestBody final CreatePortfolioRequest request) {
        final Long portfolioId = portfolioService.createPortfolio(instructorId, request);
        final URI location = ServletUriComponentsBuilder
                .fromPath("/api/v1/portfolios/{portfolioId}")
                .buildAndExpand(portfolioId)
                .toUri();
        //TODO 303 응답으로 변경
        return ResponseEntity.created(location).build();
    }

    @PostMapping
    public ResponseEntity<InstructorResponse> createInstructor(@RequestBody final InstructorRequest request) {
        final Instructor instructor = instructorService.createInstructor(
                request.name(),
                request.email(),
                request.bio()
        );

        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(instructor.getId())
                .toUri();

        return ResponseEntity.created(location).body(InstructorResponse.from(instructor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructorResponse> updateInstructor(
            @PathVariable final Long id,
            @RequestBody final InstructorRequest request) {
        final Instructor instructor = instructorService.updateInstructor(
                id,
                request.name(),
                request.bio()
        );
        return ResponseEntity.ok(InstructorResponse.from(instructor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable final Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }
}
