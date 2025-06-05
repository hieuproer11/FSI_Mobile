package com.example.fsi_mobile;

import com.example.fsi_mobile.Etudiant;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiFSI {
    String BASE_URL = "https://capyfsi.site/API/";

        @FormUrlEncoded
        @POST("Hieu.php")
        Call<LoginReponse> login(@Field("login") String login, @Field("mdp") String mdp);


}