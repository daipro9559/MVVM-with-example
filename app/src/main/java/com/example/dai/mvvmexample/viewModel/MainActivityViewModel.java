package com.example.dai.mvvmexample.viewModel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dai.mvvmexample.model.Article;
import com.example.dai.mvvmexample.model.ArticleRepository;
import com.example.dai.mvvmexample.model.ArticlesResponse;
import com.example.dai.mvvmexample.view.adapter.ArticleAdapter;


import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dai on 8/24/2017.
 */

public class MainActivityViewModel extends BaseObservable {

    private CompositeSubscription compositeSubscription;
    private ArticleRepository articleRepository;
    private ObservableArrayList<List<Article>> list = new ObservableArrayList<>();
    private ObservableBoolean isLoading = new ObservableBoolean();


    public MainActivityViewModel(CompositeSubscription compositeSubscription, ArticleRepository articleRepository) {
        this.compositeSubscription = compositeSubscription;
        this.articleRepository = articleRepository;
    }



    public void loadDefault() {
        getArticle();
    }

    private void getArticle(){
        setIsLoading(true);
        compositeSubscription.add(
                articleRepository.excuteGetArticles()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ArticlesResponse>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("debug",e.getMessage());
                            }

                            @Override
                            public void onNext(ArticlesResponse articlesResponse) {
                                setIsLoading(false);
                                list.add(0,articlesResponse.getArticles());

                            }
                        }));

    }

    public ObservableArrayList<List<Article>> getList() {
        return list;
    }

    public void setList(ObservableArrayList<List<Article>> list) {
        this.list = list;
    }

    @BindingAdapter( "app:bindData")
    public static void setBindData(RecyclerView recyclerView, ObservableArrayList<List<Article>> articles) {
        if (articles.size()==0){
            return;
        }
        ArticleAdapter articleAdapter= (ArticleAdapter) recyclerView.getAdapter();
        if (articleAdapter ==null){
            articleAdapter = new ArticleAdapter(articles.get(0));
            recyclerView.setAdapter(articleAdapter);
        }else {
            articleAdapter.changeListArticle(articles.get(0));
        }
    }
    public void onDestroy(){
        compositeSubscription.unsubscribe();
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading.set(isLoading);
    }
}
