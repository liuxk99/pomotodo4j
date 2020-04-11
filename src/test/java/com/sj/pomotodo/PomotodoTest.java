package com.sj.pomotodo;

import com.sj.jlibs.misc.DumpUtils;
import com.sj.jlibs.persistence.FileUtils;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PomotodoTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testcase_pomotodo() {
        String token = FileUtils.readStringFromFile("pomotodo.token");
        System.out.println(token);
    }

    @Test
    public void testcase_todos() throws InterruptedException {
        String token = FileUtils.readStringFromFile("pomotodo.token");
//        Pomotodo pomotodo = new Pomotodo(token);

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Pomotodo.Todos.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        final CountDownLatch mLocker = new CountDownLatch(1);
        {
            Pomotodo.TodosService service = retrofit.create(Pomotodo.TodosService.class);
            Call<TodoList> todoListCall = service.listTodos(String.format("token %s", token));
            todoListCall.enqueue(new Callback<TodoList>() {
                @Override
                public void onResponse(Call<TodoList> call, Response<TodoList> response) {
                    System.out.println("onResponse(" + call + ", " + response.code() + ")");

                    if (response.isSuccessful()) {
                        TodoList todos = response.body();
                        for (Todo todo: todos) {
                            DumpUtils.dump(todo.getClass().getName(), todo.toString());
                        }
                    }
                    mLocker.countDown();
                }

                @Override
                public void onFailure(Call<TodoList> call, Throwable t) {
                    System.out.println("onFailure(" + call + ", " + t + ")");
                    mLocker.countDown();
                }
            });
        }
        mLocker.await();
    }
}