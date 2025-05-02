package com.unstage.core.testfixtures.config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@TestComponent
public class DatabaseCleanup {
    @PersistenceContext
    private EntityManager em;
    private List<String> tableNames;

    @PostConstruct
    public void afterPropertiesSet() {
        tableNames = em.getMetamodel().getEntities().stream()
                .map(this::getTableName)
                .collect(Collectors.toList());
    }

    //TODO 초기화 코드 Jdbc Template 을 이용해 리팩터링하기
    private String getTableName(EntityType<?> entityType) {
        Class<?> javaType = entityType.getJavaType();
        // @Table 어노테이션이 있는 경우 테이블 이름 가져오기
        if (javaType.isAnnotationPresent(Table.class)) {
            Table table = javaType.getAnnotation(Table.class);
            return table.name();
        }
        // @Table 어노테이션이 없는 경우 엔티티 이름에서 테이블 이름으로 변환
        return convertCamelCaseToSnakeCase(entityType.getName());
    }

    @Transactional
    public void execute() {
        em.flush();

        for (String tableName : tableNames) {
            // Check if table exists before truncating
            Long tableCount = (Long) em.createNativeQuery(
                            "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = '" + tableName + "'")
                    .getSingleResult();

            if (tableCount > 0) {
                em.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            } else {
                System.out.println("Table does not exist: " + tableName + ". Skipping truncate.");
            }
        }
    }

    private String convertCamelCaseToSnakeCase(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
