package com.example.testws;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface UserDAORF{

    @FormUrlEncoded
    @POST("save.php")
    public Call<User> create(@Field("id") int id, @Field("name") String name, @Field("email") String email);

    @FormUrlEncoded
    @POST("edit.php")
    public Call<User> update(@Field("name") String name, @Field("email") String email);

    @FormUrlEncoded
    @POST("delete.php")
    public boolean delete(@Field("name") String name, @Field("email") String email);

    @GET("fetch.php")
    public Call<User> get(@Query("name") String name);

    @GET("fetchAll.php")
    public Call<List<User>> getAll();


}
