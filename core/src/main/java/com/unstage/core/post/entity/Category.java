package com.unstage.core.post.entity;

public enum Category {
    NOTICE("공지사항"),
    JOB("채용");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}