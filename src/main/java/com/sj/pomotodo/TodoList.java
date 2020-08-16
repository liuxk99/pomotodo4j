package com.sj.pomotodo;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoList extends ArrayList<Todo> {
    public static class AsyncCallback<T> implements Callback<TodoList> {
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

        }

        @Override
        public void onFailure(Call<TodoList> call, Throwable t) {
            System.out.println("onFailure(" + call + ", " + t + ")");
        }
    }

    public static class SyncCallback<T> extends AsyncCallback<T> {
        private final CountDownLatch mLocker;

        public SyncCallback(CountDownLatch locker) {
            mLocker = locker;
        }

        @Override
        public void onResponse(Call<TodoList> call, Response<TodoList> response) {
            super.onResponse(call, response);

            mLocker.countDown();
        }

        @Override
        public void onFailure(Call<TodoList> call, Throwable t) {
            super.onFailure(call, t);

            mLocker.countDown();
        }
    }
}
