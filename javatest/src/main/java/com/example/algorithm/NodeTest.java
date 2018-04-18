package com.example.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Administrator on 2016/12/1.
 */
public class NodeTest {
    public static void main(String[] args){
        Node node10 = new Node(10, null);
        Node node11 = new Node(11, null);
        Node node8 = new Node(8, null);
        Node node6 = new Node(6, null);
        Node node7 = new Node(7, null);
        Node node4 = new Node(4, null);

        List<Node> list9 = new ArrayList<>();
        list9.add(node10);
        list9.add(node11);
        Node node9 = new Node(9, list9);

        List<Node> list5 = new ArrayList<>();
        list5.add(node8);
        list5.add(node9);
        Node node5 = new Node(5,list5);

        List<Node> list2 = new ArrayList<>();
        list2.add(node5);
        list2.add(node6);
        Node node2 = new Node(2,list2);

        List<Node> list3 = new ArrayList<>();
        list3.add(node7);
        Node node3 = new Node(3,list3);

        List<Node> list1 = new ArrayList<>();
        list1.add(node2);
        list1.add(node3);
        list1.add(node4);
        Node node1 = new Node(1,list1);

//        search1(node1);
        search2(node1);
    }

    /**
     * 打印 节点信息num
     * 深度优先
     * 深度优先遍历图的方法是，从图中某顶点v出发：
     *（1）访问顶点v；
     *（2）依次从v的未被访问的邻接点出发，对图进行深度优先遍历；直至图中和v有路径相通的顶点都被访问；
     *（3）若此时图中尚有顶点未被访问，则从一个未被访问的顶点出发，重新进行深度优先遍历，直到图中所有顶点均被访问过为止。
     * @param node
     */
    public static void search1(Node node){
        System.out.println(node.getNum());
        if(node.getList() != null){
            for(int x=0;x<node.getList().size();x++){
                search1(node.getList().get(x));
            }
        }
    }

    /**
     * 广度优先
     * @param node
     */
    public static void search2(Node node){
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(node);

        while (!queue.isEmpty()){
            Node node1 =  queue.poll();
            System.out.println(node1.getNum());
            if(node1.getList() != null){
                for(int x=0;x<node1.getList().size();x++){
                    queue.offer(node1.getList().get(x));
                }
            }
        }
    }
}
