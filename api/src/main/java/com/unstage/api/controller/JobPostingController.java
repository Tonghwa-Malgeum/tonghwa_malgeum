package com.unstage.api.controller;

import com.unstage.core.jobposting.dto.GetJobPostingsResponse;
import com.unstage.core.jobposting.service.JobPostingService;
import com.unstage.core.paging.PageParams;
import com.unstage.core.paging.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-postings")
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @GetMapping
    public ResponseEntity<PageResponse<GetJobPostingsResponse>> getJobPostings(@ModelAttribute final PageParams pageParams) {
        final PageResponse<GetJobPostingsResponse> response = jobPostingService.getJobPostings(pageParams.page(), pageParams.size());
        return ResponseEntity.ok(response);
    }
}
