package com.sj.pomotodo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class Pomotodo {
    String token;

    public interface TodosService {
        @FormUrlEncoded
        @POST("todos")
        Call<Todo> postTodo(@Header("Authorization") String token, @Field("description") String description);
        @GET("todos")
        Call<TodoList> getTodos(@Header("Authorization") String token);


//        Call<Todo> getTodo(@Path("uuid") String uuid);

    }

    static class Todos {
        static final String baseUrl = "https://api.pomotodo.com/1/";
    }

    public Pomotodo(String token) {
        this.token = token;
    }
}
