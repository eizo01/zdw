package com.设计模式.singleton;

/**
 * 懒汉式加锁版本 synchronized
 * 效率下降
 * 可以确定一个线程
 */
public class singleton04 {
    private static singleton04 INSTANCE;
    private singleton04(){

    }

    private static synchronized singleton04 getInstance(){
        if (INSTANCE == null){
            //多线程测试
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            INSTANCE = new singleton04();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                System.out.println(singleton04.getInstance().hashCode());
            }).start();
        }
    }
}
