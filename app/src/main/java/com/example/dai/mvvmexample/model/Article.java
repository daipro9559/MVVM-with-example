package com.example.dai.mvvmexample.model;

import com.example.dai.mvvmexample.common.AppConstants;
import com.google.gson.annotations.Expose;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by lamvu on 2/17/2017.
 */

public class Article implements Serializable {

    @Expose
    private String author;
    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    private String url;
    @Expose
    private String urlToImage;
    @Expose
    private String publishedAt;
    @Expose
    private String idSource;

    public Article() {
    }

    public Article(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getIdSource() {
        return idSource;
    }

    public void setIdSource(String idSource) {
        this.idSource = idSource;
    }

    public String convertTime() {
        if (publishedAt ==null || publishedAt.isEmpty()){
            return "";
        }
        SimpleDateFormat sourceFormat = new SimpleDateFormat(AppConstants.DATE_FORMAT);
        sourceFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date parsed = new Date();
        try {
             parsed = sourceFormat.parse(publishedAt.substring(0,10)+" "+publishedAt.substring(11,19));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TimeZone tz = TimeZone.getDefault();
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        destFormat.setTimeZone(tz);
        return destFormat.format(parsed);
    }
}
