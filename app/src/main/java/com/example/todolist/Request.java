package com.example.todolist;
// package YOUR_PACKAGE.Services;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Request {

    @GET("/projects.json")
    Call<ArrayList<Todo_List>> call();
    @FormUrlEncoded
    @POST("/todos/")
    public Call<ResponseBody> sendTODO_object(@Body Todo todo_for_send);

}

