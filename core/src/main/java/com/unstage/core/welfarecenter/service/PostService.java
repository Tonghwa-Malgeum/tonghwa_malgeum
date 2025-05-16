package com.unstage.core.welfarecenter.service;

import com.unstage.core.paging.PageParams;
import com.unstage.core.paging.PageResponse;
import com.unstage.core.welfarecenter.component.PostReader;
import com.unstage.core.welfarecenter.dto.GetPostNoticesResponse;
import com.unstage.core.welfarecenter.dto.GetPostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostReader postReader;

    public PageResponse<GetPostsResponse> getPostingsRes(final PageParams pageParams) {
        return postReader.readPostsResWithPage(pageParams.page(), pageParams.size());
    }

    public PageResponse<GetPostNoticesResponse> getPostNoticesRes(final PageParams pageParams) {
        return postReader.readPostNoticesResWithPage(pageParams.page(), pageParams.size());
    }
//
//    public PageResponse<GetPostsResponse> getJobPosts(int page, int size) {
//        return postReader.readJobWithPage(page, size);
//    }
}