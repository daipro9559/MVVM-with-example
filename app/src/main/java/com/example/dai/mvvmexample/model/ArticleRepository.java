package com.example.dai.mvvmexample.model;

import com.example.dai.mvvmexample.common.AppConstants;

import rx.Observable;

/**
 * Created by Dai_Nguyen on 5/30/2017.
 */

public class ArticleRepository {

    private NewsService service;

    public ArticleRepository(NewsService service) {
        this.service = service;
    }

    public Observable<ArticlesResponse> excuteGetArticles(){
        return service.getArticles("the-next-web","latest",AppConstants.API_KEY);
    }


}
