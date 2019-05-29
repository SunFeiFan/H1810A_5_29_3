package com.example.dell.h1810a_5_29_3.api;



import com.example.dell.h1810a_5_29_3.bean.GrilBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiServer {
//    http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1
    String getGrilUrl = "http://gank.io/";
    @GET("api/data/%E7%A6%8F%E5%88%A9/20/1")
    Observable<GrilBean> getGrilUrl();
}
