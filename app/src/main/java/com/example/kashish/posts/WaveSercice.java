package com.example.kashish.posts;

import com.example.kashish.posts.photoref.PhotorefResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WaveSercice{

        @GET("details/json")
        Call<PhotorefResponse> getPhotoref(@Query("placeid") String str, @Query("key")String api_key);

        @GET("photo")
        Call<PhotorefResponse> getPhoto(@Query("maxwidth") String str, @Query("photoreference")String api_key,@Query("key") String key);


}
