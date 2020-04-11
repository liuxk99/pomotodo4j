package com.sj.pomotodo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public class Pomotodo {
    String token;

    public interface TodosService {
        @GET("todos")
        Call<TodoList> listTodos(@Header("Authorization") String token);

//        Call<Todo> getTodo(@Path("uuid") String uuid);
    }

    static class Todos {
        static final String baseUrl = "https://api.pomotodo.com/1/";
    }

    public Pomotodo(String token) {
        this.token = token;
    }
}
