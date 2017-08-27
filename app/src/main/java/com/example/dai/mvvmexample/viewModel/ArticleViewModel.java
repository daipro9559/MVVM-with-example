package com.example.dai.mvvmexample.viewModel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.dai.mvvmexample.model.Article;
import com.squareup.picasso.Picasso;

/**
 * Created by dai on 8/24/2017.
 */

public class ArticleViewModel extends BaseObservable {
    private Article article;

    public ArticleViewModel(Article article) {
        this.article =article;
    }

    public String getTitle() {
        return article.getTitle();
    }

    public String getDescription() {
        return article.getDescription();
    }

    public String getImageUrl() {
       return article.getUrlToImage();
    }
    public String getPubDate(){
        return article.getPublishedAt();
    }


    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext()).load(imageUrl).into(view);
    }

    public void setArticle(Article article) {
        this.article = article;
        notifyChange();
    }
}
