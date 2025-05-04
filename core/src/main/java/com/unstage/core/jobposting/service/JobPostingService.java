package com.unstage.core.jobposting.service;

import com.unstage.core.jobposting.component.JobPostingReader;
import com.unstage.core.jobposting.dto.GetJobPostingsResponse;
import com.unstage.core.paging.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingReader jobPostingReader;

    public PageResponse<GetJobPostingsResponse> getJobPostings(int page, int size) {
        return jobPostingReader.readListWithPage(page, size);
    }
}
