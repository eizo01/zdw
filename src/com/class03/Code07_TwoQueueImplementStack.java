package com.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用两个队列来实现栈
 * 思路 ： 一个队列queue 是栈的主体所有功能都是它来实现 ，help是辅助队列 ，用数据来时，所有数据加入queue队列，然后poll操作时
 * 把queue的元素都加入help 队列，留下一个元素这个元素就是栈需要弹出的元素，后进先出，peek操作时需要注意跟poll操作一样，但需要把
 * peek的那个元素重新加入help队列
 * ps ： 为了代码好看 ，两个队列互相倒完数据后，交换引用地址。
 */
public class Code07_TwoQueueImplementStack {
    public static class TwoQueueStack<T> {
        public Queue<T> queue;
        public Queue<T> help;

        public TwoQueueStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }
        public void push(T value) {

            queue.offer(value);
        }
        //弹出
        public T poll(){
            while (queue.size() > 1){
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            Queue<T> tmp = queue;
            queue =help;
            help = tmp;
            return ans;

        }
        public T peek(){
            while (queue.size() > 1){
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            help.offer(ans);
            Queue<T> tmp = queue;
            queue =help;
            help = tmp;
            return ans;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }
}
