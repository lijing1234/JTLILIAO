package com.app.nuts.app.mvp.model.api.service;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 存放通用的一些API
 * Created by 王立强 on 2017/2/4.
 */
public interface CommonService {

//    http://www.jianshu.com/p/5bc866b9cbb9   框架解说
    @GET("https://api.douban.com/v2/movie/top250")
    Observable<String> getMovieInfo(@Query("start") int start, @Query("count") int count);

    //    @GET("v2/book/{id}")//1220562
//    Observable<String> getReadInfo(@Path("id") String id);
    @FormUrlEncoded
    @POST("http://172.17.20.5:8080/ROP/api")
    Observable<String> getReadInfo(@FieldMap  Map<String, String> map);
}
