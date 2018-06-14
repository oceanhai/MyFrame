package com.myframe.www.utils;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by wuhai on 2018/6/14.
 */
public class JunitUtilsTest {

    private JunitUtils junitUtils;

    @Before
    public void setUp() throws Exception {
        junitUtils = new JunitUtils();
    }

    @Test
    public void divide() throws Exception {
//        double num = junitUtils.divide(10,2);
        double num = junitUtils.divide(10,0);
        System.out.println("结果："+num);
    }

}