package com.example.dai.mvvmexample.model;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by lamvu on 2/23/2017.
 */

public class ArticlesResponse {

    @Expose
    private String source;
    @Expose
    private String sortBy;
    @Expose
    private List<Article> articles;

    public ArticlesResponse() {
    }

    public ArticlesResponse(String source, String sortBy, List<Article> articles) {
        this.source = source;
        this.sortBy = sortBy;
        this.articles = articles;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
