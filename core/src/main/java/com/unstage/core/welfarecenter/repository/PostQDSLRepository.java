package com.unstage.core.welfarecenter.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.unstage.core.paging.PageResponse;
import com.unstage.core.paging.PageUtils;
import com.unstage.core.welfarecenter.dto.GetPostNoticesResponse;
import com.unstage.core.welfarecenter.dto.GetPostsResponse;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.unstage.core.welfarecenter.entity.QPost.post;
import static com.unstage.core.welfarecenter.entity.QPostNotice.postNotice;

@Repository
@RequiredArgsConstructor
public class PostQDSLRepository {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public PageResponse<GetPostsResponse> findPostsResWithPage(final int page, final int size) {
        final Pageable pageable = PageRequest.of(page, size);

        final JPAQuery<GetPostsResponse> query = queryFactory
                .select(Projections.fields(GetPostsResponse.class,
                        post.id.as("id"),
                        post.title.as("title"),
                        post.url.as("url"),
                        post.welfareCenter.id.as("welfareCenterId"),
                        post.welfareCenter.name.as("welfareCenterName"),
                        post.welfareCenter.region.as("region"),
                        post.category.as("category"),
                        post.createdDate.as("registrationDate")
                ))
                .from(post);

        final JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post);

        final Page<GetPostsResponse> resultPage = PageUtils.toPage(query, countQuery, pageable);
        return new PageResponse<>(
                resultPage.getTotalPages(),
                resultPage.getTotalElements(),
                resultPage.getNumber(),
                resultPage.getSize(),
                resultPage.getContent()
        );
    }

    public PageResponse<GetPostNoticesResponse> findByPostNoticesResWithPage(final int page, final int size) {
        final Pageable pageable = PageRequest.of(page, size);

        final JPAQuery<GetPostNoticesResponse> query = queryFactory
                .select(Projections.fields(GetPostNoticesResponse.class,
                        postNotice.id.as("id"),
                        postNotice.title.as("title"),
                        postNotice.url.as("url"),
                        postNotice.welfareCenter.id.as("welfareCenterId"),
                        postNotice.welfareCenter.name.as("welfareCenterName"),
                        postNotice.welfareCenter.region.as("region"),
                        postNotice.category.as("category"),
                        postNotice.createdDate.as("registrationDate")
                ))
                .from(postNotice);

        final JPAQuery<Long> countQuery = queryFactory
                .select(postNotice.count())
                .from(postNotice);

        final Page<GetPostNoticesResponse> resultPage = PageUtils.toPage(query, countQuery, pageable);
        return new PageResponse<>(
                resultPage.getTotalPages(),
                resultPage.getTotalElements(),
                resultPage.getNumber(),
                resultPage.getSize(),
                resultPage.getContent()
        );
    }
//
//    public PageResponse<GetPostsResponse> findByJobWithPage(int page, int size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//
//        String jpql = "SELECT new com.unstage.core.welfarecenter.dto.GetPostsResponse(" +
//                "p.id, p.title, w.name, w.region, p.url, p.category, " +
//                "(SELECT MIN(pj.recruitmentStartDate) FROM PostJob pj WHERE pj.post = p), " +
//                "(SELECT MIN(pj.recruitmentEndDate) FROM PostJob pj WHERE pj.post = p), " +
//                "p.createdDate) " +
//                "FROM Post p JOIN p.welfareCenter w " +
//                "WHERE p.category = :category";
//
//        TypedQuery<GetPostsResponse> query = entityManager.createQuery(jpql, GetPostsResponse.class);
//        query.setParameter("category", Category.JOB);
//        query.setFirstResult((int) pageable.getOffset());
//        query.setMaxResults(pageable.getPageSize());
//        List<GetPostsResponse> content = query.getResultList();
//
//        String countJpql = "SELECT COUNT(p) FROM Post p WHERE p.category = :category";
//        Long total = entityManager.createQuery(countJpql, Long.class)
//                .setParameter("category", Category.JOB)
//                .getSingleResult();
//
//        return new PageResponse<>(
//                (int) Math.ceil((double) total / size),
//                total,
//                pageable.getPageNumber(),
//                pageable.getPageSize(),
//                content
//        );
//    }
}
