package com.example.datastructure;

import java.util.LinkedList;

/**
 * 数据结构 算法
 */
public class DataStructureUtils {

    /**
     * LinkedList 反转
     * @param list
     * @return
     */
    public static LinkedList reverse(LinkedList list){
        LinkedList newlist = new LinkedList();
        Object[] listArr = list.toArray();
        for(int x=listArr.length-1;x>=0;x--){
            newlist.offer(listArr[x]);
        }
        return newlist;
    }

    /**
     * 算法
     * 单链表 反转  递归
     */
    public static Node reverse1(Node head){
        //如果是空链表或者尾结点
        if(head==null || head.getNext()==null){
            return head;
        }
        //先反转后续结点
        Node reverseHead = reverse1(head.getNext());
        //当前结点指针指向前一结点
        head.getNext().setNext(head);
        //令前一结点的指针域为null
        head.setNext(null);

        return reverseHead;
    }

    /**
     * 算法
     * 单链表 反转  遍历
     * @param head
     */
    public static Node reverse2(Node head){

        if(head == null){
            return head;
        }
        //上一结点
        Node pre = head;
        //当前结点
        Node cur = head.getNext();
        //用于存储下一节点
        Node tem;
        //cur==null 即尾结点
        while(cur != null){
            //下一节点存入临时结点
            tem = cur.getNext();
            //将当前结点指针指向上一节点
            cur.setNext(pre);
            //移动指针
            pre = cur;
            cur = tem;
        }
        head.setNext(null);
        return pre;
    }
}
