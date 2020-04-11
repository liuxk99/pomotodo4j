package com.sj.pomotodo;

import com.sj.jlibs.persistence.FileUtils;
import com.sj.pomotodo.Todo;
import com.sj.pomotodo.TodoList;
import com.sj4j.utils.GsonStore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TodoTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testcase_Todo() throws Exception {
        String todoJson = FileUtils.readStringFromFile("todo.json");
        System.out.println(todoJson);

        GsonStore<Todo> gsonStore = new GsonStore<Todo>("todo.json", Todo.class);
        Todo todo = gsonStore.load();
        System.out.println(todo.toString());
    }

    @Test
    public void testcase_Todos() throws Exception {
        GsonStore<TodoList> gsonStore = new GsonStore<TodoList>("todos.json", TodoList.class);
        TodoList todoList = gsonStore.load();
        System.out.println(todoList.toString());
    }
}