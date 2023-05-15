package com.class03;
//用数组实现栈 ，用size控制
public class Code04_RingArray {

	public static class MyQueue {
		private int[] arr;
		private int push;// end
		private int poll;// begin
		private int size;
		private final int limit;

		public MyQueue(int limit) {
			arr = new int[limit];
			push = 0;
			poll = 0;
			size = 0;
			this.limit = limit;
		}

		public void push(int value) {
			if (size == limit) {
				throw new RuntimeException("队列满了，不能再加了");
			}
			size++;
			arr[push] = value;
			push = nextIndex(push);
		}

		public int pop() {
			if (size == 0) {
				throw new RuntimeException("队列空了，不能再拿了");
			}
			size--;
			int ans = arr[poll];
			poll = nextIndex(poll);
			return ans;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		// 如果现在的下标是i，返回下一个位置
		private int nextIndex(int i) {

			return i < limit - 1 ? i + 1 : 0;
		}

	}

}
