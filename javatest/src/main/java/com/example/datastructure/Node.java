package com.example.datastructure;

/**
 * Created by Administrator on 2016/12/4.
 */
public class Node {
    private int data;  //数据域
    private Node next;    //指针域

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
