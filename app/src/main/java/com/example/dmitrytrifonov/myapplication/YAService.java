package com.example.dmitrytrifonov.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dmitrytrifonov on 19.04.17.
 */

public interface YAService {
    @GET("api/v1.5/tr.json/translate")
    Call<YAResponse> translate(@Query("key") String key, @Query("text") String text, @Query("lang") String lang);
}
