package com.taisho6339.man.crawler.controller.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by taisho6339 on 16/06/02.
 */
public class SearchRequest {
    @NotNull
    @Size(max = 20)
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
