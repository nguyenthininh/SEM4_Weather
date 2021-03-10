package com.demo.assignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIManager {
    String APIKEY = "eSTe7nGyP6wDOYf5WUrydajLaNMOzUVz";
    String SERVER = "http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/";
    String URI = "353412?apikey="+APIKEY+"&language=vi-vn&metric=true";

    @GET(URI)
    Call<List<Weather>> getListData();
}
