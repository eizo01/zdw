package com.设计模式.singleton;

/**
 * singleton01
 * 类加载道内存之后，就实例化一个对象，JVM保证线程保存
 * 唯一缺点：类装载时就会实例化
 * 最常见的写法
 */
public class singleton01 {
    private static final singleton01 INSTANCE = new singleton01();

    private singleton01(){};

    public static singleton01 getInstance(){
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }
}
