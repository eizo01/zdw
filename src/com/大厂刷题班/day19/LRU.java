package com.大厂刷题班.day19;


import java.util.HashMap;
import java.util.LinkedHashMap;

//最近最少使用
// 本题测试链接 : https://leetcode.com/problems/lru-cache/
public class LRU {
    //力扣是整型
    private MyCache<Integer,Integer> cache;
    public  LRU(int capacity){
        cache = new MyCache<>(capacity);
    }

    public  int get(int key){
        Integer integer = cache.get(key);
        return  integer == null ? -1 : integer;
    }
    public void put(int key, int value){
        cache.set(key,value);
    }
    public static class Node<K,V>{
        public Node<K,V> next;
        public Node<K,V> last;
        public K key;
        public V value;
        public Node(K key,V value){
            this.key = key;
            this.value = value;
        }
    }
    //构建双向链表
    //三个功能 第一：添加节点  把节点添加到尾巴
    // 第二 更新操作 把当前更新的节点左右两边连好，自己滚去尾巴
    // 第三 删除头节点 返回老头部
    public static class NodeDoubleLinkedList<K,V>{
        private Node<K,V> head;
        private Node<K,V> tail;

        public NodeDoubleLinkedList(){
            head = null;
            tail = null;
        }
        // 两种情况
        //添加尾巴 并且自己成为尾巴
        public void addNode(Node<K,V> newNode){
            if (newNode == null) return;
            if (head == null){
                head = newNode;
                tail = newNode;
            }else{
                tail.next = newNode;
                newNode.last = tail;
                tail = newNode;
            }
        }
        // node 入参，一定保证！node在双向链表里！
        // node原始的位置，左右重新连好，然后把node分离出来
        // 挂到整个链表的尾巴上
        //判断 尾巴是不是要移动节点
        public void moveNodeToTail(Node<K, V> node) {
            if (tail == node) {
                return;
            }
            //我不是头节点
            if (head != node){
                node.last.next = node.next;
                node.next.last = node.last;
            }else {
                //我是头节点
                head = node.next;
                head.last = null;
            }
            tail.next = node;
            node.last = tail;
            node.next = null;
            tail = node;
        }
        //逻辑 抓住头节点 判断链表现在有几个节点 如果一个的话就让首尾指向空
        public  Node<K,V> removeHead(){
            if (head == null) return null;
            Node<K,V> res = head;
            if (head == tail){
                head = null;
                tail = null;
            }else{
                head = head.next;
                res.next = null;
                head.last = null;
            }
            return res;
        }
    }
    //真正构建 LRU 双向链表 + hashmap
    //功能 get set set的时候要更新双向链表 删除
    public static class MyCache<K,V>{
        private HashMap<K,Node<K,V>> keyNodeMap;
        private NodeDoubleLinkedList<K,V> nodeList;
        private final int capacity;

        public MyCache(int capacity){
            this.capacity = capacity;
            keyNodeMap = new HashMap<>();
            nodeList = new NodeDoubleLinkedList<>();
        }
        //查询 就是被使用需要被更新
        public V get(K key){
            if (keyNodeMap.containsKey(key)){
                Node<K, V> node = keyNodeMap.get(key);
                nodeList.moveNodeToTail(node);
                return node.value;
            }
            return null;
        }
        //map 有就是修改，没有 就是 新增
        //map只是作为查询，真正修改要在双向链表的节点
        public void set(K key, V value){
            if (keyNodeMap.containsKey(key)){
                Node<K, V> node = keyNodeMap.get(key);
                node.value = value;
                nodeList.moveNodeToTail(node);
            }else{
                Node<K,V> node  = new Node<>(key,value);
                keyNodeMap.put(key,node);
                nodeList.addNode(node);
                //新增需要判断 是否超过容量
                //初始化时已经 给定capacity
                if (keyNodeMap.size() == capacity + 1){
                    removeMostUnusedCache();
                }
            }
        }
        private void removeMostUnusedCache(){
            Node<K, V> node = nodeList.removeHead();
            keyNodeMap.remove(node.key);
        }
    }
}
