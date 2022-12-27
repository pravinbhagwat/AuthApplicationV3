package com.digitox.authapplicationv3.API_Controller;

import com.digitox.authapplicationv3.pojo.MultipleResource;
import com.digitox.authapplicationv3.pojo.SignUpResponse;
import com.digitox.authapplicationv3.pojo.User;
import com.digitox.authapplicationv3.pojo.UserList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/api/unknown")
    Call<MultipleResource> doGetListResources();

    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);


    // API-2
    @FormUrlEncoded // annotation used in POST type requests
    @POST("/retrofit/register.php")
    // API's endpoints
    Call<SignUpResponse> registration(@Field("name") String name,
                                      @Field("email") String email,
                                      @Field("password") String password,
                                      @Field("logintype") String logintype);

    // In registration method @Field used to set the keys and String data type is representing its a string type value and callback is used to get the response from api and it will set it in our POJO class

}
