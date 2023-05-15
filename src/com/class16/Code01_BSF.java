package com.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Code01_BSF {
    //队列 + set
    //从node节点开始寻找
    public static void bsf(Node node){
        if (node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> hashSet = new HashSet<>();
        queue.add(node);
        hashSet.add(node);
        while (!queue.isEmpty()){
            //弹出就打印
            Node cur = queue.poll();
            System.out.println(cur.value);
            //遍历每个子节点
            for (Node next : cur.nexts){
                if (!hashSet.contains(next)){
                    queue.add(next);
                    hashSet.add(next);

                }
            }
        }
    }
}
