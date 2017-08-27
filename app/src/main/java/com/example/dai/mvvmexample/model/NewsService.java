package com.example.dai.mvvmexample.model;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lamvu on 2/17/2017.
 */

public interface NewsService {

    @GET("articles?")
    Observable<ArticlesResponse> getArticles(@Query("source") String source, @Query("sortBy") String sortBy,
                                             @Query("apiKey") String apiKey);

}
