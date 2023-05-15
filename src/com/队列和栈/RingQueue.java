package com.队列和栈;

public class RingQueue {

    public static class Myqueue{
        private int[] arr;
        private int begin;//弹出
        private int end;//添加
        private int size;
        private  final int litim;
        public Myqueue(int litim){
            arr = new int[litim];
            begin = 0;
            end = 0;
            size = 0;
            this.litim = litim;
        }
        //队头弹出
        public  int poll(){
            if (size == 0){
                throw new RuntimeException("队列是空！");
            }
            size--;
            int res = arr[begin];
            begin = indexofnext(begin);
            return res;
        }
        public void push(int value){
            if (size == litim){
                throw  new RuntimeException("队列是满！！");
            }
            size ++;
            arr[end] = value;
            end = indexofnext(end);

        }

        public boolean isEmpty() {
            return size == 0;
        }

        private int indexofnext(int i){
          return i < litim - 1 ? i + 1 : 0;
        }
    }
}
