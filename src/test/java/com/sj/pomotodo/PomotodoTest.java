package com.sj.pomotodo;

import com.sj.jlibs.RandomUtils;
import com.sj.jlibs.persistence.FileUtils;

import org.junit.Test;

import java.util.Collections;
import java.util.TimeZone;
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
    public void testcase_postPomo() throws InterruptedException {
        final CountDownLatch mLocker = new CountDownLatch(1);
        {
            TimeZone timeZone = TimeZone.getDefault();
            long ended_at = System.currentTimeMillis();
            int duration = 60 * 1000;
            long started_at = ended_at - RandomUtils.random(duration, duration + 60 * 1000 * 2);

            String description = "Pomo-XXX";
            Callback<Pomo> cb = new Pomo.SyncCallbackPomo<Pomo>(mLocker);
            Pomotodo.Pomos.postPomo(pomotodo.retrofit, pomotodo.token,
                timeZone.getID(), started_at, ended_at, description, cb);
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

        Collections.sort(cb.mPomoList);
        System.out.println("size: " + cb.mPomoList.size());
        for (Pomo pomo: cb.mPomoList) {
            System.out.println(pomo.toLine());
        }
    }
}