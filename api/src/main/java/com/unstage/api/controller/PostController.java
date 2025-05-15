package com.unstage.api.controller;

import com.unstage.core.paging.PageParams;
import com.unstage.core.paging.PageResponse;
import com.unstage.core.post.dto.GetPostsResponse;
import com.unstage.core.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<PageResponse<GetPostsResponse>> getAllPosts(@ModelAttribute final PageParams pageParams) {
        final PageResponse<GetPostsResponse> response = postService.getPostings(pageParams);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/notice")
//    public ResponseEntity<PageResponse<GetPostsResponse>> getNoticePosts(@ModelAttribute final PageParams pageParams) {
//        final PageResponse<GetPostsResponse> response = postService.getNoticePosts(pageParams.page(), pageParams.size());
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/job")
//    public ResponseEntity<PageResponse<GetPostsResponse>> getJobPosts(@ModelAttribute final PageParams pageParams) {
//        final PageResponse<GetPostsResponse> response = postService.getJobPosts(pageParams.page(), pageParams.size());
//        return ResponseEntity.ok(response);
//    }
}