package com.thoughtworks.todo.dagger.modules;

import android.app.Application;

import com.thoughtworks.todo.dagger.annotations.scopes.ApplicationScope;
import com.thoughtworks.todo.data.WebService;
import com.thoughtworks.todo.utils.Constants;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    @ApplicationScope
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideHttpClient(Cache cache, Interceptor interceptor, HttpLoggingInterceptor logging, Authenticator authenticator) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        builder.addInterceptor(logging);
        builder.authenticator(authenticator);
        builder.cache(cache);
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        return builder.build();
    }

    @Provides
    @ApplicationScope
    Authenticator provideAuthenticator(/*PreferenceManager preferenceManager, TodoDatabase doctorDatabase*/) {
        return (route, response) -> {

            return null;
        };
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)//TODO:Add base url
                .client(okHttpClient)
                .build();
    }

    @Provides
    @ApplicationScope
    WebService provideWebService(Retrofit retrofit) {
        return retrofit.create(WebService.class);
    }

    @Provides
    @ApplicationScope
    Interceptor provideApplicationInterceptor(/*PreferenceManager preferenceManager*/) {
        return chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
            /*TimeZone timeZone = TimeZone.getDefault();
            requestBuilder.addHeader(WebService.Header.TIMEZONE, timeZone.getID());
            if (preferenceManager.isLoggedIn()) {
                String authToken = preferenceManager.getToken();
                requestBuilder.header(WebService.Header.AUTHORIZATION, authToken).build();
            }*/
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        }
        return logging;
    }


}
