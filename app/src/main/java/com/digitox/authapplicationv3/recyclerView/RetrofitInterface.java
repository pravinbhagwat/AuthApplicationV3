package com.digitox.authapplicationv3.recyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("posts")
    public Call<List<User>> getUsers();
}
