package com.sj.pomotodo;

import com.sj.jlibs.persistence.FileUtils;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PomotodoTest {
    private Pomotodo pomotodo;

    @org.junit.Before
    public void setUp() throws Exception {
        String token = FileUtils.readStringFromFile("pomotodo.token");
        System.out.println(token);

        pomotodo = new Pomotodo(token);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void testcase_todos() throws InterruptedException {
        final CountDownLatch mLocker = new CountDownLatch(1);
        {
            Callback<TodoList> cb = new Callback<TodoList>() {
                @Override
                public void onResponse(Call<TodoList> call, Response<TodoList> response) {
                    System.out.println("onResponse(" + call + ", " + response.code() + ")");

                    if (response.isSuccessful()) {
                        TodoList todos = response.body();
                        for (Todo todo : todos) {
                            System.out.println(todo.getDescription());
//                            DumpUtils.dump(todo.getClass().getName(), todo.getDescription());
                        }
                    }
                    mLocker.countDown();
                }

                @Override
                public void onFailure(Call<TodoList> call, Throwable t) {
                    System.out.println("onFailure(" + call + ", " + t + ")");
                    mLocker.countDown();
                }
            };

            Pomotodo.Todos.getTodos(pomotodo.retrofit, pomotodo.token, cb);
        }
        mLocker.await();
    }

    @Test
    public void testcase_postTodo() throws InterruptedException {
        final CountDownLatch mLocker = new CountDownLatch(1);
        {
            String description = "xxx";
            Callback<Todo> cb = new Callback<Todo>() {
                @Override
                public void onResponse(Call<Todo> call, Response<Todo> response) {
                    System.out.println("onResponse(" + call + ", " + response.code() + ")");

                    if (response.isSuccessful()) {
                        Todo todo = response.body();
                        System.out.println(todo.toString());
                    }
                    mLocker.countDown();
                }

                @Override
                public void onFailure(Call<Todo> call, Throwable t) {
                    System.out.println("onFailure(" + call + ", " + t + ")");
                    mLocker.countDown();
                }
            };
            Pomotodo.Todos.postTodo(pomotodo.retrofit, pomotodo.token, description, cb);
        }
        mLocker.await();
    }

}