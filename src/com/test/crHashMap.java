package com.test;

import java.util.concurrent.ConcurrentHashMap;

public class crHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        int n = 8;
        int sc = n - (n >>> 2);
        System.out.println(sc);
    }
}
