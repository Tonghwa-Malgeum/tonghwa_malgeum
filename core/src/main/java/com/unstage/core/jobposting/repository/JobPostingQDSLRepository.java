package com.unstage.core.jobposting.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.unstage.core.jobposting.dto.GetJobPostingsResponse;
import com.unstage.core.paging.PageResponse;
import com.unstage.core.paging.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.unstage.core.jobposting.entity.QJobPosting.jobPosting;
import static com.unstage.core.jobposting.entity.QWelfareCenter.welfareCenter;

@Repository
@RequiredArgsConstructor
public class JobPostingQDSLRepository {

    private final JPAQueryFactory queryFactory;

    public PageResponse<GetJobPostingsResponse> findListWithPage(int page, int size) {
        Pageable pageable = PageUtils.of(page, size);

        JPAQuery<GetJobPostingsResponse> query = queryFactory
                .select(Projections.fields(GetJobPostingsResponse.class,
                        jobPosting.id.as("id"),
                        jobPosting.title.as("title"),
                        welfareCenter.name.as("welfareCenterName"),
                        welfareCenter.region.as("region"),
                        jobPosting.recruitmentStartDate.as("recruitmentStartDate"),
                        jobPosting.recruitmentEndDate.as("recruitmentEndDate"),
                        jobPosting.createdDate.as("registrationDate")
                ))
                .from(jobPosting)
                .join(jobPosting.welfareCenter, welfareCenter);

        JPAQuery<Long> countQuery = queryFactory
                .select(jobPosting.count())
                .from(jobPosting)
                .join(jobPosting.welfareCenter, welfareCenter);

        Page<GetJobPostingsResponse> resultPage = PageUtils.toPage(query, countQuery, pageable);
        return new PageResponse<>(
                resultPage.getTotalPages(),
                resultPage.getTotalElements(),
                resultPage.getNumber(),
                resultPage.getSize(),
                resultPage.getContent()
        );
    }
}
