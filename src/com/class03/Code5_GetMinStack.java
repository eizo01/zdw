package com.class03;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 *
 * 1）pop、push、getMin操作的时间复杂度都是 O(1)。
 *
 * 2）设计的栈类型可以使用现成的栈结构。
 * 思路：准备两个栈，一个是数据栈 一个是最小栈， 第一次压入的数据，两个栈都要。从第二次开始，
 * 每次都与最小栈的栈顶元素比较，如果要压入的元素没有比栈顶元素小，则重复压入栈顶元素，弹出时两个
 * 栈同步弹出
 */
public class Code5_GetMinStack {
    public static class MyStack1{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;
        public MyStack1(){
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }
        public void push(int newNum){
            if (stackMin.isEmpty()){
                stackMin.push(newNum);
            }else if (newNum <= this.getmin()){
                stackMin.push(newNum);
            }
            this.stackData.push(newNum);
        }
        public int pop(){
            if (stackData.isEmpty()){
                throw new RuntimeException("栈空");
            }
            int value = stackData.pop();
            if (value == getmin()){
                this.stackMin.pop();
            }
            return value;
        }
        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }


    public static class MyStack2 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }
        public void push(int newNum){
            if (this.stackMin.isEmpty()){
                this.stackMin.push(newNum);
            }else if (newNum < this.getmin()){
                this.stackMin.push(newNum);
            }else{
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            this.stackData.push(newNum);
        }
        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }
        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }
    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push(3);
        System.out.println(stack1.getmin());
        stack1.push(4);
        System.out.println(stack1.getmin());
        stack1.push(1);
        System.out.println(stack1.getmin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getmin());

        System.out.println("=============");

        MyStack1 stack2 = new MyStack1();
        stack2.push(3);
        System.out.println(stack2.getmin());
        stack2.push(4);
        System.out.println(stack2.getmin());
        stack2.push(1);
        System.out.println(stack2.getmin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getmin());
    }

}
