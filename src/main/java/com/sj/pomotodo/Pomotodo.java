package com.sj.pomotodo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class Pomotodo {
    public final Retrofit retrofit;
    public String token;

    public interface TodosService {
        String baseUrl = "https://api.pomotodo.com/1/";

        @FormUrlEncoded
        @POST("todos")
        Call<Todo> postTodo(@Header("Authorization") String token, @Field("description") String description);
        @GET("todos")
        Call<TodoList> getTodos(@Header("Authorization") String token);


        @FormUrlEncoded
        @POST("pomos")
        Call<Todo> postPomo(@Header("Authorization") String token, @Field("description") String description);
        @GET("pomos")
        Call<TodoList> getPomos(@Header("Authorization") String token);
//        Call<Todo> getTodo(@Path("uuid") String uuid);

    }

    public static class Todos {

        public static void postTodo(Retrofit retrofit, String token, String description, Callback<Todo> cb) throws InterruptedException {
                TodosService service = retrofit.create(TodosService.class);
                Call<Todo> call = service.postTodo(String.format("token %s", token), description);
                call.enqueue(cb);
        }

        public static void getTodos(Retrofit retrofit, String token, Callback<TodoList> cb) {
            TodosService service = retrofit.create(TodosService.class);
            Call<TodoList> call = service.getTodos(String.format("token %s", token));
            call.enqueue(cb);
        }
    }

    public Pomotodo(String token) {
        this.token = token;

        retrofit = new Retrofit.Builder()
            .baseUrl(TodosService.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public static class Pomos {
        public static void postPomo(Retrofit retrofit, String token, String description, Callback<Todo> cb) throws InterruptedException {
            TodosService service = retrofit.create(TodosService.class);
            Call<Todo> call = service.postTodo(String.format("token %s", token), description);
            call.enqueue(cb);
        }

        public static void getPomos(Retrofit retrofit, String token, Callback<TodoList> cb) {
            TodosService service = retrofit.create(TodosService.class);
            Call<TodoList> call = service.getTodos(String.format("token %s", token));
            call.enqueue(cb);
        }

    }
}
