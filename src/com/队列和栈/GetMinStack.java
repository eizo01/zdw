package com.队列和栈;

import java.util.Stack;

public class GetMinStack {
    public static class MyStack{
        private Stack<Integer> stackDate;
        private Stack<Integer> stackMin;

        public MyStack(){
            stackDate = new Stack<>();
            stackMin = new Stack<>();
        }
        public void push(int value){
            if (stackMin.isEmpty()){
                stackMin.push(value);
                //最小栈有值
            }else if (value <= getMin()){
                stackMin.push(value);
            }
            stackDate.push(value);
        }
         public int poll(){
            if (stackMin.isEmpty()){
                throw new RuntimeException("栈为空，没有最小元素");
            }
             int pop = stackDate.pop();
            if (pop == getMin()){
                stackMin.pop();
            }
            return pop;
        }

        private int getMin() {
            if (stackMin.isEmpty()){
                throw new RuntimeException("Your stack is empty.");
            }

            return stackMin.peek();
        }
    }
}
