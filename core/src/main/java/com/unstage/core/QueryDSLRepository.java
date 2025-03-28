package com.unstage.core;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueryDSLRepository {

    private final JPAQueryFactory queryFactory;

    public void test() {
        List<AEntity> fetch =
                queryFactory
                .selectFrom(QAEntity.aEntity).fetch();

        fetch.forEach(aEntity -> {
            System.out.println(aEntity);
        });
    }
}
