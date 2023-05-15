package com.class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code05_UnionFind {

	public static class Node<V> {
		V value;
		public Node(V v) {
			value = v;
		}
	}

	public static class UnionFind<V> {
		//样本 对应 自己的集合 a -> {a}
		public HashMap<V, Node<V>> nodes;
		//当前节点对应的父亲节点是谁
		public HashMap<Node<V>, Node<V>> parents;
		//代表节点才会有记录，简单来说对于每个集合，（集合代表节点）头节点才会有size记录，而他的子节点不会记录
		public HashMap<Node<V>, Integer> sizeMap;
		//把所有链表样本给我
		public UnionFind(List<V> values) {

			nodes = new HashMap<>();
			parents = new HashMap<>();
			sizeMap = new HashMap<>();
			for (V cur : values) {
				//每一个元素生成自己的集合，初始化的时候
				Node<V> node = new Node<>(cur);
				//值的对应集合
				nodes.put(cur, node);
				//自己指向自己，这个是并查集的优化
				parents.put(node, node);
				//保存自己的节点数
				sizeMap.put(node, 1);
			}
		}

		// 给你一个节点，请你往上到不能再往上，把代表返回
		//优化思路：在一次cur找寻父节点的时候，把cur到父节点的路径上的每个子节点都指向父节点o(1)，用栈来做
		//只需要经历已经o(N)的痛苦
		public Node<V> findFather(Node<V> cur) {
			Stack<Node<V>> path = new Stack<>();
			//没到顶
			while (cur != parents.get(cur)) {
				//路径上的节点压入栈
				path.push(cur);
				cur = parents.get(cur);
			}
			//退出while循环时候，cur已经是父节点
			while (!path.isEmpty()) {
				//把每一个弹出的节点，都指向cur节点
				parents.put(path.pop(), cur);
			}
			return cur;
		}
		//对比是不是一个集合，查找相同父节点
		public boolean isSameSet(V a, V b) {
			return findFather(nodes.get(a)) == findFather(nodes.get(b));
		}

		public void union(V a, V b) {
			//a，b  找到集合代表节点
			Node<V> aHead = findFather(nodes.get(a));
			Node<V> bHead = findFather(nodes.get(b));

			if (aHead != bHead) {
				int aSetSize = sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				//找到最大的集合size
				Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
				//最大是a 小的就是b ，反之
				Node<V> small = big == aHead ? bHead : aHead;
				//小集合的头部 直接指向 大集合的头部
				parents.put(small, big);
				//更新大集合的size
				sizeMap.put(big, aSetSize + bSetSize);
				sizeMap.remove(small);
			}
		}

		public int sets() {
			return sizeMap.size();
		}

	}
}
