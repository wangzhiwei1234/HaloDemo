package com.halo.halodemo.http;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import com.halo.halodemo.BuildConfig;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {
    private static HttpService service = null;

    public static HttpService getHttpService() {
        if (service == null) {
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder();
            clientBuilder.readTimeout(10, TimeUnit.SECONDS);
            clientBuilder.connectTimeout(10, TimeUnit.SECONDS);
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                clientBuilder.addInterceptor(logging);
            }
            builder.client(clientBuilder.build());
            service = builder.build().create(HttpService.class);
        }
        return service;
    }
}
