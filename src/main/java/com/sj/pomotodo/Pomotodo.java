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
import retrofit2.http.Query;

public class Pomotodo {
    public final Retrofit retrofit;
    public String token;

    public interface Service {
        String baseUrl = "https://api.pomotodo.com/1/";

        @FormUrlEncoded
        @POST("todos")
        Call<Todo> postTodo(@Header("Authorization") String token, @Field("description") String description);

        @GET("todos")
        Call<TodoList> getTodos(@Header("Authorization") String token);


        @FormUrlEncoded
        @POST("pomos")
        Call<Pomo> postPomo(@Header("Authorization") String token,
                            @Field("timezone") String timezone,
                            @Field("started_at") String started_at,
                            @Field("ended_at") String ended_at,
                            @Field("description") String description
        );

        @GET("pomos?limit=40")
        Call<PomoList> getPomos(@Header("Authorization") String token, @Query("abandoned") boolean abandoned, @Query("manual") boolean manual);
//        Call<Todo> getTodo(@Path("uuid") String uuid);

    }

    public static class Todos {

        public static void postTodo(Retrofit retrofit, String token, String description, Callback<Todo> cb) throws InterruptedException {
            Service service = retrofit.create(Service.class);
            Call<Todo> call = service.postTodo(String.format("token %s", token), description);
            call.enqueue(cb);
        }

        public static void getTodos(Retrofit retrofit, String token, Callback<TodoList> cb) {
            Service service = retrofit.create(Service.class);
            Call<TodoList> call = service.getTodos(String.format("token %s", token));
            call.enqueue(cb);
        }
    }

    public Pomotodo(String token) {
        this.token = token;

        retrofit = new Retrofit.Builder()
            .baseUrl(Service.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public static class Pomos {
        public static void getPomos(Retrofit retrofit, String token, boolean abandoned, boolean manual, Callback<PomoList> cb) {
            Service service = retrofit.create(Service.class);
            Call<PomoList> call = service.getPomos(String.format("token %s", token), abandoned, manual);
            call.enqueue(cb);
        }

        public static void postPomo(Retrofit retrofit, String token, String timeZoneID, long started_at, long ended_at, String description, Callback<Pomo> cb) {
            Service service = retrofit.create(Service.class);
            Call<Pomo> call = service.postPomo(String.format("token %s", token),
                timeZoneID, Long.toString(started_at), Long.toString(ended_at), description);
            call.enqueue(cb);

        }
    }
}
