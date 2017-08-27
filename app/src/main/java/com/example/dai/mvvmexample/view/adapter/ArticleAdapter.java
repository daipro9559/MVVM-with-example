package com.example.dai.mvvmexample.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dai.mvvmexample.R;
import com.example.dai.mvvmexample.databinding.ArticleItemBinding;
import com.example.dai.mvvmexample.model.Article;
import com.example.dai.mvvmexample.viewModel.ArticleViewModel;

import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by daipc on 02-Mar-17.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    PublishSubject<Integer> publishSubject;
    private List<Article> mListArticle;
    public ArticleAdapter(List<Article> listArticle) {
        publishSubject = PublishSubject.create();
        this.mListArticle = listArticle;


    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        ArticleItemBinding articleItemBinding ;
        public ArticleViewHolder(ArticleItemBinding articleItemBinding) {
            super(articleItemBinding.cardView);
            this.articleItemBinding = articleItemBinding;
        }
        public void bindArticleItem(Article article){
           if ( articleItemBinding.getArticleViewModel() ==null){
               articleItemBinding.setArticleViewModel(new ArticleViewModel(article));
           }else {
               articleItemBinding.getArticleViewModel().setArticle(article);
           }
        }

    }
    public Observable<Integer> onClickItem(){
        return publishSubject;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ArticleItemBinding articleItemBinding =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.article_item,parent,false);
        return new ArticleViewHolder(articleItemBinding);


    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.bindArticleItem(mListArticle.get(position));
    }

    @Override
    public int getItemCount() {
        if (mListArticle != null) {
            return mListArticle.size();
        }
        return 0;
    }



    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void changeListArticle(List<Article> newList) {
//        if (mListArticle != null) {
//            mListArticle = null;
//        }
//        mIsLoadFull = false;
        mListArticle.clear();
        mListArticle.addAll(newList);
        notifyDataSetChanged();
    }

    public void addArticle(List<Article> articles) {
        mListArticle.addAll(articles);
        notifyDataSetChanged();
    }


    public Article getArticleByIndex(int index){
        if (mListArticle!= null && index < mListArticle.size()) {
            return mListArticle.get(index);
        }
            return null;

    }
}