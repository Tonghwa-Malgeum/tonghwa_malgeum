package com.unstage.core.welfarecenter.service;

import com.unstage.core.paging.PageParams;
import com.unstage.core.paging.PageResponse;
import com.unstage.core.welfarecenter.component.PostReader;
import com.unstage.core.welfarecenter.dto.GetPostJobsResponse;
import com.unstage.core.welfarecenter.dto.GetPostNoticesResponse;
import com.unstage.core.welfarecenter.dto.GetPostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostReader postReader;

    public PageResponse<GetPostsResponse> getPostingsRespWithPage(final PageParams pageParams) {
        return postReader.readPostsRespWithPage(pageParams.page(), pageParams.size());
    }

    public PageResponse<GetPostNoticesResponse> getPostNoticesRespWithPage(final PageParams pageParams) {
        return postReader.readPostNoticesRespWithPage(pageParams.page(), pageParams.size());
    }

    public PageResponse<GetPostJobsResponse> getPostJobsRespWithPage(final PageParams pageParams) {
        return postReader.readPostJobsRespWithPage(pageParams.page(), pageParams.size());
    }
}