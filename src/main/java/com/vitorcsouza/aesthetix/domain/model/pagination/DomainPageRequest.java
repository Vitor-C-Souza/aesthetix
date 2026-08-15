package com.vitorcsouza.aesthetix.domain.model.pagination;

public class DomainPageRequest {
    private final int page;
    private final int size;

    public DomainPageRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }
}
