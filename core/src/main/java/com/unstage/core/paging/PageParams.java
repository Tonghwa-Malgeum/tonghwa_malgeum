package com.unstage.core.paging;

public record PageParams(int page, int size) {
    public PageParams() {
        this(1, 10);
    }
}
