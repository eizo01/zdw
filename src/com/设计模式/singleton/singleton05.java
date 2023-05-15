package com.设计模式.singleton;

/**
 * 懒汉式的另外一种加锁
 * 锁new的过程，也会出现多对象的情况，因为判断和执行内容没有一体化操作
 */
public class singleton05 {

    private static singleton05 INSTANCE;

    private singleton05() {

    }

    private static synchronized singleton05 getInstance() {
        if (INSTANCE == null) {
            synchronized (singleton05.class) {
                //多线程测试
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new singleton05();
            }
        }
        return INSTANCE;
    }



    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(singleton05.getInstance().hashCode());
            }).start();
        }
    }

}


