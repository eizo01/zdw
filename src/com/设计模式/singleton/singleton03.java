package com.设计模式.singleton;

/**
 * lazy loading
 * 懒汉式
 * 多线程方式式会出错，创造多个实例
 */
public class singleton03 {
    private static singleton03 INSTANCE;
    private singleton03(){

    }
    public static singleton03 getInstance(){
        if (INSTANCE == null){
//            //多线程测试
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            INSTANCE = new singleton03();
        }
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                System.out.println(singleton03.getInstance().hashCode());
            }).start();
        }
    }

}
