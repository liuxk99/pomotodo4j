package com.sj.pomotodo;

import com.sj.jlibs.persistence.FileUtils;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import retrofit2.Callback;

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
            Callback<TodoList> cb = new TodoList.SyncCallback<TodoList>(mLocker);
            Pomotodo.Todos.getTodos(pomotodo.retrofit, pomotodo.token, cb);
        }
        mLocker.await();
    }

    @Test
    public void testcase_postTodo() throws InterruptedException {
        final CountDownLatch mLocker = new CountDownLatch(1);
        {
            String description = "xxx";
            Callback<Todo> cb = new Todo.SyncCallback<Todo>(mLocker);
            Pomotodo.Todos.postTodo(pomotodo.retrofit, pomotodo.token, description, cb);
        }
        mLocker.await();
    }

    @Test
    public void testcase_pomos() throws InterruptedException {
        final CountDownLatch mLocker = new CountDownLatch(2);
        PomoList.SyncCallback<PomoList> cb = new PomoList.SyncCallback<PomoList>(mLocker);
        {
            Pomotodo.Pomos.getPomos(pomotodo.retrofit, pomotodo.token, false, false, cb);
            Pomotodo.Pomos.getPomos(pomotodo.retrofit, pomotodo.token, false, true, cb);
        }
        mLocker.await();

        for (Pomo pomo: cb.mPomoList) {

        }
    }

}