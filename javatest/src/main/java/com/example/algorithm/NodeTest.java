package com.example.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
public class NodeTest {
    public static void main(String[] args){
        Node node8 = new Node(8, null);
        Node node9 = new Node(9, null);
        Node node6 = new Node(6, null);
        Node node7 = new Node(7, null);
        Node node4 = new Node(4, null);

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

        search(node1);
    }

    /**
     * 打印 节点信息num
     * @param node
     */
    public static void search(Node node){
        System.out.println(node.getNum());
        if(node.getList() != null){
            for(int x=0;x<node.getList().size();x++){
                search(node.getList().get(x));
            }
        }
    }
}
