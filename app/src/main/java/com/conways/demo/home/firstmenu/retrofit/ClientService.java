package com.conways.demo.home.firstmenu.retrofit;


import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by user on 2016/6/28.
 */
public interface ClientService {

    @GET(("/test.php/{username,pwd}"))
    String getResult(@Path("username") String username, @Path("pwd") String pwd);
}
