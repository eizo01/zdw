package com.设计模式.singleton;

public class singleton02 {

    private static final singleton02 INSTANCE;

    static {
        INSTANCE = new singleton02();
    }

    private singleton02() {};

    public static singleton02 getInstance() {
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

}
