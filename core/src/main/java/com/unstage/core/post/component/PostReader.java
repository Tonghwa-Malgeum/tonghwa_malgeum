package com.unstage.core.post.component;

import com.unstage.core.paging.PageResponse;
import com.unstage.core.post.dto.GetPostsResponse;
import com.unstage.core.post.repository.PostQDSLRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostReader {

    private final PostQDSLRepository postQDSLRepository;
    
    public PageResponse<GetPostsResponse> readAllWithPage(final int page, final int size) {
        log.debug("전체 게시물 목록 조회 실행 - page={}, size={}", page, size);

        final PageResponse<GetPostsResponse> response = postQDSLRepository.findListWithPage(page, size);

        log.debug("전체 게시물 목록 조회 완료 - totalPages: {}, totalElements: {}, page: {}, size: {}",
                response.totalPages(),
                response.totalElements(),
                response.number(),
                response.size());

        return response;
    }
    
    public PageResponse<GetPostsResponse> readNoticeWithPage(int page, int size) {
        log.debug("공지사항 목록 조회 실행 - page={}, size={}", page, size);

        PageResponse<GetPostsResponse> response = postQDSLRepository.findByNoticeWithPage(page, size);

        log.debug("공지사항 목록 조회 완료 - totalPages: {}, totalElements: {}, page: {}, size: {}",
                response.totalPages(),
                response.totalElements(),
                response.number(),
                response.size());

        return response;
    }
    
    public PageResponse<GetPostsResponse> readJobWithPage(int page, int size) {
        log.debug("채용 목록 조회 실행 - page={}, size={}", page, size);

        PageResponse<GetPostsResponse> response = postQDSLRepository.findByJobWithPage(page, size);

        log.debug("채용 목록 조회 완료 - totalPages: {}, totalElements: {}, page: {}, size: {}",
                response.totalPages(),
                response.totalElements(),
                response.number(),
                response.size());

        return response;
    }
}