package com.example.algorithm;

import java.util.List;

/**
 * 节点  二叉树
 */
public class Node {
    int num;
    List<Node> list;

    public Node(int num, List<Node> list) {
        this.num = num;
        this.list = list;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<Node> getList() {
        return list;
    }

    public void setList(List<Node> list) {
        this.list = list;
    }
}
