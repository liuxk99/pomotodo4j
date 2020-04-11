package com.sj.pomotodo;

import com.sj.jlibs.persistence.FileUtils;

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
}