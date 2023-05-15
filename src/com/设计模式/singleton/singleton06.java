package com.设计模式.singleton;

/**
 * dcl 懒汉式
 * 并不是绝对安全的，大部分的时候时安全，因为jvm内存模型时无序写入的会发生
 * // 2的语序
 * 1.给 Singleton 分配内存；
 * 2.调用 Singleton 的构造函数来初始化成员变量；
 * 3.将给 singleton 对象指向分配的内存空间（此时 singleton 才不为 null ）；
 * 实际有可能发生
 * 当线程A进入同步方法执行singleton = new Singleton();代码时，恰好这三个步骤重排序后为1 3 2，
 *
 * 那么步骤3执行后 singleton 已经不为 null ,但是未执行步骤2，singleton对象初始化不完全，
 * 此时线程B执行 getInstance() 方法，第一步判断时 singleton
 * 不为null,则直接将未完全初始化的singleton对象返回了。
 * 如果一个字段被声明成volatile，Java线程内存模型确保所有线程看到这个变量的值是一致的，同时还会禁止指令重排序
 */
public class singleton06 {
    private static singleton06 INSTANCE;
    private singleton06(){

    }

    public static singleton06 getInstance(){
        if (INSTANCE == null){
            synchronized(singleton06.class){
                if (INSTANCE == null){              // 1
                    INSTANCE = new singleton06();   // 2
                }
            }
        }
        return INSTANCE; //3
    }
}
