package com.unstage.core.jobposting.component;

import com.unstage.core.jobposting.dto.GetJobPostingsResponse;
import com.unstage.core.jobposting.repository.JobPostingQDSLRepository;
import com.unstage.core.paging.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobPostingReader {

    private final JobPostingQDSLRepository jobPostingQDSLRepository;
    
    public PageResponse<GetJobPostingsResponse> readListWithPage(int page, int size) {
        log.debug("구인공고 목록 조회 실행 - page={}, size={}", page, size);

        PageResponse<GetJobPostingsResponse> response = jobPostingQDSLRepository.findListWithPage(page, size);

        log.debug("구인공고 목록 조회 완료 - totalPages: {}, totalElements: {}, page: {}, size: {}",
                response.totalPages(),
                response.totalElements(),
                response.number(),
                response.size());

        return response;
    }
}
