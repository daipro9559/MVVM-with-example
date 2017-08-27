package com.example.dai.mvvmexample.dagger.modul;

import com.example.dai.mvvmexample.common.AppConstants;
import com.example.dai.mvvmexample.dagger.MainActivityScope;
import com.example.dai.mvvmexample.model.ArticleRepository;
import com.example.dai.mvvmexample.model.NewsService;
import com.example.dai.mvvmexample.viewModel.MainActivityViewModel;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dai on 8/27/2017.
 */

@Module
public class MainActivityModul {


    @MainActivityScope
    @Provides
    public MainActivityViewModel provideActivityViewMoldel(ArticleRepository articleRepository){
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        return new MainActivityViewModel(compositeSubscription,articleRepository);
    }
    @MainActivityScope
    @Provides
    public ArticleRepository provideArticleRepository(NewsService service){
        return new ArticleRepository(service);
    }

    @MainActivityScope
    @Provides
    public NewsService provideService() {
        Retrofit retrofit = new Retrofit.Builder()
                .client( new OkHttpClient().newBuilder().build())
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
        return retrofit.create(NewsService.class);
    }
}
