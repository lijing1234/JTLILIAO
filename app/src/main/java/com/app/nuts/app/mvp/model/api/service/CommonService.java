package com.app.nuts.app.mvp.model.api.service;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 存放通用的一些API
 * Created by 王立强 on 2017/2/4.
 */
public interface CommonService {
    @GET("v2/movie/top250")
    Observable<String> getMovieInfo(@Query("start") int start, @Query("count") int count);
//    @GET("v2/book/{id}")//1220562
//    Observable<String> getReadInfo(@Path("id") String id);
    @GET("api")//1220562
    Observable<String> getReadInfo(@QueryMap Map<String, String> map);
}
