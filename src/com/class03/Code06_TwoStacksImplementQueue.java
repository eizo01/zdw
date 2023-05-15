package com.class03;

import java.util.Stack;

/**
 * 两栈实现队列
 * 思路 准备两个栈，用两个栈来实现元素不会受到栈的规则影响，如果要pop一个元素，则push栈里的元素
 * 要一次性倒入pop栈里。什么时候再入栈呢，当pop栈元素为空时，再把新加的元素入栈
 */
public class Code06_TwoStacksImplementQueue {
    public static class TwoStacksQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }
        private void pushToPop(){
            while(stackPop.isEmpty()){
                while(!stackPush.isEmpty()){
                    stackPop.push(stackPush.pop());
                }
            }
        }
        public void  add(int pushInt){
            stackPush.push(pushInt);
            pushToPop();
        }

        public int poll(){
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.pop();
        }

        public int peek(){
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }

}
