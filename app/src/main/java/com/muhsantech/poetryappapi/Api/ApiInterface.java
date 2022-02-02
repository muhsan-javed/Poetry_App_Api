package com.muhsantech.poetryappapi.Api;

import com.muhsantech.poetryappapi.Response.DeleteResponse;
import com.muhsantech.poetryappapi.Response.GetPoetryResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("getpoetry.php")
    Call<GetPoetryResponse> getPoetry();

    @FormUrlEncoded
    @POST("deletepoetry.php")
    Call<DeleteResponse> deletePoetry(@Field("poetry_id") String poetry_id);
}
