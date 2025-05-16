package com.unstage.core.welfarecenter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostNoticesResponse {
    private Long id;
    private String title;
    private String url;
    private Long welfareCenterId;
    private String welfareCenterName;
    private String region;
    private String category;
    private LocalDateTime registrationDate;
}
