package com.class16;

import java.util.HashSet;
import java.util.Stack;

public class Code02_DFS {

    public static void dfs(Node node){
        if (node==null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            //找到一条边就推出
            for (Node next : cur.nexts){
                if (!set.contains(next)){
                    stack.push(next);
                    stack.push(cur);
                    set.add(next);
                    System.out.println(cur.value);
                    break;
                }
            }
        }


    }
}
