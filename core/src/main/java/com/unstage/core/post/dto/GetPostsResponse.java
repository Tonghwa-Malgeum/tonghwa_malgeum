package com.unstage.core.post.dto;

import com.unstage.core.post.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostsResponse {
    private Long id;
    private String title;
    private String url;
    private Long welfareCenterId;
    private String welfareCenterName;
    private String region;
    private Category category;
    private LocalDateTime registrationDate;
}